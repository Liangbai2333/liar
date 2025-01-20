<template>
  <div class="dashboard">
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
        <h3>评论总数</h3>
        <p class="stat-number">{{ stats.commentCount }}</p>
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
              <h3 class="article-title">{{ article.title }}</h3>
              <div class="article-meta">
                <span>{{ article.category }}</span>
                <span>{{ article.createTime }}</span>
                <span>浏览 {{ article.viewCount }}</span>
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
</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

// 统计数据
const stats = ref({
  articleCount: 0,
  viewCount: 0,
  commentCount: 0,
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
const formatMemorySize = (bytes) => {
  return (bytes / (1024 * 1024 * 1024)).toFixed(2)
}

// 格式化运行时间
const formatUptime = (seconds) => {
  const days = Math.floor(seconds / (24 * 60 * 60))
  const hours = Math.floor((seconds % (24 * 60 * 60)) / (60 * 60))
  const minutes = Math.floor((seconds % (60 * 60)) / 60)
  return `${days}天 ${hours}小时 ${minutes}分钟`
}

// 获取系统信息
const fetchSystemInfo = async () => {
  try {
    // TODO: 调用后端 API 获取系统信息
    // const response = await getSystemInfo()
    // systemInfo.value = response.data

    // 模拟数据
    systemInfo.value = {
      cpuUsage: Math.floor(Math.random() * 100),
      memoryUsed: 4.2,
      memoryTotal: 16,
      memoryUsagePercent: 26,
      os: 'Windows 10',
      arch: 'x64',
      uptime: '3天 14小时 22分钟',
      javaVersion: 'Java 17',
      serverTime: new Date().toLocaleString()
    }
  } catch (error) {
    console.error('获取系统信息失败:', error)
  }
}

// 获取统计数据
const fetchStats = async () => {
  try {
    // TODO: 调用后端 API 获取统计数据
    // const response = await getStats()
    // stats.value = response.data

    // 模拟数据
    stats.value = {
      articleCount: 42,
      viewCount: 1234,
      commentCount: 56,
      tagCount: 18
    }
  } catch (error) {
    console.error('获取统计数据失败:', error)
  }
}

// 获取最近文章
const fetchRecentArticles = async () => {
  try {
    // TODO: 调用后端 API 获取最近文章
    // const response = await getRecentArticles()
    // recentArticles.value = response.data

    // 模拟数据
    recentArticles.value = [
      {
        id: 1,
        title: '如何使用 Spring Boot 3.0',
        category: '技术',
        createTime: '2024-01-20 14:30',
        viewCount: 123
      },
      {
        id: 2,
        title: 'Vue 3 组合式 API 最佳实践',
        category: '前端',
        createTime: '2024-01-19 16:45',
        viewCount: 89
      },
      {
        id: 3,
        title: '深入理解 Java 虚拟机',
        category: '技术',
        createTime: '2024-01-18 10:20',
        viewCount: 256
      }
    ]
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
  router.push(`/articles/${id}`)
}

onMounted(async () => {
  // 初始化数据
  await Promise.all([
    fetchSystemInfo(),
    fetchStats(),
    fetchRecentArticles()
  ])

  // 定时更新系统信息
  timer = setInterval(fetchSystemInfo, 30000) // 每30秒更新一次
})

onUnmounted(() => {
  // 清除定时器
  if (timer) {
    clearInterval(timer)
  }
})
</script>

<style scoped>
.dashboard {
  padding: 20px;
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
  transition: width 0.3s ease;
}

.memory-detail {
  color: var(--text-secondary);
  font-size: 12px;
  text-align: right;
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

.article-title {
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