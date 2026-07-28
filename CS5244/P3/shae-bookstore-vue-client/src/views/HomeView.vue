<script setup lang="ts">
import TheHomeCategoryList from "@/components/TheHomeCategoryList.vue";
</script>

<style scoped>
.home-page {
  display: flex;
  text-align: center;
  font-family: "Marcellus SC", serif;
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
      <h2>Welcome to Shae's Books</h2><br>
      <h3>Check out our Staff Picks for the week!</h3><br><br>
      <div class="button shop-for-books">Shop for Books</div>
      <br>
      <br>
      <section class="category-images container">
        <the-home-category-list></the-home-category-list>
      </section>
    </div>
  </div>
</template>
