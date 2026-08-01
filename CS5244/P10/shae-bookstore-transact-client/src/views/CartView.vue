<script setup lang='ts'>
import CartTable from '@/components/CartTable.vue'
import router from '@/router'
import {useCartStore} from '@/stores/cart'
import {useCategoryStore} from '@/stores/category'

const cartStore = useCartStore()
const categoryStore = useCategoryStore()
</script>

<style scoped>
.cart-page {
  color: var(--default-text-color);
  padding: 1em 0;
}

h1 {
  margin: 1em auto;
  text-align: center;
}

#cart-summary {
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
  text-decoration: none;
  cursor: pointer;
  width: 15em;
  height: 3em;
  text-align: center;
  padding: 0.75em 0 0.75em 0;
  margin-bottom: 2em;
}

.button-continue:hover,
.button-continue:active {
  background-color: var(--default-text-color);
}

.button-checkout {
  background: var(--primary-color);
  border: none;
  color: white;
  font-size: 17px;
  text-decoration: none;
  cursor: pointer;
  width: 15em;
  height: 3em;
  text-align: center;
  padding: 0.75em 0 0.75em 0;
  margin-bottom: 2em;
}

.button-checkout:hover,
.button-checkout:active {
  background-color: var(--default-text-color);
}

.empty-cart-continue {
  background: var(--primary-color-light);
  border: none;
  color: white;
  font-size: 17px;
  font-weight: normal;
  cursor: pointer;
  width: 15em;
  height: 3em;
  text-align: center;
  padding: 0.75em 0 0.75em 0;
  margin-bottom: 21em;
  margin-top: 3em;
}

.empty-cart-continue:hover,
.empty-cart-continue:active {
  background-color: var(--default-text-color);
}

#cart-actions {
  display: flex;
  margin: 3em;
  justify-content: space-evenly;
}

ul {
  display: contents;
}

ul > li {
  display: contents;
  font-weight: bold;
}
</style>

<template>
  <h1>Shopping Cart</h1>
  <div class="cart-page">

    <section id="cart-summary">
      <div v-if="cartStore.count > 1">
        Your cart contains {{ cartStore.count }} items
      </div>

      <div v-else-if="cartStore.count === 1">
        Your cart contains
        {{ cartStore.count }} item
      </div>

      <div v-else>Your cart is empty</div>
      <div>
        <router-link :to="{
             name: 'category-view',
             params: {
               name: categoryStore.categoryName,
             },
           }">
          <button class='empty-cart-continue' v-if='cartStore.cart.empty'>CONTINUE SHOPPING</button>
        </router-link>
      </div>
    </section>

  </div>

  <cart-table></cart-table>

  <section id='cart-actions'>

    <router-link :to="{
        name: 'category-view',
        params: {
          name: categoryStore.categoryName,
        },
      }">
      <div class='button-continue' v-if='!cartStore.cart.empty'>CONTINUE SHOPPING</div>
    </router-link>

    <router-link :to="{
        name: 'checkout-view',
        params: {
          name: categoryStore.categoryName,
        },
      }">
      <div class='button-checkout' v-if='!cartStore.cart.empty' @click="router.push({path: '/checkout-view'})">PROCEED
        TO CHECKOUT
      </div>
    </router-link>

  </section>
</template>
