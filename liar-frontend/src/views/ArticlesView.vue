<template>
  <div class="articles">
    <header class="page-header">
      <div class="header-content">
        <h1 class="page-title">文章列表</h1>
        <p class="page-subtitle" v-if="articleStore.total > 0">
          共 {{ articleStore.total }} 篇文章
        </p>
        <div class="search-bar">
          <input 
            type="text" 
            v-model="searchQuery" 
            placeholder="搜索文章..."
            class="search-input"
            @input="handleSearch"
          >
          <select 
            v-model="selectedCategory" 
            class="category-select"
            @change="handleCategoryChange"
          >
            <option value="">全部分类</option>
            <option 
              v-for="category in categoryStore.categories" 
              :key="category.id" 
              :value="category.id"
            >
              {{ category.name }}
            </option>
          </select>
        </div>
      </div>
    </header>

    <main class="content">
      <div v-if="articleStore.loading" class="loading">
        加载中...
      </div>
      <div v-else-if="articleStore.articles.length === 0" class="no-data">
        暂无文章
      </div>
      <div v-else class="articles-list">
        <article v-for="article in articleStore.articles" :key="article.id" class="article-item">
          <div class="article-content">
            <div class="article-header">
              <h2 class="article-title">
                <router-link :to="'/article/' + article.id">{{ article.title }}</router-link>
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

      <div class="pagination" v-if="articleStore.articles.length > 0">
        <button 
          :disabled="currentPage === 1" 
          @click="handlePageChange(currentPage - 1)"
          class="page-btn"
        >
          上一页
        </button>
        <div class="page-numbers">
          <button
            v-for="page in totalPages"
            :key="page"
            :class="['page-number', { active: currentPage === page }]"
            @click="handlePageChange(page)"
          >
            {{ page }}
          </button>
        </div>
        <button 
          :disabled="currentPage === totalPages" 
          @click="handlePageChange(currentPage + 1)"
          class="page-btn"
        >
          下一页
        </button>
      </div>
    </main>
  </div>
</template>

<script setup>
import { ref, computed, onMounted, watch } from 'vue'
import { useArticleStore } from '../stores/article'
import { useCategoryStore } from '../stores/category'
import { useRoute } from 'vue-router'

const route = useRoute()
const articleStore = useArticleStore()
const categoryStore = useCategoryStore()

// 搜索和筛选状态
const searchQuery = ref('')
const selectedCategory = ref('')
const currentPage = ref(1)
const pageSize = 10

// 计算总页数
const totalPages = computed(() => {
  if (!articleStore.total) return 1
  return Math.max(1, Math.ceil(articleStore.total / pageSize))
})

// 加载文章列表
const loadArticles = async () => {
  await articleStore.fetchArticles({
    page: currentPage.value,
    size: pageSize,
    categoryId: selectedCategory.value || undefined,
    keyword: searchQuery.value || undefined
  })
}

// 处理搜索
const handleSearch = () => {
  currentPage.value = 1
  loadArticles()
}

// 处理分类变化
const handleCategoryChange = () => {
  currentPage.value = 1
  loadArticles()
}

// 处理页码变化
const handlePageChange = (page) => {
  if (page < 1 || page > totalPages.value) return
  currentPage.value = page
  loadArticles()
  window.scrollTo({ top: 0, behavior: 'smooth' })
}

// 监听搜索和分类变化
watch([searchQuery, selectedCategory], () => {
  handleSearch()
}, { debounce: 300 })

// 组件挂载时加载数据
onMounted(async () => {
  // 从路由参数中获取分类
  if (route.query.category) {
    selectedCategory.value = route.query.category
  }
  
  await Promise.all([
    categoryStore.fetchCategories(),
    articleStore.fetchTotal(),
    loadArticles()
  ])
})

// 监听路由变化
watch(() => route.query.category, (newCategory) => {
  if (newCategory !== selectedCategory.value) {
    selectedCategory.value = newCategory || ''
    currentPage.value = 1
    loadArticles()
  }
})
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

.page-subtitle {
  color: var(--text-secondary);
  margin-bottom: 1.5rem;
  font-size: 1rem;
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

.page-numbers {
  display: flex;
  gap: 0.5rem;
}

.page-number {
  padding: 0.5rem 1rem;
  border: 1px solid var(--border-color);
  border-radius: 4px;
  background: var(--card-bg);
  color: var(--text-primary);
  cursor: pointer;
  transition: all 0.3s ease;
}

.page-number:hover {
  background: var(--hover-bg);
  color: var(--primary-color);
}

.page-number.active {
  background: var(--primary-color);
  color: white;
  border-color: var(--primary-color);
}

.loading, .no-data {
  text-align: center;
  padding: 2rem;
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

  .pagination {
    flex-wrap: wrap;
  }
  
  .page-numbers {
    flex-wrap: wrap;
    justify-content: center;
  }
}
</style> 