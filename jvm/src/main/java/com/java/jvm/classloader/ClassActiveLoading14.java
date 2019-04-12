package com.java.jvm.classloader;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * @author xuweizhi
 * @date 2019/02/21 13:26
 */
public class ClassActiveLoading14 {

    public static void main(String[] args) throws IOException {

        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        String resource = "com/java/jvm/classloader/ClassActiveLoading13.class";

        Enumeration<URL> enumeration = loader.getResources(resource);

        while (enumeration.hasMoreElements()){
            URL url = enumeration.nextElement();
            System.out.println(url);
        }

        //根加载器加载，所以为空
        System.out.println("".getClass().getClassLoader());

        System.out.println(ClassActiveLoading14.class.getClassLoader());

    }

}
