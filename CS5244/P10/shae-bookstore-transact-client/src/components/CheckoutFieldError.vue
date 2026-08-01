<script setup lang='ts'>
import type {BaseValidation} from '@vuelidate/core'

const props = defineProps<{fieldName: BaseValidation;}>()
</script>

<style scoped>
form > .error {
  font-family: "Martel Sans", serif;
  color: red;
  margin-right: 1em;
  margin-bottom: -0.5em;
  margin-top: 0.5em;
  font-style: italic;
  font-size: 11px;


}
</style>

<template>
  <template v-if="fieldName.$error">
    <span
        class="error"
        v-for="error of fieldName.$errors"
        :key="error.$uid"
    >{{ error.$message }}</span
    >
  </template>
</template>
