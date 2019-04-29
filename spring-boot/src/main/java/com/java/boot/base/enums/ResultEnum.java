package com.java.boot.base.enums;

import org.jetbrains.annotations.Contract;

/**
 * @author xuweizhi
 * @date 2019/04/24 14:36
 */
public enum ResultEnum {

    /**
     * 请求成功
     */
    SUCCESS(true,0, "请求成功"),
    /**
     * 请求错误
     */
    FAILURE(false,-1, "出现异常");

    private Boolean status;

    private Integer code;

    private String message;

    @Contract(pure = true)
    ResultEnum(Boolean status, Integer code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    @Contract(pure = true)
    public Boolean getStatus() {
        return status;
    }

    @Contract(pure = true)
    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
