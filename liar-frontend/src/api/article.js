import request from './request'

/**
 * 获取文章列表
 * @param {Object} params 查询参数
 * @param {number} params.page 页码
 * @param {number} params.size 每页大小
 * @param {number} [params.categoryId] 分类ID（可选）
 * @param {number} [params.tagId] 标签ID（可选）
 * @param {number} [params.authorId] 作者ID（可选）
 * @param {string} [params.keyword] 关键词（可选）
 * @param {string} [params.sort='priority,create_time'] 排序字段
 * @param {string} [params.order='desc'] 排序方式
 */
export function getArticleList(params) {
  return request({
    url: '/article/list',
    method: 'get',
    params
  })
}

/**
 * 获取文章详情
 * @param {number} id 文章ID
 */
export function getArticleById(id) {
  return request({
    url: `/article/get/${id}`,
    method: 'get'
  })
}

/**
 * 浏览文章（增加浏览量）
 * @param {number} id 文章ID
 */
export function viewArticle(id) {
  return request({
    url: `/article/view/${id}`,
    method: 'post'
  })
}

/**
 * 获取文章数量
 */
export function getArticleCount() {
  return request({
    url: '/article/count',
    method: 'get'
  })
}
