<script setup lang='ts'>
import ConfirmationTable from '@/components/ConfirmationTable.vue'
import {useCartStore} from '@/stores/cart'
import {useCategoryStore} from '@/stores/category'
import {useOrderDetailsStore} from '@/stores/orderDetails'
import type {OrderDetails} from '@/types'

const cartStore = useCartStore()
const categoryStore = useCategoryStore()
const orderDetailsStore = useOrderDetailsStore()
const orderDetails = orderDetailsStore.orderDetails as OrderDetails
</script>

<style scoped>
.confirmation-page {
  color: var(--default-text-color);
  padding: 1em 0;
}

h1 {
  margin: 1em auto;
  text-align: center;
}

#confirmation-summary {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  font-size: 20px;
  font-weight: bolder;
  text-transform: uppercase;
  margin-bottom: 1em;
}

.button-continue {
  background: var(--primary-color-light);
  border: none;
  color: white;
  font-size: 17px;
  font-weight: normal;
  text-decoration: none;
  cursor: pointer;
  width: 15em;
  height: 3em;
  text-align: center;
  padding: 0.75em 0 0.75em 0;
}

.button-continue:hover,
.button-continue:active {
  background-color: var(--default-text-color);
}

#confirmation-continue {
  display: flex;
  margin: 3em;
  justify-content: space-evenly;
}

.empty-conf-continue {
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
  margin-bottom: 26em;
  margin-top: 3em;
}

.empty-conf-continue:hover,
.empty-conf-continue:active {
  background-color: var(--default-text-color);
}
</style>


<template>
  <h1>Confirmation</h1>
  <div class="confirmation-page">

    <div id="confirmation-summary">
      <section v-if="!orderDetails || !orderDetailsStore.hasOrderDetails()">
        <div>
          Sorry, you have not placed an order yet
        </div>
        <router-link
            :to="{
            name: 'category-view',
            params: { name: categoryStore.categoryName },
          }"
        >
          <button class="empty-conf-continue">CONTINUE SHOPPING</button>
        </router-link>
      </section>

      <template v-else>
        <section id="confirmation-summary">
          <div>Thank you for your purchase!</div>
        </section>

        <confirmation-table></confirmation-table>

        <section id="confirmation-continue">
          <router-link :to="{
            name: 'category-view',
            params: {
              name: categoryStore.categoryName,
            },
          }">
            <button class='button-continue' v-if="cartStore.cart.empty">CONTINUE SHOPPING</button>
          </router-link>
        </section>
      </template>

    </div>
  </div>
</template>