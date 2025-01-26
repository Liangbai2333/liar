import request from './request'

// 获取标签列表
export function getTagList() {
  return request({
    url: '/tag/list',
    method: 'get'
  })
}

export function getTagCount() {
  return request({
    url: '/tag/count',
    method: 'get'
  })
}