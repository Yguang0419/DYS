<template>
    <div class="icon-body">
        <!-- 头部输入框 -->
      <el-input v-model="name" class="icon-search" clearable placeholder="请输入图标名称" @clear="filterIcons" @input="filterIcons">
        <template #suffix>
          <el-icon class="el-input__icon"><search /></el-icon>
        </template>
      </el-input>
      <!-- 显示所有的icon和名字的容器 -->
      <div class="icon-list">
        <div class="list-container">
          <div v-for="(item, index) in iconList" class="icon-item-wrapper" :key="index" @click="selectedIcon(item)">
            <div :class="['icon-item', { active: activeIcon === item }]">
                <!-- 通过组件显示图标 -->
              <svg-icon :name="item" width="16px" height="25px"></svg-icon>
              <span>{{ item }}</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  import icons from './requestIcon'
  import { ref,defineProps,defineEmits } from 'vue';
  // 声明事件
  const emit = defineEmits(['selected'])
  
  defineProps({
    activeIcon: String
  })
  
  const name = ref();
  const iconList = icons;
  
  function filterIcons() {
    iconList.value = icons
    if (name.value) {
      iconList.value = iconList.value.filter(item => item.includes(name.value))
    }
  }
  
  function selectedIcon(name) {
    emit('selected',name)
    document.body.click()
  }
  
  function reset() {
    name.value = ''
    iconList.value = icons
  }
  </script>
  
  <style rel="stylesheet/scss" lang="scss" scoped>
    .icon-body {
      width: 100%;
      padding: 10px;
      .icon-search {
        position: relative;
        margin-bottom: 5px;
      }
      .icon-list {
        height: 200px;
        overflow: auto;
        .list-container {
          display: flex;
          flex-wrap: wrap;
          .icon-item-wrapper {
            width: calc(100% / 3);
            height: 25px;
            line-height: 25px;
            cursor: pointer;
            display: flex;
            .icon-item {
              display: flex;
              max-width: 100%;
              height: 100%;
              padding: 0 5px;
              &:hover {
                background: #ececec;
                border-radius: 5px;
              }
              .icon {
                flex-shrink: 0;
              }
              span {
                display: inline-block;
                vertical-align: -0.15em;
                fill: currentColor;
                padding-left: 2px;
                overflow: hidden;
                text-overflow: ellipsis;
                white-space: nowrap;
              }
            }
            .icon-item.active {
              background: #ececec;
              border-radius: 5px;
            }
          }
        }
      }
    }
  </style>