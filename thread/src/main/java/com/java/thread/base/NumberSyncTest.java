package com.java.thread.base;

import com.java.thread.synchronize.PublicClass;
import org.junit.Test;

/**
 * @author xuweizhi
 * @date 2018/12/23 21:58
 */
public class NumberSyncTest {

    public static void main(String[] args) {
        NumberSync sync = new NumberSync();
        Thread thread = new Thread(sync, "1");
        Thread thread1 = new Thread(sync, "2");
        Thread thread2 = new Thread(sync, "3");
        Thread thread3 = new Thread(sync, "4");
        Thread thread4 = new Thread(sync, "5");
        Thread thread5 = new Thread(sync, "6");
        Thread thread6 = new Thread(sync, "7");
        Thread thread7 = new Thread(sync, "8");
        Thread thread8 = new Thread(sync, "9");
        thread.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();
    }

    @Test
    public void test() {
        PublicClass publicClass = new PublicClass();
        publicClass.setUsername("usernameValue");
        publicClass.setPassword("passwordValue");
        System.out.println(publicClass.getUsername() + " " + publicClass.getPassword());

        PublicClass.PrivateClass privateClass = publicClass.new PrivateClass();
        privateClass.setAddress("addressValue");
        privateClass.setAge("ageValue");
        System.out.println(privateClass.getAge() + " " + privateClass.getAddress());


    }
}
