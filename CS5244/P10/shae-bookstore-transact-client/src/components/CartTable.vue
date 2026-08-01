<script setup lang='ts'>
import {useCartStore} from '@/stores/cart'
import {asDollarsAndCents} from '@/utils'
import type {BookItem} from '@/types'

const clearCart = function () {cartStore.clearCart()}
const cartStore = useCartStore();
const bookImagePrefix = `${import.meta.env.BASE_URL}/book-images/`

const updateCart = function (book: BookItem, quantity: number) {
  cartStore.updateBookQuantity(book, quantity)
}
const bookImageFileName = function (book: BookItem): string {
  let name = book.title.toLowerCase()
  name = name.replace(/ /g, '-')
  name = name.replace(/'/g, '')
  name = name.replace(/:/g, '')
  name = name.replace('?', '')
  name = name.replace('&', 'and')
  return `${name}.png`
}
</script>

<style scoped>
.cart-table {
  display: grid;
  grid-template-columns: max-content minmax(10em, 20em) repeat(3, max-content);
  row-gap: 1em;
  width: fit-content;
  margin: 0 auto;
  background-color: var(--secondary-background-color);
}

ul {
  display: contents;
}

ul > li {
  display: contents;
}

.table-heading {
  background-color: var(--primary-color-light);
  color: white;
}

.table-heading > * {
  background-color: var(--primary-color-light);
  padding: 0.5em 1em;
}

.heading-book {
  grid-column: 1 / 3;
}

.heading-price {
  grid-column: 3 / 4;
  padding-left: 1em;
  padding-right: 1em;
  text-align: center;
}

.heading-quantity {
  grid-column: 4 / 5;
  text-align: center;
}

.heading-subtotal {
  grid-column: -2 / -1;
  text-align: right;
}

.cart-book-image {
  padding: 0 1em;
}

.cart-book-image > * {
  margin-left: auto;
  margin-right: 0;
}

img {
  display: block;
  width: 100px;
  height: auto;
}

.cart-book-price {
  padding-left: 1em;
  padding-right: 1em;
  text-align: right;
}

.cart-book-quantity {
  padding-left: 1em;
  padding-right: 1em;
}

.cart-book-subtotal {
  text-align: right;
  padding-left: 1em;
  padding-right: 1em;
}

/* Row separators in the table */

.line-sep {
  display: block;
  height: 1px;
  background-color: var(--primary-color-light);
  grid-column: 1 / -1;
}

/* Increment/decrement buttons */

.icon-button {
  border: none;
  cursor: pointer;
  background: none;
}

.inc-button {
  font-size: 1.25rem;
  color: var(--primary-color);
  margin-right: 0.25em;
}

.inc-button:hover {
  color: var(--primary-color-light);
}

.dec-button {
  font-size: 1.25rem;
  color: var(--primary-color);

}

.dec-button:hover {
  color: var(--primary-color-light);

}

input[type='number'] {
  width: 4em;
}

select {
  background-color: var(--primary-color);
  color: white;
  border: 2px solid var(--primary-color-light);
  border-radius: 3px;
}

#cart-subtotal {
  grid-column: -1 / -3;
  align-self: end;
  text-align: right;
  font-size: 18px;
  font-weight: bolder;
  padding: 0 1em 1.5em 0;
}

.button-clear {
  position: unset;
  grid-column: -4 / -6;
  background: var(--secondary-text-color);
  transform: translate(1em);
  border: none;
  color: #7d0f74;
  font-size: 16px;
  cursor: pointer;
  width: 9em;
  height: 3em;
  text-align: center;
  padding: 0.75em 0 0 0;
  margin-bottom: 1em;
}
</style>

<template>
  <div class='cart-table' v-if='!cartStore.cart.empty'>
    <ul>

      <li class='table-heading'>
        <div class='heading-book'>Book</div>
        <div class='heading-price'>Price</div>
        <div class='heading-quantity'>Quantity</div>
        <div class='heading-subtotal'>Item Subtotal</div>
      </li>

      <template v-for='item in cartStore.cart.items' :key='item.book.bookId'>
        <li>
          <div class='cart-book-image'>
            <img
                :src='`${bookImagePrefix}${bookImageFileName(item.book)}`'
                :alt='item.book.title'
                width='100px'
                height='auto'
            />
          </div>
          <div class='cart-book-title'>{{item.book.title}}</div>
          <div class='cart-book-price'>{{asDollarsAndCents(item.book.price)}}</div>
          <div class='cart-book-quantity'>
            <button class='icon-button inc-button' @click='updateCart(item.book, item.quantity + 1)'>
              <i class='fas fa-plus-square'></i>
            </button>&nbsp;
            <span class='quantity'>{{item.quantity}}</span>&nbsp;
            <button class='icon-button dec-button' @click='updateCart(item.book, item.quantity - 1)'>
              <i class='fas fa-minus-square'></i>
            </button>
          </div>

          <div class='cart-book-subtotal'>{{asDollarsAndCents(item.book.price * item.quantity)}}</div>
        </li>
        <li class='line-sep'></li>
      </template>

    </ul>

    <div class='button-clear' v-if='!cartStore.cart.empty' @click.stop.prevent='clearCart'>CLEAR CART</div>

    <section id='cart-subtotal' v-if='!cartStore.cart.empty' >
      <ul>
        <li>Cart subtotal: {{asDollarsAndCents(cartStore.cart.subtotal)}}</li>
      </ul>
    </section>
  </div>

</template>

