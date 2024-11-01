<template>

  <el-descriptions title=" " :column="3" border v-for="item in opList" :key="item.opId">
    <el-descriptions-item
      label="创建者"
      label-align="right"
      align="center"
      label-class-name="my-label"
      class-name="my-content"
      width="150px"
    >
      {{ item.opCreator }}
    </el-descriptions-item>
    <el-descriptions-item label="案例名称" label-align="right" align="center">
          {{ item.opName }}
    </el-descriptions-item>
    <el-descriptions-item label="版本" label-align="right" align="center">
      {{ item.opVersion  }}
    </el-descriptions-item>
    <el-descriptions-item label="操作" label-align="right" align="center">
      <el-button type="success" size="small" @click="OpBrokers(item.opId)">体验</el-button>
      <el-button type="info" size="small">编辑</el-button>
    </el-descriptions-item>
    <el-descriptions-item label="案例描述" label-align="right" align="center">
      {{ item.opDes }}
    </el-descriptions-item>
  </el-descriptions>

</template>


<script setup>
    import { ref } from 'vue'
    const opList=ref([
      {
        opId:20241024,
        opCreator:"花不语",
        opName:"验证码",
        opVersion:"v1.0",
        modify:true,
        experience:true,
        opDes:"验证码案例"
      },
      {
        opId:2,
        opCreator:"花不语",
        opName:"秒杀",
        opVersion:"v1.0",
        modify:true,
        experience:false,
        opDes:"秒杀案例"
      }
    ])

// 优化-》增加互动效果，点击操作按钮时候，出来弹窗
    // 引入操作api
    import { OpBroker } from '@/api/oprate/oprate'
    const OpBrokers = function(Opid){
      OpBroker(Opid).then(res => {
        if(res.data.code==200){
          ElNotification({
            title: '发送成功',
            message: '验证码发送成功',
            type: 'success',
          })
        }
      })
    }
</script>
<style scoped>
  :deep(.my-label) {
    background: var(--el-color-success-light-9) !important;
  }

  :deep(.my-content) {
    background: var(--el-color-danger-light-9);
  }
</style>