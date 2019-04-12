package com.java.pattern.bridge;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 问题又来了，BridgeInterfaceImpl实现了AInterface接口，来看看现在的BridgeInterfaceImpl是什么样的
 * <blockquote><pre>
 *   public class BridgeInterfaceImpl implements BridgeInterface {
 *     public void func(String s) {
 *         System.out.println(s);
 *     }
 *  }
 * </pre></blockquote>
 * <p>
 * <h1>这根本就没有实现void func(Object)方法嘛，那是怎么解决的呢？</h1>
 * <p>
 * 编译器给生成了一个方法，喂给虚拟机时，BridgeInterfaceImpl长下面这样
 * <blockquote><pre>
 *  public class BridgeInterfaceImpl implements BridgeInterface {
 *     public void func(String s) {
 *         System.out.println(s);
 *     }
 *     public void func(Object s) {
 *          this.func((String) s);
 *     }
 * }
 * </pre></blockquote>
 * <p>
 * 新生成的这个方法就是桥接方法。
 * <p>
 * 我们可以看到这个方法
 * <p>
 * javap -p BridgeInterfaceImpl.class
 *
 * @author xuweizhi
 * @date 2019/04/08 13:19
 */
public class BridgeInterfaceImpl implements BridgeInterface<String> {

    /**
     * 实际上在编译期间编译器代理生成了一个中间方法
     * public void func(Object c){
     *     this.func((String)C;
     * }
     * 这就是桥接方法
     */
    @Override
    public void func(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        BridgeInterfaceImpl obj = new BridgeInterfaceImpl();
        Method func = BridgeInterfaceImpl.class.getMethod("func", String.class);
        func.invoke(obj, "AAA");
        System.out.println("是否是桥接方法"+func.isBridge());
        func = BridgeInterfaceImpl.class.getMethod("func", Object.class);
        func.invoke(obj, "BBB");
        System.out.println(func.isBridge());
    }


}
