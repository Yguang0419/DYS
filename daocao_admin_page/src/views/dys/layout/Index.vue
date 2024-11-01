<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <Header/>
      </el-header>
      <el-container>
        <el-aside right="30%">
            <Aside/>
        </el-aside>
        <el-main>
            <Main/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script setup>
    // <!-- 导入布局组件 -->
    import Header from './Header.vue'
    import Main from './Main.vue'
    import Aside from './Aside.vue'
</script>


<style scoped>

</style>
