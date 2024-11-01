import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'
import Layout from '../views/system/layout/index.vue'

// 配置路由规则
const constRouter = [
  // 登录页面
  {
    path: '',
    redirect: "/login"
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },

  // 管理系统首页
  {
    path: '/sys/index',
    component: Layout,
    children: [
      {
        path: '/sys/index',
        component: ()=>import('@/views/index.vue')
      },
      {
        path: '/system/menu/index',
        hidden: true,
        component: ()=>import('@/views/system/menu/index.vue')
      },
      {
        path: '/system/role/index',
        hidden: true,
        component: ()=>import('@/views/system/role/index.vue')
      },
      {
        path: '/system/user/index',
        hidden: true,
        component: ()=>import('@/views/system/user/index.vue')
      }
    ]
  },
  // 前台业务系统
  {
    path: '/dys/index',
    name: 'dys_index',
    component: ()=>import('@/views/dys/layout/Index.vue')
  },
  {
    path: '/dys/publish',
    name: 'dys_publish',
    component: ()=>import('@/views/dys/layout/TextEditor.vue')
  },
  {
    path: '/dys/detail/:id',
    name: 'dys_detail',
    component: ()=>import('@/views/dys/Deatail.vue')
  },
  {
    path: '/404',
    hidden: true,
    component: ()=>import('@/views/404.vue')
  },
  {
    path: '/dys/operates',
    name: 'operates',
    component:()=>import('@/views/operates.vue')
  }
  
]


// 创建路由
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  // 使用路路由规则常量
  routes: constRouter
})

// 导出router
export default router
