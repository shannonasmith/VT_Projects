<script setup lang="ts">
import type { BookItem } from "@/types";
import CategoryBookListItem from "@/components/CategoryBookListItem.vue";
const props = defineProps<{
  bookList: BookItem[];
}>();
</script>

<style scoped>
ul {
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
  gap: 1em;
  padding: 1em 0 1em 7em;
  justify-content: left;
  font-size: 15px;
}
</style>

<template>
  <ul>
    <template v-for="book in props.bookList" :key="book.bookId">
      <category-book-list-item :book="book"></category-book-list-item>
    </template>
  </ul>
</template>
