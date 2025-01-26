import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import ArticlesView from '../views/ArticlesView.vue'
import CategoriesView from '../views/CategoriesView.vue'
import AboutView from '../views/AboutView.vue'
import ArticleDetail from '../views/article/ArticleDetail.vue'
import AdminLayout from '../layouts/AdminLayout.vue'
import LoginView from '../views/admin/LoginView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/articles',
      name: 'articles',
      component: ArticlesView
    },
    {
      path: '/article/:id',
      name: 'article-detail',
      component: ArticleDetail
    },
    {
      path: '/categories',
      name: 'categories',
      component: CategoriesView
    },
    {
      path: '/about',
      name: 'about',
      component: AboutView
    },
    // 管理界面路由
    {
      path: '/admin/login',
      name: 'login',
      component: LoginView,
      meta: {
        title: '管理员登录'
      }
    },
    {
      path: '/admin',
      component: AdminLayout,
      children: [
        {
          path: '',
          redirect: '/admin/dashboard'
        },
        {
          path: 'dashboard',
          name: 'admin-dashboard',
          component: () => import('../views/admin/DashboardView.vue'),
          meta: {
            title: '仪表盘'
          }
        },
        {
          path: 'articles',
          name: 'admin-articles',
          component: () => import('../views/admin/ArticlesView.vue'),
          meta: {
            title: '文章管理'
          }
        },
        {
          path: 'articles/create',
          name: 'admin-article-create',
          component: () => import('../views/admin/ArticleEditView.vue'),
          meta: {
            title: '创建文章'
          }
        },
        {
          path: 'articles/edit/:id',
          name: 'admin-article-edit',
          component: () => import('../views/admin/ArticleEditView.vue'),
          meta: {
            title: '编辑文章'
          }
        },
        {
          path: 'categories',
          name: 'admin-categories',
          component: () => import('../views/admin/CategoriesView.vue'),
          meta: {
            title: '分类管理'
          }
        },
        {
          path: 'tags',
          name: 'admin-tags',
          component: () => import('../views/admin/TagsView.vue'),
          meta: {
            title: '标签管理'
          }
        },
        {
          path: 'comments',
          name: 'admin-comments',
          component: () => import('../views/admin/CommentsView.vue'),
          meta: {
            title: '评论管理'
          }
        },
        {
          path: 'settings',
          name: 'admin-settings',
          component: () => import('../views/admin/SettingsView.vue'),
          meta: {
            title: '系统设置'
          }
        }
      ]
    }
  ]
})

// 路由守卫
router.beforeEach((to, from, next) => {
  // 设置页面标题
  if (to.meta.title) {
    document.title = `${to.meta.title} - Liar Blog`
  }

  // 检查是否需要登录认证
  if (to.matched.some(record => record.meta.requiresAuth)) {
    // TODO: 检查登录状态
    const isLoggedIn = localStorage.getItem('token')
    if (!isLoggedIn) {
      next({
        path: '/admin/login',
        query: { redirect: to.fullPath }
      })
    } else {
      next()
    }
  } else {
    next()
  }
})

export default router 