// 请求接口
import request from '@/utils/request'
//登录接口
export function login(data){
    return request({
        url:'auth/sys',
        method:'post',
        data
    })
}