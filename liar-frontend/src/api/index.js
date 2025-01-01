import request from './request'

// 基础配置
const BASE_URL = '/api'

// 响应结构
const ResponseStructure = {
  code: 200,      // 状态码：200成功，其他表示失败
  message: '',    // 响应信息
  data: null      // 响应数据
}

// API接口定义
export const API = {
  // 文章相关接口
  article: {
    // 获取文章列表
    list: {
      url: `${BASE_URL}/articles`,
      method: 'GET',
      params: {
        page: 1,              // 页码，从1开始
        size: 10,             // 每页数量
        category: '',         // 分类ID（可选）
        tag: '',             // 标签（可选）
        keyword: '',         // 搜索关键词（可选）
        sort: 'createTime',  // 排序字段：createTime-创建时间，views-浏览量
        order: 'desc'        // 排序方式：desc-降序，asc-升序
      },
      response: {
        ...ResponseStructure,
        data: {
          total: 0,          // 总条数
          pages: 0,          // 总页数
          list: [{
            id: '',          // 文章ID
            title: '',       // 文章标题
            summary: '',     // 文章摘要
            content: '',     // 文章内容（列表页不返回）
            category: {
              id: '',        // 分类ID
              name: ''       // 分类名称
            },
            tags: [{        // 标签列表
              id: '',       // 标签ID
              name: ''      // 标签名称
            }],
            cover: '',      // 封面图片URL
            views: 0,       // 浏览量
            createTime: '', // 创建时间
            updateTime: ''  // 更新时间
          }]
        }
      }
    },

    // 获取文章详情
    detail: {
      url: `${BASE_URL}/articles/:id`,
      method: 'GET',
      response: {
        ...ResponseStructure,
        data: {
          id: '',          // 文章ID
          title: '',       // 文章标题
          content: '',     // 文章内容
          category: {
            id: '',        // 分类ID
            name: ''       // 分类名称
          },
          tags: [{        // 标签列表
            id: '',       // 标签ID
            name: ''      // 标签名称
          }],
          cover: '',      // 封面图片URL
          views: 0,       // 浏览量
          createTime: '', // 创建时间
          updateTime: ''  // 更新时间
        }
      }
    }
  },

  // 分类相关接口
  category: {
    // 获取分类列表
    list: {
      url: `${BASE_URL}/categories`,
      method: 'GET',
      response: {
        ...ResponseStructure,
        data: [{
          id: '',         // 分类ID
          name: '',       // 分类名称
          description: '', // 分类描述
          count: 0,       // 文章数量
          icon: '',       // 分类图标
          color: ''       // 分类颜色
        }]
      }
    }
  },

  // 标签相关接口
  tag: {
    // 获取标签列表
    list: {
      url: `${BASE_URL}/tags`,
      method: 'GET',
      response: {
        ...ResponseStructure,
        data: [{
          id: '',         // 标签ID
          name: '',       // 标签名称
          count: 0        // 文章数量
        }]
      }
    }
  },

  // 个人信息相关接口
  profile: {
    // 获取个人信息
    info: {
      url: `${BASE_URL}/profile`,
      method: 'GET',
      response: {
        ...ResponseStructure,
        data: {
          name: '',           // 昵称
          avatar: '',         // 头像URL
          motto: '',          // 座右铭
          description: '',    // 个人简介
          skills: [{         // 技能列表
            category: '',    // 技能分类
            items: [{       // 技能项
              name: '',     // 技能名称
              level: 0      // 熟练度（1-5）
            }]
          }],
          interests: [{     // 兴趣爱好
            icon: '',      // 图标
            name: ''       // 名称
          }],
          contacts: [{     // 联系方式
            type: '',     // 类型：github, email, qq等
            icon: '',     // 图标
            name: '',     // 显示名称
            value: '',    // 具体值
            link: ''      // 链接（可选）
          }]
        }
      }
    }
  }
}

// API调用示例
export const articleApi = {
  // 获取文章列表
  getList: (params = {}) => {
    return request({
      url: '/articles',
      method: 'get',
      params
    })
  },

  // 获取文章详情
  getDetail: (id) => {
    return request({
      url: `/articles/${id}`,
      method: 'get'
    })
  }
}

export const categoryApi = {
  // 获取分类列表
  getList: () => {
    return request({
      url: '/categories',
      method: 'get'
    })
  }
}

export const tagApi = {
  // 获取标签列表
  getList: () => {
    return request({
      url: '/tags',
      method: 'get'
    })
  }
}

export const profileApi = {
  // 获取个人信息
  getInfo: () => {
    return request({
      url: '/profile',
      method: 'get'
    })
  }
} 