package com.java.jvm.order;

import java.io.*;

/**
 * -XX:+TraceClassUnloading
 * @author xuweizhi
 * @date 2019/02/21 16:42
 */
public class MyClassLoader extends ClassLoader {


    private String classLoaderName;

    private final String fileExtension = ".class";

    private String path;

    public MyClassLoader(String classLoaderName) {
        //将系统类加载器当做该类加载器的父类加载器
        super();
        this.classLoaderName = classLoaderName;
    }

    /**
     * 类加载器是包含关系，不是继承关系，因为ClassLoader类中有一个parent变量，对子加载器的引用
     */
    public MyClassLoader(ClassLoader parent, String classLoaderName) {
        //显示指定该类加载器的父加载器
        super(parent);
        this.classLoaderName = classLoaderName;
    }

    /**
     * 必须定义这个方法，用于双亲委托机制
     */
    public MyClassLoader(ClassLoader parent){
        super(parent);
    }

    public void setPath(String path) {
        this.path = path;
    }


    /**
     * 没有被执行，因为加载器是由系统类加载器加载的，双亲委托机制让类加载器从上到下依次执行
     * 想要被执行，删除classPath下的文件且重新制定位置
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = this.loadClassData(name);
        System.out.println(name);
        System.out.println("loaderName" + this.classLoaderName);
        return this.defineClass(name, data, 0, data.length);
    }

    private byte[] loadClassData(String className) {
        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream baos = null;
        className = className.replace(".", "/");
        try {
            is = new FileInputStream(new File(this.path + className + this.fileExtension));
            baos = new ByteArrayOutputStream();

            int ch = 0;

            while (-1 != (ch = is.read())) {
                baos.write(ch);
            }
            data = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert is != null;
                is.close();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public static void test(ClassLoader classLoader) throws Exception {
        Class<?> clazz = classLoader.loadClass("com.java.jvm.classloader.ClassActiveLoading1");
        Object newInstance = clazz.getDeclaredConstructor().newInstance();
        System.out.println(newInstance);
    }

    /**
     * 双亲委托机制示例*
     * 1. 如果是系统类加载器，根据命名空间规则文件只加载一次，且生成的Class对象为同一个对象，自定义的类加载方法不会被执行
     * 2. 如果是自定义加载器，根据命名空间规则文件文件会被重复加载，生成不同的Class对象实例，自定义的类加载方法会被执行
     * 命名空间
     * <li>
     * - 每个类加载器都有自己的命名空间，命名空间由该加载器及所有父加载器所加载的类组成
     * - 在同一个命名空间中，不会出现类的完整名字（包括类的包名）相同的两个类
     * - 在不同的命名空间中，有可能会出现类的完整名字（包括类的包名）相同的两个类
     * </li>
     * <p>
     * loaderNameloader
     * clazz:600746945
     * com.java.jvm.classloader.ClassActiveLoading1@5d5eef3d
     * ------------------------------
     * loaderNameloader1
     * clazz1:981661423
     * com.java.jvm.classloader.ClassActiveLoading1@100fc185
     */
    public static void main(String[] args) throws Exception {

        MyClassLoader loader = new MyClassLoader("loader");
        // 加载非classPath路径的class文件，终于加载了自定义加载器，前提是删除classPath路径下对应的文件
        loader.setPath("C:\\Users\\Administrator\\Desktop\\");

        Class<?> clazz = loader.loadClass("com.java.jvm.classloader.ClassActiveLoading1");
        System.out.println("clazz:" + clazz.hashCode());
        Object o = clazz.getDeclaredConstructor().newInstance();
        System.out.println(o);

        System.out.println("------------------------------");

        MyClassLoader loader1 = new MyClassLoader("loader1");
        loader1.setPath("C:\\Users\\Administrator\\Desktop\\");

        Class<?> clazz1 = loader1.loadClass("com.java.jvm.classloader.ClassActiveLoading1");
        System.out.println("clazz1:" + clazz1.hashCode());
        Object o1 = clazz.getDeclaredConstructor().newInstance();
        System.out.println(o1);

        System.out.println("------------------------------");

        MyClassLoader loader2 = new MyClassLoader(loader1, "loader2");
        loader1.setPath("C:\\Users\\Administrator\\Desktop\\");

        Class<?> clazz2 = loader2.loadClass("com.java.jvm.classloader.ClassActiveLoading1");
        System.out.println("clazz2:" + clazz2.hashCode());
        Object o2 = clazz.getDeclaredConstructor().newInstance();
        System.out.println(o2);

        System.out.println("------------------------------");

        MyClassLoader loader3 = new MyClassLoader("loader3");
        loader3.setPath("C:\\Users\\Administrator\\Desktop\\");

        Class<?> clazz3 = loader3.loadClass("com.java.jvm.classloader.ClassActiveLoading1");
        System.out.println("clazz3:" + clazz3.hashCode());
        Object o3 = clazz.getDeclaredConstructor().newInstance();
        System.out.println(o1);

        System.out.println("------------------------------");
        //test(loader);

        //模拟类被卸载
        loader = null;
        clazz = null;
        o = null;

        System.gc();

        Thread.sleep(200000);

        loader = new MyClassLoader("loader");
        loader.setPath("C:\\Users\\Administrator\\Desktop\\");

        clazz = loader.loadClass("com.java.jvm.classloader.ClassActiveLoading1");
        System.out.println("clazz:" + clazz.hashCode());
        o = clazz.getDeclaredConstructor().newInstance();
        System.out.println(o);


    }


}
