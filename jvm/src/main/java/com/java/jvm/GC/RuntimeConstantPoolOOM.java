package com.java.jvm.GC;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuweizhi
 * @date 2019/03/10 15:25
 * VM Args: -XX:PermSize=10m -XX:MaxPermSize=10m JDK1.6
 *          -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError JDK1.7 up
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        // 使用List保持着常量池引用，避免Full GC回收常量池行为
        List<String> list = new ArrayList<String>();

        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }

}
