<template>
  <div class="layout-Main">
    <!-- 上方组件区域 -->
    <di class="top-area">
      <!-- 搜索框 -->
      <transition name="rotate-out">
      <div class="search-area">
        <!-- <el-input v-model="input2" style="width: 240px" placeholder="Type something" :prefix-icon="Search" /> 
         -->
         <XLR/>
      </div>
    </transition>
      <!-- 按钮（排序和上传） -->
      <div class="upload-area">
        <el-upload v-model:file-list="fileList" class="upload-demo"
          action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15" multiple :on-preview="handlePreview"
          :on-remove="handleRemove" :before-remove="beforeRemove" :limit="3" :on-exceed="handleExceed">
          <el-button type="primary">Click to upload</el-button>
        </el-upload>
      </div>
    </di>
    <!-- 内容区域（右下角） -->
    <Conten />
  </div>
</template>
<script setup>
  import { ElMessage, ElMessageBox ,ElInput } from 'element-plus'
  import Conten from './Conten.vue'
  import XLR from '@/views/dys/XLR.vue'
  import { Calendar, Search } from '@element-plus/icons-vue'
  import { nextTick,ref } from 'vue'
  const dynamicTags = ref(['Tag 1', 'Tag 2', 'Tag 3'])
  const count = ref(0)
  const load = () => {
    count.value += 2
  }


  const fileList = ref([

]);
// 上传文件功能
  const handleRemove = function(file, uploadFiles) {
    console.log(file, uploadFiles);
  };

  const handlePreview = function(uploadFile) {
    console.log(uploadFile);
  };

  const handleExceed = function(files, uploadFiles) {
    ElMessage.warning(
      `The limit is 3, you selected ${files.length} files this time, add up to ${files.length + uploadFiles.length} totally`
    );
  };

  const beforeRemove = function(uploadFile, uploadFiles) {
    return ElMessageBox.confirm(
      `Cancel the transfer of ${uploadFile.name} ?`
    ).then(function() {
      return true;
    }, function() {
      return false;
    });
  };
// 删除标签功能
let inputValue = ref('')
let inputVisible = ref(false)
let InputRef = ref(null)
const handleClose = function(tag) {
  let index = dynamicTags.value.indexOf(tag)
  if (index !== -1) {
    dynamicTags.value.splice(index, 1)
  }
}

const showInput = function() {
  inputVisible.value = true
  nextTick(function() {
    InputRef.value.input.focus()
  })
}

const handleInputConfirm = function() {
  if (inputValue.value) {
    dynamicTags.value.push(inputValue.value)
  }
  inputVisible.value = false
  inputValue.value = ''
}
</script>



<style scoped>
/* 顶部区域 */
.top-area {
  display: flex;
  justify-content: flex-end;
  margin-right: 20px;
}

.search-area,
.upload-area {
  display: inline-flex;
  align-items: center;
}

.search-area + .upload-area {
  margin-left: 10px;
}
/* 定义旋转动画 */
.rotate-out-enter-active,
.rotate-out-leave-active {
  transition: transform 0.5s ease;
}

.rotate-out-enter-from,
.rotate-out-leave-to {
  transform: rotate(360deg);
  opacity: 0;
}

.rotate-out-enter-to,
.rotate-out-leave-from {
  transform: rotate(0deg);
  opacity: 1;
}


</style>