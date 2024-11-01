import request from '@/utils/request'
// 上传图片
// export function devServer(data) {
//     return request({
//         url:'/api/upload',
//         method:'POST',
//         data:data
//     })
//   };

module.exports = {
    devServer: {
      proxy: {
        '/api': {
          target: 'http://localhost:8099',
          changeOrigin: true,
          pathRewrite: {
            '^/api': ''
          }
        }
      }
    }
  };

  //查询用户的所有
export function serachAllBoggers(AuthorId){
    return request({
        url:`dys/boggers/${AuthorId}`,
        method:'GET'
    })
}