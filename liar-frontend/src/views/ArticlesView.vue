<template>
  <div class="articles">
    <header class="page-header">
      <div class="header-content">
        <h1 class="page-title">文章列表</h1>
        <div class="search-bar">
          <input 
            type="text" 
            v-model="searchQuery" 
            placeholder="搜索文章..."
            class="search-input"
          >
          <select v-model="selectedCategory" class="category-select">
            <option value="">全部分类</option>
            <option value="tech">技术</option>
            <option value="life">生活</option>
            <option value="thoughts">随想</option>
          </select>
        </div>
      </div>
    </header>

    <main class="content">
      <div class="articles-list">
        <article v-for="article in filteredArticles" :key="article.id" class="article-item">
          <div class="article-content">
            <div class="article-header">
              <h2 class="article-title">
                <router-link :to="'/articles/' + article.id">{{ article.title }}</router-link>
              </h2>
              <div class="article-meta">
                <span class="category">{{ article.category }}</span>
                <span class="date">{{ article.date }}</span>
                <span class="views">{{ article.views }} 阅读</span>
              </div>
            </div>
            <p class="article-summary">{{ article.summary }}</p>
            <div class="article-tags">
              <span v-for="tag in article.tags" :key="tag" class="tag">{{ tag }}</span>
            </div>
          </div>
          <div class="article-cover" v-if="article.cover">
            <img :src="article.cover" :alt="article.title">
          </div>
        </article>
      </div>

      <div class="pagination">
        <button 
          :disabled="currentPage === 1" 
          @click="currentPage--"
          class="page-btn"
        >
          上一页
        </button>
        <span class="page-info">{{ currentPage }} / {{ totalPages }}</span>
        <button 
          :disabled="currentPage === totalPages" 
          @click="currentPage++"
          class="page-btn"
        >
          下一页
        </button>
      </div>
    </main>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'

// 模拟文章数据
const articles = ref([
  {
    id: 1,
    title: '深入理解Vue3组合式API',
    category: '技术',
    date: '2024-03-15',
    views: 1250,
    summary: 'Vue3的组合式API（Composition API）是一个革命性的特性，它为我们提供了更好的代码组织方式和逻辑复用能力...',
    tags: ['Vue3', 'JavaScript', '前端开发'],
    cover: 'https://picsum.photos/200/150'
  },
  {
    id: 2,
    title: 'Spring Boot 实践指南',
    category: '技术',
    date: '2024-03-14',
    views: 980,
    summary: '本文将介绍Spring Boot的核心特性和最佳实践，帮助你更好地使用这个强大的框架...',
    tags: ['Java', 'Spring Boot', '后端开发'],
    cover: 'https://picsum.photos/200/150'
  },
  // 添加更多文章...
])

const searchQuery = ref('')
const selectedCategory = ref('')
const currentPage = ref(1)
const pageSize = 10

const filteredArticles = computed(() => {
  let result = articles.value

  if (searchQuery.value) {
    result = result.filter(article => 
      article.title.toLowerCase().includes(searchQuery.value.toLowerCase()) ||
      article.summary.toLowerCase().includes(searchQuery.value.toLowerCase())
    )
  }

  if (selectedCategory.value) {
    result = result.filter(article => 
      article.category === selectedCategory.value
    )
  }

  return result
})

const totalPages = computed(() => 
  Math.ceil(filteredArticles.value.length / pageSize)
)
</script>

<style scoped>
.articles {
  min-height: 100vh;
}

.page-header {
  background: linear-gradient(to right, var(--bg-primary), var(--bg-secondary));
  padding: 2rem;
  margin-bottom: 2rem;
}

.header-content {
  max-width: 800px;
  margin: 0 auto;
}

.page-title {
  font-size: 2rem;
  color: var(--text-primary);
  margin-bottom: 1.5rem;
}

.search-bar {
  display: flex;
  gap: 1rem;
}

.search-input,
.category-select {
  padding: 0.5rem 1rem;
  border: 1px solid var(--border-color);
  border-radius: 4px;
  background: var(--card-bg);
  color: var(--text-primary);
  font-size: 1rem;
}

.search-input {
  flex: 1;
}

.category-select {
  width: 120px;
}

.content {
  max-width: 800px;
  margin: 0 auto;
  padding: 0 1rem;
}

.articles-list {
  display: flex;
  flex-direction: column;
  gap: 1.5rem;
}

.article-item {
  display: flex;
  gap: 2rem;
  padding: 1.5rem;
  background: var(--card-bg);
  border-radius: 8px;
  border: 1px solid var(--border-color);
  transition: all 0.3s ease;
}

.article-item:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.article-content {
  flex: 1;
}

.article-header {
  margin-bottom: 1rem;
}

.article-title {
  font-size: 1.5rem;
  margin-bottom: 0.5rem;
}

.article-title a {
  color: var(--text-primary);
  text-decoration: none;
  transition: color 0.3s ease;
}

.article-title a:hover {
  color: var(--primary-color);
}

.article-meta {
  display: flex;
  gap: 1rem;
  color: var(--text-secondary);
  font-size: 0.9rem;
}

.article-summary {
  color: var(--text-secondary);
  margin-bottom: 1rem;
  line-height: 1.6;
}

.article-tags {
  display: flex;
  gap: 0.5rem;
  flex-wrap: wrap;
}

.tag {
  padding: 0.25rem 0.75rem;
  background: var(--hover-bg);
  color: var(--primary-color);
  border-radius: 16px;
  font-size: 0.85rem;
}

.article-cover {
  width: 200px;
  flex-shrink: 0;
}

.article-cover img {
  width: 100%;
  height: 150px;
  object-fit: cover;
  border-radius: 4px;
}

.pagination {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 1rem;
  margin: 2rem 0;
}

.page-btn {
  padding: 0.5rem 1rem;
  border: 1px solid var(--border-color);
  border-radius: 4px;
  background: var(--card-bg);
  color: var(--text-primary);
  cursor: pointer;
  transition: all 0.3s ease;
}

.page-btn:hover:not(:disabled) {
  background: var(--hover-bg);
  color: var(--primary-color);
}

.page-btn:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}

.page-info {
  color: var(--text-secondary);
}

@media (max-width: 768px) {
  .page-header {
    padding: 1.5rem 1rem;
  }

  .search-bar {
    flex-direction: column;
  }

  .category-select {
    width: 100%;
  }

  .article-item {
    flex-direction: column;
    gap: 1rem;
    padding: 1rem;
  }

  .article-cover {
    width: 100%;
    order: -1;
  }

  .article-cover img {
    height: 200px;
  }

  .article-title {
    font-size: 1.25rem;
  }
}
</style> 