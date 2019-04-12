package com.java.netty.spring.protocol.message;

import lombok.Data;

/**
 * 包
 *
 * @author xuweizhi
 */
@Data
public abstract class Packet {
    /**
     * 版本
     */
    private Byte version = 1;

    public abstract Byte getCommand();
}
