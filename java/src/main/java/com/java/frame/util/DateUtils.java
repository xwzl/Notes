package com.java.frame.util;

import com.java.frame.auto.MyComponent;
import com.java.frame.auto.MyValue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author xuweizhi
 * @date 2019/04/12 11:14
 */
@MyComponent
public class DateUtils {

    @MyValue("${value}")
    private String value;

    @MyValue("${format}")
    private static String format;

    public static String getTime() {
        // 暂时未考虑时区
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 时间戳转LocalDateTime
     */
    public static LocalDateTime getLocalDateTime(Date date) {
        return LocalDateTime.ofEpochSecond(date.getTime(), 0, ZoneOffset.ofHours(8));
    }

    /**
     * 时间转换
     *
     * @param date
     * @return
     */
    public static Date getDate(String date) {
        Date dates = new Date();
        try {
            if (StringUtils.isEmpty(format)) {
                dates = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
            } else {
                dates = new SimpleDateFormat(format).parse(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dates;
    }
}


