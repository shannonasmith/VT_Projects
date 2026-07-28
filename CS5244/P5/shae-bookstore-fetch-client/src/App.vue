<script setup lang="ts">
import {RouterView, useRoute} from "vue-router";
import TheHeader from "@/components/TheHeader.vue";
import TheFooter from "@/components/TheFooter.vue";

const route = useRoute();
</script>

<style></style>

<template>
  <suspense>
    <div id="root" :class="{ 'app-hero': route.name === 'home', 'app-default': route.name != 'home' }">
      <the-header></the-header>
      <router-view :key="route.fullPath"></router-view>
      <the-footer></the-footer>
    </div>
  </suspense>
</template>