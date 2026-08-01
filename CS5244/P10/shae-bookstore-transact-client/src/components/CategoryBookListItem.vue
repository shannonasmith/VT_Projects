<script setup lang='ts'>
import type {BookItem} from '@/types'
import {useCartStore} from '@/stores/cart'

const cartStore = useCartStore()
const props = defineProps<{ book: BookItem; }>()
const bookImageFileName = function (book: BookItem): string {
  let name = book.title.toLowerCase()
  name = name.replace(/ /g, "-")
  name = name.replace(/'/g, "")
  name = name.replace(/:/g, "")
  name = name.replace("?", "")
  name = name.replace("&", "and")
  return `${name}.png`
}
const bookImagePrefix = `${import.meta.env.BASE_URL}/book-images/`
</script>

<style scoped>
.book-box {
  display: flex;
  flex-direction: column;
  background-image: linear-gradient(0deg, rgba(205, 230, 246, 0.8) 30%, rgb(4, 75, 122) 100%);
  padding: 1em;
  gap: 0.25em;
  position: relative;
  margin-left: 1.4em;
}

.book-title {
  font-family: "Century Gothic", serif;
  font-size: 17px;
  width: 170px; /* wraps the book title */
  font-weight: bolder;
  bottom: 2px;
}

.book-author {
  font-weight: bold;
  font-family: "Martel Sans", serif;
  font-style: italic;
  font-size: 12px;
  text-transform: uppercase;
  margin-bottom: auto;
}

.book-price {
  align-self: flex-end;
  font-family: "Martel Sans", serif;
  font-weight: bolder;
  color: rgba(7, 7, 7, 0.7);
  font-size: 16px;
  margin-top: 4px;
}

.add-to-cart,
.add-to-cart:visited {
  font-family: "Martel Sans", serif;
  padding: 0.4em 2.8em 0.1em 2.8em;
  background: #7d0f74;
  color: white;
  border: none;
  font-size: 14px;
  cursor: pointer;
  transition: background-color 1s, all 1s ease;
  transform: scale(1);
}

.add-to-cart:hover,
.add-to-cart:active {
  background-color: var(--nav-bar-hover-color) !important;
}

.add-to-cart.router-link-active {
  background-image: linear-gradient(0deg, rgba(205, 230, 246, 0.8) 30%, rgb(4, 75, 122) 100%);
}

.read-now,
.read-now:visited {
  background-color: #f0ecfc;
  background-image: linear-gradient(0deg, rgb(255, 213, 0) 0%, rgb(251, 97, 2) 100%);
  line-height: 18px;
  padding: 1.3em 0.7em;
  border: none;
  position: absolute;
  cursor: pointer;
  text-align: center;
  color: white;
  width: 65px;
  height: 68px;
  margin-top: .5em;
  margin-right: .5em;
  border-radius: 50%;
  font-size: .8rem;
  right: -1.3em;
  top: -1.3em;
  transition: background-color 1s, all 1s ease;
  transform: scale(1);
}

.read-now:hover,
.read-now:active {
  background-image: linear-gradient(0deg, rgba(251, 2, 31, 1) 0%, rgb(132, 0, 255) 100%);
  transform: scale(1.15) perspective(1px);
}
</style>

<template>
  <li class="book-box">
    <div class="book-image">
      <img
          :src="`${bookImagePrefix}${bookImageFileName(book)}`"
          :alt="book.title"
      />
    </div>
    <div class="book-title">{{ book.title }}</div>
    <div class="book-author">{{ book.author }}</div>
    <div class="book-price">${{ (book.price / 100).toFixed(2) }}</div>
    <div class="button add-to-cart" @click="cartStore.addToCart(book)">ADD TO CART</div>
    <div v-if="book.isPublic" class="button read-now">READ NOW</div>
  </li>
</template>