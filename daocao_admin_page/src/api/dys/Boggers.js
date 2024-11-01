// 请求接口
import request from '@/utils/request'
//查询用户的所有
export function serachAllBoggers(){
    return request({
        url:`dys/boggers`,
        method:'GET'
    })
}
//查询博客详情
export function searchOneBogger(BoggerId){
    return request({
        url:`dys/boggers/Deatail/${BoggerId}`,
        method:'GET'
    })
}

//新增博客接口
export function addBogger(data){
    return request({
        url:'dys/boggers',
        method:'POST',
        data:data
    })
}
// 增加越读量
export function addViewCount(BoggerId){
    return request({
        url:`dys/boggers/${BoggerId}/increase`,
        method:'GET'
    })
}