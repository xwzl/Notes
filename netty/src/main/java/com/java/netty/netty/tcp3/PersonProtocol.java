package com.java.netty.netty.tcp3;

/**
 *
 * 自定义TCP协议，解决TCP粘包与拆包问题
 *
 * @author xuweizhi
 * @date 2019/01/21 23:42
 */
public class PersonProtocol {

    private int length;

    private byte[] content;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
