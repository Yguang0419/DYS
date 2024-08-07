<template>
    <!-- 根div -->
    <div class="login_container">
        <!-- 登录表单 -->
        <div class="login_form">
            <h3>稻草开发平台</h3>
            <el-form ref="formRef" :model="LoginForm" label-width="auto"> 
                <!-- 用户名-->
                <el-form-item label="用户名">
                    <el-input v-model="LoginForm.account" placeholder="请输入账号">
                        <template #prefix>
                            <el-icon><UserFilled /></el-icon>
                        </template>
                    </el-input>
                </el-form-item>
                <el-form-item label="密码">
                    <el-input v-model="LoginForm.password" type="password" placeholder="请输入密码">
                        <template #prefix>
                            <el-icon><Lock /></el-icon>
                        </template>
                    </el-input>
                </el-form-item>
             
                <div class="form-footer">
                    <!-- 记住我 -->
                    <el-checkbox v-model="LoginForm.Isremeberme" label="记住我" />
                    <!-- 忘记密码 -->
                    <el-link href='https://element-plus.org' type="primary">忘记密码？</el-link>
                </div>
                <!-- 分割线 -->
                <el-divider content-position="center">其他登录方式</el-divider>
            </el-form>
            <!-- 其他登录方式 -->
             <div class="icon-container">
                <el-icon><ChromeFilled /></el-icon>
                <el-icon><ElementPlus /></el-icon>
                <el-icon><SwitchFilled /></el-icon>
             </div>
            <!-- 按钮 -->
             <el-button @click="handlelogin">登录</el-button>
        </div>
        <!-- 右侧 图片 -->
    </div>
  
</template>

<script setup>
    //导入ref
    import { ref } from 'vue'
    // 导入login方法
    import { login } from '@/api/auth';
    import { serachAllTasks, changeTaskState } from "@/api/dys/Task";
    // 引入token方法
    import { setToken } from '@/utils/token';
    // 引入store
    // import { useMnuStore } from '@/stores/menu.js'
    // import { useUserStore } from '@/stores/user.js'
    import { TaskStore } from '@/stores/Task.js'
    import { useRouter } from 'vue-router';
    // 引入router
    // import { useRouter } from 'vue-router'
    const router = useRouter()
    // 构建store
    const taskstore = TaskStore()

    //声明表单绑定的值
    const LoginForm=ref({
        account:undefined,
        password:undefined,
        Isremeberme:'false'

    })
    // 是否选择记住我
    const Isremeberme=ref(false)
    // 获取任务清单
    const getTaskList = function(){
        serachAllTasks().then(res => {
            console.log("获取任务清单===>", res)
            taskstore.setTaskList(res.data.data)
        })
    }

    // 定义登录方法
    function handlelogin() {
        console.log("登录")
        
        // 调用login方法
        login(LoginForm.value).then(res => {
            console.log("登录===>", res)
            // 判断是否登录成功
            if (res.data.code == 200) {
                // 将token存储seesion中
                setToken("daocaoToken", res.data.token)
                getTaskList()

                // router.addRoute({
                //     path: '/index',
                //     component: () => import('../layout/index.vue')
                // })
                router.push("/dys/index")


            }

        })
    }
        

</script>

<style lang ='scss' scoped>
.login_container{
    //背景图
    background-image: url('../assets/bgimage/background.jpg');
        /* 图片铺满容器 */
        background-size: cover;
    /* 图片位置居中 */
    background-position: center;
    /* 保证背景图不被内容撑开 */
    background-repeat: no-repeat;
    /* 让容器充满整个屏幕 */
    width: 100%;
    height: 100vh;
    /* 确保容器在页面中是独立的一层 */
    position: absolute;
    top: 0;
    left: 0;
/* 登录表单样式 */
    .login_form {
        /* 设置表单宽度和高度 */
        width: 300px;
        height: 300px;
        
        /* 设置背景颜色为白色 */
        background-color: #fff;
        
        /* 边框圆角，让表单看起来更柔和 */
        border-radius: 10px;
        
        /* 绝对定位，以便通过top, left及transform居中 */
        position: absolute;
        top: 50%;
        left: 50%;
        /* 使用transform中心对齐 */
        transform: translate(-50%, -50%);
        
        /* 内边距，增加内容与边界的间距 */
        padding: 20px;
        
        /* 设置盒模型为border-box，包含padding和border在宽高内 */
        box-sizing: border-box;
        
        /* h3样式，让标题居中显示 */
        h3 {
            text-align: center;
        }

    }

    
    /* 按钮样式 */
    .el-button {
        width: 100%;
        margin-top: 20px;
    }
    .form-footer {
    display: flex;
    justify-content: space-between;
    }

}
</style>