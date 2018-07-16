Java 工具类--时间工具类
=============================
时间工具类（Date）
-----------------------------
   ```
    .
    ├── DateUtil  
         ├── isLeapYear(int yearNum) 是否是润年
         ├── isDate(String date) 判断是否是日期
         ├── compare_date(String DATE1, String DATE2) 两个string类型的日期比较大小(DATE1在DATE2之前输出1)
         ├── daysBetween(String smdate,String bdate) 回两个string类型日期之间相差的天数
         ├── daysBetween2(String startTime, String endTime) 返回两个string类型日期相差的小时数
         ├── comparisonRQ(String str1, String str2, String str3,String str4) 计算两段日期的重合日期
         ├── calculateTimeDifferenceByChronoUnit()  使用java 8的ChronoUnit
         ├── getMonthsNum(String date1,String date2)  用SimpleDateFormat计算时间差
         ├── getDaysNum(String date1,String date2) 用SimpleDateFormat计算时间差
         ├── getHoursNum(String date1,String date2)  用SimpleDateFormat计算时间差
         ├── getMinutesNum(String date1,String date2)  用SimpleDateFormat计算分钟差
         ├── parseDate(String date, String format)  格式化字符串为日期
         ├── parseDate(String date)
         ├── parseChinaDate(String date)
         ├── parseDateTime(String date)
         ├── parseTime(String date)
         ├── formatDate(Date date, String format)  格式化日期为字符串
         ├── formatDate(Date date)
         ├── formateChinaDate(Date date)
         ├── formateDateTime(Date date)
         ├── formateTime(Date date)
         ├── getAddDate(Date date, int iCount)  日期增加天数
         ├── getAddDateTime(String strDate, int count, int dayType)  添加天数或月份或年得到新的时间
         ├── getYear(Date date)  获取年份
         ├── getMonth(Date date)  获取月份
         ├── getDay(Date date)  获取日
         ├── getWeek(Date date)  获取星期
         ├── getHour(Date date)  获取时间
         ├── getMinute(Date date)  获取分种
         ├── getSecond(Date date)  获取秒
         ├── getWeekDayName(String strDate)  获取星期几
         ├── getWeekDayName(Date date)  获取星期几
         ├── getWeekNumOfYear(Date date)  一年中的星期几
         ├── getWeekNumOfYear(String date)  一年中的星期几
         ├── getYearWeekFirstDay(int yearNum, int weekNum)  获取本周星期一的日期
         ├── getYearWeekEndDay(int yearNum, int weekNum)  获取本周星期天的日期
         ├── getYearMonthEndDay(int yearNum, int monthNum)  获取某年某月的最后一天
         ├── getYearMonthEndDay(Date date)  获取当前月的最后一天
         ├── getWeek(String strDate, int weekNum)  获取当前星期
         ├── getNextMonday(Date date)  下个月日期
         ├── getPreviousDate(Date date)  获得某一日期的前一天
         ├── getDaysInMonth(int year, int month)  获取一个月的天数
         ├── getAgeByBirthday(Date birthday)  根据用户生日计算年龄
         └── getSqlDate(java.util.Date date)  由java.util.Date到java.sql.Date的类型转换
    
