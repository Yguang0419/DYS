// 请求接口
import request from '@/utils/request'
//查询所有菜单
export function searchMenuList(data){
    return request({
        url:'sys/menu/list',
        method:'GET'
    })
}
//新增接口
export function saveMenu(data){
    return request({
        url:'sys/menu',
        method:'POST',
        data:data
    })
}
// 查询菜单详情
export function searchMenuById(id){
    return request({
        url:`sys/menu/${id}`,
        method:'GET'
    })
}
// 修改菜单
export function updateMenu(data){
    return request({
        url:'sys/menu',
        method:'PUT',
        data:data
    })
}

// 删除数据
export function removeMenu(ids){
    return request({
        url:'sys/menu',
        method:'DELETE',
        data:ids
    })
}