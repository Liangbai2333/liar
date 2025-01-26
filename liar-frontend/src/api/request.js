import axios from 'axios'

// 创建 axios 实例
const request = axios.create({
  baseURL: 'http://localhost:8888/api',  // API 基础路径
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
    // 直接返回完整的响应结构
    if (res.code) {
      return res
    }
    // 如果后端没有返回标准结构，包装一下
    return {
      code: 200,
      message: 'success',
      data: res
    }
  },
  error => {
    console.error('请求错误:', error)
    return Promise.reject(error)
  }
)

export default request 