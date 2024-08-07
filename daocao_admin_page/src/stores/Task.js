import {defineStore} from 'pinia'

// 定义store导出
export const TaskStore = defineStore('task', {
    // 定义状态【数据】
    state: () => ({
        TaskList :[]

    }),
    // 获取数据的方法
    getters: {

        Array: (state) => state.TaskList
    },
    actions: {
        setTaskList(data){
            this.TaskList = data;
        },
        // 往TaskList追加数据
        addTask(data){
            this.TaskList.push(data);
        }
    },
        // 持久化
    persist: {
        enabled: true,
        storage:localStorage,
        key: 'tasklist',
        paths: ['TaskList'] 
    }
    })