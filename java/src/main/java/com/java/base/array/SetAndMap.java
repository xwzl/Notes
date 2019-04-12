package com.java.base.array;

/**
 * <h1>HashSet 和 HashMap 的比较</h1>
 * <p>
 * HashMap 和 HashSet 都是 collection 框架的一部分，它们让我们能够使用对象的集合。collection 框架有自己的接口和实现，主要分为 Set
 * 接口，List 接口和 Queue 接口。它们有各自的特点，Set 的集合里不允许对象有重复的值，List 允许有重复，它对集合中的对象进行索引，Queue
 * 的工作原理是 FCFS 算法(First Come, First Serve)。
 * <p>
 * 首先让我们来看看什么是 HashMap 和 HashSet，然后再来比较它们之间的分别。
 *
 * <h2>什么是 HashSet</h2>
 * HashSet 实现了 Set 接口，它不允许集合中有重复的值，当我们提到 HashSet 时，第一件事情就是在将对象存储在 HashSet 之前，要先确保对象重
 * 写 equals()和 hashCode()方法，这样才能比较对象的值是否相等，以确保set中没有储存相等的对象。如果我们没有重写这两个方法，将会使用这个方
 * 法的默认实现。
 * <p>
 * public boolean add(Object o)方法用来在 Set 中添加元素，当元素值重复时则会立即返回 false，如果成功添加的话会返回 true。
 *
 * <h2>什么是 HashMap</h2>
 * HashMap 实现了 Map 接口，Map 接口对键值对进行映射。Map 中不允许重复的键。Map 接口有两个基本的实现，HashMap 和 TreeMap。TreeMap 保
 * 存了对象的排列次序，而 HashMap 则不能。HashMap 允许键和值为 null。HashMap 是非 synchronized 的，但 collection 框架提供方法能保证
 * HashMap synchronized，这样多个线程同时访问 HashMap 时，能保证只有一个线程更改 Map。
 * <p>
 * public Object put(Object Key,Object value)方法用来将元素添加到 map 中。
 * <h2>HashSet 和 HashMap 的区别</h2>
 * public Object put(Object Key,Object value)方法用来将元素添加到 map 中。
 * <h2>HashSet 和 HashMap 的区别</h2>
 * <p>
 * HashMap	                        HashSet
 * <p>
 * HashMap实现了Map接口	            HashSet实现了Set接口
 * HashMap储存键值对	                HashSet仅仅存储对象
 * 使用put()方法将元素放入map中	        使用add()方法将元素放入set中
 * HashMap中使用键对象来计算hashcode值	HashSet使用成员对象来计算hashcode值，对于两个对象来说hashcode可能相同，所以equals()方法用来判断对象的相等性，如果两个对象不同的话，那么返回false
 * HashMap比较快，因为是使用唯一的键来获取对象	HashSet较HashMap来说比较慢
 *
 * @author xuweizhi
 * @date 2019/03/19 9:17
 */
public class SetAndMap<K,V> {

    public static void main(String[] args){


    }
}
