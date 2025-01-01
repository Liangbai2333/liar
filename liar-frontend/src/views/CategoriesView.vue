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
        <div v-for="category in categories" :key="category.name" class="category-card">
          <div class="category-icon" :style="{ backgroundColor: category.color + '10' }">
            <span class="icon">{{ category.icon }}</span>
          </div>
          <h2 class="category-name">{{ category.name }}</h2>
          <p class="category-count">{{ category.count }} 篇文章</p>
          <p class="category-desc">{{ category.description }}</p>
          <router-link :to="{ path: '/articles', query: { category: category.id }}" class="view-more">
            查看文章
            <span class="arrow">→</span>
          </router-link>
        </div>
      </div>

      <section class="tags-section">
        <h2 class="section-title">热门标签</h2>
        <div class="tags-cloud">
          <router-link 
            v-for="tag in tags" 
            :key="tag.name"
            :to="{ path: '/articles', query: { tag: tag.name }}"
            class="tag"
            :style="{ fontSize: tag.size + 'rem' }"
          >
            {{ tag.name }}
            <span class="tag-count">({{ tag.count }})</span>
          </router-link>
        </div>
      </section>
    </main>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const categories = ref([
  {
    id: 'tech',
    name: '技术',
    icon: '💻',
    color: '#409EFF',
    count: 25,
    description: '分享编程技术、开发经验和最佳实践'
  },
  {
    id: 'life',
    name: '生活',
    icon: '🌱',
    color: '#67C23A',
    count: 15,
    description: '记录日常生活、兴趣爱好和个人成长'
  },
  {
    id: 'thoughts',
    name: '随想',
    icon: '💭',
    color: '#E6A23C',
    count: 10,
    description: '分享想法、感悟和对事物的思考'
  },
  {
    id: 'reading',
    name: '读书',
    icon: '📚',
    color: '#F56C6C',
    count: 8,
    description: '书籍推荐、读书笔记和心得体会'
  }
])

const tags = ref([
  { name: 'Vue.js', count: 12, size: 1.4 },
  { name: 'Spring Boot', count: 10, size: 1.3 },
  { name: 'Java', count: 15, size: 1.5 },
  { name: 'JavaScript', count: 18, size: 1.6 },
  { name: 'Docker', count: 8, size: 1.2 },
  { name: '微服务', count: 6, size: 1.1 },
  { name: '前端开发', count: 20, size: 1.7 },
  { name: '后端开发', count: 16, size: 1.5 },
  { name: '数据库', count: 9, size: 1.2 },
  { name: '设计模式', count: 7, size: 1.1 },
  { name: '算法', count: 5, size: 1 },
  { name: '架构设计', count: 4, size: 1 }
])
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
  padding: 2rem;
  transition: all 0.3s ease;
}

.category-card:hover {
  transform: translateY(-4px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.category-icon {
  width: 60px;
  height: 60px;
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 1.5rem;
}

.icon {
  font-size: 2rem;
}

.category-name {
  font-size: 1.5rem;
  color: var(--text-primary);
  margin-bottom: 0.5rem;
}

.category-count {
  color: var(--text-secondary);
  font-size: 0.9rem;
  margin-bottom: 1rem;
}

.category-desc {
  color: var(--text-secondary);
  margin-bottom: 1.5rem;
  line-height: 1.6;
}

.view-more {
  display: inline-flex;
  align-items: center;
  color: var(--primary-color);
  text-decoration: none;
  font-weight: 500;
  transition: all 0.3s ease;
}

.arrow {
  margin-left: 0.5rem;
  transition: transform 0.3s ease;
}

.view-more:hover .arrow {
  transform: translateX(4px);
}

.tags-section {
  margin-bottom: 4rem;
}

.section-title {
  font-size: 1.8rem;
  color: var(--text-primary);
  margin-bottom: 2rem;
  text-align: center;
}

.tags-cloud {
  display: flex;
  flex-wrap: wrap;
  gap: 1rem;
  justify-content: center;
}

.tag {
  color: var(--primary-color);
  text-decoration: none;
  padding: 0.5rem 1rem;
  border-radius: 20px;
  background: var(--hover-bg);
  transition: all 0.3s ease;
}

.tag:hover {
  background: var(--primary-color);
  color: white;
}

.tag-count {
  font-size: 0.85em;
  opacity: 0.8;
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