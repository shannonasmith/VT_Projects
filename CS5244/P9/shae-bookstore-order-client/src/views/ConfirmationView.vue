<script setup lang='ts'>
import {useCategoryStore} from '@/stores/category'

const categoryStore = useCategoryStore()
</script>

<style scoped>
#confirmation-page {
  display: grid;
  align-content: center;
}

h1 {
  margin: 1em auto;
  text-align: center;
}

h3 {
  margin: 1em auto;
  text-align: center;
}

.button-continue {
  background: var(--primary-color-light);
  border: none;
  color: white;
  font-size: 16px;
  text-decoration: none;
  font-weight: lighter;
  cursor: pointer;
  width: 17em;
  height: 3em;
  padding: 0.75em 0 0.75em 3.25em;
  align-content: center;
  margin-left: 35em;
  margin-bottom: 30em;
}

.button-continue:hover,
.button-continue:active {
  background-color: var(--default-text-color);
}
</style>

<template>
  <div id="confirmation-page">
    <h1>Confirmation Page</h1>
    <h3> Thank you for your purchase!</h3>
    <div>
      <router-link :to="{
      name: 'category-view',
      params: {
        name: categoryStore.categoryName,
      },
    }">
        <div class='button-continue'>CONTINUE SHOPPING</div>
      </router-link>
    </div>
  </div>
</template>