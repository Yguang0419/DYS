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

// 提交任务
const onSubmit = () => {
    console.log('form==========>', form.value)
    form.value.taskId = Date.now()
    console.log(typeof(form.value.taskStartTime))
    addTask(form.value).then(res=>{
        if(res.data.code==200){
            ElMessage({
                message: '任务添加成功',
                type: 'success',
            })
              // 往pinia中添加数据
              console.log("添加任务详情==============================>",form.value)
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
          
        // 建立WebSocket连接
        connectWebSocket();
        }else{
            ElMessage({
                message: '任务添加失败',
                type: 'error',
            })
        }
    })

    // 跳转到任务页面
  }
// 建立WebSocket连接
const connectWebSocket = () => {
  const webSocket = new WebSocket("ws://47.109.137.214/:8099/websocket/1");

  webSocket.onopen = function (event) {
    console.log("WebSocket 连接成功");
  };

  webSocket.onerror = function (event) {
    console.log("WebSocket 连接失败");
  };

  webSocket.onclose = function (event) {
    console.log("WebSocket 连接关闭");
  };

  webSocket.onmessage = function (event) {
    const data = event.data;//所需传输的任务id
    console.log("WebSocke:接受所处理任务，id为：", data);
    // 处理接收到的数据
    handleWebSocketMessage(data);
  };
}

// 处理WebSocket接收到的消息
const handleWebSocketMessage = (data) => {
  // 在这里处理WebSocket接收到的数据
  //弹窗或者调动系统进行提醒
  // 检查浏览器是否支持Notification API
  if ("Notification" in window) {
    // 检查用户是否已经授予了显示通知的权限
    if (Notification.permission === "granted") {
      // 创建并显示一个通知
      var notification = new Notification("提醒", {
        body: "你制定的任务已经开始啦",
        icon: "path/to/icon.png"
      });
    } else if (Notification.permission !== 'denied') {
      // 请求用户的权限
      Notification.requestPermission().then(function (permission) {
        // 如果用户同意，则创建并显示通知
        if (permission === "granted") {
          var notification = new Notification("提醒", {
            body: "你制定的任务已经开始啦",
            icon: "path/to/icon.png"
          });
        }
      });
    }
  } else {
    console.log("您的浏览器不支持Notification API");
  }
}

</script>

<style scoped>

</style>
