<script setup lang="ts">
import { useCategoryStore } from '@/stores/category'
const categoryStore = useCategoryStore()
</script>

<style scoped>
nav {
  text-align: center;
}

.category-buttons {
  display: inline-flex;
  flex-direction: row;
  background-color: var(--primary-color);
  text-align: center;
}

.button.category-button.router-link-active {
  background-image: linear-gradient(0deg, rgba(195, 226, 245, 0.6) 0%, rgba(4, 75, 122, 0.6) 100%);
}

.button.category-button {
  background-color: var(--card-background-color);
  min-width: 175px;
  max-width: 1400px;
}

.button.category-button:hover,
.button.category-button:active {
  background-color: #044a79;
}
</style>

<template>
  <nav class="category-nav">
    <ul class="category-buttons">
      <li v-for="category in categoryStore.categoryList" :key="category.categoryId">
        <router-link :to="'/category/' + category.name" class="button category-button">
          {{ category.name }}
        </router-link>
      </li>
    </ul>
  </nav>
</template>
