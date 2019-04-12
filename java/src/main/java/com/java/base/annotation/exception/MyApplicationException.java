package com.java.base.annotation.exception;

import com.java.base.annotation.util.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 启动类异常
 *
 * @author xuweizhi
 * @date 2019/04/12 11:32
 */
public class MyApplicationException extends Exception {

    protected final Log logger = LogFactory.getLog(getClass());

    public MyApplicationException(String message) {
        logger.info(DateUtils.getTime() + message);
    }
}
