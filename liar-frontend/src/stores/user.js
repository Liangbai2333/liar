import { defineStore } from 'pinia'
import { ref } from 'vue'
import { getUserList } from '../api/user'

export const useUserStore = defineStore('user', () => {
  // 状态
  const users = ref([])
  const loading = ref(false)

  // actions
  const fetchUsers = async () => {
    try {
      loading.value = true
      const response = await getUserList()
      if (response.code === 200) {
        users.value = response.data
        console.log(response.data)
      }
    } catch (error) {
      console.error('获取用户列表失败:', error)
    } finally {
      loading.value = false
    }
  }

  return {
    // 状态
    users,
    loading,
    // actions
    fetchUsers
  }
}) 