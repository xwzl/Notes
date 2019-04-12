package com.java.netty.netty.bytebuf;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.stream.Stream;

/**
 * @author xuweizhi
 * @date 2019/01/16 13:24
 */
public class AtomicUpdaterTest {

    public static void main(String[] args) throws Exception {

        Class<?> clazz = Class.forName("com.java.netty.netty.bytebuf.Person");
        Person instance = (Person) clazz.getDeclaredConstructor().newInstance();


        System.out.println(instance.getAge());

        Constructor<?> declaredConstructor = clazz.getDeclaredConstructor(int.class);
        declaredConstructor.setAccessible(true);

        Person instance1 = (Person) declaredConstructor.newInstance(13);
        System.out.println(instance1.getAge());

        Class<?>[] params = new Class[]{int.class, String.class, String.class};
        Object[] objects = new Object[]{1, "12", "123"};
        Person person1 = (Person) clazz.getDeclaredConstructor(params).newInstance(objects);
        System.out.println(person1);

        Field[] fields = clazz.getDeclaredFields();

        /**
         * JAVA 反射机制中，Field的getModifiers()方法返回int类型值表示该字段的修饰符。
         *
         * 其中，该修饰符是java.lang.reflect.Modifier的静态属性。
         *
         * 对应表如下：
         *
         * PUBLIC: 1
         * PRIVATE: 2
         * PROTECTED: 4
         * STATIC: 8
         * FINAL: 16
         * SYNCHRONIZED: 32
         * VOLATILE: 64
         * TRANSIENT: 128
         * NATIVE: 256
         * INTERFACE: 512
         * ABSTRACT: 1024
         * STRICT: 2048
         */
        Stream.of(fields).forEach(field -> {
            System.out.println(field.getName());
            System.out.println(field.getModifiers());
            System.out.println(Modifier.isPrivate(field.getModifiers()));
            System.out.println(Modifier.isVolatile(field.getModifiers()));
            System.out.println(Modifier.isStatic(field.getModifiers()));
            System.out.println(Modifier.isSynchronized(field.getModifiers()));
            System.out.println(Modifier.isNative(field.getModifiers()));
            System.out.println(Modifier.toString(field.getModifiers()));
        });

    }

}

class IntegerWell {

    private volatile int age = 1;

    public static void main(String[] args) throws InterruptedException {
        IntegerWell well = new IntegerWell();
        AtomicIntegerFieldUpdater<IntegerWell> personAtomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(IntegerWell.class, "age");
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 50, 20, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(50), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        });
        //for (int i = 0; i < 10; i++) {
        //    threadPoolExecutor.execute(() -> {
        //        try {
        //            Thread.sleep(10);
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }
        //        System.out.println(well.age++);
        //    });
        //}
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(personAtomicIntegerFieldUpdater.getAndIncrement(well));
            });
        }
        //Thread.sleep(1000);
        //System.out.println(well.age);
    }


}

class Person {

    private volatile int age;

    private String username;

    private String password;

    public Person() {
    }

    private Person(int age) {
        this.age = age;
    }

    public Person(int age, String username, String password) {
        this.age = age;
        this.username = username;
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
