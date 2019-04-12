package com.java.netty.nio.socketnet;

import java.io.IOException;
import java.net.Socket;

/**
 * @author xuweizhi
 * @date 2018/12/18 9:11
 */
public class TraditionalSocketClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8888);
    }

}
