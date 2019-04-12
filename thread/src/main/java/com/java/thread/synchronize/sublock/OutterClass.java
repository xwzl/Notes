package com.java.thread.synchronize.sublock;

import com.java.thread.synchronize.PublicClass;

/**
 * @author xuweizhi
 * @date 2018/12/26 16:58
 */
public class OutterClass {

    public static void main(String[] args){
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
