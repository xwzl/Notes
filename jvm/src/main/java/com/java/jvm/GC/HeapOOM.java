package com.java.jvm.GC;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuweizhi
 * @date 2019/03/10 14:20
 * -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOOM {

    static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
