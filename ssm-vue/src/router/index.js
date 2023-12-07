import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '首页', icon: 'dashboard' }
    }]
  },
  {
    path: '/base',
    component: Layout,
    redirect: '/base/class',
    name: 'Example',
    meta: { title: '基础资料', icon: 'component' },
    children: [
      {
        path: 'class',
        name: 'class',
        component: () => import('@/views/class/index'),
        meta: { title: '班级管理', icon: 'peoples' }
      },
    ],
  },
  {
    path: '/sys',
    component: Layout,
    redirect: '/sys/user',
    name: 'Example',
    meta: { title: '系统管理', icon: 'component' },
    children: [
      {
        path: 'user',
        name: 'User',
        component: () => import('@/views/user/index'),
        meta: { title: '用户管理', icon: 'peoples' }
      },
    ],

  },
  {
    path: '/base',
    component: Layout,
    redirect: '/base/stu',
    name: 'Base',
    meta: { title: '基础数据', icon: 'list' },
    children: [
      {
        path: 'stu',
        name: 'stu',
        component: () => import('@/views/stu/index'),
        meta: { title: '学生管理', icon: 'peoples' }
      },
    ]
  },
  {
    path: '/base',
    component: Layout,
    redirect: '/base/user',
    name: 'Example',
    meta: { title: '教材管理', icon: 'component' },
    children: [
      {
        path: 'book',
        name: 'book',
        component: () => import('@/views/book/index'),
        meta: { title: '教材管理', icon: 'peoples' }
      },
    ],

  },
  {
    path: '/base',
    component: Layout,
    redirect: '/base/classroom',
    name: 'Example',
    meta: { title: '教室管理', icon: 'component' },
    children: [
      {
        path: 'classroom',
        name: 'classroom',
        component: () => import('@/views/classroom/index'),
        meta: { title: '教室管理', icon: 'peoples' }
      },
    ],

  },
  {
    path: '/base',
    component: Layout,
    redirect: '/base/teacher',
    name: 'Example',
    meta: { title: '教师管理', icon: 'component' },
    children: [
      {
        path: 'teacher',
        name: 'teacher',
        component: () => import('@/views/teacher/index'),
        meta: { title: '教师管理', icon: 'peoples' }
      },
    ],

  },

  {
    path: '/base',
    component: Layout,
    redirect: '/base/announce',
    name: 'Example',
    meta: { title: '公告管理', icon: 'component' },
    children: [
      {
        path: 'announce',
        name: 'announce',
        component: () => import('@/views/announce/index'),
        meta: { title: '公告管理', icon: 'peoples' }
      },
    ],

  },
  // {
  //   path: 'external-link',
  //   component: Layout,
  //   children: [
  //     {
  //       path: 'https://github.com/Zephon-H',
  //       meta: { title: 'External Link', icon: 'link' }
  //     }
  //   ]
  // },
  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
// eslint-disable-next-line
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
