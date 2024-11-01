<template>
    <div>
        <el-menu
            background-color="#304157"
            text-color="#b1becd"
            active-text-color="#336cab"
    >
        <!-- 遍历子元素 -->
        <template v-for="menu in menuList" :key="menu.path">">
            <!-- 判断是否有子菜单 -->
             <el-sub-menu v-if="hashChildren(menu)" :index="menu.path">
                <template #title>
                    <!-- <el-icon><setting /></el-icon> -->
                    <span>{{ menu.menuName }}</span>
                </template>
                <!-- 渲染子菜单 -->
                 <el-menu-item-group>
                    <el-menu-item 
                        v-for="children in menu.children"
                        :key="children.path"
                        @click="handlerRouter(children)"
                    >
                        <span> {{ children.menuName }}</span>
                    </el-menu-item>
                 </el-menu-item-group>
             </el-sub-menu>
             <!-- 没有子菜单 -->
             <el-menu-item v-else :index="menu.path" @click="handlerRouter(menu)">
                <span> {{ menu.menuName }}</span>
             </el-menu-item>
        </template>
        </el-menu>
    </div>
</template>

<script setup>
    // 1.获取所有菜单
    import{useMnuStore}from'@/stores/menu.js'

    // 放到ref中
    import{storeToRefs} from'pinia'
    import { reactive } from 'vue';
    // 引入vue-router
    import { useRouter } from 'vue-router';
    // 2.获取所有菜单
    const router=useRouter()
    const menustores=useMnuStore()
  
    const menuList =reactive([
          {
            path:"/index",
            menuName:'首页'
          },
        {
            path:"/system/user/index",
            menuName:'用户管理'
        },
        {
            path:"/system/role/index",
            menuName:'权限管理'
        },
        {
            path:"/system/menu/index",
            menuName:'菜单管理'
        }
    ]
        
    )

    // 判断是否有子菜单
    function hashChildren(menu){
    
        if(menu.children && menu.children.length>0){
            return true
        }
        return false
    }
    // 切换路由
    function handlerRouter(menu){
        // 跳转页面的同时向tablist添加数据（已经添加过就不用添加了）
        let hashNode = menustores.tabList.filter(item => item.path == menu.path)
        if(hashNode == null || hashNode.length == 0){
            let data ={title:menu.menuName,path:menu.path}
            menustores.setTabList(data)
        }
        //修改ActiveTab的值
        menustores.setActive(menu.path)
    
        router.push(menu.path)
    }

</script>

<style lang ='scss' scoped >

</style>