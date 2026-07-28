<script setup lang="ts">
import type { BookItem } from "@/types";
const props = defineProps<{
  book: BookItem;
}>();
const bookImageFileName = function (book: BookItem): string {
  let name = book.title.toLowerCase();
  name = name.replace(/ /g, "-");
  name = name.replace(/'/g, "");
  return `${name}.png`;
};
const bookImagePrefix = `${import.meta.env.BASE_URL}book-images/`;
</script>

<style scoped>
.book-box {
  display: flex;
  flex-direction: column;
  background-color: var(--card-background-color);
  padding: 1em;
  gap: 0.25em;
  position: relative;
  z-index: -1;
}

.book-title {
  font-family: "Marcellus SC", serif;
  width: 165px; /* wraps the book title */
  font-weight: bold;
  bottom: 0;
}

.book-author {
  font-style: italic;
  font-size: 14px;
  margin-bottom: auto;
}

.book-price {
  font-weight: bold;
}

.add-to-cart-button {
  padding: 0.4em 3.35em 0.4em 3.35em;
  align-self: baseline;
  background: #063D0F;
  color: white;
  border: none;
  cursor: pointer;
  font-size: 14px;
}

.add-to-cart-button:hover,
.add-to-cart-button:active {
  background-color: var(--nav-bar-hover-color);
}

.read-now-button {
  position: absolute;
  padding: 0.25em 3.35em 0.25em 3.35em;
  background: rgb(214, 223, 229);
  color: #063D0F;
  font-weight: bolder;
  border: none;
  cursor: pointer;
  top: 3em;
  left: 1em;
}

.read-now-button:hover,
.read-now-button:active {
  background-color: var(--nav-bar-hover-color);
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

    <div v-if="book.isPublic" class="read-now-button">Read Now</div>
    <div class="book-title">{{ book.title }}</div>
    <div class="book-author">{{ book.author }}</div>
    <div class="book-price">${{ (book.price / 100).toFixed(2) }}</div>
    <div class="add-to-cart-button">Add to Cart</div>
  </li>
</template>
