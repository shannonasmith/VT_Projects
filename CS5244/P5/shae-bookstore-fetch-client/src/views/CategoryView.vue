<script setup lang="ts">
import TheCategoryNav from "@/components/TheCategoryNav.vue";
import TheCategoryBookList from "@/components/TheCategoryBookList.vue";
import {useRoute} from "vue-router";
import {useRouter} from "vue-router";
import type {BookItem} from "@/types";

const route = useRoute();
const router = useRouter();

const apiUrl =
    `${location.protocol}//${location.hostname}:` +
    `${location.port === '5173' ? '8080' : location.port}` +
    `${import.meta.env.BASE_URL}api`

let response = await fetch(`${apiUrl}/categories/name/${route.params.name}/books`)
let data = await response.json();
let bookList = data as BookItem[];

</script>

<style scoped></style>

<template>
  <div>
    <the-category-nav></the-category-nav>
    <the-category-book-list :bookList="bookList"></the-category-book-list>
  </div>
</template>
