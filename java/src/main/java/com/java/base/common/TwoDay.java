package com.java.base.common;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author xuweizhi
 * @date 2019/03/14 21:30
 */
@Slf4j
public class TwoDay  {

    /**
     * HashMap：它根据键的hashCode值存储数据，大多数情况下可以直接定位到它的值，
     * 因而具有很快的访问速度，但遍历顺序却是不确定的。 HashMap最多只允许一条记
     * 录的键为null，允许多条记录的值为null。HashMap非线程安全，即任一时刻可以
     * 有多个线程同时写HashMap，可能会导致数据的不一致。如果需要满足线程安全，可
     * 以用 Collections的synchronizedMap方法使HashMap具有线程安全的能力，或
     * 者使用ConcurrentHashMap。
     */
    @Test
    public void hashMap() {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            map.put("" + i, "" + i);
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("value " + entry.getValue());
        }
    }

    /**
     * LinkedHashMap：LinkedHashMap是HashMap的一个子类，保存了记录的插入顺序，
     * 在用Iterator遍历LinkedHashMap时，先得到的记录肯定是先插入的，也可以在构
     * 造时带参数，按照访问次序排序。
     */
    @Test
    public void linkedHashMap() {
        Map<String, String> map = new LinkedHashMap<>();
        for (int i = 0; i < 100; i++) {
            map.put("" + i, "" + i);
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("key " + entry.getKey());
        }
    }

    /**
     * TreeMap：TreeMap实现SortedMap接口，能够把它保存的记录根据键排序，默认是按键
     * 值的升序排序，也可以指定排序的比较器，当用Iterator遍历TreeMap时，得到的记录是
     * 排过序的。如果使用排序的映射，建议使用TreeMap。在使用TreeMap时，key必须实现
     * Comparable接口或者在构造TreeMap传入自定义的Comparator，否则会在运行时抛出
     * java.lang.ClassCastException类型的异常。
     */
    @Test
    public void treeMap() {
        Map<Order, String> map = new TreeMap<>((o1, o2) -> o1.age - o2.age);
        for (int i = 0; i < 100; i++) {
            map.put(new Order(i), i + "");
        }
        for (Map.Entry<Order, String> entry : map.entrySet()) {
            System.out.println("key " + entry.getKey().age);
        }
    }

    /**
     * 异或:用于位运算，每个位相同为0，不同为1
     * 10 01 01 01
     * 00 00 10 10
     * 00 00 00 00
     *
     * 10 01 01 01
     */
    @Test
    public void hashCode1() {
        int i = "15".hashCode();
        int i1 = "15".hashCode();
        System.out.println(i);
        System.out.println(i >>> 16);
        System.out.println(i1^(i>>>16));

    }

    /**
     * 对于上述四种Map类型的类，要求映射中的key是不可变对象。不可变对象是该对象在创建
     * 后它的哈希值不会被改变。如果对象的哈希值发生变化，Map对象很可能就定位不到映射的
     * 位置了。
     * <p>
     * 通过上面的比较，我们知道了HashMap是Java的Map家族中一个普通成员，鉴于它可以满足
     * 大多数场景的使用条件，所以是使用频度最高的一个。下文我们主要结合源码，从存储结构、
     * 常用方法分析、扩容以及安全性等方面深入讲解HashMap的工作原理。
     */
    @Data
    static class Order /*implements Comparable<Order>*/ {

        private int age;

        public Order(int age) {
            this.age = age;
        }

        /*@Override
        public int compareTo(Order o) {
            return this.age - ((Order) o).age;
        }*/
    }

}


