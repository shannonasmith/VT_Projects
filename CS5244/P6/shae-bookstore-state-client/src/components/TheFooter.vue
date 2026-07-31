<script setup lang="ts">
const siteImagePrefix = `${import.meta.env.BASE_URL}/icons`
</script>

<style scoped>
footer {
  background: var(--secondary-background-color);
  color: var(--default-text-color);
  justify-content: space-between;
  align-items: center;
  display: flex;
  flex-direction: row;
  gap: 1em;
}

.container {
  padding: 0.75em 1.25em;
  font-family: 'Century Gothic', serif;
  font-size: 17px;
  font-weight: bold;
}

footer > * {
  flex-basis: 100%;
  margin-left: 1.5em;
}

.links {
  font-size: 17px;
  text-align: center;
  text-decoration: none;
}

.social-media-icons {
  display: flex;
  justify-content: flex-end;
  gap: 0.6em;
  margin-right: 1.5em;
}
</style>

<template>
  <footer class="container">
    <p>&copy; 2023 Shae's Books</p>

    <section class="links">
      <a href="#">DIRECTIONS</a><a> | </a> <a href="#">CONTACT</a><a> | </a>
      <a href="#">PRIVACY</a>
    </section>

    <section class="social-media-icons">
      <p>Follow Us</p>
      <a href="https://www.facebook.com"
        ><img :src="`${siteImagePrefix}/facebook.png`" alt="facebook icon" width="30"
      /></a>

      <a href="https://www.instagram.com"
        ><img :src="`${siteImagePrefix}/instagram.png`" alt="instagram icon" width="30"
      /></a>

      <a href="https://www.twitter.com"
        ><img :src="`${siteImagePrefix}/twitter.png`" alt="twitter icon" width="30"
      /></a>
    </section>
  </footer>
</template>
