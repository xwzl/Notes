package com.java.netty.thrift.thrift;

import com.java.netty.thrift.generated.Person;
import com.java.netty.thrift.generated.PersonService;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * @author xuweizhi
 * @date 2018/12/11 14:10
 */
public class ThriftClient {

    public static void main(String[] args) {

        TTransport transport = new TFramedTransport(new TSocket("localhost", 8888), 600);
        TProtocol protocol = new TCompactProtocol(transport);
        PersonService.Client client = new PersonService.Client(protocol);

        try{
            transport.open();

            Person person = client.getPersonByUsername("Marry");
            System.out.println(person.getAge());
            System.out.println(person.getUsername());
            System.out.println(person.isSetMarried());
            System.out.println("---------------------");

            Person person1 = new Person();
            person1.setUsername("李四");
            person1.setMarried(false);
            person1.setAge(12);

            client.savePerson(person1);
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage(),e);
        }finally{
            transport.close();
        }
    }

}
