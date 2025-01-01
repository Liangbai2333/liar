import axios from 'axios'

// 创建 axios 实例
const service = axios.create({
  baseURL: '/api',
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json'
  }
})

// 请求拦截器
service.interceptors.request.use(
  config => {
    // 在这里可以添加请求头、token等
    return config
  },
  error => {
    console.error('请求错误:', error)
    return Promise.reject(error)
  }
)

// 响应拦截器
service.interceptors.response.use(
  response => {
    const res = response.data
    
    // 这里可以根据后端的响应结构进行统一处理
    if (res.code === 200) {
      return res.data
    } else {
      // 处理业务错误
      const error = new Error(res.message || '请求失败')
      error.code = res.code
      return Promise.reject(error)
    }
  },
  error => {
    // 处理 HTTP 错误
    console.error('响应错误:', error)
    const message = error.response?.data?.message || error.message || '网络错误'
    return Promise.reject(new Error(message))
  }
)

export default service 