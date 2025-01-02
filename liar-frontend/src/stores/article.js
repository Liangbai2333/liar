import { defineStore } from 'pinia'

export const useArticleStore = defineStore('article', {
  state: () => ({
    currentArticle: {
      id: 1,
      title: '使用 Vue 3 和 Vite 构建现代化博客系统',
      content: `
# 使用 Vue 3 和 Vite 构建现代化博客系统

## 引言

在当今的互联网时代，拥有一个个人博客不仅能够记录自己的学习和成长，还能与他人分享知识和经验。本文将详细介绍如何使用 Vue 3 和 Vite 构建一个现代化的博客系统。

## 技术栈选择

### 前端技术栈

- **Vue 3**: 采用组合式 API，提供更好的代码组织和复用能力
- **Vite**: 下一代前端构建工具，提供极速的开发体验
- **Pinia**: 新一代状态管理工具，更简单、更直观
- **Vue Router**: 官方路由管理器，支持历史模式和 Hash 模式

### 后端技术栈

- **Spring Boot**: 简化 Spring 应用的初始搭建和开发过程
- **MyBatis Plus**: 增强 MyBatis 的使用体验，提供更多便捷功能
- **MySQL**: 可靠的关系型数据库，存储博客文章和用户数据

## 系统设计

### 前端架构

1. **目录结构**
\`\`\`
src/
  ├── api/        # API 接口
  ├── assets/     # 静态资源
  ├── components/ # 公共组件
  ├── router/     # 路由配置
  ├── stores/     # 状态管理
  ├── views/      # 页面组件
  └── App.vue     # 根组件
\`\`\`

2. **核心功能**
- 文章列表展示
- 文章详情页
- 分类管理
- 标签系统
- 响应式设计

### 后端架构

1. **数据库设计**
- 文章表
- 分类表
- 标签表
- 用户表

2. **接口设计**
- RESTful API
- 统一响应格式
- 错误处理机制

## 开发实践

### 组件设计原则

1. **单一职责**
每个组件应该只负责一个特定的功能，这样可以提高组件的可复用性和可维护性。

2. **组件通信**
- Props 向下传递数据
- Emit 向上传递事件
- Provide/Inject 跨层级传递数据

### 状态管理

使用 Pinia 进行状态管理，相比 Vuex 具有以下优势：
- 更简单的 API
- 更好的 TypeScript 支持
- 更轻量级的包体积

\`\`\`javascript
// 示例：文章 Store
const useArticleStore = defineStore('article', {
  state: () => ({
    articles: [],
    currentArticle: null
  }),
  actions: {
    async fetchArticles() {
      // 获取文章列表
    },
    async fetchArticleById(id) {
      // 获取文章详情
    }
  }
})
\`\`\`

### 性能优化

1. **路由懒加载**
\`\`\`javascript
const routes = [
  {
    path: '/article/:id',
    component: () => import('./views/ArticleDetail.vue')
  }
]
\`\`\`

2. **图片懒加载**
使用 \`v-lazy\` 指令延迟加载图片，提升页面加载速度。

3. **组件缓存**
使用 \`<keep-alive>\` 缓存不活动的组件实例。

## 部署与维护

### 部署流程

1. 构建前端项目
\`\`\`bash
npm run build
\`\`\`

2. 打包后端项目
\`\`\`bash
mvn clean package
\`\`\`

3. 使用 Docker 部署
\`\`\`dockerfile
FROM node:16 as build-stage
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build
\`\`\`

### 监控与日志

1. **前端监控**
- 使用 Vue DevTools 进行开发调试
- 集成错误追踪系统

2. **后端监控**
- 使用 Spring Boot Actuator 监控应用健康状态
- ELK 日志收集和分析

## 总结

通过使用 Vue 3 和 Vite 构建博客系统，我们不仅能够获得极佳的开发体验，还能为用户提供流畅的使用体验。在实际开发中，我们需要注意以下几点：

1. 合理的代码组织和架构设计
2. 组件的可复用性和可维护性
3. 性能优化和用户体验
4. 系统的可扩展性和可维护性

希望本文能够帮助你更好地理解如何构建现代化的博客系统。如果你有任何问题或建议，欢迎在评论区留言讨论。
      `,
      category: {
        id: 'tech',
        name: '技术'
      },
      tags: [
        { id: 1, name: 'Vue.js' },
        { id: 2, name: '前端开发' },
        { id: 3, name: '博客系统' }
      ],
      cover: 'https://picsum.photos/800/400',
      views: 1234,
      createTime: '2024-03-15T08:00:00.000Z',
      updateTime: '2024-03-15T10:30:00.000Z'
    },
    relatedArticles: [
      {
        id: 2,
        title: 'Vue 3 组合式 API 最佳实践',
        summary: '深入探讨 Vue 3 组合式 API 的使用技巧和最佳实践，帮助你写出更好的 Vue 应用。',
        category: { id: 'tech', name: '技术' },
        cover: 'https://picsum.photos/800/400?random=1',
        views: 856,
        createTime: '2024-03-14T08:00:00.000Z'
      },
      {
        id: 3,
        title: 'Vite 构建工具深度解析',
        summary: '详细介绍 Vite 的工作原理、优势特点以及在实际项目中的应用。',
        category: { id: 'tech', name: '技术' },
        cover: 'https://picsum.photos/800/400?random=2',
        views: 657,
        createTime: '2024-03-13T08:00:00.000Z'
      },
      {
        id: 4,
        title: '现代化前端工程化实践',
        summary: '探讨现代前端开发中的工程化实践，包括构建工具、代码规范、自动化测试等。',
        category: { id: 'tech', name: '技术' },
        cover: 'https://picsum.photos/800/400?random=3',
        views: 543,
        createTime: '2024-03-12T08:00:00.000Z'
      }
    ]
  }),

  getters: {
    articleDetail: (state) => state.currentArticle,
    relatedPosts: (state) => state.relatedArticles
  },

  actions: {
    async fetchArticle(id) {
      // 模拟 API 请求
      return new Promise((resolve) => {
        setTimeout(() => {
          resolve(this.currentArticle)
        }, 300)
      })
    },

    async fetchRelatedArticles() {
      // 模拟 API 请求
      return new Promise((resolve) => {
        setTimeout(() => {
          resolve(this.relatedArticles)
        }, 300)
      })
    }
  }
}) 