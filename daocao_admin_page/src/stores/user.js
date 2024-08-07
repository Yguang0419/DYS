
import {defineStore} from 'pinia'

// 定义store导出
export const useUserStore = defineStore('user', {
    // 定义状态【数据】
    state: () => ({
        id: undefined,
        nickname: undefined,
        avatar:undefined  

    }),
    // 获取数据的方法
    getters: {

        Number: (state) => state.id,
        String: (state) => state.nickname,
        String: (state) => state.avatar
    },
    actions: {
        setUserInfo(data){
            this.id = data.id;
            this.nickname = data.nickname;
            this.avatar = data.avatar;
        }
    },
        // 持久化
    persist: {
        enabled: true,
        storage:localStorage,
        key: 'userInfo',
        paths: ['id','nickname','avatar'] 
    }
    })