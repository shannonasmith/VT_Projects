<script setup lang='ts'>
import TheHeader from '@/components/TheHeader.vue'
import TheFooter from '@/components/TheFooter.vue'
import { RouterView, useRoute } from 'vue-router'
import { useCategoryStore } from '@/stores/category'

const categoryStore = useCategoryStore();
categoryStore.fetchCategories();

const route = useRoute();
</script>

<style></style>

<template>
    <div id="root" :class="{'app-hero': route.name === 'home', 'app-default': route.name != 'home'}">
      <the-header></the-header>
      <router-view :key="route.fullPath"></router-view>
      <the-footer></the-footer>
    </div>
</template>