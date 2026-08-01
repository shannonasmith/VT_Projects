<script setup lang='ts'>
import CheckoutFieldError from '@/components/CheckoutFieldError.vue'
import useVuelidate from '@vuelidate/core'
import router from '@/router'
import type {OrderDetails, ServerErrorResponse} from '@/types'
import {email, helpers, maxLength, minLength, required} from '@vuelidate/validators'
import {reactive, ref} from 'vue'
import {useCategoryStore} from '@/stores/category'
import {useCartStore} from '@/stores/cart'
import {isCreditCard, isMobilePhone} from '@/validators'
import {asDollarsAndCents} from '@/utils'

const categoryStore = useCategoryStore()
const cartStore = useCartStore()
const cart = cartStore.cart
const defaultServerErrorMessage = "An unexpected error occurred, please try again."
const serverErrorMessage = ref(defaultServerErrorMessage)

const months: string[] = [
  "Jan",
  "Feb",
  "Mar",
  "Apr",
  "May",
  "Jun",
  "Jul",
  "Aug",
  "Sep",
  "Oct",
  "Nov",
  "Dec",
]

const form = reactive({
  name: "",
  address: "",
  phone: "",
  email: "",
  ccNumber: "",
  ccExpiryMonth: new Date().getMonth() + 1,
  ccExpiryYear: new Date().getFullYear(),
  checkoutStatus: "",
})

const rules = {
  name: {
    required: helpers.withMessage("PLEASE PROVIDE YOUR NAME", required),
    minLength: helpers.withMessage("NAME MUST HAVE AT LEAST 4 LETTERS", minLength(4)),
    maxLength: helpers.withMessage("NAME CAN BE UP TO 45 LETTERS", maxLength(45)),
  },
  address: {
    required: helpers.withMessage("PLEASE PROVIDE AN ADDRESS", required),
    minLength: helpers.withMessage("ADDRESS MUST HAVE AT LEAST 2 LETTERS", minLength(4)),
    maxLength: helpers.withMessage("ADDRESS CAN BE UP TO 45 LETTERS", maxLength(45)),
  },
  phone: {
    required: helpers.withMessage("PLEASE PROVIDE A PHONE NUMBER", isMobilePhone),
    phone: helpers.withMessage("PLEASE PROVIDE A VALID PHONE NUMBER",
        (value: string) => !helpers.req(value) || isMobilePhone(value))
  },
  email: {
    required: helpers.withMessage("PLEASE PROVIDE AN EMAIL ADDRESS", required),
    email: helpers.withMessage("PLEASE PROVIDE A VALID EMAIL ADDRESS", email)
  },
  ccNumber: {
    required: helpers.withMessage("PLEASE PROVIDE A CREDIT CARD NUMBER", isCreditCard),
    ccNumber: helpers.withMessage("PLEASE PROVIDE A VALID CREDIT CARD NUMBER",
        (value: string) => !helpers.req(value) || isCreditCard(value))
  },
  ccExpiryMonth: {},
  ccExpiryYear: {},
}
const v$ = useVuelidate(rules, form);

function yearFrom(index: number) {
  /* NOTE: For example yearFrom(0) == <current_year> */
  return new Date().getFullYear() + index;
}

/* Submit event listener */
async function submitOrder() {
  console.log("Submit order");
  const isFormCorrect = await v$.value.$validate();
  if (!isFormCorrect) {
    form.checkoutStatus = "ERROR";
  } else {
    try {
      form.checkoutStatus = "PENDING";
      serverErrorMessage.value = defaultServerErrorMessage;

      const placeOrderResponse: OrderDetails | ServerErrorResponse =
          await cartStore.placeOrder({
            name: form.name,
            address: form.address,
            phone: form.phone,
            email: form.email,
            ccNumber: form.ccNumber,
            ccExpiryMonth: form.ccExpiryMonth,
            ccExpiryYear: form.ccExpiryYear,
          })

      if ("error" in placeOrderResponse) {
        form.checkoutStatus = "SERVER_ERROR";
        serverErrorMessage.value = placeOrderResponse.message
        console.log("Error placing order", placeOrderResponse);
      } else {
        form.checkoutStatus = "OK";
        await router.push({name: "confirmation-view"});
      }

    } catch (e) {
      form.checkoutStatus = "SERVER_ERROR";
      serverErrorMessage.value = defaultServerErrorMessage;
      console.log("Error placing order", e);
    }
  }
}
</script>

<style scoped>
.checkout-page {
  color: var(--default-text-color);
}

.checkout-page-body {
  display: flex;
  padding: 2em 2em;
}

h1 {
  margin: 1em auto;
  text-align: center;
}

#checkout-description {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  font-weight: bolder;
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
  padding: 0.75em 0 0.75em 0;
  margin: 2em 0 26em 2.5em;
}

.button-continue:hover,
.button-continue:active {
  background-color: var(--default-text-color);
}

form {
  background: var(--secondary-background-color);
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  font-weight: bold;
  padding: 3em 7em 1em 5em;
  margin-left: 20em;
}

form > div {
  display: flex;
  justify-content: flex-end;
  margin-bottom: 0.7em;
  padding: 0.15em;
}

form > div > input,
form > div > select {
  background-color: white;
  font-size: 16px;
  margin-left: 0.5em;
  border: none;
  font-weight: lighter;
}

.checkoutStatusBox {
  background: var(--secondary-background-color);
  text-align: center;
  color: red;
  padding: 0 2.5em 1em 0;
}

.button-submit input {
  background: var(--primary-color-light);
  border: none;
  font-size: 17px;
  text-decoration: none;
  cursor: pointer;
  width: 15em;
  height: 3em;
  text-align: center;
  color: white;
  text-transform: uppercase;
  margin-right: 2.5em;
}

.button-submit input:hover,
.button-submit input:active {
  background-color: var(--default-text-color);
}

#checkout-totals {
  text-align: center;
  font-size: 17px;
  font-weight: bolder;
  padding: 0 2.25em 1.5em 0;
}
</style>

<template>
  <h1>Checkout Page</h1>
  <div class="checkout-page">
    <div id="checkout-description">
      <div v-if='cartStore.cart.empty'>YOUR CART IS EMPTY<br><br>Please add items to your cart to checkout.
        <router-link :to="{
      name: 'category-view',
      params: {
        name: categoryStore.categoryName,
      },
    }">
          <br><br><br>
          <div class='button-continue'>CONTINUE SHOPPING</div>
        </router-link>
      </div>
    </div>

    <section class="checkout-page-body" v-if="!cart.empty">
      <form @submit.prevent="submitOrder">

        <checkout-field-error :field-name="v$.name"/>
        <div>
          <label for="name">Name</label>
          <input
              type="text"
              size="30"
              id="name"
              name="name"
              v-model.lazy="v$.name.$model"
          />
        </div>

        <checkout-field-error :field-name="v$.address"/>
        <div>
          <label for="address">Address</label>
          <input
              class="textField"
              type="text"
              size="30"
              id="address"
              name="address"
              v-model.lazy="v$.address.$model"
          />
        </div>

        <checkout-field-error :field-name="v$.phone"/>
        <div>
          <label for="phone">Phone</label>
          <input
              class="textField"
              type="text"
              size="30"
              id="phone"
              name="phone"
              v-model.lazy="v$.phone.$model"
          />
        </div>

        <checkout-field-error :field-name="v$.email"/>
        <div>
          <label for="email">Email</label>
          <input
              type="text"
              size="30"
              id="email"
              name="email"
              v-model.lazy="v$.email.$model"
          />
        </div>

        <checkout-field-error :field-name="v$.ccNumber"/>
        <div>
          <!-- for testing, use credit card # 4444333322221111 -->
          <label for="ccNumber">Credit Card</label>
          <input
              type="text"
              size="30"
              id="ccNumber"
              name="ccNumber"
              v-model.lazy="v$.ccNumber.$model"
          />
        </div>

        <div>
          <label>Exp Date</label>
          <select v-model.lazy="v$.ccExpiryMonth.$model">
            <option
                v-for="(month, index) in months"
                :key="index"
                :value="index + 1"
            >
              {{ month }} ({{ index + 1 }})
            </option>
          </select>

          <select v-model.lazy="v$.ccExpiryYear.$model">
            <option
                v-for="years in 16"
                :key="years"
                :value="yearFrom(years - 1)"
            >
              {{ yearFrom(years - 1) }}
            </option>
          </select>
        </div>

        <div class="button-submit">
          <input
              type="submit"
              name="submit"
              :disabled="form.checkoutStatus === 'PENDING'"
              value="Complete Purchase"
          />
        </div>

        <div id='checkout-totals' v-if='!cartStore.cart.empty'>
          <ul>
            <li>Your card will be charged {{ asDollarsAndCents(cartStore.cart.total) }}</li>
            <li>({{ asDollarsAndCents(cartStore.cart.subtotal) }} + {{ asDollarsAndCents(cartStore.cart.surcharge) }}
              surcharge)
            </li>
          </ul>
        </div>

        <section v-show="form.checkoutStatus !== ''" class="checkoutStatusBox">
          <div v-if="form.checkoutStatus === 'ERROR'">
            ERROR: <br>Please fix the problems above<br> and try again.
          </div>

          <div v-else-if="form.checkoutStatus === 'PENDING'">Processing...</div>

          <div v-else-if="form.checkoutStatus === 'OK'">Order placed...</div>

          <div v-else>{{ serverErrorMessage }}</div>
        </section>

      </form>
    </section>
  </div>
</template>
