<template>
  <header class="navbar">
    <div class="logo">
      <h1>靓白的个人博客</h1>
    </div>
    <nav class="nav-menu">
      <router-link to="/" class="nav-item">首页</router-link>
      <router-link to="/articles" class="nav-item">文章</router-link>
      <router-link to="/categories" class="nav-item">分类</router-link>
      <router-link to="/about" class="nav-item">关于</router-link>
    </nav>
    <button class="theme-switch" @click="toggleTheme">
      <span class="icon">{{ isDark ? '🌞' : '🌙' }}</span>
    </button>
  </header>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const isDark = ref(false)

const toggleTheme = () => {
  isDark.value = !isDark.value
  document.documentElement.classList.toggle('dark-mode')
}

onMounted(() => {
  const prefersDark = window.matchMedia('(prefers-color-scheme: dark)').matches
  isDark.value = prefersDark
  if (prefersDark) {
    document.documentElement.classList.add('dark-mode')
  }

  window.matchMedia('(prefers-color-scheme: dark)').addEventListener('change', (e) => {
    isDark.value = e.matches
    document.documentElement.classList.toggle('dark-mode', e.matches)
  })
})
</script>

<style scoped>
.navbar {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 20px;
  height: 60px;
  position: fixed;
  width: 100%;
  top: 0;
  z-index: 1000;
  background-color: var(--card-bg);
  border-bottom: 1px solid var(--border-color);
}

.logo h1 {
  margin: 0;
  font-size: 24px;
  color: var(--primary-color);
}

.nav-menu {
  display: flex;
  gap: 20px;
  margin-right: 125px;
}

.nav-item {
  color: var(--text-primary);
  text-decoration: none;
  font-size: 16px;
  padding: 8px 12px;
  border-radius: 4px;
  transition: all 0.3s ease;
}

.nav-item:hover,
.nav-item.router-link-active {
  color: var(--primary-color);
  background-color: var(--hover-bg);
}

.theme-switch {
  background: none;
  border: none;
  cursor: pointer;
  padding: 8px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: all 0.3s ease;
}

.theme-switch:hover {
  background-color: var(--hover-bg);
}

.icon {
  font-size: 20px;
}

@media (max-width: 768px) {
  .navbar {
    padding: 0 10px;
  }

  .logo h1 {
    font-size: 20px;
  }

  .nav-menu {
    gap: 10px;
  }

  .nav-item {
    padding: 6px 8px;
    font-size: 14px;
  }
}
</style> 