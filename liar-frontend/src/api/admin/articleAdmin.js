import request from '../request'

// 管理员接口
/**
 * 保存文章
 * @param {Object} params 文章数据
 * @param {number} params.id 文章ID
 * @param {string} [params.title] 标题（可选）
 * @param {string} [params.state] 状态（可选）
 * @param {string} [params.summary] 摘要（可选）
 * @param {string} [params.motto] 格言（可选）
 * @param {string} [params.content] 内容（可选）
 * @param {string} [params.cover] 封面（可选）
 * @param {number} params.categoryId 分类ID
 * @param {number[]} [params.tags] 标签ID列表（可选）
 * @param {number} [params.priority=100] 优先级
 * @param {number} [params.authorId=0] 作者ID
 */
export function saveArticle(id, params) {
    return request({
      url: `/api/admin/article/save/${id}`,
      method: 'post',
      params
    })
  }
  
  /**
   * 删除文章
   * @param {number} id 文章ID
   */
  export function deleteArticle(id) {
    return request({
      url: `/api/admin/article/delete/${id}`,
      method: 'post'
    })
  }
  
  /**
   * 上传文章封面
   * @param {FormData} formData 包含文件的表单数据
   */
  export function uploadCover(formData) {
    return request({
      url: '/api/admin/article/upload/cover',
      method: 'post',
      headers: {
        'Content-Type': 'multipart/form-data'
      },
      data: formData
    })
  }