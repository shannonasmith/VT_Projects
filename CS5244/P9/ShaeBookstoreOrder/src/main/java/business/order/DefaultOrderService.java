package business.order;

import api.ApiException;
import business.book.Book;
import business.book.BookDao;
import business.cart.ShoppingCart;
import business.customer.CustomerForm;

import java.time.YearMonth;

public class DefaultOrderService implements OrderService {

    private BookDao bookDao;

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public OrderDetails getOrderDetails(long orderId) {
        // NOTE: THIS METHOD PROVIDED NEXT PROJECT
        return null;
    }

    @Override
    public long placeOrder(CustomerForm customerForm, ShoppingCart cart) {

        validateCustomer(customerForm);
        validateCart(cart);

        // NOTE: MORE CODE PROVIDED NEXT PROJECT

        return -1;
    }


    private void validateCustomer(CustomerForm customerForm) {

        String name = customerForm.getName();
        // All fields (including name and address): should be present and non-null and non-empty
        // Name and address must have at least 4 and at most 45 characters in length.
        if (name == null || name.length() < 4 || name.length() > 45) {
            throw new ApiException.ValidationFailure("name", "Invalid name field");
        }

        String address = customerForm.getAddress();
        if (address == null || address.length() < 4 || address.length() > 45) { // || address.equals("")
            throw new ApiException.ValidationFailure("address", "Invalid address field");
        }

        String phone = customerForm.getPhone();
        if (phone == null || phone.equals("")) {
            throw new ApiException.ValidationFailure("phone", "Invalid phone field");
        }

        String adjPhone = phone.replaceAll("\\D", ""); // "\\D" replaces all non-digits
        // Phone: after removing all spaces, dashes, and parens from the string it should have exactly 10 digits
        if (adjPhone.length() != 10) {
            throw new ApiException.ValidationFailure("phone", "Invalid phone field");
        }

        String email = customerForm.getEmail();
        // Email: should not contain spaces; should contain a "@"; and the last character should not be "."
        if (email == null || email.contains(" ") || email.endsWith(".") || !email.contains("@")) {
            throw new ApiException.ValidationFailure("email", "Invalid email field");
        }

        String ccNumber = customerForm.getCcNumber();
        // Credit card number: after removing spaces and dashes, the number of characters should be between 14 and 16
        if (ccNumber == null || ccNumber.equals("")) { // null is not working!!!!
            throw new ApiException.ValidationFailure("ccNumber", "Invalid ccNumber field");
        }

        String adjCcNumber = ccNumber.replaceAll("\\D", "");
        if (adjCcNumber.length() < 14 || adjCcNumber.length() > 16) {
            throw new ApiException.ValidationFailure("ccNumber", "Invalid ccNumber field");
        }

        // Expiration date: the month and year should be the current month and year or later
        // For expiration date issues, do NOT specify a field but use an error message "Please enter a valid expiration date."
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

    /*
    For cart validation issues, use an error message of your choice relevant to the validation failure.
    The validations should each have a distinct short clear message that displays upon invalid input.
    You should expect to see and MUST use 'Transactions have not been implemented yet' with a 400 code with valid input.
     */
    private void validateCart(ShoppingCart cart) {
        // The cart should contain at least one item
        if (cart.getItems().size() < 1) {
            throw new ApiException.ValidationFailure("Cart is empty.");
        }

        cart.getItems().forEach(item -> {
            // Each cart item has quantity of books between 1 and 99
            if (item.getQuantity() < 1 || item.getQuantity() > 99) {
                throw new ApiException.ValidationFailure("Invalid quantity");
            }
            Book databaseBook = bookDao.findByBookId(item.getBookId());

            // Each cart item's price should match the price for the item from the database
            if (item.getBookForm().getPrice() != databaseBook.price()) {
                throw new ApiException.ValidationFailure("Invalid price");
            }

            // Each cart item's category should match the category for the item from the database.
            if (item.getBookForm().getCategoryId() != databaseBook.categoryId()) {
                throw new ApiException.ValidationFailure("Invalid category");
            }
        });
    }

}
