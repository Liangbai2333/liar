import axios from 'axios'

// 创建 axios 实例
const request = axios.create({
  baseURL: '/api',  // API 基础路径
  timeout: 10000    // 请求超时时间
})

// 请求拦截器
request.interceptors.request.use(
  config => {
    // 在请求发送前做一些处理，比如添加 token
    const token = localStorage.getItem('token')
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`
    }
    return config
  },
  error => {
    return Promise.reject(error)
  }
)

// 响应拦截器
request.interceptors.response.use(
  response => {
    const res = response.data
    // 根据状态码判断请求是否成功
    if (res.code === 200) {
      return res.data
    } else {
      // 处理错误情况
      console.error(res.message)
      return Promise.reject(new Error(res.message || '请求失败'))
    }
  },
  error => {
    console.error('请求错误:', error)
    return Promise.reject(error)
  }
)

export default request 