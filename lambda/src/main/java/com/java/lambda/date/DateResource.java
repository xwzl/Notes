package com.java.lambda.date;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author xuweizhi
 * @date 2018/12/04 14:29
 */
public class DateResource {

    /**
     * 关于日期与时间
     * <p>
     * 1. 格林威治标准时间
     * 2. UTC时间 不带时区 2010-12-1T11:23.594
     * 3. ISO8601 Joda 默认时间
     */
    public static void main(String[] args) {
        //localMethod();

        LocalDate localDate = LocalDate.now();

        //添加时间操作 通用方法
        LocalDate plus = localDate.plus(2, ChronoUnit.WEEKS);
        System.out.println(plus);
        System.out.println("--------");

        LocalDate minus = localDate.minus(2, ChronoUnit.WEEKS);
        System.out.println(minus);
        System.out.println("--------");

        //时钟
        Clock clock = Clock.systemDefaultZone();
        System.out.println(clock.millis());
        System.out.println("--------");

        LocalDate localDate1 = LocalDate.of(2013, 12, 12);
        System.out.println(localDate1.isBefore(localDate));
        System.out.println(localDate1.isAfter(localDate));
        System.out.println(localDate1.isEqual(localDate));
        System.out.println("--------");

        System.out.println("获取时区");
        Set<String> set = ZoneId.getAvailableZoneIds();
        Set<String> zSet = new TreeSet() {
            {
                addAll(set);
            }
        };
        //zSet.forEach(System.out::println);
        System.out.println("--------");

        System.out.println("根据时区创造时间");
        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        LocalDateTime localTime = LocalDateTime.now();
        System.out.println(localTime);

        ZonedDateTime zonedDateTime = ZonedDateTime.of(localTime, zoneId);
        System.out.println(zonedDateTime);

        System.out.println("年月测试");
        YearMonth yearMonth = YearMonth.now();
        System.out.println(yearMonth);
        //是否是闰年
        System.out.println("闰年？" + yearMonth.isLeapYear());
        System.out.println("--------");

        YearMonth yearMonth1 = YearMonth.of(2016, 2);
        System.out.println(yearMonth1.lengthOfMonth());
        System.out.println(yearMonth1.lengthOfYear());
        System.out.println("闰年？" + yearMonth1.isLeapYear());
        System.out.println("--------");

        System.out.println("周期性  ");
        LocalDate localDate2 = LocalDate.now();
        LocalDate localDate3 = LocalDate.of(2017, 12, 12);

        Period period = Period.between(localDate2, localDate3);
        System.out.println("间隔年数：" + period.getYears());
        System.out.println("间隔月数：" + period.getMonths());
        System.out.println("间隔天数：" + period.getDays());

        LocalDateTime localDateTime = LocalDateTime.now();

    }

    public static void localMethod() {
    /*
      locakDate 不在意时分秒信息
     */
        LocalDate localDate = LocalDate.now();
        System.out.println("localDate:" + localDate);

        LocalDate localDate1 = LocalDate.of(2018, 3, 12);
        System.out.println(localDate1);

        MonthDay monthDay = MonthDay.of(localDate1.getMonth(), localDate1.getDayOfMonth());
        MonthDay monthDay1 = MonthDay.from(LocalDate.of(2013, 3, 12));

        if (monthDay.equals(monthDay1)) {
            System.out.println("equal");
        } else {
            System.out.println("false");
        }

        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);

        LocalTime localTime1 = LocalTime.of(12, 12);
        System.out.println(localTime1);

    }

}
