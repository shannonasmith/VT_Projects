<script setup lang="ts">
import { provide } from "vue";
import { RouterView, useRoute } from "vue-router";
import type { CategoryItem } from "@/types";
import TheHeader from "@/components/TheHeader.vue";
import TheFooter from "@/components/TheFooter.vue";
import sourceData from "@/db.json";
const route = useRoute();
const categoryList = sourceData.categories as CategoryItem[];
provide("categoryList", categoryList);
</script>

<style>


</style>

<template>
  <div id="root" :class="{ 'app-hero': route.name === 'home', 'app-default': route.name != 'home' }">
    <the-header></the-header>
    <router-view :key="route.fullPath"></router-view>
    <the-footer></the-footer>
  </div>

</template>