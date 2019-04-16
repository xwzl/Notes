package com.java.pattern.factorymethod.sample;

/**
 * @author xuweizhi
 */
class FileLogger implements Logger {
    @Override
    public void writeLog() {
        System.out.println("文件日志记录。");
    }
}