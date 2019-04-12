package com.java.jvm.bytecode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;

/**
 *
 * this
 *
 * 对于Java类中的每一个实例方法（非static方法），其在编译后所生成的字节码当中，方法参数的数量总是会比源代码中方法参数多一个（this）,它位于方法
 * 的第一个参数位置；这样，我们就可以在Java的实例方法中使用this来去访问当前对象的属性以及其他方法。
 *
 * 这个操作是在编译器间完成的，即由javac编译器在编译的时候将对this的访问转化为对一个普通实例方法参数的访问，接下来在运行期间，由JVM调用实例方法
 * 时，自动向实例方法传入该this参数。所以，在实例方法的局部变量表中，至少会有一个指向当前对象的局部变量。
 *
 * Java字节码对于异常的处理方式：
 *
 * 统一采用异常表的方式异常进行处理；
 * 在jdk 1.4.2之前的版本中，并不是使用异常表的方式来对异常进行处理的，而是采用特定的指令方式。
 * 当异常处理存在finally语句块时，现代化JVM采取的处理方式是将finally语句块的字节码拼接到每一个catch快后面，换句话说，程序中存在多少个catch块，
 * 就会在每一个catch块后面重复多少个finally语句块的字节码。
 * 表示上面反编译的字节码代码块，从0到26行(不包括26行，从编译结果来看26行是finally中执行的代码)指令异常处理结果，handler_pc表示真正处理异常的
 * 指令，异常类型
 */
public class MyTest3 {

    public void test() throws NullPointerException {
        try {
            InputStream in = new FileInputStream("test.txt");
            ServerSocket serverSocket = new ServerSocket(9999);
            serverSocket.accept();
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        } catch (Exception ex) {

        } finally {
            System.out.println("finally");
        }
    }

}
