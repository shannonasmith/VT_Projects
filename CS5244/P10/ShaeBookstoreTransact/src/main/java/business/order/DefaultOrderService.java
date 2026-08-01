package business.order;

import api.ApiException;
import business.BookstoreDbException;
import business.JdbcUtils;
import business.book.Book;
import business.book.BookDao;
import business.cart.ShoppingCart;
import business.cart.ShoppingCartItem;
import business.customer.Customer;
import business.customer.CustomerDao;
import business.customer.CustomerForm;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.*;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DefaultOrderService implements OrderService {
    private BookDao bookDao;
    private OrderDao orderDao;
    private LineItemDao lineItemDao;
    private CustomerDao customerDao;

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void setLineItemDao(LineItemDao lineItemDao) {
        this.lineItemDao = lineItemDao;
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public long placeOrder(CustomerForm customerForm, ShoppingCart cart) {
        validateCustomer(customerForm);
        validateCart(cart);
        try (Connection connection = JdbcUtils.getConnection()) {
            Date ccExpDate = getCardExpirationDate(
                    customerForm.getCcExpiryMonth(),
                    customerForm.getCcExpiryYear());
            return performPlaceOrderTransaction(
                    customerForm.getName(),
                    customerForm.getAddress(),
                    customerForm.getPhone(),
                    customerForm.getEmail(),
                    customerForm.getCcNumber(),
                    ccExpDate, cart, connection);
        } catch (SQLException e) {
            throw new BookstoreDbException("Error during close connection for customer order", e);
        }
    }

    @Override
    public OrderDetails getOrderDetails(long orderId) {
        Order order = orderDao.findByOrderId(orderId);
        Customer customer = customerDao.findByCustomerId(order.customerId());
        List<LineItem> lineItems = lineItemDao.findByOrderId(orderId);
        List<Book> books = lineItems
                .stream()
                .map(lineItem -> bookDao.findByBookId(lineItem.bookId()))
                .toList();
        return new OrderDetails(order, customer, lineItems, books);
    }

    private long performPlaceOrderTransaction(
            String name, String address, String phone,
            String email, String ccNumber, Date date,
            ShoppingCart cart, Connection connection) {
        try {
            connection.setAutoCommit(false);
            long customerId = customerDao.create(
                    connection, name, address, phone, email,
                    ccNumber, date);
            long customerOrderId = orderDao.create(
                    connection,
                    cart.getComputedSubtotal() + cart.getSurcharge(),
                    generateConfirmationNumber(), customerId);
            for (ShoppingCartItem item : cart.getItems()) {
                lineItemDao.create(connection, customerOrderId,
                        item.getBookId(), item.getQuantity());
            }
            connection.commit();
            return customerOrderId;
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new BookstoreDbException("Failed to roll back transaction", e1);
            }
            return 0;
        }
    }

    private int generateConfirmationNumber() {
        return ThreadLocalRandom.current().nextInt(999999999);
    }

    private Date getCardExpirationDate(String monthString, String yearString) throws ApiException.ValidationFailure {
        try {
            int month = Integer.parseInt(monthString);
            int year = Integer.parseInt(yearString);

            YearMonth expirationYearMonth = YearMonth.of(year, month);
            LocalDate lastDayOfMonth = expirationYearMonth.atEndOfMonth();
            LocalDateTime expirationDateTime = lastDayOfMonth.atTime( 23, 59, 59);
            return Date.from(expirationDateTime.atZone(ZoneId.systemDefault()).toInstant());

        } catch (NumberFormatException | DateTimeException e) {
            throw new ApiException.ValidationFailure("Invalid month or year.");
        }
    }


    private void validateCustomer(CustomerForm customerForm) {
        String name = customerForm.getName();
        if (name == null || name.length() < 4 || name.length() > 45) {
            throw new ApiException.ValidationFailure("name", "Invalid name field");
        }

        String address = customerForm.getAddress();
        if (address == null || address.length() < 4 || address.length() > 45) {
            throw new ApiException.ValidationFailure("address", "Invalid address field");
        }

        String phone = customerForm.getPhone();
        if (phone == null || phone.equals("")) {
            throw new ApiException.ValidationFailure("phone", "Invalid phone field");
        }

        String adjPhone = phone.replaceAll("\\D", "");
        if (adjPhone.length() != 10) {
            throw new ApiException.ValidationFailure("phone", "Invalid phone field");
        }

        String email = customerForm.getEmail();
        if (email == null || email.contains(" ") || email.endsWith(".") || !email.contains("@")) {
            throw new ApiException.ValidationFailure("email", "Invalid email field");
        }

        String ccNumber = customerForm.getCcNumber();
        if (ccNumber == null || ccNumber.equals("")) {
            throw new ApiException.ValidationFailure("ccNumber", "Invalid ccNumber field");
        }

        String adjCcNumber = ccNumber.replaceAll("\\D", "");
        if (adjCcNumber.length() < 14 || adjCcNumber.length() > 16) {
            throw new ApiException.ValidationFailure("ccNumber", "Invalid ccNumber field");
        }

        if (expiryDateIsInvalid(customerForm.getCcExpiryMonth(), customerForm.getCcExpiryYear())) {
            throw new ApiException.ValidationFailure("Please enter a valid expiration date");
        }

    }

    private boolean expiryDateIsInvalid(String ccExpiryMonth, String ccExpiryYear) {
        try {
            int month;
            int year;
            try {
                month = Integer.parseInt(ccExpiryMonth);
                year = Integer.parseInt(ccExpiryYear);
            } catch (NumberFormatException e) {
                return true;
            }
            YearMonth currentYearMonth = YearMonth.now();
            YearMonth expYearMonth = YearMonth.of(year, month);
            return expYearMonth.isBefore(currentYearMonth);
        } catch (Exception e) {
            return true;
        }
    }

    private void validateCart(ShoppingCart cart) {
        if (cart.getItems().size() < 1) {
            throw new ApiException.ValidationFailure("Cart is empty.");
        }
        cart.getItems().forEach(item -> {
            if (item.getQuantity() < 1 || item.getQuantity() > 99) {
                throw new ApiException.ValidationFailure("Invalid quantity");
            }
            Book databaseBook = bookDao.findByBookId(item.getBookId());

            if (item.getBookForm().getPrice() != databaseBook.price()) {
                throw new ApiException.ValidationFailure("Invalid price");
            }

            if (item.getBookForm().getCategoryId() != databaseBook.categoryId()) {
                throw new ApiException.ValidationFailure("Invalid category");
            }
        });
    }

}
