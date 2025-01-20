import request from './request'

// 获取文章列表
export function getArticles(params) {
  return request({
    url: '/article/list',
    method: 'get',
    params
  })
}

// 获取文章详情
export function getArticle(id) {
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

// 创建文章
export function createArticle(data) {
  return request({
    url: '/article/create',
    method: 'post',
    data
  })
}

// 更新文章
export function updateArticle(id, data) {
  return request({
    url: `/article/${id}`,
    method: 'put',
    data
  })
}

// 删除文章
export function deleteArticle(id) {
  return request({
    url: `/article/${id}`,
    method: 'delete'
  })
}

// 获取草稿箱文章列表
export function getDrafts(params) {
  return request({
    url: '/article/drafts',
    method: 'get',
    params
  })
}

// 保存草稿
export function saveDraft(data) {
  return request({
    url: '/article/draft',
    method: 'post',
    data
  })
}

// 发布草稿
export function publishDraft(id) {
  return request({
    url: `/article/draft/${id}/publish`,
    method: 'post'
  })
} 