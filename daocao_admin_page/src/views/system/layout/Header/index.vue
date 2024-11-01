<template>
    <div class="header_contatiner">
        <!-- 面包屑 -->
         <div class="header_left">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item v-for='(item,index) in breadcrumbList' :key='index'>
                    {{item}}
                </el-breadcrumb-item>
            </el-breadcrumb>
         </div>
         <!-- 右侧的用户名，头像 -->
          <div class="header_right">
            <!-- 头像 -->
             <div class="avatar">
                <el-avatar shape="circle" style="width: 40px; height: 40px" :src="avatar" :fit="fit" />
             </div>
             <!-- 用户名 -->
              <div>
                <el-dropdown>
                    <span class="el-dropdown-link">
                    {{ nickname }}
                    <el-icon class="el-icon--right">
                        <arrow-down />
                    </el-icon>
                    </span>
                    <template #dropdown>
                    <el-dropdown-menu>
                        <el-dropdown-item>个人中心</el-dropdown-item>
                        <el-dropdown-item divided>退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                    </template>
                </el-dropdown>
              </div>
          </div>


    </div>
</template>

<script setup>
    import {ref} from "vue"
    import{useUserStore} from'@/stores/user.js'
    import{storeToRefs} from 'pinia'
    const UserStore=useUserStore()
    let{avatar,nickname} =storeToRefs(UserStore)
    const breadcrumbList=ref(['系统管理','用户管理'])


</script>

<style lang ='scss' scoped >
.header_contatiner{
    display:flex;
    justify-content:space-between;
    align-items:center;
    box-sizing:border-box;
    
    .header_right{
        display: flex;
        align-items: center;
        justify-content: flex-end; /* 如果需要靠右对齐 */
        gap: 10px; 
    }
    .el-dropdown-link{
        cursor:pointer;
        display: flex;
        align-items: center;
        border: none;
        outline: none;
    }
}

</style>