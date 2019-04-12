package com.java.lambda.date;


import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.junit.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author xuweizhi
 * @date 2018/12/04 16:16
 */
public class JodaTime {

    public static void main(String[] args) {

        DateTime dateTime = new DateTime();
        DateTime tomorrow = dateTime.plusDays(1);

        System.out.println(dateTime);
        System.out.println(tomorrow);
        System.out.println(tomorrow.toString("yyyy-MM-dd"));
        System.out.println(dateTime.toDate());

        LocalDate localDate = new LocalDate();
        System.out.println(localDate);

        localDate = localDate.plusMonths(3).dayOfMonth().withMaximumValue();
        System.out.println(localDate);

        //计算两年前第三个月最后一天的日期
        DateTime dateTime1 = new DateTime();
        DateTime dateTime2 = dateTime1.minusYears(2).monthOfYear().setCopy(3).dayOfMonth().withMaximumValue();
        System.out.println(dateTime2.toString("yyyy-MM-dd"));

    }

    /**
     * 标准时间规则 2018-12-04T16:30:11.827+08:00
     */
    public static Date convertUTC2Date(String utcDate) {
        try {
            Assert.assertNotNull(utcDate);
            DateTime dateTime = DateTime.parse(utcDate, DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
            return dateTime.toDate();
        } catch (Exception e) {
            return null;
        }
    }

    public static String convertDate2UTC(Date javaDate) {
        DateTime dateTime = new DateTime(javaDate, DateTimeZone.UTC);
        return dateTime.toString();
    }
}

class DateFormat {

    /**
     * utc 时间格式转换正常格式 2018-08-07T03:41:59Z
     *  
     *
     * @param utcTime 时间
     * @return
     */
    public static String formatStrUTCToDateStr(String utcTime) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'", Locale.US);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        TimeZone utcZone = TimeZone.getTimeZone("UTC");
        sf.setTimeZone(utcZone);
        Date date = null;
        String dateTime = "";
        try {
            date = sf.parse(utcTime);
            dateTime = sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateTime;
    }

    public static void main(String[] args) throws ParseException {
        String utcTime = "2019-03-10T12:01:20.000Z";
        String time = formatStrUTCToDateStr("2019-03-10T12:01:20.000Z");
        Date parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
        System.out.println(parse);
        System.out.println("utcTime 转换前：" + utcTime);
        System.out.println("utcTime 转换后 time ：" + time);
    }

}
