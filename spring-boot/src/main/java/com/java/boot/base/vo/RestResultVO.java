package com.java.boot.base.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一返回格式
 *
 * @author xuweizhi
 * @date 2019/04/24 11:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestResultVO<T> extends BaseRestResultVO {


    private T data;

    @Override
    public String toString() {
        return "RestResultVO{" +
                "data=" + data +
                ", success=" + success +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}

