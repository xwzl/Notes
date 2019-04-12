package com.java.netty.thrift.thrift;

import com.java.netty.thrift.generated.DataException;
import com.java.netty.thrift.generated.Person;
import com.java.netty.thrift.generated.PersonService;
import org.apache.thrift.TException;

/**
 * @author xuweizhi
 * @date 2018/12/11 13:21
 */
public class PersonServiceImpl implements PersonService.Iface {

    @Override
    public Person getPersonByUsername(String username) throws DataException, TException {
        System.out.println("Got Client Praram:"+username);
        Person person = new Person();
        person.setAge(11);
        person.setUsername("Marry");
        person.setMarried(true);
        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        System.out.println("Got Client Param");

        System.out.println(person.age);
        System.out.println(person.username);
        System.out.println(person.married);
    }

}
