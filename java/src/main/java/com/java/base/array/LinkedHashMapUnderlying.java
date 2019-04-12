package com.java.base.array;

import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

/**
 * <h1>LinkedHashMap 的实现原理</h1>
 * <h2>LinkedHashMap 概述</h2>
 * HashMap 是无序的，HashMap 在 put 的时候是根据 key 的 hashcode 进行 hash 然后放入对应的地方。所以在按照一定顺序 put 进 HashMap 中，
 * 然后遍历出 HashMap 的顺序跟 put 的顺序不同（除非在 put 的时候 key 已经按照 hashcode 排序号了，这种几率非常小）
 *
 * <b>JAVA 在 JDK1.4 以后提供了 LinkedHashMap 来帮助我们实现了有序的 HashMap！</b>
 * <p>
 * LinkedHashMap 是 HashMap 的一个子类，它保留插入的顺序，如果需要输出的顺序和输入时的相同，那么就选用 LinkedHashMap。
 * <p>
 * LinkedHashMap 是 Map 接口的哈希表和链接列表实现，具有可预知的迭代顺序。此实现提供所有可选的映射操作，并允许使用 null 值和 null 键。此类不
 * 保证映射的顺序，特别是它不保证该顺序恒久不变。
 * <p>
 * LinkedHashMap 实现与 HashMap 的不同之处在于，LinkedHashMap 维护着一个运行于所有条目的双重链接列表。此链接列表定义了迭代顺序，该迭代顺序可
 * 以是插入顺序或者是访问顺序。
 * <p>
 * 注意，此实现不是同步的。如果多个线程同时访问链接的哈希映射，而其中至少一个线程从结构上修改了该映射，则它必须保持外部同步。
 * <p>
 * 根据链表中元素的顺序可以分为：按插入顺序的链表，和按访问顺序(调用 get 方法)的链表。默认是按插入顺序排序，如果指定按访问顺序排序，那么调用get方法后，
 * 会将这次访问的元素移至链表尾部，不断访问可以形成按访问顺序排序的链表。
 * <p>
 * 这也就是我们之前提到过的，LinkedHashMap 可以选择按照访问顺序进行排序。
 *
 * @author xuweizhi
 * @date 2019/03/18 13:51
 */
public class LinkedHashMapUnderlying {

    @Test
    public void test1() {

        Map<String, String> linkedHashMap = new LinkedHashMap<>();
        for (int i = 0; i < 2; i++) {
            linkedHashMap.put("" + i, "我么" + i);
        }

        Set<Map.Entry<String, String>> entrySet = linkedHashMap.entrySet();
        System.out.println(entrySet.getClass());
        for (Map.Entry<String, String> entry : entrySet) {
            System.out.println(entry.getKey() + entry.getValue());
        }

        Stream.of(entrySet).parallel().forEach(entries -> {
            entries.forEach(entry -> {
                System.out.println(entry.getKey() + entry.getValue());
            });
        });

    }

    /**
     * 无序访问
     */
    @Test
    public void test2() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("apple", "苹果");
        map.put("watermelon", "西瓜");
        map.put("banana", "香蕉");
        map.put("peach", "桃子");

        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }

    /**
     * 这也就是我们之前提到过的，LinkedHashMap 可以选择按照访问顺序进行排序。
     */
    @Test
    public void test3() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("apple", "苹果1");
        map.put("watermelon", "西瓜");
        map.put("banana", "香蕉1");
        map.put("peach", "桃子");

        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    /**
     * 按照访问顺序访问
     */
    @Test
    public void test4() {
        Map<String, String> map = new LinkedHashMap<String, String>(16, 0.75f, true);
        map.put("apple", "苹果");
        map.put("watermelon", "西瓜");
        map.put("banana", "香蕉");
        map.put("banana1", "香蕉1");
        map.put("peach", "桃子");

        map.get("banana");
        map.get("apple");

        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            System.out.println(entry.getKey() + "？" + entry.getValue());
        }
    }

    /**
     * <h2>LinkedHashMap 的实现</h2>
     * 对于 LinkedHashMap 而言，它继承与 HashMap(public class LinkedHashMap<K,V> extends HashMap<K,V> implements Map<K,V>)、
     * 底层使用哈希表与双向链表来保存所有元素。其基本操作与父类 HashMap 相似，它通过重写父类相关的方法，来实现自己的链接列表特性。下面我们来分析
     *
     * <h2>成员变量</h2>
     * <p>
     * LinkedHashMap 采用的 hash 算法和 HashMap 相同，但是它重新定义了数组中保存的元素 Entry，该 Entry 除了保存当前对象的引用外，还保存了
     * 其上一个元素 before 和下一个元素 after 的引用，从而在哈希表的基础上又构成了双向链接列表.
     *
     * <h2>初始化</h2>
     * 通过源代码可以看出，在 LinkedHashMap 的构造方法中，实际调用了父类 HashMap 的相关构造方法来构造一个底层存放的 table 数组，但额外可以增加
     * accessOrder 这个参数，如果不设置，默认为 false，代表按照插入顺序进行迭代；当然可以显式设置为 true，代表以访问顺序进行迭代.
     * <p>
     * 我们已经知道 LinkedHashMap 的 Entry 元素继承 HashMap 的 Entry，提供了双向链表的功能。在上述 HashMap 的构造器中，最后会调用 init() 方法，
     * 进行相关的初始化，这个方法在 HashMap 的实现中并无意义，只是提供给子类实现相关的初始化调用。
     * <p>
     * 但在 LinkedHashMap 重写了 init() 方法，在调用父类的构造方法完成构造后，进一步实现了对其元素 Entry 的初始化操作。
     *
     * <h2>存储</h2>
     * <p>
     * LinkedHashMap 并未重写父类 HashMap 的 put 方法，而是重写了父类 HashMap 的 put 方法调用的子方法void recordAccess(HashMap m) ，
     * void addEntry(int hash, K key, V value, int bucketIndex) 和void createEntry(int hash, K key, V value, int bucketIndex)，
     * 提供了自己特有的双向链接列表的实现。我们在之前的文章中已经讲解了HashMap的put方法，我们在这里重新贴一下 HashMap 的 put 方法的源代码：
     * <p>
     * HashMap.put:
     * <blockquote><pre>
     *     public V put(K key, V value) {
     *         if (key == null)
     *             return putForNullKey(value);
     *         int hash = hash(key);
     *         int i = indexFor(hash, table.length);
     *         for (Entry<K,V> e = table[i]; e != null; e = e.next) {
     *             Object k;
     *             if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
     *                 V oldValue = e.value;
     *                 e.value = value;
     *                 e.recordAccess(this);
     *                 return oldValue;
     *             }
     *         }
     *
     *         modCount++;
     *         addEntry(hash, key, value, i);
     *         return null;
     *     }
     * </pre></blockquote>
     * <h2>重写方法</h2>
     * <blockquote><pre>
     * void recordAccess(HashMap<K,V> m) {
     *     LinkedHashMap<K,V> lm = (LinkedHashMap<K,V>)m;
     *     if (lm.accessOrder) {
     *         lm.modCount++;
     *         remove();
     *         addBefore(lm.header);
     *         }
     * }
     *
     * void addEntry(int hash, K key, V value, int bucketIndex) {
     *     // 调用create方法，将新元素以双向链表的的形式加入到映射中。
     *     createEntry(hash, key, value, bucketIndex);
     *
     *     // 删除最近最少使用元素的策略定义
     *     Entry<K,V> eldest = header.after;
     *     if (removeEldestEntry(eldest)) {
     *         removeEntryForKey(eldest.key);
     *     } else {
     *         if (size >= threshold)
     *             resize(2 * table.length);
     *     }
     * }
     *
     * void createEntry(int hash, K key, V value, int bucketIndex) {
     *     HashMap.Entry<K,V> old = table[bucketIndex];
     *     Entry<K,V> e = new Entry<K,V>(hash, key, value, old);
     *     table[bucketIndex] = e;
     *     // 调用元素的addBrefore方法，将元素加入到哈希、双向链接列表。
     *     e.addBefore(header);
     *     size++;
     * }
     *
     * private void addBefore(Entry<K,V> existingEntry) {
     *     after  = existingEntry;
     *     before = existingEntry.before;
     *     before.after = this;
     *     after.before = this;
     * }
     * </pre></blockquote>
     *
     * <h2>读取</h2>
     * <p>
     * LinkedHashMap 重写了父类 HashMap 的 get 方法，实际在调用父类 getEntry() 方法取得查找的元素后，再判断当排序模式 accessOrder 为 true 时，
     * 记录访问顺序，将最新访问的元素添加到双向链表的表头，并从原来的位置删除。由于的链表的增加、删除操作是常量级的，故并不会带来性能的损失。
     *
     * <blockquote><pre>
     *      public V get(Object key) {
     *             // 调用父类HashMap的getEntry()方法，取得要查找的元素。
     *             Entry<K,V> e = (Entry<K,V>)getEntry(key);
     *             if (e == null)
     *                 return null;
     *             // 记录访问顺序。
     *             e.recordAccess(this);
     *             return e.value;
     *         }
     *
     *         void recordAccess(HashMap<K,V> m) {
     *             LinkedHashMap<K,V> lm = (LinkedHashMap<K,V>)m;
     *             // 如果定义了LinkedHashMap的迭代顺序为访问顺序，
     *             // 则删除以前位置上的元素，并将最新访问的元素添加到链表表头。
     *             if (lm.accessOrder) {
     *                 lm.modCount++;
     *                 remove();
     *                 addBefore(lm.header);
     *             }
     *         }
     *
     *         //Removes this entry from the linked list.
     *         private void remove() {
     *             before.after = after;
     *             after.before = before;
     *         }
     *
     *         //clear链表，设置header为初始状态
     *         public void clear() {
     *             super.clear();
     *             header.before = header.after = header;
     *         }
     * </pre></blockquote>
     *
     * <h2>排序模式</h2>
     * <p>
     * LinkedHashMap 定义了排序模式 accessOrder，该属性为 boolean 型变量，对于访问顺序，为 true；对于插入顺序，则为 false。
     * 一般情况下，不必指定排序模式，其迭代顺序即为默认为插入顺序。
     * <p>
     * 这些构造方法都会默认指定排序模式为插入顺序。如果你想构造一个 LinkedHashMap，并打算按从近期访问最少到近期访问最多的顺序（即访问顺序）来保存元素，
     * 那么请使用下面的构造方法构造 LinkedHashMap：public LinkedHashMap(int initialCapacity, float loadFactor, boolean accessOrder)
     * <p>
     * 该哈希映射的迭代顺序就是最后访问其条目的顺序，这种映射很适合构建 LRU 缓存。LinkedHashMap 提供了 removeEldestEntry(Map.Entry<K,V> eldest) 方法。
     * 该方法可以提供在每次添加新条目时移除最旧条目的实现程序，默认返回 false，这样，此映射的行为将类似于正常映射，即永远不能移除最旧的元素。
     * <p>
     * 我们会在后面的文章中详细介绍关于如何用 LinkedHashMap 构建 LRU 缓存。
     *
     * <h2>总结</h2>
     * 其实 LinkedHashMap 几乎和 HashMap 一样：从技术上来说，不同的是它定义了一个 Entry<K,V> header，这个 header 不是放在 Table 里，它是额外独立出来的。
     * LinkedHashMap 通过继承 hashMap 中的 Entry<K,V>,并添加两个属性 Entry<K,V> before,after,和 header 结合起来组成一个双向链表，来实现按插入顺序或访问顺序排序。
     * <p>
     * 在写关于 LinkedHashMap 的过程中，记起来之前面试的过程中遇到的一个问题，也是问我 Map 的哪种实现可以做到按照插入顺序进行迭代？当时脑子是突然短路的，但现在想想，
     * 也只能怪自己对这个知识点还是掌握的不够扎实，所以又从头认真的把代码看了一遍。
     * <p>
     * 不过，我的建议是，大家首先首先需要记住的是：LinkedHashMap 能够做到按照插入顺序或者访问顺序进行迭代，这样在我们以后的开发中遇到相似的问题，才能想到用 LinkedHashMap
     * 来解决，否则就算对其内部结构非常了解，不去使用也是没有什么用的。
     * <p>
     * 我们学习的目的是为了更好的应用。
     */
    static class LinkedHashMap1<K, V> extends HashMap {

        /**
         * The iteration ordering method for this linked hash map: <tt>true</tt>
         * for access-order, <tt>false</tt> for insertion-order.
         * 如果为true，则按照访问顺序；如果为false，则按照插入顺序。
         */
        private final boolean accessOrder;

        /**
         * 双向链表的表头元素。
         */
        private transient Entry<K, V> header;

        /**
         * 我们已经知道 LinkedHashMap 的 Entry 元素继承 HashMap 的 Entry，提供了双向链表的功能。在上述 HashMap 的构造器中，最后会调用 init() 方法，
         * 进行相关的初始化，这个方法在 HashMap 的实现中并无意义，只是提供给子类实现相关的初始化调用。
         */
        public LinkedHashMap1(int initialCapacity, float loadFactor, boolean accessOrder) {
            super(initialCapacity, loadFactor);
            this.accessOrder = accessOrder;
        }

        /**
         * Called by superclass constructors and pseudoconstructors (clone,
         * readObject) before any entries are inserted into the map.  Initializes
         * the chain.
         */
        void init() {
            header = new Entry<>(-1, null, null, null);
            header.before = header.after = header;
        }


        /**
         * LinkedHashMap的Entry元素。
         * 继承HashMap的Entry元素，又保存了其上一个元素before和下一个元素after的引用。
         * <p>
         * LinkedHashMap 中的 Entry 集成与 HashMap 的 Entry，但是其增加了 before 和 after 的引用，指的是上一个元素和下一个元素的引用。
         */
        private static class Entry<K, V> implements Map.Entry<K, V> {
            Entry<K, V> before, after;

            public Entry(int i, Object o, Object o1, Object o2) {

            }

            @Override
            public K getKey() {
                return null;
            }

            @Override
            public V getValue() {
                return null;
            }

            @Override
            public V setValue(V value) {
                return null;
            }

            @Override
            public boolean equals(Object o) {
                return false;
            }

            @Override
            public int hashCode() {
                return 0;
            }
        }


    }

}
