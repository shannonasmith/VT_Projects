<script setup lang="ts">
import { inject } from "vue";
import type { CategoryItem } from "@/types";
const categoryList = inject("categoryList") as CategoryItem[];
</script>

<style scoped>
.category-buttons {
  display: flex;
  flex-direction: row;
  background-color: var(--primary-color);
  text-align: center;
  text-transform: uppercase;
  justify-content: center;
}

.button.category-button.router-link-active {
  background-color: var(--primary-color);
}

.button.category-button {
  background-color: var(--primary-color);
}

.button.category-button:hover,
.button.category-button:active {
  background-color: var(--nav-bar-hover-color);
}
</style>

<template>
  <nav class="category-nav">
    <ul class="category-buttons">
      <li v-for="category in categoryList" :key="category.categoryId">
        <router-link :to="'/category/' + category.name" class="button category-button">
          {{ category.name }}
        </router-link>
      </li>
    </ul>
  </nav>
</template>
