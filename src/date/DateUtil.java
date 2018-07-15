package date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class DateUtil {
    public static final String CHINA_DATE_FORMAT = "yyyy年MM月dd日";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";
    /**
     * 是否是润年
     * @param yearNum
     * @return
     */
    public static boolean isLeapYear(int yearNum) {
        boolean isLeep = false;
        if ((yearNum % 4 == 0) && (yearNum % 100 != 0))
            isLeep = true;
        else if (yearNum % 400 == 0)
            isLeep = true;
        else {
            isLeep = false;
        }
        return isLeep;
    }
    /**
     * 判断是否是日期
     * @param date
     * @return
     */
    public static boolean isDate(String date) {
        //判断年月日的正则表达式，接受输入格式为2010-12-24，可接受平年闰年的日期
        String regex = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(date).matches();
    }
    /**
     * 两个string类型的日期比较大小
     * @param DATE1
     * @param DATE2
     * @return DATE1在DATE2之前输出1
     */
    public static int compare_date(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat(DATETIME_FORMAT);
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                //System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                //System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 返回两个string类型日期之间相差的天数
     * @param smdate
     * @param bdate
     * @return smdate>bdate 输出负数，smdate<bdate输出正数
     */
    public static int daysBetween(String smdate,String bdate){
        SimpleDateFormat sdf=new SimpleDateFormat(DATE_FORMAT);
        Calendar cal = Calendar.getInstance();
        long time1 = 0;
        long time2 = 0;
        try{
            cal.setTime(sdf.parse(smdate));
            time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(bdate));
            time2 = cal.getTimeInMillis();
        }catch(Exception e){
            e.printStackTrace();
        }
        long between_days=(time2-time1)/(1000*3600*24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 返回两个string类型日期相差的小时数
     * @param startTime
     * @param endTime
     * @return
     */
    public static int daysBetween2(String startTime, String endTime) {
        SimpleDateFormat sdf=new SimpleDateFormat(DATETIME_FORMAT);
        Calendar cal = Calendar.getInstance();
        long time1 = 0;
        long time2 = 0;
        try{
            cal.setTime(sdf.parse(startTime));
            time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(endTime));
            time2 = cal.getTimeInMillis();
        }catch(Exception e){
            e.printStackTrace();
        }
        long between_days=(time2-time1)/(1000*3600);
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 计算两段日期的重合日期
     * @param str1 开始日期1
     * @param str2 结束日期1
     * @param str3 开始日期2
     * @param str4 结束日期2
     * @return
     * @throws Exception
     */

    public static Map<String,Object> comparisonRQ(String str1, String str2, String str3,String str4) throws Exception {
        String mesg = "";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String startdate = "";
        String enddate = "";
        try {
            Date dt1 = df.parse(str1);
            Date dt2 = df.parse(str2);
            Date dt3 = df.parse(str3);
            Date dt4 = df.parse(str4);
            if (dt1.getTime()<=dt3.getTime()&&dt3.getTime()<=dt2.getTime()&&dt2.getTime()<=dt4.getTime()) {
                mesg = "f";//重合
                startdate = str3;
                enddate = str2;
            }
            if (dt1.getTime()>=dt3.getTime()&&dt3.getTime()<=dt2.getTime()&&dt2.getTime()<=dt4.getTime()) {
                mesg = "f";//重合
                startdate = str1;
                enddate = str2;
            }

            if (dt3.getTime()<=dt1.getTime()&&dt1.getTime()<=dt4.getTime()&&dt4.getTime()<=dt2.getTime()) {
                mesg = "f";//重合
                startdate = str1;
                enddate = str4;
            }
            if (dt3.getTime()>=dt1.getTime()&&dt1.getTime()<=dt4.getTime()&&dt4.getTime()<=dt2.getTime()) {
                mesg = "f";//重合
                startdate = str3;
                enddate = str4;
            }
            //System.out.println(startdate+"----"+enddate);
        }catch (ParseException e) {
            e.printStackTrace();
            throw new ParseException(e.getMessage(), 0);
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception(e);
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("startdate", startdate);
        map.put("enddate", enddate);
        return map;
    }

    /**
     * 使用java 8的ChronoUnit，ChronoUnit可以以多种单位（基本涵盖了所有的，看源码发现竟然还有“FOREVER”这种单位。。）表示两个时间的时间差
     */
    public static void calculateTimeDifferenceByChronoUnit() {
        LocalDate startDate = LocalDate.of(2003, Month.MAY, 9);
        System.out.println("开始时间：" + startDate);

        LocalDate endDate = LocalDate.of(2015, Month.JANUARY, 26);
        System.out.println("结束时间：" + endDate);

        long daysDiff = ChronoUnit.DAYS.between(startDate, endDate);
        System.out.println("两个时间之间的天数差为：" + daysDiff);
//  long hoursDiff = ChronoUnit.HOURS.between(startDate, endDate);  //这句会抛出异常，因为LocalDate表示的时间中不包含时分秒等信息
    }

    /**
     * 用SimpleDateFormat计算时间差
     * @param date1
     * @param date2
     * @return 两个时间之间的月数差
     * @throws ParseException
     */
    public static Integer getMonthsNum(String date1,String date2) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        bef.setTime(sdf.parse(date1));
        aft.setTime(sdf.parse(date2));
        int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
        int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
        return Math.abs(month + result);
    }

    /**
     * 用SimpleDateFormat计算时间差
     * @param date1
     * @param date2
     * @return 两个时间之间的天数差
     * @throws ParseException
     */
    public static String getDaysNum(String date1,String date2) throws ParseException {
        SimpleDateFormat simpleFormat = new SimpleDateFormat(DATETIME_FORMAT);
        /*天数差*/
        Date fromDate1 = simpleFormat.parse(date1);
        Date toDate1 = simpleFormat.parse(date2);
        long from1 = fromDate1.getTime();
        long to1 = toDate1.getTime();
        int days = (int) ((to1 - from1) / (1000 * 60 * 60 * 24));
        return "两个时间之间的天数差为：" + days;
    }

    /**
     * 用SimpleDateFormat计算时间差
     * @param date1
     * @param date2
     * @return 两个时间之间的小时差为
     * @throws ParseException
     */
    public static String getHoursNum(String date1,String date2) throws ParseException {
        SimpleDateFormat simpleFormat = new SimpleDateFormat(DATETIME_FORMAT);
        /*小时差*/
        Date fromDate2 = simpleFormat.parse(date1);
        Date toDate2 = simpleFormat.parse(date2);
        long from2 = fromDate2.getTime();
        long to2 = toDate2.getTime();
        int hours = (int) ((to2 - from2) / (1000 * 60 * 60));
        return "两个时间之间的小时差为：" + hours;
    }

    /**
     * 用SimpleDateFormat计算时间差
     * @param date1
     * @param date2
     * @return 两个时间之间的分钟差
     * @throws ParseException
     */
    public static String getMinutesNum(String date1,String date2) throws ParseException {
        SimpleDateFormat simpleFormat = new SimpleDateFormat(DATETIME_FORMAT);
        /*分钟差*/
        Date fromDate3 = simpleFormat.parse(date1);
        Date toDate3 = simpleFormat.parse(date2);
        long from3 = fromDate3.getTime();
        long to3 = toDate3.getTime();
        int minutes = (int) ((to3 - from3) / (1000 * 60));
        return "两个时间之间的分钟差为：" + minutes;
    }

    /**
     * 格式化字符串为日期
     * @param date
     * @param format
     * @return
     */
    public static Date parseDate(String date, String format) {
        try {
            return new SimpleDateFormat(format).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parseDate(String date) {
        return parseDate(date, DATE_FORMAT);
    }
    public static Date parseChinaDate(String date) {
        return parseDate(date, CHINA_DATE_FORMAT);
    }
    public static Date parseDateTime(String date) {
        return parseDate(date, DATETIME_FORMAT);
    }
    public static Date parseTime(String date) {
        return parseDate(date, TIME_FORMAT);
    }

    /**
     * 格式化日期为字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String formatDate(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    public static String formatDate(Date date) {
        return formatDate(date, DATE_FORMAT);
    }

    public static String formateChinaDate(Date date) {
        return formatDate(date, CHINA_DATE_FORMAT);
    }

    public static String formateDateTime(Date date) {
        return formatDate(date, DATETIME_FORMAT);
    }

    public static String formateTime(Date date) {
        return formatDate(date, TIME_FORMAT);
    }

    /**
     * 日期增加天数
     * @param date
     * @param iCount
     * @return
     */
    public static Date getAddDate(Date date, int iCount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, iCount);
        return cal.getTime();
    }

    /**
     * 添加天数或月份或年得到新的时间
     * @param strDate
     * @param count
     * @param dayType Calendar.YEAR Calendar.MONTH Calendar.DAY_OF_MONTH
     * @return
     */
    public static String getAddDateTime(String strDate, int count, int dayType) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(parseDate(strDate));
        cal.add(dayType, count);
        SimpleDateFormat sdf = null;
        if ((dayType == Calendar.YEAR) || (dayType == Calendar.MONTH) || (dayType == Calendar.DAY_OF_MONTH))
            sdf = new SimpleDateFormat(DATE_FORMAT);
        else
            sdf = new SimpleDateFormat(DATETIME_FORMAT);
        return sdf.format(cal.getTime());
    }

    /**
     * 获取年份
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     * 获取月份
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取星期
     * @param date
     * @return
     */
    public static int getWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取时间
     * @param date
     * @return
     */
    public static int getHour(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取分种
     * @param date
     * @return
     */
    public static int getMinute(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MINUTE);
    }

    /**
     * 获取秒
     * @param date
     * @return
     */
    public static int getSecond(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.SECOND);
    }

    /**
     * 获取星期几
     * @param strDate
     * @return
     */
    public static String getWeekDayName(String strDate) {
        String[] mName = {"日", "一", "二", "三", "四", "五", "六"};
        Date date = parseDate(strDate);
        int week = getWeek(date);
        return "星期" + mName[week];
    }

    public static String getWeekDayName(Date date) {
        String[] mName = {"日", "一", "二", "三", "四", "五", "六"};
        int week = getWeek(date);
        return "星期" + mName[week];
    }

    /**
     * 一年中的星期几
     * @return
     */
    public static int getWeekNumOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }
    public static int getWeekNumOfYear(String date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parseDate(date, DATE_FORMAT));
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取本周星期一的日期
     * @param yearNum
     * @param weekNum
     * @return
     * @throws ParseException
     */
    public static String getYearWeekFirstDay(int yearNum, int weekNum)  {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, yearNum);
        cal.set(Calendar.WEEK_OF_YEAR, weekNum);
        cal.set(Calendar.DAY_OF_WEEK, 2);
        String tempYear = Integer.toString(yearNum);
        String tempMonth = Integer.toString(cal.get(Calendar.MONTH) + 1);
        String tempDay = Integer.toString(cal.get(Calendar.DAY_OF_MONTH)-1);
        return tempYear + "-" + tempMonth + "-" + tempDay;
    }

    /**
     *  获取本周星期天的日期
     * @param yearNum
     * @param weekNum
     * @return
     * @throws ParseException
     */
    public static String getYearWeekEndDay(int yearNum, int weekNum)  {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, yearNum);
        cal.set(Calendar.WEEK_OF_YEAR, weekNum + 1);
        cal.set(Calendar.DAY_OF_WEEK, 1);

        String tempYear = Integer.toString(yearNum);
        String tempMonth = Integer.toString(cal.get(Calendar.MONTH) + 1);
        String tempDay = Integer.toString(cal.get(Calendar.DAY_OF_MONTH)-1);
        return tempYear + "-" + tempMonth + "-" + tempDay;
    }

    /**
     * 获取某年某月的第一天
     * @param yearNum
     * @param monthNum
     * @return
     */
    public static Date getYearMonthFirstDay(int yearNum, int monthNum) {
        Calendar cal = Calendar.getInstance();
        cal.set(yearNum, monthNum - 1, 1, 0, 0, 0);
        cal.set(14, 0);
        return cal.getTime();
    }

    /**
     * 获取某年下个月的第一天
     * @param yearNum
     * @param monthNum
     * @return
     */
    public static Date getNextYearMonthFirstDay(int yearNum, int monthNum) {
        Calendar cal = Calendar.getInstance();
        cal.set(yearNum, monthNum, 1, 0, 0, 0);
        cal.set(14, 0);
        return cal.getTime();
    }

    /**
     * 获取某年某月的最后一天
     * @param yearNum
     * @param monthNum
     * @return
     */
    public static Date getYearMonthEndDay(int yearNum, int monthNum) {
        Calendar cal = Calendar.getInstance();
        cal.set(yearNum, monthNum, 0, 0, 0, 0);
        cal.set(14, 0);
        return cal.getTime();
    }

    /**
     * 获取某月的第一天
     * @param date
     * @return
     */
    public static Date getYearMonthFirstDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(5, 1);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        return cal.getTime();
    }

    /**
     * 获取当前月的最后一天
     * @param date
     * @return
     */
    public static Date getYearMonthEndDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(5, cal.getActualMaximum(5));
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        return cal.getTime();
    }

    /**
     * 获取当前星期
     * @param strDate
     * @param weekNum
     * @return
     */
    public static String getWeek(String strDate, int weekNum) {
        Calendar c = Calendar.getInstance();
        c.setTime(parseDate(strDate));
        if (weekNum == 1)
            c.set(7, 2);
        else if (weekNum == 2)
            c.set(7, 3);
        else if (weekNum == 3)
            c.set(7, 4);
        else if (weekNum == 4)
            c.set(7, 5);
        else if (weekNum == 5)
            c.set(7, 6);
        else if (weekNum == 6)
            c.set(7, 7);
        else if (weekNum == 0)
            c.set(7, 1);
        return formatDate(c.getTime());
    }

    /**
     * 下个月日期
     * @param date
     * @return
     */
    public static Date getNextMonday(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        do
            c.add(Calendar.DAY_OF_MONTH, 1);
        while (c.get(Calendar.DAY_OF_WEEK) != 2);
        return c.getTime();
    }

    /**
     * 获得某一日期的前一天
     *
     */
    public static Date getPreviousDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);

        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return getSqlDate(calendar.getTime());
    }

    /**
     * 获得某年某月最后一天的日期
     *
     */
    public static Date getLastDayOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, 1);
        return getPreviousDate(getSqlDate(calendar.getTime()));
    }

    /**
     * 获取一个月的天数
     * @param year
     * @param month
     * @return
     */
    public static int getDaysInMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);// Java月份才0开始算
        return cal.getActualMaximum(Calendar.DATE);
    }

    /**
     * 根据用户生日计算年龄
     */
    public static int getAgeByBirthday(Date birthday) {
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthday)) {
            throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthday);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else {
                age--;
            }
        }
        return age;
    }

    /**
     * 由java.util.Date到java.sql.Date的类型转换
     * java.util.Date : Sat Jul 14 01:07:57 GMT+08:00 2018
     * java.sql.Date : 2018-07-14
     */
    public static Date getSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    public static void main(String[] args) throws Exception{
        String date1 = "2018-02-05 12:00:00";
        String date2 = "2018-08-06 12:00:00";
        String date3 = "2018-02-07";
        String date4 = "2018-02-10";
        System.out.println(getSqlDate(new Date()));
        /*
        java.util.Date d=new java.util.Date ();
        java.sql.Date date=new java.sql.Date(d.getTime());
        System.out.println(d);
        System.out.println(date);
        */
    }
}
