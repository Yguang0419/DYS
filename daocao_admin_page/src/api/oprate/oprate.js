// 请求接口
import request from '@/utils/request'
// -------------------------------案例管理------------------------------------------
//查询所有案例demo
export function getAllOP(data){
    return request({
        url:'oprates/getAllOp',
        method:'GET'
    })
}
//新增操作案例接口
export function ADDdemo(data){
    return request({
        url:'oprates/addOp',
        method:'POST',
        data:data
    })
}
// -----------------------------------案例demo------------------------------------
//操作转发接口，根据Opid进行转发
export function OpBroker(Opid){
    return request({
        url:`op/${Opid}`,
        method:'GET'
    })
}