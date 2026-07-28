<script setup lang="ts">
import { inject } from "vue";
import type { CategoryItem } from "@/types";
const categoryList = inject("categoryList") as CategoryItem[];
const categoryImageFileName = function (category: CategoryItem): string {
  let name = category.name.toLowerCase();
  name = name.replace(/ /g, "-");
  name = name.replace(/'/g, "");
  return `${name}.png`;
};
</script>

<style scoped>

ul {
  justify-content: left;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  gap: 1em;
  padding-left: 1.7em;
}

li {
  text-align: center;
  cursor: pointer;
}

li div {
  margin-bottom: -2em;
  padding: 0.5em 0 0.5em 0;
  background: rgba(6, 61, 15, 0.75); /* last # is percent opacity */
  color: white;
  transform: translateY(-2.25em);
  cursor: pointer;
}

li div:hover,
li div:active {
  background-color: var(--primary-color-light);
}
</style>

<template>
  <ul>
    <li v-for="category in categoryList" :key="category.categoryId">
      <router-link :to="'../category/' + category.name">
        <img
            :src="'category-images/' + categoryImageFileName(category)"
            :alt="category.name + ' category'"
        />
        <div>{{ category.name }}</div>
      </router-link>
    </li>
  </ul>
</template>
