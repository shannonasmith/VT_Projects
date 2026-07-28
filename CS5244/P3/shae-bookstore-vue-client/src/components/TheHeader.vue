<script setup lang="ts">
import TheHeaderDropdown from "@/components/TheHeaderDropdown.vue";
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
  margin-bottom: -0.9em;
}

.text-logo {
  font-family: "Marcellus SC", serif;
  text-align: center;
  font-size: 30px;
  margin-top: auto;
  text-decoration: none;
  color: #063D0F;
  flex: 25%;
}

.search-bar {
  display: flex;
  flex-direction: row;
  width: 90%;
  max-width: 500px;
  right: 20%;
  background-color: white;
  align-items: center;
  border: 2px solid #063D0F;
  font-size: 14px;
  text-align: left;

}

.search-bar input {
  font-family: "Martel Sans", serif;
  width: 1450px;
  padding-left: 1em;
  border: none;
  outline: 0;
  color: #063D0F;
  font-size: 15px;
}

.search-bar button {
  display: inline-block;
  background-color: var(--primary-color);
  border: none;
  padding: 0.5em 1.5em;
  cursor: pointer;
  height: 35px;
}

.shopping-cart {
  display: flex;
  flex-direction: row;
  transform: translate(-20%);
  max-width: 100px;
  width: 60%;
}

.quantity {
  position: absolute;
  font-family: "Marcellus SC", serif;
  background-color: #063D0F;
  border-radius: 50%;
  padding: 0.1em 0.3em;
  transform: translate(130%);
}

.user-account {
  display: inline-block;
  font-family: "Marcellus SC", serif;
  cursor: pointer;
  transform: translateX(-1.5px);
}

.user-account img {
  display: inline-block;
  width: 22px;
  transform: translateX(-12px);
}

.header-dropdown {
  text-align: left;
  transform: translateX(-8px);
  padding-top: 0.5em;
}

</style>

<template>
  <!-- Beginning header -->
  <header class="container">

    <!-- Bookstore logo section -->
    <section>
      <div class="bookstore-logo">
        <router-link to="/">
          <h1 class="text-logo">Shae's Books</h1>
          <img src="\site-images\bookstore-logo.png" alt="Shae's Books Logo" width="225" height="98"/>
        </router-link>
      </div>
    </section>

    <!-- Search bar section -->
    <section>
      <div>
        <form action="category.html" class="search-bar">
          <input type="text" placeholder="Search by Title, Author, or Keyword"/>
          <button type="submit">
            <router-link to="/">
              <img src="\icons\search.png" width="80" alt="search icon"/>
            </router-link>
          </button>
        </form>
      </div>
    </section>

    <!-- cart section -->
    <section>
      <div class="shopping-cart">
        <router-link to="/">
          <img src="\icons\cart.png" alt="cart icon"/>
        </router-link>
        <span class="quantity">0</span>
      </div>
    </section>

    <!-- account and header dropdown section -->
    <section>
      <div class="user-account">
        <router-link to="/">
          <img src="\icons\user.png" alt="account icon"/>
          <h3 class="user-account"> My Account</h3>
        </router-link>
      </div>
      <div class="header-dropdown">
        <the-header-dropdown></the-header-dropdown>
      </div>
    </section>
  </header>
</template>
