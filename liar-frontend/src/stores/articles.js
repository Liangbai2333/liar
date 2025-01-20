import { defineStore } from 'pinia'
import { getArticles, getArticle, viewArticle, createArticle, updateArticle, deleteArticle, getDrafts, saveDraft, publishDraft } from '@/api/articles'

export const useArticlesStore = defineStore('articles', {
  state: () => ({
    articles: [],        // 文章列表
    total: 0,           // 文章总数
    currentArticle: null, // 当前文章
    drafts: [],         // 草稿列表
    loading: false,     // 加载状态
    error: null         // 错误信息
  }),

  getters: {
    // 获取已发布的文章列表
    publishedArticles: (state) => state.articles.filter(article => article.status === 'published'),
    
    // 获取草稿列表
    draftArticles: (state) => state.drafts,
    
    // 获取当前文章
    getCurrentArticle: (state) => state.currentArticle,
    
    // 是否正在加载
    isLoading: (state) => state.loading
  },

  actions: {
    // 设置加载状态
    setLoading(status) {
      this.loading = status
    },

    // 设置错误信息
    setError(error) {
      this.error = error
    },

    // 获取文章列表
    async fetchArticles(params) {
      try {
        this.setLoading(true)
        const { list, total } = await getArticles(params)
        this.articles = list
        this.total = total
      } catch (error) {
        this.setError(error.message)
      } finally {
        this.setLoading(false)
      }
    },

    // 获取文章详情
    async fetchArticle(id) {
      try {
        this.setLoading(true)
        const article = await getArticle(id)
        this.currentArticle = article
        return article
      } catch (error) {
        this.setError(error.message)
        return null
      } finally {
        this.setLoading(false)
      }
    },

    // 查看文章（增加浏览量）
    async viewArticle(id) {
      try {
        await viewArticle(id)
        if (this.currentArticle && this.currentArticle.id === id) {
          this.currentArticle.views++
        }
      } catch (error) {
        this.setError(error.message)
      }
    },

    // 创建文章
    async createArticle(articleData) {
      try {
        this.setLoading(true)
        const article = await createArticle(articleData)
        this.articles.unshift(article)
        return article
      } catch (error) {
        this.setError(error.message)
        return null
      } finally {
        this.setLoading(false)
      }
    },

    // 更新文章
    async updateArticle(id, articleData) {
      try {
        this.setLoading(true)
        const article = await updateArticle(id, articleData)
        const index = this.articles.findIndex(item => item.id === id)
        if (index !== -1) {
          this.articles[index] = article
        }
        if (this.currentArticle && this.currentArticle.id === id) {
          this.currentArticle = article
        }
        return article
      } catch (error) {
        this.setError(error.message)
        return null
      } finally {
        this.setLoading(false)
      }
    },

    // 删除文章
    async deleteArticle(id) {
      try {
        this.setLoading(true)
        await deleteArticle(id)
        this.articles = this.articles.filter(article => article.id !== id)
        if (this.currentArticle && this.currentArticle.id === id) {
          this.currentArticle = null
        }
        return true
      } catch (error) {
        this.setError(error.message)
        return false
      } finally {
        this.setLoading(false)
      }
    },

    // 获取草稿列表
    async fetchDrafts(params) {
      try {
        this.setLoading(true)
        const { list } = await getDrafts(params)
        this.drafts = list
      } catch (error) {
        this.setError(error.message)
      } finally {
        this.setLoading(false)
      }
    },

    // 保存草稿
    async saveDraft(draftData) {
      try {
        this.setLoading(true)
        const draft = await saveDraft(draftData)
        this.drafts.unshift(draft)
        return draft
      } catch (error) {
        this.setError(error.message)
        return null
      } finally {
        this.setLoading(false)
      }
    },

    // 发布草稿
    async publishDraft(id) {
      try {
        this.setLoading(true)
        const article = await publishDraft(id)
        // 从草稿列表中移除
        this.drafts = this.drafts.filter(draft => draft.id !== id)
        // 添加到文章列表
        this.articles.unshift(article)
        return article
      } catch (error) {
        this.setError(error.message)
        return null
      } finally {
        this.setLoading(false)
      }
    }
  }
}) 