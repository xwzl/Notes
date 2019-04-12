package com.java.pattern.bridge;

/**
 * <h1>桥接方法</h1>
 * <p>
 * Java的泛型是要擦除的，虚拟机不认这个玩意儿，那么问题来了，BridgeInterface中的func方法的参数在虚拟机中是什么？
 * <p>
 * 只能是Object了吧，还能是其他东西？
 * <p>
 * 于是它其实是这样的
 * <p>
 * <blockquote><pre>
 * public interface AInterface {
 *     void func(Object t);
 * }
 * </pre></blockquote>
 * <p>
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
 * @date 2019/04/08 13:17
 */
public interface BridgeInterface<T> {
    /**
     * 桥接方法
     * <p>
     * java -p BridgeInterface.class 反编译后，其代码是
     * public interface AInterface {
     * void func(Object t);
     * }
     *
     * @param t 泛型参数
     */
    void func(T t);

}
