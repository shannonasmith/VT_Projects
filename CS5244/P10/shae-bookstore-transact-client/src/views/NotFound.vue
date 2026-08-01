<script setup lang='ts'>
import {useCategoryStore} from '@/stores/category'

const categoryStore = useCategoryStore()
</script>

<style scoped>
.not-found-page {
  color: var(--default-text-color);
  padding: 1em 0;
}

h1 {
  margin: 1em auto;
  text-align: center;
}

#not-found-summary {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  font-size: 20px;
  font-weight: bolder;
  text-transform: uppercase;
}

.button-continue {
  background: var(--primary-color-light);
  border: none;
  color: white;
  font-size: 17px;
  font-weight: normal;
  text-transform: uppercase;
  cursor: pointer;
  width: 15em;
  height: 3em;
  text-align: center;
  padding: 0.75em 0 0.75em 0;
  margin-bottom: 27em;
  margin-top: 3em;
}

.button-continue:hover,
.button-continue:active {
  background-color: var(--default-text-color);
}
</style>

<template>
  <h1>OOPS! Content not found</h1>
  <div class="not-found-page">

    <div id="not-found-summary">
      <div>
        There appears to be nothing to see here<br>
      </div>
      <router-link
          :to="{
              name: 'category-view',
              params: { name: categoryStore.categoryName },
            }"
      >
        <button class="button-continue">Continue Shopping</button>
      </router-link>
    </div>
  </div>
</template>