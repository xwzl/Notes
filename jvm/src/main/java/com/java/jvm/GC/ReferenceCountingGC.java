package com.java.jvm.GC;

/**
 * @author xuweizhi
 * @date 2019/03/10 20:32
 * testGC() 方法执行后，objA和objB会不会被GC呢？
 * -XX:+PrintGC 输出GC日志
 * -XX:+PrintGCDetails 输出GC的详细日志
 * -XX:+PrintGCTimeStamps 输出GC的时间戳（以基准时间的形式）
 * -XX:+PrintGCDateStamps 输出GC的时间戳（以日期的形式，如 2013-05-04T21:53:59.234+0800）
 * -XX:+PrintHeapAtGC 在进行GC的前后打印出堆的信息
 * -Xloggc:../logs/gc.log 日志文件的输出路径
 * -XX:+PrintGCDateStamps -XX:+PrintGCDetails -Xloggc:./gclogs
 */
public class ReferenceCountingGC {

    public Object instance = null;

    private static final int int_iMB = 1024 * 1024;

    /**
     * 这个成员属性的唯一意义就是占点内存，以便能在GC日志中看清楚是否被回收过
     */
    private byte[] bigSize = new byte[2 * int_iMB];

    public static void main(String[] args) {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();

        //objA 中instance 引用未失效，同理objB的引用也未失效，引用计数法数量永远为1，
        //不能触发垃圾回收，因此引用计数法无法作为JVM判断对象是否回收的标准。
        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        //假设在这行发生GC，objA和objB是否能被回收？
        System.gc();
    }

}
