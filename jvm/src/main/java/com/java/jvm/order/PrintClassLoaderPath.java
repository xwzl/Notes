package com.java.jvm.order;

//import com.sun.crypto.provider.AESKeyGenerator;

/**
 *
 * @author xuweizhi
 * @date 2019/02/25 10:19
 */
public class PrintClassLoaderPath {

    public static void main(String[] args) throws Exception {

        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));

        /*
          扩展类加载器 1.8有效
         */
        //AESKeyGenerator keyGenerator = new AESKeyGenerator();
        //System.out.println(keyGenerator.getClass().getClassLoader());
        System.out.println(PrintClassLoaderPath.class.getClassLoader());

        //MyClassLoader loader1 = new MyClassLoader("loader1");
        //MyClassLoader loader2 = new MyClassLoader("loader2");
        //
        //Class<?> loadClass1 = loader1.loadClass("com.java.jvm.order.Person");
        //Class<?> loadClass2 = loader2.loadClass("com.java.jvm.order.Person");
        //
        //System.out.println(loadClass1 == loadClass2);
        //
        //Object o1 = loadClass1.getDeclaredConstructor().newInstance();
        //Object o2 = loadClass2.getDeclaredConstructor().newInstance();
        //
        //Method method = loadClass1.getMethod("setPerson", Object.class);
        //method.invoke(o1, o2);

    }

}
