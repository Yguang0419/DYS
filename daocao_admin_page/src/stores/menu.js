// 引入pinia
import { defineStore } from 'pinia'
// 导入查询用户菜单接口
import { searchSelfRouter } from '@/api/user/index.js'
// const modules = import.meta.glob('../views/**/*.vue')
// 定义 store 并导出
export const useMnuStore = defineStore('menu', {
    // 定义状态【数据】
    state: () => ({ 
        menuList: [],
        routerList: [], // 动态路由数据，也就是左侧菜单的路由信息
        tabList: [{title:'首页',path:"/index",isClose: false}],// 所有的tab
        activeTab: '/index'// 当前选中的tab，通过path体现
    }),
    // 获取数据的方法
    getters: {
        Array: (state) => state.menuList,
        Array: (state) => state.routerList,
        Array: (state) => state.tabList,
        String: (state) => state.activeTab
    },

    // 修改数据方法
    actions: {
        setMenuList(data) {
            this.menuList = data;
        },
        // 渲染动态路由数据结构，存储到pinia中，不需要每次都去渲染数据结构了
        // data就是查询出来的用户菜单
        // setRouterList(data) {
        //     data.forEach(item => {
        //         // 定义数据结构
        //         let routerInfo = {
                    
        //             name: item.menuName,
        //             path: item.path,
        //             meta: {title: item.menuName},
        //             // 设置组件，
        //             component: () => import(`./src/views/${item.path}.vue`)
        //             // component: modules[`../views/${item.path}.vue`]
        //         }
        //         // 将路由信息添加到数组中
        //         this.routerList.push(routerInfo)
        //         // 设置子菜单
        //         let childrenList = item.children;
        //         childrenList.forEach(children => {
        //             let routerInfo = {
        //                 name: children.menuName,
        //                 path: children.path,
        //                 meta: {title: children.menuName},
        //                 // 设置组件，
        //                 component: () => import(`./src/views/${children.path}.vue`)
        //                 // component: modules[`../views/${children.path}.vue`]
        //             }
        //             this.routerList.push(routerInfo)
        //         })
        //     })
        // },
        // 查询用户菜单，并且生成动态路由数据结构
        generateRouter() {
            return new Promise((resolve,reject) => {
                // 查询用户的菜单
                searchSelfRouter().then(res => {
                    if(res.data.code == 200) {
                        console.log("res=====>",res);
                        this.setRouterList(res.data.data);
                        resolve()
                    }
                    reject()
                })
            })
        },
        // 设置tabList {title:'首页',path:'/index'}
        setTabList(data) {
            this.tabList.push(data);
        },
        // 删除tabList
        delTabList(name) {
            // 一定要重新赋值
            this.tabList = this.tabList.filter(item => {
                if(item.path == name) {
                    return false
                }else {
                    return true;
                }
            })
        },
        // 设置activeTab
        setActive(name) {
            this.activeTab = name;
        }
    },

    // 使用持久化
    // persist: {
    // 	enabled: true,
    //     storage: localStorage,
    //     key: 'useMenu',
    //     path: ['menuList','routerList']
    // }
})