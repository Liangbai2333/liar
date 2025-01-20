<template>
  <div class="admin-layout">
    <!-- 侧边栏 -->
    <aside class="sidebar" :class="{ collapsed: isCollapsed }">
      <div class="logo">
        <h1 v-if="!isCollapsed">后台管理</h1>
        <span v-else>L</span>
      </div>
      <nav class="menu">
        <router-link to="/admin/dashboard" class="menu-item">
          <span class="icon">📊</span>
          <span class="text" v-show="!isCollapsed">仪表盘</span>
        </router-link>
        <router-link to="/admin/articles" class="menu-item">
          <span class="icon">📝</span>
          <span class="text" v-show="!isCollapsed">文章管理</span>
        </router-link>
        <router-link to="/admin/categories" class="menu-item">
          <span class="icon">📁</span>
          <span class="text" v-show="!isCollapsed">分类管理</span>
        </router-link>
        <router-link to="/admin/tags" class="menu-item">
          <span class="icon">🏷️</span>
          <span class="text" v-show="!isCollapsed">标签管理</span>
        </router-link>
        <router-link to="/admin/comments" class="menu-item">
          <span class="icon">💬</span>
          <span class="text" v-show="!isCollapsed">评论管理</span>
        </router-link>
        <router-link to="/admin/settings" class="menu-item">
          <span class="icon">⚙️</span>
          <span class="text" v-show="!isCollapsed">系统设置</span>
        </router-link>
      </nav>
      <div class="collapse-btn" @click="toggleCollapse">
        {{ isCollapsed ? '→' : '←' }}
      </div>
    </aside>

    <!-- 主要内容区 -->
    <div class="main-content">
      <!-- 顶部导航栏 -->
      <header class="header">
        <div class="header-left">
          <h2 class="page-title">{{ currentPageTitle }}</h2>
        </div>
        <div class="header-right">
          <div class="user-info">
            <img src="@/assets/avatar.jpg" alt="用户头像" class="avatar">
            <span class="username">靓白</span>
          </div>
        </div>
      </header>

      <!-- 内容区域 -->
      <main class="content">
        <router-view></router-view>
      </main>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRoute } from 'vue-router'

const isCollapsed = ref(false)
const route = useRoute()

const toggleCollapse = () => {
  isCollapsed.value = !isCollapsed.value
}

const currentPageTitle = computed(() => {
  const routeTitles = {
    '/admin/dashboard': '仪表盘',
    '/admin/articles': '文章管理',
    '/admin/categories': '分类管理',
    '/admin/tags': '标签管理',
    '/admin/comments': '评论管理',
    '/admin/settings': '系统设置'
  }
  return routeTitles[route.path] || '后台管理'
})
</script>

<style scoped>
.admin-layout {
  display: flex;
  min-height: 100vh;
}

.sidebar {
  width: 240px;
  background-color: var(--card-bg);
  border-right: 1px solid var(--border-color);
  transition: width 0.3s ease;
  display: flex;
  flex-direction: column;
}

.sidebar.collapsed {
  width: 64px;
}

.logo {
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-bottom: 1px solid var(--border-color);
  padding: 0 20px;
}

.logo h1 {
  margin: 0;
  font-size: 20px;
  color: var(--primary-color);
}

.menu {
  flex: 1;
  padding: 20px 0;
}

.menu-item {
  display: flex;
  align-items: center;
  padding: 12px 20px;
  color: var(--text-primary);
  text-decoration: none;
  transition: all 0.3s ease;
}

.menu-item:hover,
.menu-item.router-link-active {
  background-color: var(--hover-bg);
  color: var(--primary-color);
}

.menu-item .icon {
  width: 24px;
  text-align: center;
  margin-right: 12px;
}

.collapse-btn {
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  color: var(--text-secondary);
  border-top: 1px solid var(--border-color);
}

.collapse-btn:hover {
  background-color: var(--hover-bg);
  color: var(--primary-color);
}

.main-content {
  flex: 1;
  display: flex;
  flex-direction: column;
  background-color: var(--bg-primary);
}

.header {
  height: 60px;
  background-color: var(--card-bg);
  border-bottom: 1px solid var(--border-color);
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 20px;
}

.page-title {
  margin: 0;
  font-size: 18px;
  color: var(--text-primary);
}

.user-info {
  display: flex;
  align-items: center;
  gap: 12px;
}

.avatar {
  width: 32px;
  height: 32px;
  border-radius: 50%;
  object-fit: cover;
}

.username {
  color: var(--text-primary);
}

.content {
  flex: 1;
  padding: 20px;
  overflow-y: auto;
}

@media (max-width: 768px) {
  .sidebar {
    position: fixed;
    z-index: 1000;
    height: 100vh;
  }

  .main-content {
    margin-left: 64px;
  }

  .sidebar:not(.collapsed) {
    box-shadow: 2px 0 8px rgba(0, 0, 0, 0.1);
  }
}
</style> 