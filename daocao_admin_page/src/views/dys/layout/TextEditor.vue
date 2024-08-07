<template>
  <!-- md编辑区 -->
  <div class="MdEditor">
    <MdEditor v-model="text" :toolbarsExclude="toolbarsExclude" :onGetCatalog="getMdTtite" :onUploadImg="onUploadImg"/>
  </div>
  <!-- 按钮 -->
  <div class="Rooter">
    <el-button type="primary" size="large" round>返回</el-button>
    <el-button type="success" size="large" round @click="centerDialogVisible = true">提交</el-button>
  </div>
  <!-- 弹出框 -->
  <el-dialog v-model="centerDialogVisible" title="Warning" width="500" align-center>
    <!-- 弹框内容 -->
    <el-form :model="form" label-width="auto" style="max-width: 600px">
      <el-form-item label="标题">
        <el-input v-model="form.boggerTitle" />
      </el-form-item>
      <el-form-item label="作者">
        <el-select v-model="form.authorName" placeholder="please select your zone">
          <el-option label="Zone one" value="shanghai" />
          <el-option label="Zone two" value="beijing" />
        </el-select>
      </el-form-item>
      <!--  -->
      <!-- <el-form-item label="日期">
        <el-col :span="11">
          <el-date-picker v-model="form.boggerCreateTime" type="date" placeholder="Pick a date" style="width: 100%" />
        </el-col>
      </el-form-item> -->
      <!-- 封面 -->
      <el-form-item label="封面">
          <el-upload
            action="http://localhost:8099/api/upload"
            :on-success="handleSuccess"
            :on-error="handleError"
            :before-upload="beforeUpload"
          >
            <el-button size="small" type="primary">Click to Upload</el-button>
          </el-upload>

        
            <h3>Uploaded Images Preview:</h3>
            <img  :src="form.boggerCoverImage" alt="Uploaded Image" />
          

      </el-form-item>
    </el-form>

    <template #footer>
      <div class="dialog-footer">
        <el-button @click="centerDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="onSubmit">
          确认
        </el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup>
// 导包区
  import { Delete, Download, Plus, ZoomIn } from '@element-plus/icons-vue'
  import { ref } from 'vue';
  import axios from 'axios';
  import { useRouter } from 'vue-router';
  import { MdEditor } from 'md-editor-v3';
  import 'md-editor-v3/lib/style.css';
  import {addBogger} from "@/api/dys/Boggers.js"
// // 定义参数
  const router=useRouter()

  const dialogVisible = ref(false)

  const centerDialogVisible = ref(false)
  // 设置编辑器包括哪些东西
  const toolbarsExclude =ref(['github'])
  const text = ref(`
  基础模板
  # 一级标题
  ## 二级标题
  ### 三级标题
  #### 四级标题
  ##### 五级标题
  ###### 六级标题
  > 引用

  今天任务包括：
  - [√] 吃饭饭
  - [√] 睡觉觉
  - [×] 打游戏
  - [×] ~~学习~~

  **学习计划**
  1. 数学
  2. 英语
  3. 语文

  **吃饭安排**
  - 薯条
  - 汉堡
  - 炸鸡



    `);
  const BaseUrl=ref('http://localhost:8099/Coverimg/')
  const form = ref({
    boggerId:undefined,
    boggerTitle: undefined,
    boggerAuthor: 1,
    boggerContent:text,
    boggerCoverImage:undefined,
    authorName:undefined
  })

    // 博客上传
  const onSubmit = () => {
    centerDialogVisible.value = false
    addBogger(form.value).then(res=>{
      console.log("res=======>",res);
      if(res.data.code ==200){
        // 弹窗提示
        uploadResult('success')
        setTimeout(() => {
            // 跳转页面
          router.replace({
            path: '/dys/index'
          })
        }, 2000)
      

      }else{
        uploadResult('error ')
      }
    })
  }
  const dialogImageUrl = ref('');

  const MdrTitle = ref([]);
// 获取目录信息
  const getMdTtite=function(title) {
    console.log("MdrTitle=======>",title)
    MdrTitle.value=title
  }
  // 关于上传图片的方法
  const handleSuccess = (response, file, fileList) => {
    console.log('File uploaded successfully:', response);
    form.value.boggerCoverImage=response; // 假设服务器返回的URL在response.url中
  };
// 上传结果
  const uploadResult=function(Result) {
    ElNotification({
      title: Result,
      message: `This is a ${Result} message`,
      type: Result,
    })
  }
// 加载动画
const fullscreenLoading = ref(false)
const Loading =function() {
  fullscreenLoading.value = true
  setTimeout(() => {
    fullscreenLoading.value = false
  }, 2000)
}

  const handleError = (error, file, fileList) => {
    console.error('File upload error:', error);
  };

  const beforeUpload = (file) => {
    const isJPG = file.type === 'image/jpeg';
    const isLt2M = file.size / 1024 / 1024 < 2;

    if (!isJPG) {
      ElMessage.error('Upload only supports JPG format!');
    }
    if (!isLt2M) {
      ElMessage.error('Image must be smaller than 2MB!');
    }
    return isJPG && isLt2M;
  };

  const onUploadImg = async (files, callback) => {
  const res = await Promise.all(
    files.map((file) => {
      return new Promise((rev, rej) => {
        const form = new FormData();
        form.append('file', file);

        axios
          .post('http://localhost:8099/api/upload', form, {
            headers: {
              'Content-Type': 'multipart/form-data'
            }
          })
          .then((res) => rev(res))
          .catch((error) => rej(error));
      });
    })
  );

  // 方式一
  callback(res.map((item) => item.data.url));
  };
</script>
<style scoped>
.Rooter {
  display: flex;
  /* 启用 Flexbox */
  justify-content: center;
  /* 水平居中 */
  min-height: 100px;
  /* 设置最小高度 */
  padding: 20px;
  /* 添加内边距 */
  gap: 20px;
  /* 在按钮之间添加间距 */
}
</style>