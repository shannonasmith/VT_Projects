<script setup lang='ts'>
import CartTable from '@/components/CartTable.vue'

</script>

<style scoped>
h1 {
  margin: 1em auto;
  text-align: center;
}
</style>

<template>
  <h1>Shopping Cart</h1>
  <!-- Element cart-table doesn't have required attribute book-->
  <cart-table></cart-table>
</template>