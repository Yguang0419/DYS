<template>
  <el-row class="row-bg">
    <el-col :span="6">
      <div class="grid-content ep-bg-purple" />
    </el-col>
    <el-col :span="6">
      <div class="grid-content ep-bg-purple-light" />
    </el-col>
    <el-col :span="6">
      <div class="grid-content ep-bg-purple" />
    </el-col>
  </el-row>

  <el-row class="row-bg" justify="end">
    <el-col :span="6">
      <div class="grid-content ep-bg-purple" />
    </el-col>
    <el-col :span="6">
      <div class="grid-content ep-bg-purple-light" >
       
      </div>
    </el-col>
    <el-col :span="6">
      <div class="grid-content ep-bg-purple" >
        <el-text tag="b">编辑日期:</el-text>
        {{ article.boggerCreateTime }}
      </div>
    </el-col>
  </el-row>
  <el-row class="row-bg" justify="space-between">
    <el-col :span="6">
      <div class="grid-content ep-bg-purple" />
    </el-col>
    <el-col :span="6">
      <div class="grid-content ep-bg-purple-light" >
        <h2>
          {{article.boggerTitle}}
        </h2>
      </div>
    </el-col>
    <el-col :span="6">
      <div class="grid-content ep-bg-purple" >
        <el-text tag="b">作者：</el-text>
        {{ article.authorName }}
        <el-icon><View /></el-icon>
        {{ article.ViewCount }}
      </div>
    </el-col>
  </el-row>
  <el-row class="row-bg" justify="space-around">
   
    <el-col :span="3">
      <el-divider content-position="left">目录</el-divider>
      <div class="grid-content ep-bg-purple" >
        <el-tree
          style="max-width: 600px"
          :data="data"
          :props="defaultProps"
          @node-click="handleNodeClick"
        />
      </div>
    </el-col>
    <el-col :span="14">
      <div class="grid-boggercontent ep-bg-purple-light" @contextmenu.prevent="showContextMenu($event)">
          <div class="markdown-body" v-html="article.boggerContent" ref="contentDiv"></div>

        </div>
    </el-col>
    <el-col :span="4">
      <div class="grid-content ep-bg-purple" >
      </div>
        <div>
        
            <!-- 侧方标注信息 -->
            <Mark :question="selectedQuestion" :bogger-id="article.boggerId"/>
            
        
        </div>
    </el-col>
  </el-row>

</template>
<script setup>
  import Mark from './layout/Mark.vue'
  import {searchOneBogger,addViewCount} from '@/api/dys/Boggers'
  import router from '@/router';
  import { nextTick,onMounted,ref } from 'vue'
  import { useRoute } from 'vue-router';
  import 'github-markdown-css';
  import {marked} from 'marked';
  // import { View } from '@element-plus/icons-vue/dist/types';
 
  marked.setOptions({
  highlight: function (code) {
    return hljs.highlightAuto(code).value;
  }
});
  const article = ref({
    boggerId: undefined,
    boggerTitle: undefined,
    boggerContent: undefined,
    boggerAutho: undefined,
    boggerCreateTime: undefined,
    authorName:undefined,
    ViewCount: undefined
  });
  const route = useRoute();
  onMounted(()=>{
    BoggerDetail()
  })
  // 获取文章详情
  const BoggerDetail = async () => {
    // 增加阅读量
    await addViewCount(route.params.id)
    const id = route.params.id;
    try {
      const response = await searchOneBogger(id);
      article.value.boggerId      = response.data.data.boggerId;
      article.value.boggerTitle      = response.data.data.boggerTitle;
      article.value.boggerContent    = marked(response.data.data.boggerContent);
      article.value.boggerAutho      = response.data.data.boggerAuthor;
      article.value.boggerCreateTime = response.data.data.boggerCreateTime; 
      article.value.authorName       = response.data.data.authorName;
      article.value.ViewCount        = response.data.data.viewCount;
      console.log("获取文章详情", response.data);
    } catch (error) {
      console.error("获取文章详情失败:", error);
    }
  };
  // 目录控制
  const handleNodeClick = function(data) {
  console.log(data);
};

  // 定义树数据结构
  const data = [
    {
      label: 'Level one 1',
      children: [
        {
          label: 'Level two 1-1',
          children: [
            {
              label: 'Level three 1-1-1',
            },
          ],
        },
      ],
    },
    {
      label: 'Level one 2',
      children: [
        {
          label: 'Level two 2-1',
          children: [
            {
              label: 'Level three 2-1-1',
            },
          ],
        },
        {
          label: 'Level two 2-2',
          children: [
            {
              label: 'Level three 2-2-1',
            },
          ],
        },
      ],
    },
    {
      label: 'Level one 3',
      children: [
        {
          label: 'Level two 3-1',
          children: [
            {
              label: 'Level three 3-1-1',
            },
          ],
        },
        {
          label: 'Level two 3-2',
          children: [
            {
              label: 'Level three 3-2-1',
            },
          ],
        },
      ],
    },
  ];

  // 定义默认属性
  const defaultProps = {
    children: 'children',
    label: 'label',
  };
  // 显示上下文菜单
  const showContextMenu = (event) => {
    event.preventDefault();
    if (event.target.tagName.toLowerCase() === 'p') {
    const selection = window.getSelection();
    const range = selection.getRangeAt(0);
    const selectedText = range.toString();
      // 获取选中文本的位置
    const rect = range.getBoundingClientRect();
    const question = {
      // top: rect.top + window.scrollY,
      // left: rect.left + window.scrollX,
      boggerId:article.value.boggerId,
      top: rect.top-200 ,
      // left: rect.left ,
      text: selectedText
    };
    console.log(question);
   // 和Mark组件进行通信，将选中文本传递给Mark组件，以及位置等信息
   selectedQuestion.value = question;
   
  }
  };
   // 用于传递给 Mark 组件的 selectedQuestion
   const selectedQuestion = ref(null);

</script>
<style scoped>
/* @import '@/assets/markdown.css'; */
  .el-row {
    margin-bottom: 20px;
  }

  .el-row:last-child {
    margin-bottom: 0;
  }

  .el-col {
    border-radius: 4px;
  }

  .grid-content {
    border-radius: 4px;
    min-height: 36px;
    
  }
  .grid-boggercontent {
    border-radius: 4px;
    min-height: 36px;
  }
  /* .question {
    position: absolute;
    background-color: white;
    border: 1px solid #3d0505;
    right: 5px;
    padding: 5px;
    z-index: 1000;
  } */


</style>
