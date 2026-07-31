<script setup lang='ts'>
import CartTable from '@/components/CartTable.vue'
import {useCartStore} from '@/stores/cart'
import {useCategoryStore} from '@/stores/category'

const categoryStore = useCategoryStore()
const cartStore = useCartStore()
const clearCart = function () {
  cartStore.clearCart()
}
</script>

<style scoped>
h1 {
  margin: 1em auto;
  text-align: center;
}
</style>

<template>
  <h1>Shopping Cart</h1>
  <cart-table></cart-table>     <!-- Element cart-table doesn't have required attribute book-->
</template>