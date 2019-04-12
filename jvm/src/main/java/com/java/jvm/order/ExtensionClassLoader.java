package com.java.jvm.order;

import com.java.jvm.classloader.ClassActiveLoading1;

/**
 * @author xuweizhi
 * @date 2019/02/25 13:58
 * @description D:\root\JavaPlus\jvm\out\production\classes>java -Djava.ext.dirs=./ com.java.jvm.order.ExtensionClassLoader
 * 我们都是好孩子！
 * sun.misc.Launcher$AppClassLoader@73d16e93
 * sun.misc.Launcher$AppClassLoader@73d16e93
 * <p>
 * D:\root\JavaPlus\jvm\out\production\classes>jar cvf test.jar com/java/jvm/classloader/ClassActiveLoading1.class
 * 已添加清单
 * 正在添加: com/java/jvm/classloader/ClassActiveLoading1.class(输入 = 698) (输出 = 390)(压缩了 44%)
 * <p>
 * 扩展类加载器识别jar包，不识别class文件
 * <p>
 * D:\root\JavaPlus\jvm\out\production\classes>java -Djava.ext.dirs=./ com.java.jvm.order.ExtensionClassLoader
 * 我们都是好孩子！
 * sun.misc.Launcher$AppClassLoader@2a139a55
 * sun.misc.Launcher$ExtClassLoader@3d4eac69
 */
public class ExtensionClassLoader {

    static {
        System.out.println("我们都是好孩子！");
    }


    public ExtensionClassLoader() {
        super();
    }

    public static void main(String[] args) {
        System.out.println(ExtensionClassLoader.class.getClassLoader());
        System.out.println(ClassActiveLoading1.class.getClassLoader());
    }

}
