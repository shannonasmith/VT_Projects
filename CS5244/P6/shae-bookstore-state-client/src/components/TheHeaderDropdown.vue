<script setup lang="ts">
import { useCategoryStore } from '@/stores/category'
const categoryStore = useCategoryStore()
</script>

<style scoped>
.header-dropdown {
  position: relative;
}

.categories-button {
  font-family: 'Century Gothic', serif;
  margin-top: 0.5em;
  background-color: var(--primary-color);
  padding: 0.5em 0.5em;
  width: 160px;
  border: none;
  font-size: 16px;
}

.header-dropdown ul {
  display: none;
  background-color: white;
  z-index: 1;
  /*color: var(--primary-color);*/
  font-weight: bolder;
}

.header-dropdown li {
  padding: 0.25em 0.75em;
  font-family: 'Century Gothic', serif;
  font-size: 16px;
  color: var(--primary-color);
  text-decoration: none;
  width: 100%;
}

.header-dropdown li:hover {
  background-image: linear-gradient(0deg, rgba(195, 226, 245, 0.6) 0%, rgba(4, 75, 122, 0.6) 100%);
}

.header-dropdown:hover ul {
  position: absolute;
  display: block;
  min-width: 8em;
  cursor: pointer;
}
</style>

<template>
  <div class="header-dropdown">
    <button class="categories-button">CATEGORIES <i class="fa-solid fa-caret-down"></i></button>
    <ul>
      <li v-for="category in categoryStore.categoryList" :key="category.categoryId">
        <router-link :to="'/category/' + category.name">
          {{ category.name }}
        </router-link>
      </li>
    </ul>
  </div>
</template>
