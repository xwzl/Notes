package com.java.lambda.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * @author xuweizhi
 * @date 2018/11/22 16:40
 */
public class PersonTest {

    @Test
    public void testPerson() {
        Person perosn1 = new Person("A", 1);
        Person perosn2 = new Person("B", 2);
        Person perosn3 = new Person("C", 3);
        Person perosn4 = new Person("D", 4);
        List<Person> list = Arrays.asList(perosn1, perosn2, perosn3, perosn4);
        System.out.println(getPersonsByUser(perosn1, list));
        getPersonByAge(perosn2, list).forEach(person -> System.out.println(person));
    }

    public List<Person> getPersonsByUser(Person person, List<Person> list) {
        //返回为true返回符合标准的
        return list.stream().filter(person1 -> person1.getUser().equals(person.getUser())).collect(Collectors.toList());
    }

    public List<Person> getPersonByAge(Person person, List<Person> list) {
        BiFunction<Person, List<Person>, List<Person>> biFunction = (person1, list1) -> {
            return list1.stream().filter(person2 -> person2.getAge() > person1.getAge()).collect(Collectors.toList());
        };
        return biFunction.apply(person, list);
    }

}
