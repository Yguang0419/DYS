
// 导入icon库
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import { createPinia } from 'pinia'
import { createApp } from 'vue'
import App from './App.vue'
//引入路由规则
import router from './router/index.js'
// // 导入路由守卫
import './router/permission.js'
// 引用持久化
import  piniapluginpersistedstate from 'pinia-plugin-persistedstate'
// 引入markdown编辑器
// import mavonEditor from 'mavon-editor'
// import 'mavon-editor/dist/css/index.css'
// Vue.component("mavon-editor", mavonEditor);
const app = createApp(App)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}
// 使用持久化
const pinia = createPinia()
pinia.use(piniapluginpersistedstate);



// app.use(mavonEditor)
app.use(pinia)
app.use(router)

app.mount('#app')
