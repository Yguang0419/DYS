<template>
    <div class="layout-Header">
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" :ellipsis="false"
            @select="handleSelect">
            <el-menu-item index="0">
                <img style="width: 50px" src="../../../assets/dys.ico" alt="Element logo" />
            </el-menu-item>
            <div class="flex-grow" />
            <!-- <el-menu-item index="1"></el-menu-item> -->
            <el-sub-menu index="2">
                <template #title>工作空间</template>
                <el-menu-item index="2-1" @click="addNewText()">发布新文章</el-menu-item>
                <el-menu-item index="2-2" @click="addNewTask()">新增任务</el-menu-item>
                <el-menu-item index="2-3" @click="toOperate()">场景操作</el-menu-item>
                <el-sub-menu index="2-4">
                    <template #title>开发中</template>
                    <el-menu-item index="2-4-2">暂无</el-menu-item>
                    <el-menu-item index="2-4-3">暂无</el-menu-item>
                </el-sub-menu>
            </el-sub-menu>
        </el-menu>
    </div>

    <el-dialog v-model="dialogFormVisible" title="添加任务信息" width="500" align-center>
        <Task/>
    </el-dialog>

    <el-drawer v-model="drawer" title="任务清单" :with-header="false">
        <TaskForm/>
    </el-drawer>
</template>
<script setup>

    import { useRouter } from 'vue-router';
    import { ref } from 'vue'
    import  Task from '../Task.vue'
    import TaskForm from '../TaskForm.vue'

    // 引入router
    // import { useRouter } from 'vue-router'
    const router = useRouter()
    const dialogFormVisible =ref(false) 
    const drawer  =ref(false)


    const addNewText = function(){
        router.push("/dys/publish")
    }
    // 新增任务
    const addNewTask =function(){
        dialogFormVisible.value=true;
        drawer.value=true
        console.log("addNewTask");
    }
    //场景操作
    const toOperate =function(){
       
        router.push("/dys/operates")
    }
</script>



<style scoped>
.flex-grow {
    flex-grow: 1;
  }
</style>