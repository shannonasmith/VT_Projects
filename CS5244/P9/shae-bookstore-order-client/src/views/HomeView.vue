<script setup lang='ts'>
import TheHomeCategoryList from '@/components/TheHomeCategoryList.vue'
</script>

<style scoped>
.home-page {
  display: flex;
  text-align: center;
  font-family: "Century Gothic", serif;
}

.button.shop-for-books {
  background: var(--primary-color);
  border: none;
  color: white;
  font-size: 17px;
  text-decoration: none;
  padding: 0.5em 1em;
  cursor: pointer;
}

.button.shop-for-books:hover,
.button.shop-for-books:active {
  background-color: var(--primary-color-light);
}
</style>

<template>
  <div class="home-page">
    <div class="container">
      <br>
      <h1>Welcome!</h1>
      <br>
      <h2>Check out our staff picks for the week!</h2>
      <br>
      <router-link to="/category/STAFF PICKS/">
        <div class="button shop-for-books" >SHOP FOR BOOKS</div>
      </router-link>
      <br><br>
      <section class="category-images container">
        <the-home-category-list></the-home-category-list>
      </section>
    </div>
  </div>
</template>
