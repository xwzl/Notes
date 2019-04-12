package com.java.base.io.singleton;

import org.junit.Test;

import java.io.*;

/**
 * 答案是否定的，很多人都知道使用反射可以破坏单例模式，除了反射以外，使用序列化与反序列化也同样会破坏单例。
 *
 * @author xuweizhi
 * @date 2019/03/18 11:26
 */
public class SingletonAndSerializableTest {

    /**
     * 通过对Singleton的序列化与反序列化得到的对象是一个新的对象，这就破坏了Singleton的单例性。
     *
     * <blockquote><pre>
     *     private Object readOrdinaryObject(boolean unshared)
     *             throws IOException {
     *         //此处省略部分代码
     *
     *         Object obj;
     *         try {
     *             obj = desc.isInstantiable() ? desc.newInstance() : null;
     *         } catch (Exception ex) {
     *             throw (IOException) new InvalidClassException(
     *                     desc.forClass().getName(),
     *                     "unable to create instance").initCause(ex);
     *         }
     *
     *         //此处省略部分代码
     *
     *         if (obj != null &&
     *                 handles.lookupException(passHandle) == null &&
     *                 desc.hasReadResolveMethod()) {
     *             Object rep = desc.invokeReadResolve(obj);
     *             if (unshared && rep.getClass().isArray()) {
     *                 rep = cloneArray(rep);
     *             }
     *             if (rep != obj) {
     *                 handles.setObject(passHandle, obj = rep);
     *             }
     *         }
     *
     *         return obj;
     *     }
     * </pre></blockquote>
     * <p>
     * 这里创建的这个obj对象，就是本方法要返回的对象，也可以暂时理解为是ObjectInputStream的readObject返回的对象。
     * <p>
     * isInstantiable：如果一个serializable/externalizable的类可以在运行时被实例化，那么该方法就返回true。
     * <p>
     * desc.newInstance：该方法通过反射的方式调用无参构造方法新建一个对象。
     * <p>
     * hasReadResolveMethod:如果实现了serializable 或者 externalizable接口的类中包含readResolve则返回true
     * <p>
     * invokeReadResolve:通过反射的方式调用要被反序列化的类的readResolve方法。
     * <p>
     * 所以，原理也就清楚了，主要在Singleton中定义readResolve方法，并在该方法中指定要返回的对象的生成策略，就可以防止单例被破坏。
     */
    @Test
    public void test7() throws Exception {
        //Write Obj to file
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tempFile"));
        oos.writeObject(Singleton7.getSingleton());
        //Read Obj from file
        File file = new File("tempFile");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Singleton7 newInstance = (Singleton7) ois.readObject();
        //判断是否是同一个对象
        System.out.println(newInstance == Singleton7.getSingleton());
        oos.close();
        ois.close();
    }


}

/**
 * 使用双重校验锁方式实现单例
 */
class Singleton7 implements Serializable {
    private volatile static Singleton7 singleton7;

    private Singleton7() {
    }

    public static Singleton7 getSingleton() {
        if (singleton7 == null) {
            synchronized (Singleton7.class) {
                if (singleton7 == null) {
                    singleton7 = new Singleton7();
                }
            }
        }
        return singleton7;
    }

    private Object readResolve() {
        return singleton7;
    }

}