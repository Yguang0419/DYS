 <template>
  <div class="question" v-for="q in questions" :key="q.title" :style="{ top: q.top + 'px' }">
    <div class="demo-collapse">
      <el-collapse v-model="q.comment.id" accordion>
        <el-collapse-item :title="q.title" :name="q.title">
          <div>
            <el-input
              v-model="q.comment.content"
              style="width: 240px"
              :autosize="{ minRows: 2, maxRows: 4 }"
              type="textarea"
              placeholder="输入批注信息"
              :change="saveComment()"
            />
          </div>

        </el-collapse-item>
      </el-collapse>
    </div>
  </div>
</template>




<script setup>
  import { ref,watch} from 'vue'

  import { CommentStore } from '@/stores/Comments.js'
  const commentStore = CommentStore()
  
  const BoggeriId = ref()
  const comment = ref({
    id:undefined,
    boggerId:undefined,
    content:undefined,
    author:undefined
  })
  const questionbody = ref({
      title:undefined,
      top: 0,
      comment:undefined
  })
  const questions =ref([
  ]);


  const props = defineProps({
    question: {
      type: Object,
      default: () => ({})
    },
    boggerId: {
      type: String,
      required: true
    }
  });
      // 从 pinia 中获取批注信息
  watch(() => props.boggerId, (newBoggerId) => {
    console.log('newBoggerId', newBoggerId)
    const comments = commentStore.getCommentkList(newBoggerId);
    console.log('comments:', comments)
    questions.value = comments;
  })
  const emit = defineEmits(['updateQuestion']);
  // 当 question 发生变化时，检查是否需要发送
    watch(() => props.question, (newQuestion) => {
     comment.value.id           = Date.now();
     comment.value.boggerId      = newQuestion.boggerId;
     questionbody.value.title   = newQuestion.text;
     questionbody.value.top      = newQuestion.top+200;
     questionbody.value.comment  = comment.value
         // 创建一个深拷贝的对象，然后将其推入数组
    const newQuestionBody = JSON.parse(JSON.stringify(questionbody.value));
    console.log('questionbody',questionbody.value)
    questions.value.push(newQuestionBody);
     

    if (newQuestion.text) {
      console.log('--------')
      sendQuestionToParent();
    }
  });

  const sendQuestionToParent = () => {
    emit('question-sent', props.question);
  };
  const saveComment = () => {
    commentStore.setCommentkList(BoggeriId.value,questions.value)
  }
  



</script>
  
  
  
<style scoped>

  
</style>