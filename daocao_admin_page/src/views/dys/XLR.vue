<template>
  <!-- 卜卦 -->
   <div class="xl_btn">
  <el-button plain @click="visible = true">
    卜卦
  </el-button>
  <el-dialog v-model="visible" :show-close="false" width="500">
    <template #header="{ close, titleId, titleClass }">
      <div class="my-header">
        <h4 :id="titleId" :class="titleClass">
          <el-text class="mx-1">求仙问卜，</el-text>
          <el-text class="mx-1" type="danger" size="large">不如自己做主！</el-text>
        </h4>
        <el-button type="danger" @click="close">
          <el-icon class="el-icon--left">
            <CircleCloseFilled />
          </el-icon>
          关闭
        </el-button>
      </div>
    </template>
    <div class="zhanbu_content">
        <div class="wenzi_content">
            <span>{{ result.Nonli_str }}</span>
            <h1>天干：{{result.tianGan}}</h1>
            <h1>地支:{{result.diZhi}}</h1>
            <h1>神将:{{result.shenJiang}}</h1>
        
            <img src="../../static/xiaoliuren/xiaoliuren_241028.jpg" style="width: 100%;height: auto;">
        </div>
      <el-card style="max-width: 250px" shadow="hover">
        <template #header >{{result.zhanbu_res  }}</template>
        <div class="img_content">
          <img class="previewable-image" src="../../static/xiaoliuren/xiaoliuren_241027.jpg"
          />
        </div>
      </el-card>
    </div>
  </el-dialog>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { CircleCloseFilled } from '@element-plus/icons-vue'
import calculateXiaoliuren from '../../api/common/xiaoliuren'
const visible = ref(false)
const date = new Date()
const result = calculateXiaoliuren(date)
// ../../assets/xiaoliuren/xiaoliuren_241037.jpg"
// 获取随机数
  function getRandomInt(min, max) {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min + 1)) + min;
  }
// 获取随机图片路径
const randomInt = getRandomInt(241026, 241077);
const imagePath = `../../static/xiaoliuren/xiaoliuren_${randomInt}.jpg`
</script>

<style scoped>
  .my-header {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    gap: 16px;
  }
  .zhanbu_content {
      display: flex;
      align-items: stretch; /* 使两个子元素高度一致 */
  }

  .wenzi_content, .img_content {
      flex: 1; /* 每个子元素占据一半的宽度 */
      padding: 10px; /* 可选：添加内边距 */
  }

  .img_content img {
      width: 110%; /* 图片宽度占满 img_content */
      height: auto; /* 保持图片的宽高比 */
  }
  .previewable-image {
      cursor: pointer;
    }
    
</style>