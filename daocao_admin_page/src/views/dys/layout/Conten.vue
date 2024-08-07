<template>
  <div>
      <el-space :fill="true"   >
        <el-card v-for="item in boggerInfo" :key="item.boggerId" class="box-card">
          <template #header>
            <div class="card-header">
              <!-- 标题 -->
              <router-link :to="{name:'dys_detail',params:{id:item.boggerId}}" class="title-link">
                    <h2>{{item.boggerTitle}}</h2>
              </router-link>

              <el-text class="date" >{{ item.boggerCreateTime }}</el-text>
            </div>
          </template>
            <!-- 展示内容 -->
             <!-- 封面 -->  
             <!-- 内容，展示四行 -->
             <el-text line-clamp="4">
              <span class="markdown-body" v-html="item.boggerContent"></span>
             </el-text>   
        </el-card>
      </el-space>
    </div>
</template>
  <script setup>
    import { nextTick,onMounted,ref } from 'vue'
    import {serachAllBoggers} from '@/api/dys/Boggers'
    import 'github-markdown-css';

    // import MarkdownIt from 'markdown-it'
    import {marked} from 'marked';
    // md = new MarkdownIt();
    const dynamicTags = ref(['Tag 1', 'Tag 2', 'Tag 3'])

    const load = () => {
      count.value += 2
    }
    const fileList = ref([
  
  ]);
  onMounted(()=>{
    getBoggers()
  })
  const boggerList = ref([]); // 声明为响应式引用
  const boggerInfo = ref([]); // 更改为数组，因为我们将向其中添加多个博客信息
// 获取博客信息
  const getBoggers = () => { // 更改函数声明为箭头函数
    serachAllBoggers().then((res) => {
      const list = res.data.data;
      list.forEach((item) => {
        // 创建一个博客信息对象，并填充数据
        const blogItem = {
          boggerId: item.boggerId,
          type: "text",
          boggerCoverImage: Array.isArray(item.boggerCoverImage)
                          ? item.boggerCoverImage // 如果已经是数组，则直接使用
                          : [item.boggerCoverImage],
          boggerTitle: item.boggerTitle,
          boggerContent:marked( item.boggerContent),//将md格式转换成html格式
          boggerCreateTime: item.boggerCreateTime,
          boggerTags: item.boggerTags,
        };
        // 将博客信息对象添加到boggerInfo中
        boggerInfo.value.push(blogItem); // 使用 .value 访问并修改数组
      });
    });
    // 将boggerInfo中的所有博客信息添加到boggerList中
    boggerList.value.push(...boggerInfo.value); // 使用 .value 并解构数组
  };
  

 
  </script>
  
  
  
  <style scoped>
    .card-header {
    display: flex;
    flex-direction: column; /* 内容垂直堆叠 */
    justify-content: center; /* 水平居中对齐 */
    align-items: center; /* 垂直居中对齐 */
    position: relative; /* 设置相对定位，以便绝对定位按钮 */
    height: 50px;
  }

  .date {
    position: absolute; /* 绝对定位按钮 */
    bottom: 0; /* 置底 */
    right: 0; /* 靠右 */
  }
  .title-link {
  color: black;
  text-decoration: none;
}

.title-link:hover {
  text-decoration: underline;
}
  </style>