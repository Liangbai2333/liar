<template>
  <div class="app">
    <Navbar />
    <main class="main-container">
      <router-view />
    </main>
    <footer class="footer">
      <p>© 2024 Liangbai2333. All rights reserved.</p>
    </footer>
  </div>
</template>

<script setup>
import Navbar from './components/Navbar.vue'
</script>

<style scoped>
.app {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

.main-container {
  margin-top: 60px;
  padding: 20px;
  flex: 1;
  background-color: var(--bg-primary);
}

.footer {
  text-align: center;
  padding: 20px;
  color: var(--text-secondary);
  background-color: var(--card-bg);
  border-top: 1px solid var(--border-color);
}

@media (max-width: 768px) {
  .main-container {
    padding: 10px;
  }
}
</style>
