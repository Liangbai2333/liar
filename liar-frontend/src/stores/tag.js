import { defineStore } from 'pinia'
import { ref } from 'vue'
import { getTagList, getTagCount } from '../api/tag'

export const useTagStore = defineStore('tag', () => {
  // 状态
  const tags = ref([])
  const total = ref(0)
  const loading = ref(false)

  // actions
  const fetchTags = async () => {
    try {
      loading.value = true
      const response = await getTagList()
      if (response.code === 200) {
        tags.value = response.data
      }
    } catch (error) {
      console.error('获取标签列表失败:', error)
    } finally {
      loading.value = false
    }
  }

  const fetchTotal = async () => {
    try {
      const response = await getTagCount()
      if (response.code === 200) {
        total.value = response.data
      }
    } catch (error) {
      console.error('获取标签总数失败:', error)
    }
  }

  return {
    // 状态
    tags,
    total,
    loading,
    // actions
    fetchTags,
    fetchTotal
  }
}) 