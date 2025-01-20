import request from './request'

// 获取文章列表
export function getArticleList(params) {
  return request({
    url: '/article/list',
    method: 'get',
    params
  })
}

// 获取文章详情
export function getArticleById(id) {
  return request({
    url: `/article/${id}`,
    method: 'get'
  })
}

// 增加文章浏览量
export function viewArticle(id) {
  return request({
    url: `/article/view/${id}`,
    method: 'post'
  })
} 