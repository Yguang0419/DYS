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
    <el-col :span="18">
      <div class="grid-boggercontent ep-bg-purple-light" >
        <div class="markdown-body"  v-html="article.boggerContent"></div>
      </div>

    </el-col>
    <el-col :span="2">
      <div class="grid-content ep-bg-purple" />
    </el-col>
  </el-row>

</template>
<script setup>
  import {searchOneBogger} from '@/api/dys/Boggers'
  import router from '@/router';
  import { nextTick,onMounted,ref } from 'vue'
  import { useRoute } from 'vue-router';
  import 'github-markdown-css';
  import {marked} from 'marked';


  // import 'md-editor-v3/lib/style.css';

  marked.setOptions({
  highlight: function (code) {
    return hljs.highlightAuto(code).value;
  }
});
  const article = ref({
    boggerTitle: undefined,
    boggerContent: undefined,
    boggerAutho: undefined,
    boggerCreateTime: undefined,
    authorName:undefined
  });
  const route = useRoute();

  onMounted(()=>{
    BoggerDetail()
  })
  // 获取文章详情
  const BoggerDetail = async () => {
    const id = route.params.id;
    try {
      const response = await searchOneBogger(id);
      article.value.boggerTitle      = response.data.data.boggerTitle;
      article.value.boggerContent    = marked(response.data.data.boggerContent);
      article.value.boggerAutho      = response.data.data.boggerAuthor;
      article.value.boggerCreateTime = response.data.data.boggerCreateTime; 
      article.value.authorName       = response.data.data.authorName;
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
  /* .markdown-body blockquote  {
    border-left: 4px solid #61b375;
    padding: 0 15px;
    color: #000000;
}
  .markdown-body body {
      font-family: "Open Sans","Clear Sans", "Helvetica Neue", Helvetica, Arial, 'Segoe UI Emoji', sans-serif;
      color: rgb(0, 0, 0);
      line-height: 1.6;
  }
  .markdown-body #write {
    max-width: 1200px;
    margin: 0 auto;
    padding: 30px;
    padding-bottom: 100px;
} */


</style>
