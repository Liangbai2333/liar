<template>
  <div class="articles-manage">
    <!-- 顶部操作栏 -->
    <div class="action-bar">
      <div class="left">
        <button class="primary-btn" @click="createArticle">
          <span class="icon">✏️</span>
          写文章
        </button>
      </div>
      <div class="right">
        <div class="search-box">
          <input 
            type="text" 
            v-model="searchQuery" 
            placeholder="搜索文章..."
            @input="handleSearch"
          >
        </div>
      </div>
    </div>

    <!-- 筛选栏 -->
    <div class="filter-bar">
      <div class="filter-item">
        <select v-model="filters.category">
          <option value="">全部分类</option>
          <option v-for="category in categories" :key="category.id" :value="category.id">
            {{ category.name }}
          </option>
        </select>
      </div>
      <div class="filter-item">
        <select v-model="filters.status">
          <option value="">全部状态</option>
          <option value="published">已发布</option>
          <option value="draft">草稿</option>
          <option value="private">私密</option>
        </select>
      </div>
      <div class="filter-item">
        <select v-model="filters.sort">
          <option value="createTime">创建时间</option>
          <option value="updateTime">更新时间</option>
          <option value="views">浏览量</option>
        </select>
      </div>
      <div class="filter-item">
        <select v-model="filters.order">
          <option value="desc">降序</option>
          <option value="asc">升序</option>
        </select>
      </div>
    </div>

    <!-- 文章列表 -->
    <div class="articles-table">
      <table>
        <thead>
          <tr>
            <th style="width: 60px">
              <input type="checkbox" v-model="selectAll" @change="handleSelectAll">
            </th>
            <th>标题</th>
            <th style="width: 100px">分类</th>
            <th style="width: 100px">状态</th>
            <th style="width: 100px">浏览</th>
            <th style="width: 180px">创建时间</th>
            <th style="width: 150px">操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="article in articles" :key="article.id" :class="{ selected: selectedArticles.includes(article.id) }">
            <td>
              <input 
                type="checkbox" 
                :value="article.id" 
                v-model="selectedArticles"
              >
            </td>
            <td>
              <div class="article-title-cell">
                <img v-if="article.cover" :src="article.cover" :alt="article.title">
                <span class="title">{{ article.title }}</span>
              </div>
            </td>
            <td>{{ article.category?.name }}</td>
            <td>
              <span class="status-tag" :class="article.status">
                {{ getStatusText(article.status) }}
              </span>
            </td>
            <td>{{ article.views }}</td>
            <td>{{ formatDate(article.createTime) }}</td>
            <td>
              <div class="actions">
                <button class="action-btn edit" @click="editArticle(article)">编辑</button>
                <button class="action-btn view" @click="viewArticle(article)">查看</button>
                <button class="action-btn delete" @click="deleteArticle(article)">删除</button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- 底部操作栏 -->
    <div class="bottom-bar">
      <div class="batch-actions" v-if="selectedArticles.length > 0">
        <span class="selected-count">已选择 {{ selectedArticles.length }} 项</span>
        <button class="action-btn delete" @click="batchDelete">批量删除</button>
      </div>
      <div class="pagination">
        <button 
          :disabled="currentPage === 1" 
          @click="currentPage--"
        >上一页</button>
        <span class="page-info">{{ currentPage }} / {{ totalPages }}</span>
        <button 
          :disabled="currentPage === totalPages" 
          @click="currentPage++"
        >下一页</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

// 搜索和筛选
const searchQuery = ref('')
const filters = ref({
  category: '',
  status: '',
  sort: 'createTime',
  order: 'desc'
})

// 分页
const currentPage = ref(1)
const pageSize = ref(10)
const total = ref(0)
const totalPages = computed(() => Math.ceil(total.value / pageSize.value))

// 分类列表
const categories = ref([
  { id: 1, name: '技术' },
  { id: 2, name: '生活' },
  { id: 3, name: '随想' }
])

// 文章列表
const articles = ref([
  {
    id: 1,
    title: '使用 Vue 3 和 Vite 构建现代化博客系统',
    cover: 'https://picsum.photos/50/50',
    category: { id: 1, name: '技术' },
    status: 'published',
    views: 1234,
    createTime: '2024-03-15T08:00:00.000Z'
  },
  {
    id: 2,
    title: 'Spring Boot 实践指南',
    cover: 'https://picsum.photos/50/50',
    category: { id: 1, name: '技术' },
    status: 'draft',
    views: 0,
    createTime: '2024-03-14T10:30:00.000Z'
  }
])

// 选择功能
const selectedArticles = ref([])
const selectAll = ref(false)

const handleSelectAll = () => {
  if (selectAll.value) {
    selectedArticles.value = articles.value.map(article => article.id)
  } else {
    selectedArticles.value = []
  }
}

// 状态文本
const getStatusText = (status) => {
  const statusMap = {
    published: '已发布',
    draft: '草稿',
    private: '私密'
  }
  return statusMap[status] || status
}

// 格式化日期
const formatDate = (date) => {
  if (!date) return ''
  return new Date(date).toLocaleString('zh-CN', {
    year: 'numeric',
    month: '2-digit',
    day: '2-digit',
    hour: '2-digit',
    minute: '2-digit'
  })
}

// 搜索处理
const handleSearch = () => {
  // 实现搜索逻辑
  currentPage.value = 1
  fetchArticles()
}

// 获取文章列表
const fetchArticles = async () => {
  // 实现获取文章列表的逻辑
}

// 文章操作
const createArticle = () => {
  router.push('/admin/articles/create')
}

const editArticle = (article) => {
  router.push(`/admin/articles/edit/${article.id}`)
}

const viewArticle = (article) => {
  window.open(`/articles/${article.id}`, '_blank')
}

const deleteArticle = async (article) => {
  if (confirm(`确定要删除文章"${article.title}"吗？`)) {
    // 实现删除逻辑
  }
}

const batchDelete = async () => {
  if (confirm(`确定要删除选中的 ${selectedArticles.value.length} 篇文章吗？`)) {
    // 实现批量删除逻辑
  }
}

onMounted(() => {
  fetchArticles()
})
</script>

<style scoped>
.articles-manage {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.action-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.primary-btn {
  display: flex;
  align-items: center;
  gap: 8px;
  padding: 8px 16px;
  background-color: var(--primary-color);
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: all 0.3s ease;
}

.primary-btn:hover {
  opacity: 0.9;
}

.search-box input {
  padding: 8px 12px;
  border: 1px solid var(--border-color);
  border-radius: 4px;
  width: 240px;
  font-size: 14px;
  color: var(--text-primary);
  background-color: var(--card-bg);
}

.filter-bar {
  display: flex;
  gap: 12px;
}

.filter-item select {
  padding: 6px 12px;
  border: 1px solid var(--border-color);
  border-radius: 4px;
  background-color: var(--card-bg);
  color: var(--text-primary);
  font-size: 14px;
}

.articles-table {
  background-color: var(--card-bg);
  border: 1px solid var(--border-color);
  border-radius: 8px;
  overflow: hidden;
}

table {
  width: 100%;
  border-collapse: collapse;
}

th, td {
  padding: 12px;
  text-align: left;
  border-bottom: 1px solid var(--border-color);
}

th {
  background-color: var(--bg-secondary);
  color: var(--text-secondary);
  font-weight: normal;
  font-size: 14px;
}

.article-title-cell {
  display: flex;
  align-items: center;
  gap: 12px;
}

.article-title-cell img {
  width: 40px;
  height: 40px;
  border-radius: 4px;
  object-fit: cover;
}

.article-title-cell .title {
  color: var(--text-primary);
}

.status-tag {
  padding: 4px 8px;
  border-radius: 4px;
  font-size: 12px;
}

.status-tag.published {
  background-color: rgba(103, 194, 58, 0.1);
  color: #67c23a;
}

.status-tag.draft {
  background-color: rgba(144, 147, 153, 0.1);
  color: #909399;
}

.status-tag.private {
  background-color: rgba(230, 162, 60, 0.1);
  color: #e6a23c;
}

.actions {
  display: flex;
  gap: 8px;
}

.action-btn {
  padding: 4px 8px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 12px;
  transition: all 0.3s ease;
}

.action-btn.edit {
  background-color: var(--hover-bg);
  color: var(--primary-color);
}

.action-btn.view {
  background-color: rgba(144, 147, 153, 0.1);
  color: #909399;
}

.action-btn.delete {
  background-color: rgba(245, 108, 108, 0.1);
  color: #f56c6c;
}

.action-btn:hover {
  opacity: 0.8;
}

.bottom-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 12px 0;
}

.batch-actions {
  display: flex;
  align-items: center;
  gap: 12px;
}

.selected-count {
  color: var(--text-secondary);
  font-size: 14px;
}

.pagination {
  display: flex;
  align-items: center;
  gap: 12px;
}

.pagination button {
  padding: 6px 12px;
  border: 1px solid var(--border-color);
  border-radius: 4px;
  background-color: var(--card-bg);
  color: var(--text-primary);
  cursor: pointer;
}

.pagination button:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}

.page-info {
  color: var(--text-secondary);
}

tr.selected {
  background-color: var(--hover-bg);
}

@media (max-width: 768px) {
  .action-bar {
    flex-direction: column;
    align-items: stretch;
    gap: 12px;
  }

  .search-box input {
    width: 100%;
  }

  .filter-bar {
    flex-wrap: wrap;
  }

  .filter-item {
    flex: 1;
    min-width: 150px;
  }

  .articles-table {
    overflow-x: auto;
  }

  .bottom-bar {
    flex-direction: column;
    gap: 12px;
  }
}
</style> 