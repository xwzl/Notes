package com.java.base.enums;

/**
 * @author xuweizhi
 * @date 2019/04/09 15:53
 */
public enum CodeStateEnum {

    SPRING(1,"春天"),
    SUMMER(2,"夏天"),
    AUTUMN(3,"秋天"),
    WINTER(4,"冬天");

    private Integer code;

    private String message;

    CodeStateEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
