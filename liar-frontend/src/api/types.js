// API 基础响应结构
export const Result = {
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

// 文章列表请求参数
export const ArticleListParams = {
  page: 1,                // 当前页码
  size: 10,              // 每页条数
  categoryId: undefined,  // 分类ID（可选）
  tagId: undefined,      // 标签ID（可选）
  authorId: undefined,   // 作者ID（可选）
  keyword: undefined,    // 关键词（可选）
  sort: 'priority,create_time', // 排序字段
  order: 'desc'          // 排序方式
}

// 分页响应结构
export const PageResponse = {
  total: 0,       // 总记录数
  pages: 0,       // 总页数
  current: 1,     // 当前页码
  size: 10,       // 每页条数
  list: []        // 数据列表
}

// 用户视图对象
export const UserVO = {
  id: 0,              // 用户ID
  username: '',       // 用户名
  avatar: '',         // 头像URL
  motto: '',          // 座右铭
  description: '',    // 个人描述
  status: '',         // 用户状态
  skills: [{          // 技能列表
    id: 0,
    category: {       // 技能分类
      id: 0,
      name: '',       // 分类名称
      description: '' // 分类描述
    },
    name: '',         // 技能名称
    level: 0         // 技能等级
  }],
  interests: [{       // 兴趣爱好
    id: 0,
    name: '',        // 兴趣名称
    description: ''  // 兴趣描述
  }],
  contacts: [{        // 联系方式
    id: 0,
    type: '',        // 联系方式类型
    value: '',       // 联系方式值
    icon: '',        // 图标
    link: ''         // 链接
  }]
}

// 仪表盘视图对象
export const DashboardVO = {
  os: '',               // 操作系统
  arch: '',             // 系统架构
  cpuUsedPercent: 0,    // CPU使用率
  memoryUsed: 0,        // 已用内存
  memoryTotal: 0,       // 总内存
  memoryUsedPercent: 0, // 内存使用率
  javaVersion: '',      // Java版本
  runningTime: 0,       // 运行时间
  startTime: 0,         // 启动时间
  serverTime: 0         // 服务器时间
}

// 设置视图对象
export const SettingVO = {
  title: '',           // 网站标题
  headline: '',        // 网站标语
  summary: '',         // 网站简介
  footer: ''           // 页脚信息
}

// 文章视图对象
export const ArticleVO = {
  id: 0,               // 文章ID
  title: '',           // 标题
  state: '',           // 状态
  summary: '',         // 摘要
  motto: '',           // 格言
  content: '',         // 内容
  cover: '',           // 封面图片
  category: {          // 分类
    id: 0,
    name: '',
    description: '',
    articleCount: 0
  },
  tags: [{             // 标签列表
    id: 0,
    name: '',
    color: '',
    articleCount: 0
  }],
  views: 0,            // 浏览量
  authorId: 0,         // 作者ID
  priority: 0,         // 优先级
  createTime: '',      // 创建时间
  updateTime: ''       // 更新时间
}

// 分类视图对象
export const CategoryVO = {
  id: 0,               // 分类ID
  name: '',            // 分类名称
  description: '',     // 分类描述
  articleCount: 0      // 文章数量
}

// 标签视图对象
export const TagVO = {
  id: 0,               // 标签ID
  name: '',            // 标签名称
  color: '',           // 标签颜色
  articleCount: 0      // 文章数量
}

// 用户相关类型
export const UserTypes = {
  // 登录请求
  LoginRequest: {
    username: '',      // 用户名
    password: ''       // 密码
  },

  // 注册请求
  RegisterRequest: {
    username: '',      // 用户名
    password: '',      // 密码
    email: '',         // 邮箱
    code: ''          // 验证码
  },

  // 更新用户信息请求
  UpdateUserRequest: {
    avatar: '',        // 头像URL
    motto: '',         // 座右铭
    description: '',   // 个人描述
    skills: [],        // 技能ID列表
    interests: [],     // 兴趣ID列表
    contacts: []       // 联系方式列表
  }
}

// 仪表盘统计类型
export const DashboardTypes = {
  // 仪表盘数据
  DashboardVO: {
    os: '',               // 操作系统
    arch: '',             // 系统架构
    cpuUsedPercent: 0,    // CPU使用率
    memoryUsed: 0,        // 已用内存
    memoryTotal: 0,       // 总内存
    memoryUsedPercent: 0, // 内存使用率
    javaVersion: '',      // Java版本
    runningTime: 0,       // 运行时间
    startTime: 0,         // 启动时间
    serverTime: 0         // 服务器时间
  }
}

// 设置相关类型
export const SettingTypes = {
  // 更新设置请求
  UpdateSettingRequest: {
    title: '',           // 网站标题
    headline: '',        // 网站标语
    summary: '',         // 网站简介
    footer: ''           // 页脚信息
  }
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
  // 创建文章请求
  CreateArticleRequest: {
    title: '',           // 标题
    summary: '',         // 摘要
    motto: '',           // 格言
    content: '',         // 内容
    cover: '',           // 封面图片
    categoryId: 0,       // 分类ID
    tagIds: [],          // 标签ID列表
    state: '',           // 状态
    priority: 0          // 优先级
  },

  // 更新文章请求
  UpdateArticleRequest: {
    id: 0,               // 文章ID
    title: '',           // 标题
    summary: '',         // 摘要
    motto: '',           // 格言
    content: '',         // 内容
    cover: '',           // 封面图片
    categoryId: 0,       // 分类ID
    tagIds: [],          // 标签ID列表
    state: '',           // 状态
    priority: 0          // 优先级
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

// 管理员用户相关类型
export const UserAdminTypes = {
  // 保存用户请求
  SaveUserRequest: {
    id: 0,              // 用户ID
    name: '',           // 用户名
    motto: '',          // 座右铭（可选）
    description: '',    // 描述
    status: ''          // 状态（可选）
  },

  // 修改密码请求
  ChangePasswordRequest: {
    id: 0,              // 用户ID
    oldPassword: '',    // 旧密码
    newPassword: ''     // 新密码
  }
}

// 管理员文章相关类型
export const ArticleAdminTypes = {
  // 保存文章请求
  SaveArticleRequest: {
    id: 0,              // 文章ID
    title: '',          // 标题（可选）
    state: '',          // 状态（可选）
    summary: '',        // 摘要（可选）
    motto: '',          // 格言（可选）
    content: '',        // 内容（可选）
    cover: '',          // 封面（可选）
    categoryId: 0,      // 分类ID
    tags: [],           // 标签ID列表（可选）
    priority: 100,      // 优先级
    authorId: 0         // 作者ID
  }
}

// 管理员分类相关类型
export const CategoryAdminTypes = {
  // 保存分类请求
  SaveCategoryRequest: {
    id: 0,              // 分类ID
    name: '',           // 分类名称
    description: '',    // 描述（可选）
    icon: '',           // 图标（可选）
    color: ''           // 颜色（可选）
  }
}

// 管理员标签相关类型
export const TagAdminTypes = {
  // 保存标签请求
  SaveTagRequest: {
    id: 0,              // 标签ID
    name: ''            // 标签名称
  }
}

// 文件上传相关类型
export const UploadTypes = {
  // 文件上传请求
  UploadRequest: {
    file: null          // 文件对象 (MultipartFile)
  },
  
  // 文件上传响应
  UploadResponse: {
    url: ''            // 文件访问URL
  }
} 