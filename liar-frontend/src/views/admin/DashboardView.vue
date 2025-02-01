<template>
  <div class="dashboard">
    <!-- 顶部导航栏 -->
    <div class="top-nav">
      <div class="nav-left">
        <h1 class="site-title">博客管理系统</h1>
      </div>
      <div class="nav-right">
        <div class="user-menu" @click="showDropdown = !showDropdown" v-click-outside="closeDropdown">
          <span class="user-avatar">👤</span>
          <span class="user-name">{{ authStore.username }}</span>
          <span class="dropdown-arrow">▼</span>
          <div class="dropdown-menu" v-show="showDropdown">
            <button class="dropdown-item" @click="handleLogout">
              <span class="icon">🚪</span>
              退出登录
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- 原有的仪表盘内容 -->
    <div class="dashboard-main">
      <!-- 统计卡片 -->
      <div class="stat-cards">
        <div class="stat-card">
          <h3>文章总数</h3>
          <p class="stat-number">{{ stats.articleCount }}</p>
        </div>
        <div class="stat-card">
          <h3>总浏览量</h3>
          <p class="stat-number">{{ stats.viewCount }}</p>
        </div>
        <div class="stat-card">
          <h3>标签总数</h3>
          <p class="stat-number">{{ stats.tagCount }}</p>
        </div>
      </div>

      <div class="dashboard-content">
        <!-- 系统信息 -->
        <div class="system-info card">
          <h2 class="card-title">系统信息</h2>
          <div class="info-grid">
            <!-- CPU 使用率 -->
            <div class="info-item">
              <div class="info-label">CPU 使用率</div>
              <div class="progress-bar">
                <div class="progress" :style="{ width: systemInfo.cpuUsage + '%' }">
                  {{ systemInfo.cpuUsage }}%
                </div>
              </div>
            </div>

            <!-- 内存使用情况 -->
            <div class="info-item">
              <div class="info-label">内存使用</div>
              <div class="progress-bar">
                <div class="progress" :style="{ width: systemInfo.memoryUsagePercent + '%' }">
                  {{ systemInfo.memoryUsagePercent }}%
                </div>
              </div>
              <div class="memory-detail">
                {{ systemInfo.memoryUsed }}GB / {{ systemInfo.memoryTotal }}GB
              </div>
            </div>

            <!-- 系统基本信息 -->
            <div class="info-item">
              <div class="info-label">操作系统</div>
              <div class="info-value">{{ systemInfo.os }}</div>
            </div>

            <div class="info-item">
              <div class="info-label">系统架构</div>
              <div class="info-value">{{ systemInfo.arch }}</div>
            </div>

            <div class="info-item">
              <div class="info-label">运行时间</div>
              <div class="info-value">{{ systemInfo.uptime }}</div>
            </div>

            <div class="info-item">
              <div class="info-label">Java 版本</div>
              <div class="info-value">{{ systemInfo.javaVersion }}</div>
            </div>

            <div class="info-item">
              <div class="info-label">服务器时间</div>
              <div class="info-value">{{ systemInfo.serverTime }}</div>
            </div>
          </div>
        </div>

        <!-- 最近文章 -->
        <div class="recent-articles card">
          <h2 class="card-title">最近文章</h2>
          <div class="article-list">
            <div v-for="article in recentArticles" :key="article.id" class="article-item">
              <div class="article-info">
                <div class="article-title-cell">
                  <img v-if="article.cover" :src="article.cover" :alt="article.title">
                  <span class="title">{{ article.title }}</span>
                </div>
                <div class="article-meta">
                  <span>{{ article.category?.name }}</span>
                  <span class="status-tag" :class="article.status">
                    {{ getStatusText(article.status) }}
                  </span>
                  <span>浏览 {{ article.views }}</span>
                  <span>{{ formatDate(article.createTime) }}</span>
                </div>
              </div>
              <div class="article-actions">
                <button class="action-btn edit" @click="editArticle(article.id)">编辑</button>
                <button class="action-btn view" @click="viewArticle(article.id)">查看</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '../../stores/auth'
import { useArticleStore } from '../../stores/article'
import { getDashboard, getTotalView } from '../../api/admin/dashboard'
import { getArticleCount } from '../../api/article'
import { getTagCount } from '../../api/tag'

const router = useRouter()
const authStore = useAuthStore()
const articleStore = useArticleStore()
const showDropdown = ref(false)

// 统计数据
const stats = ref({
  articleCount: 0,
  viewCount: 0,
  tagCount: 0
})

// 系统信息
const systemInfo = ref({
  cpuUsage: 0,
  memoryUsed: 0,
  memoryTotal: 0,
  memoryUsagePercent: 0,
  os: '',
  arch: '',
  uptime: '',
  javaVersion: '',
  serverTime: ''
})

// 最近文章
const recentArticles = ref([])

// 定时更新系统信息
let timer = null

// 格式化内存大小
const formatMemorySize = (gb) => {
  return gb.toFixed(2)
}

// 格式化百分比
const formatPercent = (value) => {
  return value.toFixed(2)
}

// 格式化运行时间
const formatUptime = (milliseconds) => {
  const seconds = Math.floor(milliseconds / 1000)
  const days = Math.floor(seconds / (24 * 60 * 60))
  const hours = Math.floor((seconds % (24 * 60 * 60)) / (60 * 60))
  const minutes = Math.floor((seconds % (60 * 60)) / 60)
  return `${days}天 ${hours}小时 ${minutes}分钟`
}

// 获取系统信息
const fetchSystemInfo = async () => {
  try {
    const response = await getDashboard()
    if (response.code === 200) {
      const info = response.data
      systemInfo.value = {
        cpuUsage: formatPercent(info.cpuUsedPercent),
        memoryUsed: formatMemorySize(info.memoryUsed),
        memoryTotal: formatMemorySize(info.memoryTotal),
        memoryUsagePercent: formatPercent(info.memoryUsedPercent),
        os: info.os,
        arch: info.arch,
        uptime: formatUptime(info.runningTime),
        javaVersion: info.javaVersion,
        serverTime: new Date(info.serverTime).toLocaleString()
      }
    }
  } catch (error) {
    console.error('获取系统信息失败:', error)
  }
}

// 获取统计数据
const fetchStats = async () => {
  try {
    const [viewResponse, articleResponse, tagResponse] = await Promise.all([
      getTotalView(),
      getArticleCount(),
      getTagCount()
    ])
    
    stats.value = {
      viewCount: viewResponse.code === 200 ? viewResponse.data : 0,
      articleCount: articleResponse.code === 200 ? articleResponse.data : 0,
      tagCount: tagResponse.code === 200 ? tagResponse.data : 0
    }
  } catch (error) {
    console.error('获取统计数据失败:', error)
  }
}

// 获取最近文章
const fetchRecentArticles = async () => {
  try {
    await articleStore.fetchArticles({ page: 1, size: 3, sort: 'update_time', order: 'desc' })
    recentArticles.value = articleStore.articles
  } catch (error) {
    console.error('获取最近文章失败:', error)
  }
}

// 编辑文章
const editArticle = (id) => {
  router.push(`/admin/articles/edit/${id}`)
}

// 查看文章
const viewArticle = (id) => {
  router.push(`/article/${id}`)
}

// 点击外部关闭下拉菜单
const closeDropdown = () => {
  showDropdown.value = false
}

// 处理退出登录
const handleLogout = () => {
  authStore.logoutAction()
}

// 自定义指令：点击外部关闭
const vClickOutside = {
  mounted(el, binding) {
    el._clickOutside = (event) => {
      if (!(el === event.target || el.contains(event.target))) {
        binding.value()
      }
    }
    document.addEventListener('click', el._clickOutside)
  },
  unmounted(el) {
    document.removeEventListener('click', el._clickOutside)
  }
}

onMounted(async () => {
  // 初始化数据
  await Promise.all([
    fetchSystemInfo(),
    fetchStats(),
    fetchRecentArticles()
  ])

  // 定时更新系统信息
  timer = setInterval(fetchSystemInfo, 2000) // 每5秒更新一次
})

onUnmounted(() => {
  // 清除定时器
  if (timer) {
    clearInterval(timer)
  }
})

// 添加样式
const style = `
.progress {
  transition: width 0.5s ease-in-out;
}

.stat-number {
  transition: all 0.3s ease-in-out;
}

.memory-detail {
  transition: all 0.3s ease-in-out;
}

.info-value {
  transition: all 0.3s ease-in-out;
}
`

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
</script>

<style scoped>
.dashboard {
  min-height: 100vh;
  background-color: var(--bg-secondary);
}

.top-nav {
  background-color: var(--card-bg);
  padding: 0.75rem 1.5rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  position: sticky;
  top: 0;
  z-index: 100;
}

.site-title {
  font-size: 1.25rem;
  color: var(--text-primary);
  margin: 0;
}

.user-menu {
  position: relative;
  display: flex;
  align-items: center;
  gap: 0.5rem;
  padding: 0.5rem;
  cursor: pointer;
  border-radius: 6px;
  transition: background-color 0.2s;
}

.user-menu:hover {
  background-color: var(--bg-hover);
}

.user-avatar {
  font-size: 1.25rem;
}

.user-name {
  color: var(--text-primary);
  font-weight: 500;
}

.dropdown-arrow {
  font-size: 0.75rem;
  color: var(--text-secondary);
  transition: transform 0.2s;
}

.user-menu:hover .dropdown-arrow {
  transform: rotate(180deg);
}

.dropdown-menu {
  position: absolute;
  top: 100%;
  right: 0;
  margin-top: 0.5rem;
  background-color: var(--card-bg);
  border-radius: 6px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
  min-width: 160px;
  padding: 0.5rem;
}

.dropdown-item {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  padding: 0.5rem;
  width: 100%;
  border: none;
  background: none;
  color: var(--text-primary);
  cursor: pointer;
  border-radius: 4px;
  transition: background-color 0.2s;
}

.dropdown-item:hover {
  background-color: var(--bg-hover);
}

.icon {
  font-size: 1.1rem;
}

.dashboard-main {
  padding: 1.5rem;
}

.stat-cards {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 20px;
  margin-bottom: 20px;
}

.stat-card {
  background-color: var(--card-bg);
  border-radius: 8px;
  padding: 20px;
  text-align: center;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.stat-card h3 {
  color: var(--text-secondary);
  font-size: 14px;
  margin-bottom: 10px;
}

.stat-number {
  color: var(--text-primary);
  font-size: 24px;
  font-weight: bold;
}

.dashboard-content {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 20px;
}

.card {
  background-color: var(--card-bg);
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.card-title {
  color: var(--text-primary);
  font-size: 18px;
  margin-bottom: 20px;
  padding-bottom: 10px;
  border-bottom: 1px solid var(--border-color);
}

.info-grid {
  display: grid;
  gap: 16px;
}

.info-item {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.info-label {
  color: var(--text-secondary);
  font-size: 14px;
}

.info-value {
  color: var(--text-primary);
  font-size: 14px;
}

.progress-bar {
  width: 100%;
  height: 20px;
  background-color: var(--bg-secondary);
  border-radius: 10px;
  overflow: hidden;
}

.progress {
  height: 100%;
  background-color: var(--primary-color);
  color: white;
  text-align: right;
  padding: 0 8px;
  font-size: 12px;
  line-height: 20px;
  transition: width 0.5s ease-in-out;
}

.memory-detail {
  color: var(--text-secondary);
  font-size: 12px;
  text-align: right;
  transition: all 0.3s ease-in-out;
}

.article-list {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.article-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 12px;
  border-radius: 4px;
  background-color: var(--bg-secondary);
}

.article-info {
  flex: 1;
}

.article-title-cell {
  display: flex;
  align-items: center;
  gap: 8px;
}

.title {
  color: var(--text-primary);
  font-size: 16px;
  margin-bottom: 4px;
}

.article-meta {
  color: var(--text-secondary);
  font-size: 12px;
  display: flex;
  gap: 12px;
}

.article-actions {
  display: flex;
  gap: 8px;
}

.action-btn {
  padding: 4px 12px;
  border: none;
  border-radius: 4px;
  font-size: 12px;
  cursor: pointer;
  transition: all 0.3s ease;
}

.action-btn.edit {
  background-color: var(--primary-color);
  color: white;
}

.action-btn.view {
  background-color: var(--bg-primary);
  color: var(--text-primary);
}

.action-btn:hover {
  opacity: 0.9;
}

.article-title-cell img {
  width: 40px;
  height: 40px;
  border-radius: 4px;
  object-fit: cover;
}

.status-tag {
  padding: 2px 8px;
  border-radius: 12px;
  font-size: 12px;
}

.status-tag.published {
  background-color: var(--success-bg);
  color: var(--success-text);
}

.status-tag.draft {
  background-color: var(--warning-bg);
  color: var(--warning-text);
}

.status-tag.private {
  background-color: var(--info-bg);
  color: var(--info-text);
}

@media (max-width: 768px) {
  .stat-cards {
    grid-template-columns: repeat(2, 1fr);
  }

  .dashboard-content {
    grid-template-columns: 1fr;
  }
}

@media (max-width: 480px) {
  .stat-cards {
    grid-template-columns: 1fr;
  }

  .article-item {
    flex-direction: column;
    align-items: flex-start;
    gap: 12px;
  }

  .article-actions {
    width: 100%;
    justify-content: flex-end;
  }
}
</style> 