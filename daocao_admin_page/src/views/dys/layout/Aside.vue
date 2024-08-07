<template>
    <div class="layout-Aside">
      <!-- 头像 -->
      <div class="avatar-image">
        <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" size="large"
          style="width: 100px; height: 100px" />
      </div>
      <!-- 文本区 -->
      <div class="text-area" >
        <!-- 名字 -->
        <div class="nickName" style=" display: flex;align-items: center; justify-content: center;">
          <h3>花不语</h3>
        </div>
        <!-- 签名/介绍 -->
        <div class="introduction">
            <el-text line-clamp="2">
                好雨知时节，当春乃发生。<br />
                随风潜入夜、润物细无声。<br/>
            </el-text>
        </div>

      </div>
    
      <!-- 待完成事项 -->
      <div class="Task-area">
        <TaskForm/>
      </div>

    
    </div>
          <!-- 日历选择器 -->
    <!-- <div class="calendar">
        <Calendar ></Calendar>
    </div> -->
  </template>
  <script setup>
  import { ref } from 'vue'
  import TaskForm from '../TaskForm.vue'

  </script>
  
  
  
  <style scoped>
    /* 定义布局中侧边栏的样式 */
    .layout-Aside {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
    }
    /* .calendar{
      widows: 100%;
    } */



    
 
  </style>