<template>
      <el-tabs
            v-model="activateTab"
            type="card"
            closable="true"
            class="demo-tabs"
            @tab-change="gotoActive"
            @tab-remove="closeTab"
        >
            <el-tab-pane
            v-for="item in tabList"
            :key="item.path"
            :label="item.title"
            :name="item.path"
            >
                <RouterView/>
            </el-tab-pane>
  </el-tabs>
</template>

<script setup>
    import{useMnuStore}from'@/stores/menu.js'
    import{storeToRefs} from'pinia'
    import { useRouter } from 'vue-router';
    import {RouterView} from 'vue-router'   
    import {ref} from 'vue'
    const router=useRouter()
    const menustores=useMnuStore()
    const {tabList,activeTab}=storeToRefs(menustores)

    //从store获取tab数据
    // 选中
    function gotoActive(path){
        // 修改activeTab
        menustores.setActive(path)
        //跳转路由页面

        router.push(path)
    }
    // 移除
    function closeTab(path){
        //移除的是否是当前激活的tab
        if(path == menuStore.activeTab){
            // 移除且激活另一个页面
            menustores.setActive('/index');
            router.push('/index');
        }
        menustores.delTabList(path);
        console.log("closeTab====================>",path)
    }
</script>

<style lang ='scss' scoped >

</style>