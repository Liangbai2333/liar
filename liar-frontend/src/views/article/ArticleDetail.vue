<template>
  <div class="article-detail">
    <main class="article-content">
      <!-- 左侧内容区 -->
      <div class="content-wrapper">
        <!-- 文章头部信息 -->
        <header class="article-header">
          <div class="meta">
            <div class="category-tag">{{ article.category?.name || '未分类' }}</div>
            <div class="date-views">
              <time>{{ formatDate(article.createTime) }}</time>
              <span class="dot">·</span>
              <span>{{ article.views || 0 }} 阅读</span>
            </div>
          </div>
          <h1 class="title">{{ article.title }}</h1>
          <div class="tags">
            <span v-for="tag in article.tags" :key="tag.id" class="tag">
              {{ tag.name }}
            </span>
          </div>
        </header>

        <!-- 文章封面图 -->
        <div v-if="article.cover" class="cover">
          <img :src="article.cover" :alt="article.title">
        </div>

        <!-- 文章内容 -->
        <article class="content markdown-body" v-html="article.content"></article>

        <!-- 文章底部 -->
        <footer class="article-footer">
          <div class="update-info">
            最后更新于 {{ formatDate(article.updateTime) }}
          </div>
          <div class="copyright">
            <p>© 本文为原创文章，遵循 CC BY-NC-SA 4.0 版权协议</p>
            <p>转载请附上原文出处链接及本声明。</p>
          </div>
        </footer>
      </div>

      <!-- 右侧边栏 -->
      <aside class="sidebar">
        <!-- 作者信息 -->
        <div class="author-card">
          <div class="author-info">
            <div class="author-avatar">
              <img src="@/assets/avatar.jpg" alt="作者头像">
            </div>
            <div class="author-meta">
              <h3 class="author-name">靓白</h3>
              <p class="author-bio">保持热爱，奔赴山海</p>
            </div>
          </div>
          <div class="author-stats">
            <div class="stat-item">
              <span class="stat-value">{{ totalArticles }}</span>
              <span class="stat-label">文章</span>
            </div>
            <div class="stat-item">
              <span class="stat-value">{{ totalViews }}</span>
              <span class="stat-label">阅读</span>
            </div>
          </div>
        </div>

        <!-- 文章目录 -->
        <nav class="toc-container" v-if="toc">
          <h3 class="toc-title">目录</h3>
          <div class="toc" v-html="toc"></div>
        </nav>
      </aside>
    </main>

    <!-- 相关文章推荐 -->
    <section class="related-posts" v-if="relatedPosts.length > 0">
      <h2 class="section-title">相关推荐</h2>
      <div class="posts-grid">
        <router-link 
          v-for="post in relatedPosts" 
          :key="post.id" 
          :to="'/articles/' + post.id"
          class="related-post-card"
        >
          <div class="post-cover" v-if="post.cover">
            <img :src="post.cover" :alt="post.title">
          </div>
          <div class="post-info">
            <div class="post-meta">
              <span class="post-category">{{ post.category?.name }}</span>
              <time class="post-date">{{ formatDate(post.createTime) }}</time>
            </div>
            <h3 class="post-title">{{ post.title }}</h3>
          </div>
        </router-link>
      </div>
    </section>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { useArticleStore } from '@/stores/article'
import MarkdownIt from 'markdown-it'
import hljs from 'highlight.js'
import 'highlight.js/styles/github-dark.css'
import '@/assets/markdown.css'

// 配置 markdown-it
const md = new MarkdownIt({
  html: true,
  linkify: true,
  highlight: function (str, lang) {
    if (lang && hljs.getLanguage(lang)) {
      try {
        const highlighted = hljs.highlight(str, { language: lang, ignoreIllegals: true }).value;
        return `<pre data-lang="${lang}"><code>${highlighted}</code></pre>`;
      } catch (__) {}
    }
    return `<pre><code>${md.utils.escapeHtml(str)}</code></pre>`;
  }
});

const route = useRoute()
const articleStore = useArticleStore()
const article = ref({})
const toc = ref('')
const relatedPosts = ref([])
const totalArticles = ref(100)
const totalViews = ref(5678)

// 格式化日期
const formatDate = (date) => {
  if (!date) return ''
  return new Date(date).toLocaleDateString('zh-CN', {
    year: 'numeric',
    month: 'long',
    day: 'numeric'
  })
}

// 解析 Markdown 内容
const parseContent = (content) => {
  if (!content) return ''
  return md.render(content)
}

// 获取文章详情
const fetchArticle = async () => {
  try {
    const data = await articleStore.fetchArticle(route.params.id)
    article.value = {
      ...data,
      content: parseContent(data.content)
    }
    // 获取相关文章
    fetchRelatedPosts()
  } catch (error) {
    console.error('获取文章详情失败:', error)
  }
}

// 获取相关文章
const fetchRelatedPosts = async () => {
  try {
    const data = await articleStore.fetchRelatedArticles()
    relatedPosts.value = data
  } catch (error) {
    console.error('获取相关文章失败:', error)
  }
}

onMounted(() => {
  fetchArticle()
})
</script>

<style scoped>
.article-detail {
  min-height: 100vh;
  background-color: var(--bg-primary);
  padding: 2rem 0;
}

.article-content {
  display: flex;
  gap: 3rem;
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 2rem;
}

.content-wrapper {
  flex: 1;
  max-width: 800px;
}

.article-header {
  margin-bottom: 2rem;
}

.meta {
  display: flex;
  align-items: center;
  gap: 1rem;
  margin-bottom: 1.5rem;
}

.category-tag {
  padding: 0.4rem 1rem;
  background-color: var(--hover-bg);
  color: var(--primary-color);
  border-radius: 20px;
  font-size: 0.9rem;
}

.date-views {
  color: var(--text-secondary);
  font-size: 0.9rem;
  display: flex;
  align-items: center;
  gap: 0.5rem;
}

.dot {
  color: var(--text-secondary);
}

.title {
  font-size: 2.5rem;
  font-weight: 700;
  color: var(--text-primary);
  line-height: 1.3;
  margin-bottom: 1.5rem;
  letter-spacing: -0.5px;
}

.tags {
  display: flex;
  gap: 0.5rem;
  flex-wrap: wrap;
}

.tag {
  padding: 0.3rem 0.8rem;
  background-color: var(--bg-secondary);
  color: var(--text-secondary);
  border-radius: 6px;
  font-size: 0.85rem;
}

.cover {
  margin: 2rem 0;
  border-radius: 12px;
  overflow: hidden;
}

.cover img {
  width: 100%;
  height: auto;
  display: block;
}

.content {
  color: var(--text-primary);
  line-height: 1.8;
  font-size: 1.1rem;
}

.article-footer {
  margin-top: 4rem;
  padding-top: 2rem;
  border-top: 1px solid var(--border-color);
  color: var(--text-secondary);
  font-size: 0.9rem;
}

.update-info {
  margin-bottom: 1rem;
}

.copyright {
  padding: 1rem;
  background-color: var(--bg-secondary);
  border-radius: 8px;
  line-height: 1.6;
}

.sidebar {
  width: 300px;
  position: sticky;
  top: 80px;
  height: fit-content;
  display: flex;
  flex-direction: column;
  gap: 2rem;
}

.author-card {
  padding: 1.5rem;
  background-color: var(--card-bg);
  border-radius: 12px;
  border: 1px solid var(--border-color);
}

.author-info {
  display: flex;
  align-items: center;
  gap: 1rem;
  margin-bottom: 1.5rem;
}

.author-avatar {
  width: 60px;
  height: 60px;
  border-radius: 50%;
  overflow: hidden;
}

.author-avatar img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.author-meta {
  flex: 1;
}

.author-name {
  font-size: 1.2rem;
  color: var(--text-primary);
  margin-bottom: 0.3rem;
}

.author-bio {
  color: var(--text-secondary);
  font-size: 0.9rem;
}

.author-stats {
  display: flex;
  justify-content: space-around;
  padding-top: 1rem;
  border-top: 1px solid var(--border-color);
}

.stat-item {
  text-align: center;
}

.stat-value {
  display: block;
  font-size: 1.2rem;
  font-weight: 600;
  color: var(--text-primary);
}

.stat-label {
  font-size: 0.85rem;
  color: var(--text-secondary);
}

.toc-container {
  padding: 1.5rem;
  background-color: var(--card-bg);
  border-radius: 12px;
  border: 1px solid var(--border-color);
}

.toc-title {
  font-size: 1.1rem;
  color: var(--text-primary);
  margin-bottom: 1rem;
}

.toc {
  font-size: 0.9rem;
  color: var(--text-secondary);
}

.toc a {
  display: block;
  padding: 0.4rem 0;
  color: inherit;
  text-decoration: none;
  transition: color 0.2s ease;
  border-left: 2px solid transparent;
  padding-left: 1rem;
}

.toc a:hover {
  color: var(--primary-color);
  border-left-color: var(--primary-color);
}

.related-posts {
  max-width: 1200px;
  margin: 4rem auto 0;
  padding: 0 2rem;
}

.section-title {
  font-size: 1.5rem;
  color: var(--text-primary);
  margin-bottom: 2rem;
}

.posts-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 2rem;
}

.related-post-card {
  display: block;
  text-decoration: none;
  background-color: var(--card-bg);
  border-radius: 12px;
  overflow: hidden;
  border: 1px solid var(--border-color);
  transition: transform 0.2s ease;
}

.related-post-card:hover {
  transform: translateY(-4px);
}

.post-cover {
  aspect-ratio: 16/9;
  overflow: hidden;
}

.post-cover img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.post-info {
  padding: 1.2rem;
}

.post-meta {
  display: flex;
  justify-content: space-between;
  margin-bottom: 0.8rem;
  font-size: 0.85rem;
}

.post-category {
  color: var(--primary-color);
}

.post-date {
  color: var(--text-secondary);
}

.post-title {
  font-size: 1.1rem;
  color: var(--text-primary);
  line-height: 1.4;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}

@media (max-width: 1024px) {
  .article-content {
    flex-direction: column;
  }

  .sidebar {
    width: 100%;
    position: static;
    margin-top: 2rem;
  }

  .posts-grid {
    grid-template-columns: repeat(2, 1fr);
  }
}

@media (max-width: 768px) {
  .article-detail {
    padding: 1rem 0;
  }

  .article-content {
    padding: 0 1rem;
  }

  .title {
    font-size: 2rem;
  }

  .meta {
    flex-direction: column;
    align-items: flex-start;
  }

  .posts-grid {
    grid-template-columns: 1fr;
  }

  .related-posts {
    padding: 0 1rem;
  }
}
</style> 