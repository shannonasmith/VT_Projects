<script setup lang='ts'>
import type {CategoryItem} from '@/types'
import {useCategoryStore} from '@/stores/category'

const categoryStore = useCategoryStore()
const categoryImageFileName = function (category: CategoryItem): string {
  let name = category.name.toLowerCase()
  name = name.replace(/ /g, "-")
  name = name.replace(/'/g, "")
  name = name.replace("+", "")
  return `${name}.png`
}
</script>

<style scoped>
ul {
  justify-content: left;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  gap: 1em;
  padding-left: 5em;
}

li {
  text-align: center;
  cursor: pointer;
}

li div {
  margin-bottom: -2em;
  padding: 0.4em 0 0.4em 0;
  background: rgba(80, 6, 74, 90%);
  color: white;
  transform: translateY(-2.2em);
  cursor: pointer;
}

li div:hover,
li div:active {
  background: rgb(4, 75, 122);
}
</style>

<template>
  <ul>
    <li v-for="category in categoryStore.categoryList" :key="category.categoryId">
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
