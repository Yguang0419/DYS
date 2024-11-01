// src/xiaoliuren.js
const tianGan = ['甲', '乙', '丙', '丁', '戊', '己', '庚', '辛', '壬', '癸'];
const diZhi = ['子', '丑', '寅', '卯', '辰', '巳', '午', '未', '申', '酉', '戌', '亥'];
const shenJiang = ['青龙', '朱雀', '勾陈', '腾蛇', '白虎', '玄武', '太常', '六合'];
const shichen = {
  '子时': 1, '丑时': 2, '寅时': 3, '卯时': 4, '辰时': 5, '巳时': 6,
  '午时': 7, '未时': 8, '申时': 9, '酉时': 10, '戌时': 11, '亥时': 12
};
const months = {
  "一": 1, "二": 2, "三": 3, "四": 4, "五": 5, "六": 6,
  "七": 7, "八": 8, "九": 9, "十": 10, "十一": 11, "十二": 12
};
const result = ["大安","留连","速喜","赤口","小吉","空亡"]
// 提取汉字并转换成数字
function extractHanziMonth(dateStr) {
  const match = dateStr.match(/(\d+)年(\S+)月/);
  if (match) {
    return match[2]; // 返回年和月之间的汉字
  }
  return null; // 如果没有匹配到，返回 null
}

// 获取农历日期字符串
function getNongliDate(date) {
  return date.toLocaleString('zh-Hans-u-ca-chinese');
}

// 计算时间段
function getTimePeriod(hours) {
  const timePeriods = [
    { range: [23, 24], period: "子时" },
    { range: [0, 1], period: "子时" },
    { range: [1, 3], period: "丑时" },
    { range: [3, 5], period: "寅时" },
    { range: [5, 7], period: "卯时" },
    { range: [7, 9], period: "辰时" },
    { range: [9, 11], period: "巳时" },
    { range: [11, 13], period: "午时" },
    { range: [13, 15], period: "未时" },
    { range: [15, 17], period: "申时" },
    { range: [17, 19], period: "酉时" },
    { range: [19, 21], period: "戌时" },
    { range: [21, 23], period: "亥时" }
  ];

  for (const period of timePeriods) {
    if (hours >= period.range[0] && hours < period.range[1]) {
      return period.period;
    }
  }
  return null;
}

// 计算卜卦结果
function zhanbu(day,month,time) {
    const data1=day%6;
    const data2=data1-1+month%6;
    const data3=data2-1+time%6;
    return result[data3-1];
}
// 计算天干地支
function calculateTianGanDiZhi(year) {
  const tianGanIndex = (year - 4) % 10;
  const diZhiIndex = (year - 4) % 12;
  return {
    tianGan: tianGan[tianGanIndex],
    diZhi: diZhi[diZhiIndex]
  };
}

// 计算神将
function calculateShenJiang(hours) {
  const shenJiangIndex = (hours * 2 + 1) % 8;
  return shenJiang[shenJiangIndex];
}

// 主函数
function calculateXiaoliuren(date) {
  if (!(date instanceof Date) || isNaN(date.getTime())) {
    throw new Error('Invalid date object');
  }

  const nongliDate = getNongliDate(date);
  const year = date.getFullYear();
  const hours = date.getHours();

  // 提取农历月份和日期 的数字类型
  const numMonth = months[extractHanziMonth(nongliDate)];
  const numDay = nongliDate.split(' ')[0].split('年')[1].split('月')[1].split('日')[0];

  // 计算农历时辰
  const timePeriod = getTimePeriod(hours);

  // 构建结果字符串
  const Nonli_str = `农历：${numMonth}月-${numDay}日-${timePeriod}`;
  // 进行占卜
  const zhanbu_res=zhanbu(numDay,numMonth,shichen[timePeriod]);
  // 计算天干地支
  const { tianGan, diZhi } = calculateTianGanDiZhi(year);

  // 计算神将
  const shenJiangResult = calculateShenJiang(hours);

  return {
    tianGan,
    diZhi,
    shenJiang: shenJiangResult,
    Nonli_str,
    zhanbu_res
  };
}

export default calculateXiaoliuren;