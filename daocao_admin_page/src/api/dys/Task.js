// 请求接口
import request from '@/utils/request'
//查询用户的任务信息
export function serachAllTasks(){
    return request({
        url:`dys/task`,
        method:'GET'
    })
}


//新增任务接口
export function addTask(data){
    return request({
        url:'dys/task',
        method:'POST',
        data:data
    })
}

// 更改任务状态
export function changeTaskState(TaskId){
    return request({
        url:`dys/task/changeState/${TaskId}`,
        method:'POST'

    })
}