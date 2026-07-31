<script setup lang="ts">
import TheHeaderDropdown from '@/components/TheHeaderDropdown.vue'
import { useCartStore } from '@/stores/cart'

const cartStore = useCartStore()
</script>

<style scoped>
header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: var(--secondary-background-color);
  color: var(--secondary-text-color);
  flex-direction: row;
  gap: 1em;
}

@media (max-width: 1000px) {
  header {
    flex-direction: row;
  }
}

.bookstore-logo {
  margin-left: 2em;
}

.search-bar {
  display: flex;
  flex-direction: row;
  width: 90%;
  max-width: 500px;
  right: 20%;
  background-color: white;
  align-items: center;
  border: 2px solid #7d0f74;
  font-size: 14px;
  text-align: left;
}

.search-bar input {
  font-family: 'Martel Sans', serif;
  width: 1450px;
  padding-left: 1em;
  border: none;
  outline: 0;
  color: rgba(7, 7, 7, 0.7);
  font-size: 15px;
}

.search-bar button {
  display: inline-block;
  background-color: var(--primary-color);
  border: none;
  padding: 0.5em 1.2em;
  cursor: pointer;
}

.shopping-cart {
  display: flex;
  flex-direction: row;
  transform: translate(-50%);
  max-width: 100px;
  width: 50%;
}

.quantity {
  position: absolute;
  font-family: 'Century Gothic', serif;
  background-color: #7d0f74;
  border-radius: 50%;
  padding: 0.1em 0.5em;
  transform: translate(150%);
}

.user-account {
  display: inline-block;
  font-size: 18px;
  font-family: 'Century Gothic', serif;
  cursor: pointer;
  padding-right: 1.5em;
  padding-left: 0.5em;
}

.user-account img {
  display: inline-block;
  width: 26px;
  transform: translateY(6px);
}

.header-dropdown {
  text-align: left;
  padding-top: 0.2em;
}
</style>

<template>
  <!-- Beginning header -->
  <header class="container">
    <!-- Bookstore logo section -->
    <section>
      <div class="bookstore-logo">
        <router-link to="/">
          <img src="\site-images\bookstore-logo.png" alt="Shae's Books Logo" width="280" />
        </router-link>
      </div>
    </section>

    <!-- Search bar section -->
    <section>
      <div>
        <form action="category.html" class="search-bar">
          <input type="text" placeholder="Search by Title, Author, or Keyword" />
          <button type="submit">
            <router-link to="/">
              <img src="\icons\search.png" width="100" alt="search icon" />
            </router-link>
          </button>
        </form>
      </div>
    </section>

    <!-- cart section -->
    <section>
      <div class="shopping-cart">
        <router-link to="/">
          <img src="\icons\cart.png" alt="cart icon" />
        </router-link>
        <span class="quantity">{{ cartStore.count }}</span>
      </div>
    </section>

    <!-- account and header dropdown section -->
    <section>
      <div class="user-account">
        <router-link to="/">
          <img src="\icons\user.png" alt="account icon" />
          <h3 class="user-account">My Account</h3>
        </router-link>
      </div>
      <div class="header-dropdown">
        <the-header-dropdown></the-header-dropdown>
      </div>
    </section>
  </header>
</template>
