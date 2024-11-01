const iconsVite = import.meta.glob('../../assets/icons/svg/*.svg')
// icon数组，最终导出
let icons = [];
// 正则表达式，匹配所有的svg文件
const re = /\.\/(.*)\.svg/
// 循环，根据正则表达式，匹配值图标
for (const icon in iconsVite) {
  // 只获取图标的名字
  let name = icon.match(re)[1].substring(icon.match(re)[1].lastIndexOf('/') + 1)
  icons.push(name)
}
export default icons