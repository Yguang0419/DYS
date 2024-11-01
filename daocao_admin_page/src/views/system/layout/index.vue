<template>
    <div class="common-layout">
      <el-container>
        <el-aside  class="aside"  :style="{heigt:height+'px',minHeight:minHeight+'px'}">
          <Aside/>
        </el-aside>
        <el-container>
          <el-header class="header">
            <Header/>
          </el-header>
          <el-main>
            <Main/>
          </el-main>
        </el-container>
      </el-container>
    </div>
</template>

<script setup>
  // 导入左侧导航组件
  import Aside from '../layout/Aside/index.vue'
  import Header from '../layout/Header/index.vue'
  import Main from '../layout/Main/index.vue'
  import { searchSelfRouter } from '@/api/user/index.js'
  import {ref,onMounted} from 'vue'
  // 导入顶部导航组件
  const height=ref(0)
  const minHeight=ref(0)
  onMounted(()=>{
    getHeight()
    // 监听页面缩放事件
    minHeight.value = document.documentElement.clientHeight;
    window.addEventListener('resize',getHeight)
  })
// 获取文档的高度
  function getHeight(){
    height.value=document.documentElement.clientHeight
  }


</script>

<style lang ='scss' scoped>
.aside{
  background-color: #2f4156;
}
.header{
  // display: flex;
  // justify-content: space-between;
  // // align-items: center;
  // background-color: #fff;
  // box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}
</style>