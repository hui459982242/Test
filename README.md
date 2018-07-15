#Java 工具类--时间工具类
=============================
时间工具类（Date）
-----------------------------
    >DateUtil
    >>isLeapYear(int yearNum) 是否是润年
    >>isDate(String date) 判断是否是日期
    >>compare_date(String DATE1, String DATE2) 两个string类型的日期比较大小(DATE1在DATE2之前输出1)
    >>daysBetween(String smdate,String bdate) 回两个string类型日期之间相差的天数
    >>daysBetween2(String startTime, String endTime) 返回两个string类型日期相差的小时数
###########目录结构描述
├── Readme.md                   // help
├── app                         // 应用
├── config                      // 配置
│   ├── default.json
│   ├── dev.json                // 开发环境
│   ├── experiment.json         // 实验
│   ├── index.js                // 配置控制
│   ├── local.json              // 本地
│   ├── production.json         // 生产环境
│   └── test.json               // 测试环境
├── data
├── doc                         // 文档
├── environment
├── gulpfile.js
├── locales
├── logger-service.js           // 启动日志配置
├── node_modules
├── package.json
├── app-service.js              // 启动应用配置
├── static                      // web静态资源加载
│   └── initjson
│   	└── config.js 		// 提供给前端的配置
├── test
├── test-service.js
└── tools
