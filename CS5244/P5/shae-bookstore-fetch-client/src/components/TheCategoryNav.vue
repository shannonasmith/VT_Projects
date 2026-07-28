<script setup lang="ts">
import type {CategoryItem} from "@/types";
import {useRouter} from "vue-router";

const router = useRouter();

const apiUrl =
    `${location.protocol}//${location.hostname}:` +
    `${location.port === '5173' ? '8080' : location.port}` +
    `${import.meta.env.BASE_URL}api`
console.log(apiUrl)

let response = await fetch(`${apiUrl}/categories/`)
let data = await response.json();
let categoryList = data as CategoryItem[];
console.log(categoryList);
</script>

<style scoped>

nav {
  text-align:center;
}

.category-buttons {
  display:inline-flex;
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
      <li v-for="category in categoryList" :key="category.categoryId">
        <router-link
            :to="'/category/' + category.name"
            class="button category-button"
        >
          {{ category.name }}
        </router-link>
      </li>
    </ul>
  </nav>
</template>