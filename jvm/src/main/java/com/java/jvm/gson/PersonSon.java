package com.java.jvm.gson;

import com.google.gson.Gson;

/**
 * @author xuweizhi
 * @date 2019/02/22 16:59
 * @description
 */
public class PersonSon {

    public static void main(String[] args) {
        //注：这里也可以不使用转义字符，而用单引号：String jsonData = "{'name':'John', 'age':20}";
        String jsonData = "{\"name\":\"John\", \"age\":20}";
        Gson gson = new Gson();
        Person person = gson.fromJson(jsonData, Person.class);
        System.out.println(person.getAge());
    }

}
