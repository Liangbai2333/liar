<template>
  <div class="categories">
    <header class="page-header">
      <div class="header-content">
        <h1 class="page-title">文章分类</h1>
        <p class="page-subtitle">探索不同主题的文章</p>
      </div>
    </header>

    <main class="content">
      <div class="categories-grid">
        <div v-for="category in categoryStore.categories" :key="category.id" class="category-card">
          <div class="category-header">
            <div class="category-icon">
              <img :src="category.icon" :alt="category.name" v-if="category.icon">
              <span class="default-icon" v-else>📚</span>
            </div>
            <div class="category-info">
              <h2 class="category-name">{{ category.name }}</h2>
              <p class="category-count">{{ category.count || 0 }} 篇文章</p>
            </div>
          </div>
          <p class="category-desc" v-if="category.description">{{ category.description }}</p>
          <router-link :to="{ path: '/articles', query: { category: category.id }}" class="view-more">
            查看文章
            <span class="arrow">→</span>
          </router-link>
        </div>
      </div>
    </main>
  </div>
</template>

<script setup>
import { onMounted } from 'vue'
import { useCategoryStore } from '../stores/category'
import { useTagStore } from '../stores/tag'

const categoryStore = useCategoryStore()
const tagStore = useTagStore()

// 组件挂载时加载数据
onMounted(async () => {
  await Promise.all([
    categoryStore.fetchCategories(),
    categoryStore.fetchTotal(),
    tagStore.fetchTags(),
    tagStore.fetchTotal()
  ])
})
</script>

<style scoped>
.categories {
  min-height: 100vh;
}

.page-header {
  background: linear-gradient(to right, var(--bg-primary), var(--bg-secondary));
  padding: 3rem 2rem;
  text-align: center;
  margin-bottom: 2rem;
}

.header-content {
  max-width: 800px;
  margin: 0 auto;
}

.page-title {
  font-size: 2.5rem;
  color: var(--text-primary);
  margin-bottom: 1rem;
}

.page-subtitle {
  font-size: 1.2rem;
  color: var(--text-secondary);
}

.content {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 2rem;
}

.categories-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
  gap: 2rem;
  margin-bottom: 4rem;
}

.category-card {
  background: var(--card-bg);
  border: 1px solid var(--border-color);
  border-radius: 12px;
  padding: 1.5rem;
  transition: all 0.3s ease;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  height: 100%;
}

.category-card:hover {
  transform: translateY(-4px);
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.12);
  border-color: var(--primary-color);
}

.category-header {
  display: flex;
  align-items: flex-start;
  gap: 1rem;
  margin-bottom: 1rem;
}

.category-icon {
  width: 48px;
  height: 48px;
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: var(--hover-bg);
  flex-shrink: 0;
  overflow: hidden;
}

.category-icon img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.default-icon {
  font-size: 1.5rem;
}

.category-info {
  flex: 1;
}

.category-name {
  font-size: 1.25rem;
  color: var(--text-primary);
  margin-bottom: 0.25rem;
  font-weight: 600;
}

.category-count {
  color: var(--primary-color);
  font-size: 0.875rem;
  font-weight: 500;
}

.category-desc {
  color: var(--text-secondary);
  font-size: 0.875rem;
  line-height: 1.6;
  margin-bottom: 1rem;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
  flex: 1;
}

.view-more {
  display: inline-flex;
  align-items: center;
  color: var(--primary-color);
  text-decoration: none;
  font-weight: 500;
  font-size: 0.875rem;
  padding: 0.5rem 0;
  transition: all 0.3s ease;
  border-top: 1px solid var(--border-color);
  width: 100%;
}

.arrow {
  margin-left: 0.5rem;
  transition: transform 0.3s ease;
}

.view-more:hover {
  color: var(--primary-color);
}

.view-more:hover .arrow {
  transform: translateX(4px);
}

@media (max-width: 768px) {
  .page-header {
    padding: 2rem 1rem;
  }

  .page-title {
    font-size: 2rem;
  }

  .content {
    padding: 0 1rem;
  }

  .category-card {
    padding: 1.5rem;
  }

  .category-icon {
    width: 50px;
    height: 50px;
  }

  .icon {
    font-size: 1.5rem;
  }

  .category-name {
    font-size: 1.25rem;
  }
}
</style> 