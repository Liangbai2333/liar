import { defineStore } from 'pinia'
import { getArticleById, getArticleList, viewArticle, getArticleCount } from '../api/article'
import image1 from '@/assets/img/1702044245889.png'

export const useArticleStore = defineStore('article', {
  state: () => ({
    currentArticle: null,
    relatedArticles: [],
    topPosts: [],
    articles: [],
    total: 0,
    loading: false
  }),

  getters: {
    articleDetail: (state) => state.currentArticle,
    relatedPosts: (state) => state.relatedArticles
  },

  actions: {
    async fetchArticle(id, page = 1) {
      try {
        // 增加浏览量
        await viewArticle(id, page)
        // 获取文章详情
        const response = await getArticleById(id)
        if (response.code === 200) {
          this.currentArticle = response.data
          // 获取相关文章
          await this.fetchRelatedArticles()
          return response.data
        }
        return null
      } catch (error) {
        console.error('获取文章详情失败:', error)
        return null
      }
    },

    async fetchRelatedArticles() {
      try {
        if (!this.currentArticle) return []
        
        // 根据当前文章的分类和标签获取相关文章
        const params = {
          page: 1,
          size: 3,
          categoryId: this.currentArticle.category?.id,
          sort: 'views',
          order: 'desc'
        }
        
        const response = await getArticleList(params)
        if (response.code === 200) {
          // 过滤掉当前文章
          this.relatedArticles = response.data
            .filter(article => article.id !== this.currentArticle.id)
            .slice(0, 3)
          return this.relatedArticles
        }
        return []
      } catch (error) {
        console.error('获取相关文章失败:', error)
        return []
      }
    },

    async fetchTopPosts() {
      try {
        const response = await getArticleList({
          page: 1,
          size: 3,
          sort: 'priority',
          order: 'desc'
        })

        if (response.code === 200) {
          this.topPosts = response.data.map(article => ({
            id: article.id,
            title: article.title,
            date: new Date(article.createTime).toLocaleDateString(),
            category: article.category?.name || '未分类',
            summary: article.summary,
            image: article.cover || ''
          }))
        } else {
          console.error('获取置顶文章失败:', response.message)
          this.topPosts = []
        }
      } catch (error) {
        console.error('获取置顶文章失败:', error)
        this.topPosts = []
      }
    },

    async fetchArticles({ page = 1, size = 10, categoryId, keyword, sort = 'create_time', order = 'desc' }) {
      try {
        this.loading = true
        const response = await getArticleList({
          page,
          size,
          categoryId,
          keyword,
          sort,
          order
        })

        if (response.code === 200) {
          this.articles = response.data.map(article => ({
            id: article.id,
            title: article.title,
            category: article.category?.name || '未分类',
            date: new Date(article.createTime).toLocaleDateString(),
            views: article.views,
            summary: article.summary,
            tags: article.tags?.map(tag => tag.name) || [],
            cover: article.cover
          }))
          this.total = response.total || 0
        } else {
          console.error('获取文章列表失败:', response.message)
          this.articles = []
          this.total = 0
        }
      } catch (error) {
        console.error('获取文章列表失败:', error)
        this.articles = []
        this.total = 0
      } finally {
        this.loading = false
      }
    },

    async fetchTotal() {
      try {
        const response = await getArticleCount()
        if (response.code === 200) {
          this.total = response.data
        }
      } catch (error) {
        console.error('获取文章数量失败:', error)
      }
    }
  }
}) 