package com.xwz.boot.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 基本返回实体
 *
 * @author xuweizhi
 * @date 2019/04/24 11:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseRestResultVO implements Serializable {

    private static final long serialVersionUID = -8918332659134764288L;

    Boolean success;

    Integer code;

    String message;


    @Override
    public String toString() {
        return "BaseRestResultVO{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
