package com.java.pattern.factorymethod.sample;

/**
 * @author xuweizhi
 */
class DatabaseLogger implements Logger {
    @Override
    public void writeLog() {
        System.out.println("数据库日志记录。");
    }
}