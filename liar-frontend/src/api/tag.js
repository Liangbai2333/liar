import request from './request'

// 获取标签列表
export function getTagList() {
  return request({
    url: '/tag/list',
    method: 'get'
  })
}

// 暂时导出空函数，等后端接口完成后再实现
export const getTags = () => {}
export const createTag = () => {}
export const updateTag = () => {}
export const deleteTag = () => {} 