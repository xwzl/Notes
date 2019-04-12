package com.java.base.common;

/**
 * @author xuweizhi
 * @date 2018/12/15 20:19
 */
public class Human {

    public String name;

    public static String race;

    public static Integer index = 0;

    {
        System.out.println("父类构造代码块" + index++);
    }

    static {
        System.out.println("父类静态代码块："+index++);
    }

    public Human() {
        System.out.println("父类构造函数："+index++);
    }

    public Human(String name) {
        System.out.println("父类有参构造函数："+index++);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("-------------");
        this.name = name;
    }

    public static String getRace() {
        return race;
    }

    public static void setRace(String race) {
        Human.race = race;
    }
}
