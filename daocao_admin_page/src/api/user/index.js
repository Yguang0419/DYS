// 请求接口
import request from '@/utils/request'
//登录接口
export function searchSelfRouter(data){
    return request({
        url:'sys/menu/self',
        method:'GET'
    })
}
// 查询个人信息
export function searchSelfInfo(data){
    return request({
        url:'sys/user/info',
        method:'GET'
    })
}