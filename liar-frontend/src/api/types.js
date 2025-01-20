// API 响应结构
export const ResponseStructure = {
  code: 200,      // 状态码：200成功，其他表示失败
  message: '',    // 响应信息
  data: null      // 响应数据
}

// 分页请求参数
export const PageParams = {
  page: 1,        // 当前页码，从1开始
  size: 10,       // 每页条数
  sort: '',       // 排序字段，例如：'createTime'
  order: 'desc'   // 排序方式：asc升序，desc降序
}

// 分页响应结构
export const PageResponse = {
  total: 0,       // 总记录数
  pages: 0,       // 总页数
  current: 1,     // 当前页码
  size: 10,       // 每页条数
  list: []        // 数据列表
}

// 文章状态枚举
export const ArticleStatus = {
  DRAFT: 'draft',         // 草稿
  PUBLISHED: 'published', // 已发布
  PRIVATE: 'private',     // 私密
  DELETED: 'deleted'      // 已删除
}

// 文章相关类型
export const ArticleTypes = {
  // 文章基础信息
  ArticleBase: {
    title: '',           // 文章标题
    summary: '',         // 文章摘要
    content: '',         // 文章内容（Markdown格式）
    htmlContent: '',     // 文章内容（HTML格式）
    cover: '',           // 封面图片URL
    categoryId: 0,       // 分类ID
    tagIds: [],         // 标签ID列表
    status: '',         // 文章状态：draft, published, private, deleted
    priority: 0,        // 优先级，用于置顶等功能
    allowComment: true  // 是否允许评论
  },

  // 文章详细信息（继承基础信息）
  ArticleDetail: {
    id: 0,              // 文章ID
    ...this.ArticleBase,
    category: {         // 分类信息
      id: 0,
      name: '',
      path: ''         // 分类路径，例如：/tech/java
    },
    tags: [{           // 标签列表
      id: 0,
      name: '',
      color: ''       // 标签颜色
    }],
    author: {          // 作者信息
      id: 0,
      username: '',
      nickname: '',
      avatar: ''
    },
    stats: {           // 统计信息
      views: 0,       // 浏览量
      likes: 0,       // 点赞数
      comments: 0     // 评论数
    },
    createTime: '',    // 创建时间
    updateTime: '',    // 更新时间
    publishTime: ''    // 发布时间
  },

  // 文章列表项（精简信息）
  ArticleListItem: {
    id: 0,
    title: '',
    summary: '',
    cover: '',
    categoryId: 0,
    category: {
      id: 0,
      name: ''
    },
    tags: [{
      id: 0,
      name: '',
      color: ''
    }],
    status: '',
    priority: 0,
    stats: {
      views: 0,
      likes: 0,
      comments: 0
    },
    createTime: '',
    publishTime: ''
  },

  // 创建文章请求
  CreateArticleRequest: {
    ...this.ArticleBase
  },

  // 更新文章请求
  UpdateArticleRequest: {
    ...this.ArticleBase,
    id: 0
  }
}

// 分类相关类型
export const CategoryTypes = {
  // 分类信息
  CategoryItem: {
    id: 0,              // 分类ID
    name: '',           // 分类名称
    path: '',           // 分类路径
    description: '',    // 分类描述
    parentId: 0,        // 父分类ID，0表示顶级分类
    level: 0,           // 层级，从0开始
    sort: 0,            // 排序值
    articleCount: 0,    // 文章数量
    createTime: '',     // 创建时间
    updateTime: ''      // 更新时间
  },

  // 创建分类请求
  CreateCategoryRequest: {
    name: '',
    path: '',
    description: '',
    parentId: 0,
    sort: 0
  }
}

// 标签相关类型
export const TagTypes = {
  // 标签信息
  TagItem: {
    id: 0,              // 标签ID
    name: '',           // 标签名称
    color: '',          // 标签颜色
    description: '',    // 标签描述
    articleCount: 0,    // 文章数量
    createTime: '',     // 创建时间
    updateTime: ''      // 更新时间
  },

  // 创建标签请求
  CreateTagRequest: {
    name: '',
    color: '',
    description: ''
  }
}

// 评论相关类型
export const CommentTypes = {
  // 评论信息
  CommentItem: {
    id: 0,              // 评论ID
    content: '',        // 评论内容
    articleId: 0,       // 文章ID
    parentId: 0,        // 父评论ID，0表示顶级评论
    replyTo: 0,         // 回复的评论ID
    author: {           // 评论作者
      id: 0,
      username: '',
      nickname: '',
      avatar: ''
    },
    status: '',         // 评论状态：pending, approved, rejected
    createTime: '',     // 创建时间
    children: []        // 子评论列表
  },

  // 创建评论请求
  CreateCommentRequest: {
    content: '',
    articleId: 0,
    parentId: 0,
    replyTo: 0
  }
} 