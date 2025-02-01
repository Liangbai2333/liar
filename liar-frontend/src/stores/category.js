import { defineStore } from 'pinia'
import { ref } from 'vue'
import { getCategoryList, getCategoryCount, getArticleCountByCategoryId } from '../api/category'

export const useCategoryStore = defineStore('category', () => {
  // 状态
  const categories = ref([])
  const total = ref(0)
  const loading = ref(false)

  // actions
  const fetchCategories = async () => {
    try {
      loading.value = true
      const response = await getCategoryList()
      if (response.code === 200) {
        categories.value = response.data
        // 获取每个分类的文章数量
        await Promise.all(
          categories.value.map(async (category) => {
            const countResponse = await getArticleCountByCategoryId(category.id)
            if (countResponse.code === 200) {
              category.count = countResponse.data
            }
          })
        )
      }
    } catch (error) {
      console.error('获取分类列表失败:', error)
    } finally {
      loading.value = false
    }
  }

  const fetchTotal = async () => {
    try {
      const response = await getCategoryCount()
      if (response.code === 200) {
        total.value = response.data
      }
    } catch (error) {
      console.error('获取分类总数失败:', error)
    }
  }

  return {
    // 状态
    categories,
    total,
    loading,
    // actions
    fetchCategories,
    fetchTotal
  }
}) 