<template>
    <div class="box">
        <el-card>
            <div id="dplayer" style="width:600px;height: 400px"></div>
        </el-card>
    </div>
</template>
 
<script>
// import DPlayer from 'dplayer';
// import Hls from 'hls.js';
// export default {
//     data() {
//         return {
//             dp: {}
//         };
//     },
//     methods: {},
//     mounted() {
//         this.dp = new DPlayer({
//             //播放器的一些参数
//             container: document.getElementById('dplayer'),
//             video: {
//                 url: 'https://api.dogecloud.com/player/get.m3u8?vcode=5ac682e6f8231991&userId=17&ext=.m3u8', //视频地址
//                 type: 'customHls',
//                 customType: {
//                     customHls: function (video, player) {
//                         const hls = new Hls(); //实例化Hls  用于解析m3u8
//                         hls.loadSource(video.src);
//                         hls.attachMedia(video);
//                     }
//                 }
//             },
//             autoplay: false, //是否自动播放
//             theme: '#FADFA3', //主题色
//             loop: true, //视频是否循环播放
//             lang: 'zh-cn',
//             screenshot: false, //是否开启截图
//             hotkey: true, //是否开启热键
//             preload: 'auto', //视频是否预加载
//             volume: 0.7, //默认音量
//             mutex: true, //阻止多个播放器同时播放，当前播放器播放时暂停其他播放器
//         });
//     }
// };
</script>
 
<style lang="less" scoped>
</style>