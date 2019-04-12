package com.java.base.annotation.util;

import com.java.base.annotation.auto.MyComponent;
import com.java.base.annotation.auto.MyValue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author xuweizhi
 * @date 2019/04/12 11:14
 */
@MyComponent
public class DateUtils {

    @MyValue("${value}")
    private String value;

    public static String getTime() {
        // 暂时未考虑时区
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
