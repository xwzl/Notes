package com.java.thread.local;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

/**
 * @author xuweizhi
 * @since 2019-09-03
 */
@Slf4j
public class ThreadLocalDemo2 {

    static ThreadLocal<SimpleDateFormat> tl = new ThreadLocal<>();
    static ThreadLocal<String> t2 = new ThreadLocal<>();

    public static class ParseDate implements Runnable {
        int i = 0;

        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                if (tl.get() == null) {
                    log.info("yyyy-MM-dd HH:mm:ss");
                    tl.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                    t2.set("xxxxxxxxxx");
                }
                Thread.sleep(1000);
                Date t = tl.get().parse("2015-03-29 19:29:" + i % 60);
                System.out.println(i + ":" + t);
                log.info(t2.get());
            } catch (ParseException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService es = new ThreadPoolExecutor(10, 10, 0, TimeUnit.SECONDS,new LinkedBlockingDeque<>(), (ThreadFactory) Thread::new);
        for (int i = 0; i < 1000; i++) {
            es.execute(new ParseDate(i));
        }

    }

}
