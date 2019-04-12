package com.java.lambda.lambda;

/**
 * @author xuweizhi
 * @date 2018/11/22 16:37
 */
public class Person {

    private String user;

    private Integer age;

    public Person() {

    }

    public Person(String user, Integer age) {
        this.user = user;
        this.age = age;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "user='" + user + '\'' +
                ", age=" + age +
                '}';
    }
}
