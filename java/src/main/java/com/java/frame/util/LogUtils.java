package com.java.frame.util;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;

/**
 * @author xuweizhi
 * @date 2019/04/12 11:56
 */
public class LogUtils {

    /**
     * 打印日志信息
     */
    public static void printLog(Log logger, String msg) {
        logger.info(msg);
    }

    public static void printLog(Logger logger, String msg) {
        logger.info(msg);
    }
}
