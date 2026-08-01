<script setup lang='ts'>
import CheckoutFieldError from '@/components/CheckoutFieldError.vue'
import useVuelidate from '@vuelidate/core'
import router from '@/router'
import {reactive, ref} from 'vue'
import {useCategoryStore} from '@/stores/category'
import {useCartStore} from '@/stores/cart'
import {isCreditCard, isMobilePhone} from '@/validators'
import {asDollarsAndCents} from '@/utils'
import {email, helpers, maxLength, minLength, required} from '@vuelidate/validators'
import type {OrderDetails, ServerErrorResponse} from '@/types'

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
    minLength: helpers.withMessage("ADDRESS MUST HAVE AT LEAST 4 LETTERS", minLength(4)),
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
  padding: 1em 0;
}

h1 {
  margin: 1em auto;
  text-align: center;
}

#checkout-summary {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  font-size: 20px;
  font-weight: bolder;
  text-transform: uppercase;
}

.empty-checkout-continue {
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

.empty-checkout-continue:hover,
.empty-checkout-continue:active {
  background-color: var(--default-text-color);
}

.checkout-page-body {
  display: flex;
  padding: 0 2em 2em 2em;
}

form {
  background: var(--secondary-background-color);
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  font-weight: bold;
  padding: 4em 6em 3em 6em;
  margin-left: 21.2em;
  margin-right: 21.2em;
}

form > div {
  display: flex;
  justify-content: flex-end;
  margin-bottom: 0.5em;
  padding: 0.15em;
}

input:-webkit-autofill,
input:-webkit-autofill:hover,
input:-webkit-autofill:focus
input:-webkit-autofill,
textarea:-webkit-autofill,
textarea:-webkit-autofill:hover
textarea:-webkit-autofill:focus,
select:-webkit-autofill,
select:-webkit-autofill:hover,
select:-webkit-autofill:focus {
  border: none;
  -webkit-text-fill-color: var(--default-text-color);
  -webkit-box-shadow: 0 0 0 1000px #ffffff inset;
  transition: background-color 5000s ease-in-out 0s;
}

form > div > input,
form > div > select {
  background-color: white;
  color: var(--default-text-color);
  font-size: 16px;
  margin-left: 0.5em;
  border: none;
  text-decoration: none;
}

.checkoutStatusBox {
  text-align: center;
  color: red;
  padding: 0 3em 1em 0;

}

.button-submit input {
  background: var(--primary-color-light);
  border: none;
  font-size: 17px;
  font-weight: normal;
  cursor: pointer;
  width: 15em;
  height: 3em;
  text-align: center;
  color: white;
  text-transform: uppercase;
  margin-top: 1em;
  margin-right: 3em;
}

.button-submit input:hover,
.button-submit input:active {
  background-color: var(--default-text-color);
}

#checkout-totals {
  text-align: center;
  font-size: 18px;
  font-weight: bolder;
  padding: 1em 0 1em 0;
  margin-right: 2em;

}
</style>

<template>
  <h1>Checkout</h1>
  <div class="checkout-page">

    <div id="checkout-summary">
      <section v-if='cartStore.cart.empty'>
        <div>
          Please add items to your cart to checkout<br>
        </div>
        <router-link :to="{
          name: 'category-view',
          params: {
            name: categoryStore.categoryName,
          },
        }">
          <button class='empty-checkout-continue'>CONTINUE SHOPPING</button>
        </router-link>
      </section>
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

        <section class='checkout-actions'>
          <div class="button-submit">
            <input
                type="submit"
                name="submit"
                :disabled="form.checkoutStatus === 'PENDING'"
                value="Complete Purchase"
            />
          </div>
        </section>

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
            ERROR: <br>Please fix the problems above<br> and try again
          </div>

          <div v-else-if="form.checkoutStatus === 'PENDING'">Processing...</div>

          <div v-else-if="form.checkoutStatus === 'OK'">Order placed...</div>

          <div v-else>{{ serverErrorMessage }}</div>
        </section>

      </form>
    </section>
  </div>
</template>
