package com.java.base.annotation.exception;

import java.io.IOException;

/**
 * @author xuweizhi
 * @date 2019/04/11 19:56
 */
public class MyIoException extends IOException {

    public MyIoException(String message) {
        super(message);
    }
}
