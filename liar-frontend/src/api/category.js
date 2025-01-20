import request from './request'

// 获取分类列表
export function getCategoryList() {
  return request({
    url: '/category/list',
    method: 'get'
  })
}

// 暂时导出空函数，等后端接口完成后再实现
export const getCategories = () => {}
export const createCategory = () => {}
export const updateCategory = () => {}
export const deleteCategory = () => {} 