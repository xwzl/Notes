package com.java.thread.time;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author xuweizhi
 * @date 2019/01/03 14:11
 */
public class ScheduleSample extends TimerTask {

    private Integer i;

    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }


    public static void main(String[] args) {

        System.out.println("当前时间为:" + LocalDateTime.now());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 10);
        Date runDate = calendar.getTime();
        System.out.println("计划时间为：" + runDate);
        ScheduleSample scheduleSample = new ScheduleSample();
        Timer timers = new Timer();
        timers.schedule(scheduleSample, runDate, 4000);
    }

    @Override
    public void run() {
        System.out.println("任务执行了:" + LocalDateTime.now());
    }
}
