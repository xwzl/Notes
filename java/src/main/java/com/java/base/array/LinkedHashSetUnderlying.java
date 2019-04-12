package com.java.base.array;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * <h1>LinkedHashSet 的实现原理</h1>
 * <h2>LinkedHashSet 概述</h2>
 * 思考了好久，到底要不要总结 LinkedHashSet 的内容 = = 我在之前的博文中，分别写了 HashMap 和 HashSet，然后我们可以看到
 * HashSet 的方法基本上都是基于 HashMap 来实现的，说白了，HashSet内部的数据结构就是一个 HashMap，其方法的内部几乎就是在
 * 调用 HashMap 的方法。
 * <p>
 * LinkedHashSet 首先我们需要知道的是它是一个 Set 的实现，所以它其中存的肯定不是键值对，而是值。此实现与 HashSet 的不同之
 * 处在于，LinkedHashSet 维护着一个运行于所有条目的双重链接列表。此链接列表定义了迭代顺序，该迭代顺序可为插入顺序或是访问顺序。
 * <p>
 * 看到上面的介绍，是不是感觉其与 HashMap 和 LinkedHashMap 的关系很像？
 * <p>
 * 注意，此实现不是同步的。如果多个线程同时访问链接的哈希Set，而其中至少一个线程修改了该 Set，则它必须保持外部同步。
 *
 * @author xuweizhi
 * @date 2019/03/18 17:42
 */
public class LinkedHashSetUnderlying {


    /**
     * <h2>LinkedHashSet 的实现</h2>
     * 对于 LinkedHashSet 而言，它继承与 HashSet、又基于 LinkedHashMap 来实现的。
     * <p>
     * LinkedHashSet 底层使用 LinkedHashMap 来保存所有元素，它继承与 HashSet，其所有的方法操作上又与 HashSet 相同，
     * 因此 LinkedHashSet 的实现上非常简单，只提供了四个构造方法，并通过传递一个标识参数，调用父类的构造器，底层构造一个
     * LinkedHashMap 来实现，在相关操作上与父类 HashSet 的操作相同，直接调用父类 HashSet 的方法即可。
     * <p>
     * 以下几乎就是 LinkedHashSet 的全部代码了，那么读者可能就会怀疑了，不是说 LinkedHashSet 是基于 LinkedHashMap
     * 实现的吗？那我为什么在源码中甚至都没有看到出现过 LinkedHashMap。不要着急，我们可以看到在 LinkedHashSet 的构造方
     * 法中，其调用了父类的构造方法。我们可以进去看一下：
     *
     * <blockquote><pre>
     *     //以指定的initialCapacity和loadFactor构造一个新的空链接哈希集合。
     *     //此构造函数为包访问权限，不对外公开，实际只是是对LinkedHashSet的支持。
     *     //
     *     //实际底层会以指定的参数构造一个空LinkedHashMap实例来实现。
     *     HashSet(int initialCapacity, float loadFactor, boolean dummy) {
     *         map = new LinkedHashMap<E,Object>(initialCapacity, loadFactor);
     *     }
     * </pre></blockquote>
     * <p>
     * 在父类 HashSet 中，专为 LinkedHashSet 提供的构造方法如下，该方法为包访问权限，并未对外公开。
     * <p>
     * 由上述源代码可见，LinkedHashSet 通过继承 HashSet，底层使用 LinkedHashMap，以很简单明了的方式来实现了其自身的所有功能。
     * <h2>总结</h2>
     * <p>
     * 以上就是关于 LinkedHashSet 的内容，我们只是从概述上以及构造方法这几个方面介绍了，并不是我们不想去深入其读取或者写入方法，而
     * 是其本身没有实现，只是继承于父类 HashSet 的方法。
     * <p>
     * 所以我们需要注意的点是：
     * <ul>
     * <li>LinkedHashSet 是 Set 的一个具体实现，其维护着一个运行于所有条目的双重链接列表。此链接列表定义了迭代顺序，该迭代顺序可为插入顺序或是访问顺序。</li>
     * <li>LinkedHashSet 继承与 HashSet，并且其内部是通过 LinkedHashMap 来实现的。有点类似于我们之前说的LinkedHashMap 其内部是基于
     * Hashmap 实现一样，不过还是有一点点区别的（具体的区别大家可以自己去思考一下）。</li>
     * <li>如果我们需要迭代的顺序为插入顺序或者访问顺序，那么 LinkedHashSet 是需要你首先考虑的。</li>
     * </ul>
     */
    class LinkedHashSet<E>
            extends HashSet<E>
            implements Set<E>, Cloneable, java.io.Serializable {

        private static final long serialVersionUID = -2851667679971038690L;

        /**
         * 构造一个带有指定初始容量和加载因子的新空链接哈希set。
         * <p>
         * 底层会调用父类的构造方法，构造一个有指定初始容量和加载因子的LinkedHashMap实例。
         *
         * @param initialCapacity 初始容量。
         * @param loadFactor      加载因子。
         */
        public LinkedHashSet(int initialCapacity, float loadFactor) {
            //super(initialCapacity, loadFactor, true);
        }

        /**
         * 构造一个带指定初始容量和默认加载因子0.75的新空链接哈希set。
         * <p>
         * 底层会调用父类的构造方法，构造一个带指定初始容量和默认加载因子0.75的LinkedHashMap实例。
         *
         * @param initialCapacity 初始容量。
         */
        public LinkedHashSet(int initialCapacity) {
            //super(initialCapacity, .75f, true);
        }

        /**
         * 构造一个带默认初始容量16和加载因子0.75的新空链接哈希set。
         * <p>
         * 底层会调用父类的构造方法，构造一个带默认初始容量16和加载因子0.75的LinkedHashMap实例。
         */
        public LinkedHashSet() {
            //super(16, .75f, true);
        }

        /**
         * 构造一个与指定collection中的元素相同的新链接哈希set。
         * <p>
         * 底层会调用父类的构造方法，构造一个足以包含指定collection
         * 中所有元素的初始容量和加载因子为0.75的LinkedHashMap实例。
         *
         * @param c 其中的元素将存放在此set中的collection。
         */
        public LinkedHashSet(Collection<? extends E> c) {
            //super(Math.max(2 * c.size(), 11), .75f, true);
            addAll(c);
        }
    }

    public static void main(String[] args){

    }

}
