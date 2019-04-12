package com.java.jvm.gson;

import com.google.gson.Gson;
import com.java.jvm.SpringBootTestClass;
import org.junit.Test;
import org.springframework.stereotype.Component;

/**
 * @author xuweizhi
 * @date 2019/02/22 16:59
 * @description
 */
@Component
public class PersonGSon extends SpringBootTestClass {

    @Test
    public void jsonDataFormatPersonClass() {
        //注：这里也可以不使用转义字符，而用单引号：String jsonData = "{'name':'John', 'age':20}";
        String jsonData = "{\"name\":\"John\", \"age\":20}";
        Gson gson = new Gson();
        Person person = gson.fromJson(jsonData, Person.class);
        System.out.println(person.getAge());
    }

}
