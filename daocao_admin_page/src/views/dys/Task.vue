<template>
    <!-- 添加任务 -->
  <el-form :model="form" label-width="auto" style="max-width: 600px">
    <el-form-item label="任务名称">
      <el-input v-model="form.taskName" />
    </el-form-item>

    <el-form-item label="时间设置">
        <el-col :span="11">
        <el-time-picker
          v-model="form.taskStartTime"
          placeholder="Pick a time"
          style="width: 100%"
        />
      </el-col>
      <el-col :span="2" class="text-center">
        <span class="text-gray-500">-至-</span>
      </el-col>
      <el-col :span="11">
        <el-time-picker
          v-model="form.taskEndTime"
          placeholder="Pick a time"
          style="width: 100%"
        />
      </el-col>
    </el-form-item>

    <el-form-item label="任务描述">
      <el-input v-model="form.taskDescrption" type="textarea" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">创建</el-button>
      <el-button>取消</el-button>
    </el-form-item>
  </el-form>
  <!-- 展示任务 -->

</template>
<script setup>
import { ref,computed  } from 'vue'
import { addTask } from '@/api/dys/Task'
import { TaskStore } from '@/stores/Task.js'
const taskstore = TaskStore()
// 获取时间戳
const form = ref({
    taskId: undefined,
    taskName: undefined,
    taskState: false,
    taskStartTime: undefined,
    taskEndTime: undefined,
    taskDescrption: undefined,
  })


const onSubmit = () => {
    console.log('form', form.value)
    form.value.taskId = Date.now()
    console.log(typeof(form.value.taskStartTime))
    addTask(form.value).then(res=>{
        if(res.data.code==200){
            ElMessage({
                message: '任务添加成功',
                type: 'success',
            })
              // 往pinia中添加数据
              taskstore.addTask(form.value)
            // 重制表单
            form.value = {
                taskId:undefined,
                taskName: undefined,
                taskState: 0,
                taskEndTime: undefined,
                taskStartTime: undefined,
                taskDescrption: undefined,
            };
          

        }else{
            ElMessage({
                message: '任务添加失败',
                type: 'error',
            })
        }
    })

    // 跳转到任务页面
  }

</script>

<style scoped>

</style>
