import {defineStore} from 'pinia'

// 定义store导出
export const CommentStore = defineStore('comments', {
    // 定义状态【数据】
    state: () => ({
        // 
        CommentkList :[],
        CommentBody :{}

    }),
    // 获取数据的方法
    getters: {
        getCommentkList: (state) => (key) => {
            // 遍历 CommentkList 数组，查找具有指定 key 的对象
            const foundItem = state.CommentkList.find(item => item.hasOwnProperty(key));
            return foundItem ? foundItem[key] : null;
          }
    },
    actions: {

        // 往CommentkList追加数据
        setCommentkList(key,value){
            // key为博客的id
            // 查找是否已存在相同的key
            const existingIndex = this.CommentkList.findIndex(item => item.hasOwnProperty(key));

            if (existingIndex !== -1) {
                // 如果存在相同的key，则更新对应的value
                this.CommentkList[existingIndex][key] = value;
            } else {
                // 如果不存在相同的key，则创建一个新的对象并添加到CommentkList
                this.CommentkList.push({ [key]: value });
            }
        }
    },
        // 持久化
    persist: {
        enabled: true,
        storage:localStorage,
        key: 'boggerComments',
        paths: ['CommentkList'] 
    }
    })