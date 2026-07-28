<script setup lang="ts">
import {inject} from "vue";
import type {CategoryItem} from "@/types";

const categoryList = inject("categoryList") as CategoryItem[];
</script>

<style scoped>
.header-dropdown {
  position: relative;
}

.categories-button {
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
  color: var(--primary-color);
  width: 100%;
  font-weight: bolder;
}

.header-dropdown li {
  padding: 0.25em 0.5em;
  font-family: "Marcellus SC", serif;
  color: var(--primary-color);
  text-decoration: none;
}

.header-dropdown li:hover {
  background: var(--hover-background-color);
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
      <li v-for="category in categoryList" :key="category.categoryId">
        <router-link :to="'/category/' + category.name">
          {{ category.name }}
        </router-link>
      </li>
    </ul>
  </div>
</template>
