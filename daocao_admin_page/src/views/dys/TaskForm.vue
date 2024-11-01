<template>
      <el-divider content-position="center">任务清单{{ finshedRatio}}</el-divider>
    <!-- 展示任务信息 -->
     <div>
            <el-timeline style="width: 250px;">
                <el-timeline-item  placement="top"
                    v-for="task in TaskList"
                    :key="task.taskId"
                    :timestamp="task.taskStartTime"
                    :icon="getTaskStyleIcon(task.taskState)"
                    :type="getTaskStyleType(task.taskState)"
                    :size ='large'
                >
                    <el-card>
                        <el-switch
                            :disabled ="task.taskState" 
                            active-text="完成"
                            inactive-text="未完成"
                            v-model="task.taskState"
                            class="ml-2"
                            style="--el-switch-on-color: #13ce66; 
                            --el-switch-off-color: #ff4949"
                            @change="CahngeState(task.taskId,task.taskState)"
                        />
                        <div class="demo-collapse">
                            <el-collapse v-model="activeNames" @change="handleChange">
                            <el-collapse-item :title="task.taskName " :name="task.taskId">
                                <div>
                                    <el-text tag="b">&nbsp;&nbsp;&nbsp;&nbsp;{{task.taskDescrption}}</el-text>
                                </div>
                            </el-collapse-item>
                            </el-collapse>
                        </div>
                    </el-card>
                </el-timeline-item>
            </el-timeline>
 
     </div>

</template>
<script setup>
import { ref, onMounted, onUpdated } from "vue";
import {  changeTaskState } from "@/api/dys/Task";
import { TaskStore } from '@/stores/Task.js'
const taskstore = TaskStore()
const activeNames = ref(["1"]);
const TaskList = taskstore.TaskList;
const finshedRatio = ref("");
// 更新完成任务比率的计算属性
const  updateFinshedRatio = function() {
  const finishedTasks = TaskList.filter((task) => task.taskState).length;
  console.log(finishedTasks,TaskList.length);
  finshedRatio.value =  `${finishedTasks}/${TaskList.length}`;
}
// 方法用于计算styleType
const getTaskStyleType = (isFinished) => {
  updateFinshedRatio()
  return isFinished ? "success" : "danger";
};

// 方法用于计算styleIcon
const getTaskStyleIcon = (isFinished) => {
  return isFinished ? "el-icon-check" : "el-icon-close";
};


// 优化任务状态改变的函数,存在bug,初始化时候会自动调用
async function CahngeState(id, isFinshed) {
  // 调用修改任务状态的接口
  console.log(id, "======>",isFinshed);
  try {
    const res = await changeTaskState(id);
    if (res.data.code === 200) {
      TaskList.value.forEach((element) => {
        if (element.TaskId === id) {
          element.StyleType = element.TaskState ? "success" : "danger";
          element.StyleIcon = element.TaskState ? "check" : "close";
        }
      });
      updateFinshedRatio();
    } else {
      ElMessage({
        message: "任务状态修改失败",
        type: "error",
      });
    }
  } catch (error) {
    console.error("修改任务状态失败", error);
  }
}





</script>
<style scoped></style>
