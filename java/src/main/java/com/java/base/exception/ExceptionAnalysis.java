package com.java.base.exception;

/**
 * <h2>异常的分类</h2>
 * <ul>
 * <li>Throwable是所有异常的根，java.lang.Throwable</li>
 * <li>Error是错误，java.lang.Error</li>
 * <li>Exception是异常，java.lang.Exception</li>
 * </ul>
 * <h3>Exception</h3>
 * <ul>
 * <li>Java.lang.ClassNotFoundException</li>
 * <li>Java.lang.NoSuchMetodException</li>
 * <li>java.io.IOException</li>
 * </ul>
 * <h3>RuntimeException</h3>
 * 运行期异常没有被捕获会导致程序终止
 * <ul>
 * <li>Java.lang.ArithmeticException</li>
 * <li>Java.lang.ArrayStoreExcetpion</li>
 * <li>Java.lang.ClassCastException</li>
 * <li>Java.lang.IndexOutOfBoundsException</li>
 * <li>Java.lang.NullPointerException</li>
 * </ul>
 * <h3>Error</h3>
 * 当程序发生不可控的错误时，通常做法是通知用户并中止程序的执行。与异常不同的是Error及其子类的对象不应被抛出。
 * <p>
 * Error是throwable的子类，代表编译时间和系统错误，用于指示合理的应用程序不应该试图捕获的严重问题。
 * <p>
 * Error由Java虚拟机生成并抛出，包括动态链接失败，虚拟机错误等。程序对其不做处理。
 *
 * @author xuweizhi
 * @date 2019/03/17 16:59
 */
public class ExceptionAnalysis {


}
