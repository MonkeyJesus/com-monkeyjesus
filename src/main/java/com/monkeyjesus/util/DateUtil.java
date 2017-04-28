package com.monkeyjesus.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by MonkeyJesus on 2017/4/6.
 */
public class DateUtil {

    public static String YYYYMMDD_hhmmss = "yyyy-MM-dd HH:mm:ss";
    public static String YYYYMMDD = "YYYY-MM-DD";

    /**
     * 日期转换：字符串转自日期对象
     * @param dateStr
     * @param dateFormat
     * @return
     */
    public static Date formatStringtoDate(String dateStr , String dateFormat){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        Date date = new Date();
        try {
            date = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            return date;
        }
        return date;
    }

    /**
     * 日期转换：日期对象转字符串
     * @param date
     * @param dateFormat
     * @return
     */
    public static String formatDateToString(Date date , String dateFormat){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(date);
    }

    public static Date getWeekDay(String targetDay){

        return null;
    }

    public static void main(String[] args) throws Exception{
        System.out.println(DateUtil.formatStringtoDate("2017-04-02 23:00:00",DateUtil.YYYYMMDD_hhmmss));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = simpleDateFormat.parse("2017-03-02 21:34:23");
        } catch (ParseException e) {
            //TODO 异常处理信息
        }
        System.out.println(date);
        System.out.println(simpleDateFormat.parse("2017-3-2 23:12:12").toString());
        System.out.println(simpleDateFormat2.parse("2017-3-2 23:12:12").toString());
        System.out.println(simpleDateFormat.format(date));
    }
}
