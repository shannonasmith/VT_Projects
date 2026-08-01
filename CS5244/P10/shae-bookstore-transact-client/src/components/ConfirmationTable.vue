<script setup lang='ts'>
import {computed} from 'vue'
import {useOrderDetailsStore} from '@/stores/orderDetails'
import {useCartStore} from '@/stores/cart'
import {asDollarsAndCents} from '@/utils'
import type {OrderDetails, BookItem} from '@/types'

const orderDetailsStore = useOrderDetailsStore()
const orderDetails = orderDetailsStore.orderDetails as OrderDetails
const cartStore = useCartStore()
const bookImagePrefix = `${import.meta.env.BASE_URL}/book-images/`

const bookImageFileName = function (book: BookItem): string {
  let name = book.title.toLowerCase()
  name = name.replace(/ /g, '-')
  name = name.replace(/'/g, '')
  name = name.replace(/:/g, '')
  name = name.replace('?', '')
  name = name.replace('&', 'and')
  return `${name}.png`
}

const cart = computed(() => {
  return cartStore.cart
})

const orderDate = computed(function () {
  let date = new Date(orderDetails.order.dateCreated)
  return date.toDateString() + " at " + date.toLocaleTimeString()
})

const ccExpDate = computed(function (): Date {
  return new Date(orderDetails.customer.ccExpDate)
})

const ccExpMonth = computed(function (): string {
  let month = ccExpDate.value.getMonth() + 1
  let result = "" + month
  if (month < 10) {
    result = "0" + month
  }
  return result
})

const ccExpYear = computed(function (): number {
  return ccExpDate.value.getFullYear()
})

const phoneNum = computed(function (): string {
  let phone = orderDetails.customer.phone
  return ("(" + phone.substring(0, 3) + ") " + phone.substring(3, 6) + "-" + phone.substring(6))
})
</script>

<style scoped>
.order-details-table {
  display: grid;
  grid-template-columns: auto minmax(10em, 20em) repeat(2, max-content);
  row-gap: 0.5em;
  width: fit-content;
  margin: 0 auto;
  background-color: var(--secondary-background-color);
  font-size: 18px;
}

ul {
  display: contents;
}

ul > li {
  display: contents;
}

.order-details {
  display: grid;
  grid-column: 1 / 5;
  flex-direction: column;
}

.order-details-heading {
  background-color: var(--primary-color-light);
  text-align: center;
  font-size: 20px;
  font-weight: normal;
  padding: 0.5em;
  color: white;
}

.order-conf {
  text-align: center;
  font-weight: bold;
  color: var(--primary-color-light);
  padding-top: 0.75em;
  padding-bottom: 0.75em;
}

.customer-details {
  text-align: center;
  font-weight: normal;
  padding-top: 0.75em;
  padding-bottom: 0.5em;
}

.table-heading > * {
  background-color: var(--primary-color-light);
  padding: 0.25em 1em 0.25em 1em;
  color: white;
}

.heading-item {
  grid-column: 1 / 3;
  text-align: left;
  background-color: var(--secondary-background-color);
  color: #7d0f74;
  font-weight: bold;
}

.heading-quantity {
  grid-column: 3 / 4;
  text-align: center;
  background-color: var(--secondary-background-color);
  color: #7d0f74;
  font-weight: bold;
  margin-left: 0.75em;
}

.heading-price {
  grid-column: -2 / -1;
  text-align: right;
  background-color: var(--secondary-background-color);
  color: #7d0f74;
  font-weight: bold;
}

.order-book-image {
  padding: 0 1em;
}

.order-book-image > * {
  margin-left: 1.25em;
  margin-right: 0;
}

img {
  display: block;
  width: 15%;
  height: auto;

}

.order-book-title {
  text-align: left;
  margin-top: 0.5em;
  font-weight: normal;
}

.order-book-quantity {
  padding-left: 1em;
  padding-right: 1em;
  text-align: center;
  margin-top: 0.5em;
  font-weight: normal;
}

.order-book-price {
  padding-left: 1em;
  padding-right: 1em;
  text-align: right;
  margin-top: 0.5em;
  font-weight: normal;
}

#order-totals {
  grid-column: -4 / -1;
  align-self: end;
  text-align: right;
  padding: 0 1em 1em 0;
  margin-top: 1em;
  margin-bottom: 1em;
  font-weight: normal;
}

/* Row separators in the table */

.line-sep {
  height: 1px;
  background-color: var(--primary-color-light);
  grid-column: 1 / -1;
}
</style>

<template>
  <div class='order-details-table'>
    <ul>

      <li class='order-details'>
        <div class="order-details-heading">ORDER DETAILS</div>
        <div class='order-conf'>
          YOUR CONFIRMATION NUMBER IS: {{ orderDetails.order.confirmationNumber }}<br>
          Order placed successfully:
          {{ orderDate }}
        </div>
        <div class='line-sep'></div>
        <div class='customer-details'>
          {{ orderDetails.customer.customerName }}<br>
          {{ orderDetails.customer.address }}<br>
          {{ orderDetails.customer.email }}<br>
          {{ phoneNum }}<br>
          Credit Card: **** **** **** {{ orderDetails.customer.ccNumber }}<br>
          Exp Date: {{ (ccExpMonth + "/" + ccExpYear) }}
        </div>
      </li>
    </ul>
    <div class='line-sep'></div>

    <ul>
      <li class='table-heading'>
        <div class='heading-item'>Item</div>
        <div class='heading-quantity'>Quantity</div>
        <div class='heading-price'>Price</div>
      </li>
      <div class='line-sep'></div>
      <template v-for="(item, index) in orderDetails.lineItems" :key="item.bookId">
        <ul>
          <li class='order-book-image'>
            <img
                :src='`${bookImagePrefix}${bookImageFileName(orderDetails.books[index])}`'
                :alt='orderDetails.books[index].title'
                width='20%'
                height='auto'
            />
            <div class='order-book-title'>{{ orderDetails.books[index].title }}</div>
            <div class='order-book-quantity'>{{ item.quantity }}</div>
            <div class='order-book-price'>{{ asDollarsAndCents(orderDetails.books[index].price) }}</div>
          </li>
        </ul>
      </template>
    </ul>

    <div class='line-sep'></div>

    <section id='order-totals'>
      <ul>
        <li>Subtotal:&nbsp;&nbsp;{{ asDollarsAndCents((orderDetails.order.amount) - (cart.surcharge)) }}</li>
        <br>
        <li>Surcharge:&nbsp;&nbsp;&nbsp; {{ asDollarsAndCents(cart.surcharge) }}</li>
        <br>
        <li><strong>Order total:&nbsp;&nbsp;{{ asDollarsAndCents(orderDetails.order.amount) }}</strong></li>
      </ul>
    </section>

  </div>
</template>
