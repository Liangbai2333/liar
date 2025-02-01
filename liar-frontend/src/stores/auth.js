import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import { login, logout } from '../api/auth'
import { useRouter } from 'vue-router'

export const useAuthStore = defineStore('auth', () => {
  const router = useRouter()
  
  // 状态
  const token = ref(localStorage.getItem('token') || '')
  const loading = ref(false)
  const error = ref('')

  // 从 token 解析信息
  const parseToken = (token) => {
    try {
      const payload = JSON.parse(atob(token.split('.')[1]))
      return {
        username: payload.name,
        expire: payload.exp * 1000 // 转换为毫秒
      }
    } catch {
      return { username: '', expire: 0 }
    }
  }

  // 计算属性
  const username = computed(() => {
    if (!token.value) return ''
    return parseToken(token.value).username
  })

  // actions
  const loginAction = async (username, password) => {
    try {
      loading.value = true
      error.value = ''
      const response = await login({ username, password })
      if (response.code === 200) {
        // 处理转义字符并只取第一个完整的 JSON 对象
        const jsonStr = response.data.replace(/\\/g, '').replace(/}{.*$/, '}')
        const data = JSON.parse(jsonStr)
        token.value = data.data.token
        console.log(parseToken(token.value));
        
        localStorage.setItem('token', token.value)
        router.push('/admin/dashboard')
        return true
      }
      error.value = response.message || '登录失败'
      return false
    } catch (err) {
      console.error('登录失败:', err)
      error.value = err.response?.data?.message || '服务器错误，请稍后重试'
      return false
    } finally {
      loading.value = false
    }
  }

  const logoutAction = async () => {
    try {
      await logout({ Authorization: `Bearer ${token.value}` })
    } catch (err) {
      console.error('退出登录失败:', err)
    } finally {
      // 无论是否调用成功，都清除本地状态
      token.value = ''
      localStorage.removeItem('token')
      router.push('/admin/login')
    }
  }

  // 检查 token 是否过期
  const checkToken = () => {
    if (!token.value) return false
    const { expire } = parseToken(token.value)
    return expire > Date.now()
  }

  return {
    // 状态
    token,
    loading,
    error,
    username,
    // actions
    loginAction,
    logoutAction,
    checkToken
  }
}) 