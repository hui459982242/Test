package generation;

import date.DateUtil;

import java.text.ParseException;
import java.util.Date;
import java.util.Random;

public class OrderUtil {

    /**
     * 根据系统毫秒数+随机数生成订单号
     * @return
     */
    public static String getLogicOrderNum(){
        return System.currentTimeMillis() + getRandomNum(5);
    }

    /**
     * 根据传入位数生成多少位的随机数
     * @param num
     * @return
     */
    private static String getRandomNum(int num) {
        Random random = new Random();
        String sRand="";
        for (int i=0;i<num;i++) {
            sRand += String.valueOf(random.nextInt(10));
        }
        return sRand;
    }

    public static void main(String[] args) {
        System.out.println(getBusinessOrderNumber());
    }

    /**
     * 根据月数相差，从2018-01开始计算101，之后每月递加1，后跟5位自增数据
     * @return
     */
    public static Integer getBusinessOrderNumber() {

        try {
            int diffNum = DateUtil.getMonthsNum("2018-01-01",DateUtil.formatDate(new Date()));
            return 101 + diffNum;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

}
