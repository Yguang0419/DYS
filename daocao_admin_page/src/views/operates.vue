<template>
  <el-button type="primary" size="large" @click="dialogTableVisible = true">添加</el-button>
  <operatesList></operatesList>
  <div class="DemoMessage_container">
    <!-- 添加弹窗 -->
    <el-dialog v-model="dialogTableVisible" title="新增操作案例" width="800">
      <div class="demo-message-container">
        <div class="DemoMessage_input">
          <el-form ref="formRef" style="max-width: 600px" :model="FormMessage" status-icon  label-width="auto"
            class="demo-ruleForm">
            <!-- 创建者 -->
            <el-form-item label="创建者" prop="pass">
              <el-input
                v-model="FormMessage.creator"
                style="width: 240px"
                disabled
                placeholder="Please input"
              />
            </el-form-item>
            <!-- 案例名称 -->
            <el-form-item label="名称" prop="checkPass">
              <el-input v-model="FormMessage.opName" style="width: 240px" placeholder="输入案例的名称" />
            </el-form-item>
            <!-- 案例的URL -->
            <el-form-item label="URL" prop="checkPass">
              <el-input v-model="FormMessage.opUrl" style="width: 240px" placeholder="输入url,方便访问,例如 /op/yz" />
            </el-form-item>
            <!-- 版本 -->
            <el-form-item label="版本" prop="age">
              <el-input-number v-model="FormMessage.version" :precision="2" :step="0.1" :max="10" />
            </el-form-item>
            <!-- 操作控制 -->
            <el-form-item label="操作控制">
              体验<el-switch v-model="FormMessage.experience" />
              <span class="switch-gap"></span>
              编辑<el-switch
                    v-model="FormMessage.modify"
                    class="ml-2"
                    style="--el-switch-on-color: #13ce66; --el-switch-off-color: #ff4949"
                  />
            </el-form-item>
            <!-- 详细描述 -->
            <el-form-item label="描述案例" prop="age">
              <el-input
                v-model="FormMessage.description"
                style="width: 240px"
                :rows="2"
                type="textarea"
                placeholder="描述案例"
              />
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm(ruleFormRef)">
                提交
              </el-button>
              <el-button @click="resetForm(ruleFormRef)">重置</el-button>
            </el-form-item>
          </el-form>
        </div>
        <!-- 上传文件 -->
        <div class="DemoMessage_upload">
          <el-upload
            class="upload-demo"
            drag
            action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
            multiple
          >
            <el-icon class="el-icon--upload"><upload-filled /></el-icon>
            <div class="el-upload__text">
              上传Mapper,service,serviceImpl,controller,sql代码文件 <em>click to upload</em>
            </div>
            <template #tip>
              <div class="el-upload__tip">
                jpg/png files with a size less than 500kb
              </div>
            </template>
          </el-upload>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script setup>
import { ElNotification } from 'element-plus'
import { ref, reactive } from 'vue'
import { UploadFilled } from '@element-plus/icons-vue'
import operatesList from './dys/oprates/operates_components.vue'
// 获取API-js函数
import { ADDdemo, OpBroker } from '@/api/oprate/oprate'
// 表单文件
const FormMessage = ref({
  opId: 11586,
  opUrl: undefined,
  creator: '花不语',
  opName: undefined,
  version: undefined,
  description: undefined,
  experience:true,
  modify:true
})
const dialogTableVisible = ref(false)
// 表单引用
const formRef = ref(null)

const submitForm = () => {
  console.log("表单数据：", FormMessage.value)
        // 调用 API 提交表单数据
        ADDdemo(FormMessage.value).then(res => {
          if (res.code === 200) {
            console.log("新增案例成功====》", res)
            // 关闭表单
            dialogTableVisible.value = false
            // 弹窗提示
            openSuccess()
          } else {
            openFail()
          }
      })
    }



const resetForm = (formRef) => {
  if (!formRef) return
  formRef.resetFields()
}

// 成功弹框
const openSucess = () => {
  ElNotification({
    title: '成功',
    message: '操作成功',
    type: 'success',
  })
}
// 失败弹框
const openFail = () => {
  ElNotification({
    title: '失败',
    message: '操作失败',
    type: 'error',
  })
}

</script>

<style scoped>
.demo-message-container {
  display: flex;
  justify-content: space-between; /* 在子元素之间均匀分配空白空间 */
  align-items: flex-start;         /* 垂直对齐方式，顶部对齐 */
}

.DemoMessage_input {
  flex: 1; /* 让输入区域占据剩余空间 */
  margin-right: 10px; /* 右边距，可根据需要调整 */
}

.DemoMessage_upload {
  flex: 0 0 auto; /* 上传区域固定宽度，不占据剩余空间 */
  width: 400px;
  margin-left: 10px; /* 将此元素推到最右侧 */
}
.switch-gap {
  display: inline-block;
  width: 20px; /* 根据需要调整间隙大小 */
}
</style>