import request from './request'

// 获取分类列表
export function getCategoryList() {
  return request({
    url: '/category/list',
    method: 'get'
  })
}

export function getCategoryCount() {
    return request({
        url: '/category/count',
        method: 'get'
    })
}

// 获取分类下的文章数量
export function getArticleCountByCategoryId(categoryId) {
    return request({
        url: `/category/article-count`,
        method: 'get',
        params: {
            id: categoryId
        }
    })
}
