package com.java.base.jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * Java 的四种引用类型
 *
 * @author xuweizhi
 * @date 2019/04/09 10:45
 */
public class ReferenceType {


}

class StrongReferenceTest {

    public static int M = 1024 * 1024;

    public static void printlnMemory(String tag) {
        Runtime runtime = Runtime.getRuntime();
        int M = StrongReferenceTest.M;
        System.out.println("\n" + tag + ":");
        System.out.println(runtime.freeMemory() / M + "M(free)/" + runtime.totalMemory() / M + "M(total)");
    }

    public static void main(String[] args) {
        StrongReferenceTest.printlnMemory("1.原可用内存和总内存");

        //实例化10M的数组并与strongReference建立强引用
        byte[] strongReference = new byte[10 * StrongReferenceTest.M];
        StrongReferenceTest.printlnMemory("2.实例化10M的数组,并建立强引用");
        System.out.println("strongReference : " + strongReference);

        System.gc();
        StrongReferenceTest.printlnMemory("3.GC后");
        System.out.println("strongReference : " + strongReference);

        //strongReference = null;后,强引用断开了
        strongReference = null;
        StrongReferenceTest.printlnMemory("4.强引用断开后");
        System.out.println("strongReference : " + strongReference);

        System.gc();
        StrongReferenceTest.printlnMemory("5.GC后");
        System.out.println("strongReference : " + strongReference);
    }
}

class WeakReferenceTest {

    public static int M = 1024 * 1024;

    public static void printlnMemory(String tag) {
        Runtime runtime = Runtime.getRuntime();
        int M = WeakReferenceTest.M;
        System.out.println("\n" + tag + ":");
        System.out.println(runtime.freeMemory() / M + "M(free)/" + runtime.totalMemory() / M + "M(total)");
    }

    public static void main(String[] args) {
        WeakReferenceTest.printlnMemory("1.原可用内存和总内存");

        //创建弱引用
        WeakReference<Object> weakRerference = new WeakReference<Object>(new byte[10 * WeakReferenceTest.M]);
        WeakReferenceTest.printlnMemory("2.实例化10M的数组,并建立弱引用");
        System.out.println("weakRerference.get() : " + weakRerference.get());

        System.gc();
        StrongReferenceTest.printlnMemory("3.GC后");
        System.out.println("weakRerference.get() : " + weakRerference.get());
    }
}

class SoftReferenceTest {

    public static int M = 1024*1024;

    public static void printlnMemory(String tag){
        Runtime runtime = Runtime.getRuntime();
        int M = StrongReferenceTest.M;
        System.out.println("\n"+tag+":");
        System.out.println(runtime.freeMemory()/M+"M(free)/" + runtime.totalMemory()/M+"M(total)");
    }

    public static void main(String[] args){
        SoftReferenceTest.printlnMemory("1.原可用内存和总内存");

        //建立软引用
        SoftReference<Object> softRerference = new SoftReference<Object>(new byte[10*SoftReferenceTest.M]);
        SoftReferenceTest.printlnMemory("2.实例化10M的数组,并建立软引用");
        System.out.println("softRerference.get() : "+softRerference.get());

        System.gc();
        SoftReferenceTest.printlnMemory("3.内存可用容量充足，GC后");
        System.out.println("softRerference.get() : "+softRerference.get());

        //实例化一个4M的数组,使内存不够用,并建立软引用
        //free=10M=4M+10M-4M,证明内存可用量不足时，GC后byte[10*m]被回收
        SoftReference<Object> softRerference2 = new SoftReference<Object>(new byte[4*SoftReferenceTest.M]);
        SoftReferenceTest.printlnMemory("4.实例化一个4M的数组后");
        System.out.println("softRerference.get() : "+softRerference.get());
        System.out.println("softRerference2.get() : "+softRerference2.get());
    }
}

class PhantomReferenceTest {

    public static int M = 1024*1024;

    public static void printlnMemory(String tag){
        Runtime runtime = Runtime.getRuntime();
        int M = PhantomReferenceTest.M;
        System.out.println("\n"+tag+":");
        System.out.println(runtime.freeMemory()/M+"M(free)/" + runtime.totalMemory()/M+"M(total)");
    }

    public static void main(String[] args) throws InterruptedException {

        PhantomReferenceTest.printlnMemory("1.原可用内存和总内存");
        byte[] object = new byte[10*PhantomReferenceTest.M];
        PhantomReferenceTest.printlnMemory("2.实例化10M的数组后");

        //建立虚引用
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<Object>();
        PhantomReference<Object> phantomReference = new PhantomReference<Object>(object,referenceQueue);

        PhantomReferenceTest.printlnMemory("3.建立虚引用后");
        System.out.println("phantomReference : "+phantomReference);
        System.out.println("phantomReference.get() : "+phantomReference.get());
        System.out.println("referenceQueue.poll() : "+referenceQueue.poll());

        //断开byte[10*PhantomReferenceTest.M]的强引用
        object = null;
        PhantomReferenceTest.printlnMemory("4.执行object = null;强引用断开后");

        System.gc();
        PhantomReferenceTest.printlnMemory("5.GC后");
        System.out.println("phantomReference : "+phantomReference);
        System.out.println("phantomReference.get() : "+phantomReference.get());
        System.out.println("referenceQueue.poll() : "+referenceQueue.poll());

        //断开虚引用
        phantomReference = null;
        System.gc();
        PhantomReferenceTest.printlnMemory("6.断开虚引用后GC");
        System.out.println("phantomReference : "+phantomReference);
        System.out.println("referenceQueue.poll() : "+referenceQueue.poll());
    }
}
