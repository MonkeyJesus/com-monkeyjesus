package com.monkeyjesus.util;

import com.sun.xml.internal.bind.v2.TODO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by MonkeyJesus on 2017/4/6.
 */
public class DateUtil {

    public static String YYYYMMDD_hhmmss = "yyyy-MM-dd HH:mm:ss";
    public static String YYYYMMDD = "YYYY-MM-DD";

    public static Date formatStringtoDate(String dateStr , String dateFormat){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        Date date = new Date();
        try {
            date = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            date = new Date();
        }
        return date;
    }

    public static void main(String[] args) throws Exception{
        System.out.println(DateUtil.formatStringtoDate("2017-04-02 23:00:00",DateUtil.YYYYMMDD_hhmmss));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
