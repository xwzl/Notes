package com.java.base.array;

import java.util.*;

/**
 * <h1>ArrayList 的实现原理</h1>
 * <h2>ArrayList 概述</h2>
 * ArrayList 可以理解为动态数组，用 MSDN 中的说法，就是 Array 的复杂版本。与 Java 中的数组相比，它的容量能动态增长。ArrayList
 * 是 List 接口的可变数组的实现。实现了所有可选列表操作，并允许包括 null 在内的所有元素。除了实现 List 接口外，此类还提供一些方法来
 * 操作内部用来存储列表的数组的大小。（此类大致上等同于 Vector 类，除了此类是不同步的。）
 * <p>
 * 每个 ArrayList 实例都有一个容量，该容量是指用来存储列表元素的数组的大小。它总是至少等于列表的大小。随着向 ArrayList 中不断添加元素，
 * 其容量也自动增长。自动增长会带来数据向新数组的重新拷贝，因此，如果可预知数据量的多少，可在构造 ArrayList 时指定其容量。在添加大量元素前，
 * 应用程序也可以使用 ensureCapacity 操作来增加 ArrayList 实例的容量，这可以减少递增式再分配的数量。
 * <p>
 * 注意，此实现不是同步的。如果多个线程同时访问一个 ArrayList 实例，而其中至少一个线程从结构上修改了列表，那么它必须保持外部同步。（结构上
 * 的修改是指任何添加或删除一个或多个元素的操作，或者显式调整底层数组的大小；仅仅设置元素的值不是结构上的修改。）
 * <p>
 * 我们先学习了解其内部的实现原理，才能更好的理解其应用。
 *
 * <h2>ArrayList 的实现</h2>
 * 对于 ArrayList 而言，它实现 List 接口、底层使用数组保存所有元素。其操作基本上是对数组的操作。下面我们来分析 ArrayList 的源代码：
 *
 * <h2>实现的接口</h2>
 *
 * <blockquote><pre>
 * public class ArrayList<E> extends AbstractList<E>
 *         implements List<E>, RandomAccess, Cloneable, java.io.Serializable
 * {
 * }
 * </pre></blockquote>
 * ArrayList 继承了 AbstractList，实现了 List。它是一个数组队列，提供了相关的添加、删除、修改、遍历等功能。
 * <p>
 * ArrayList 实现了 RandmoAccess 接口，即提供了随机访问功能。RandmoAccess 是 java 中用来被 List 实现，为 List 提供快速访问功能的。
 * 在 ArrayList 中，我们即可以通过元素的序号快速获取元素对象；这就是快速随机访问。
 * <p>
 * ArrayList 实现了 Cloneable 接口，即覆盖了函数 clone()，能被克隆。 ArrayList 实现 java.io.Serializable 接口，这意味着 ArrayList
 * 支持序列化，能通过序列化去传输。
 * <p>
 * //The array buffer into which the elements of the ArrayList are stored.
 * //The capacity of the ArrayList is the length of this array buffer.
 * 底层使用数组实现
 * <p>
 * private transient Object[] elementData;
 *
 * @author xuweizhi
 * @date 2019/03/18 17:59
 */
public class ArrayListUnderlying {


    /**
     * <h2>ArrayList 提供了三种方式的构造器：</h2>
     * <ul>
     * <li>public ArrayList()可以构造一个默认初始容量为10的空列表；</li>
     * <li>public ArrayList(int initialCapacity)构造一个指定初始容量的空列表；</li>
     * <li>public ArrayList(Collection<? extends E> c)构造一个包含指定 collection 的元素的列表，这些
     * 元素按照该collection的迭代器返回它们的顺序排列的。</li>
     * </ul>
     *
     * <h2>调整数组容量</h2>
     * 从上面介绍的向 ArrayList 中存储元素的代码中，我们看到，每当向数组中添加元素时，都要去检查添加后元素的个数是
     * 否会超出当前数组的长度，如果超出，数组将会进行扩容，以满足添加数据的需求。数组扩容有两个方法，其中开发者可以
     * 通过一个 public 的方法ensureCapacity(int minCapacity)来增加 ArrayList 的容量，而在存储元素等操作过程中，
     * 如果遇到容量不足，会调用priavte方法private void ensureCapacityInternal(int minCapacity)实现。
     * <p>
     * 从上述代码中可以看出，数组进行扩容时，会将老数组中的元素重新拷贝一份到新的数组中，每次数组容量的增长大约是其原
     * 容量的 1.5 倍（从int newCapacity = oldCapacity + (oldCapacity >> 1)这行代码得出）。这种操作的代价是很高
     * 的，因此在实际使用时，我们应该尽量避免数组容量的扩张。当我们可预知要保存的元素的多少时，要在构造 ArrayList 实
     * 例时，就指定其容量，以避免数组扩容的发生。或者根据实际需求，通过调用ensureCapacity 方法来手动增加 ArrayList
     * 实例的容量。
     */
    class ArrayList1<E> extends AbstractList<E>
            implements List<E>, RandomAccess, Cloneable, java.io.Serializable {

        private static final long serialVersionUID = 7901317461978776525L;

        private static final int MAX_ARRAY_SIZE = 10;

        private int size;

        private final Object[] elementData;

        /**
         * Constructs an empty list with an initial capacity of ten.
         */
        public ArrayList1() {
            this(10);
        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public E get(int index) {
            return null;
        }

        /**
         * Constructs an empty list with the specified initial capacity.
         *
         * @param initialCapacity the initial capacity of the list
         * @throws IllegalArgumentException if the specified initial capacity
         *                                  is negative
         */
        public ArrayList1(int initialCapacity) {
            super();
            if (initialCapacity < 0)
                throw new IllegalArgumentException("Illegal Capacity: " +
                        initialCapacity);
            this.elementData = new Object[initialCapacity];
        }

        /**
         * Constructs a list containing the elements of the specified
         * collection, in the order they are returned by the collection's
         * iterator.
         *
         * @param c the collection whose elements are to be placed into this list
         * @throws NullPointerException if the specified collection is null
         */
        public ArrayList1(Collection<E> c) {
            elementData = c.toArray();
            size = elementData.length;
            // c.toArray might (incorrectly) not return Object[] (see 6260652)
            if (elementData.getClass() != Object[].class) {
                //elementData = Arrays.copyOf(elementData, size, Object[].class);
            }
        }

        /**
         * Replaces the element at the specified position in this list with
         * the specified element.
         *
         * @param index   index of the element to replace
         * @param element element to be stored at the specified position
         * @return the element previously at the specified position
         * @throws IndexOutOfBoundsException {@inheritDoc}
         */
        public E set(int index, E element) {
            rangeCheck(index);

            E oldValue = elementData(index);
            elementData[index] = element;
            return oldValue;
        }

        /**
         * Checks if the given index is in range.  If not, throws an appropriate
         * runtime exception.  This method does *not* check if the index is
         * negative: It is always used immediately prior to an array access,
         * which throws an ArrayIndexOutOfBoundsException if index is negative.
         */
        private void rangeCheck(int index) {
            if (index >= size) {
                //throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
            }
        }

        /**
         * Appends the specified element to the end of this list.
         *
         * @param e element to be appended to this list
         * @return <tt>true</tt> (as specified by {@link Collection#add})
         */
        public boolean add(E e) {
            ensureCapacityInternal(size + 1);  // Increments modCount!!
            elementData[size++] = e;
            return true;
        }

        private void ensureCapacityInternal(int minCapacity) {
            modCount++;
            // overflow-conscious code
            if (minCapacity - elementData.length > 0)
                grow(minCapacity);
        }

        private void grow(int minCapacity) {
            // overflow-conscious code
            int oldCapacity = elementData.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            if (newCapacity - minCapacity < 0)
                newCapacity = minCapacity;
            if (newCapacity - MAX_ARRAY_SIZE > 0) {

            }
            //newCapacity = hugeCapacity(minCapacity);
            // minCapacity is usually close to size, so this is a win:
            //elementData = Arrays.copyOf(elementData, newCapacity);
        }

        //add(int index, E element)：在 index 位置插入 element。

        /**
         * Inserts the specified element at the specified position in this
         * list. Shifts the element currently at that position (if any) and
         * any subsequent elements to the right (adds one to their indices).
         *
         * @param index   index at which the specified element is to be inserted
         * @param element element to be inserted
         * @throws IndexOutOfBoundsException {@inheritDoc}
         */
        public void add(int index, E element) {
            //rangeCheckForAdd(index);

            ensureCapacityInternal(size + 1);  // Increments modCount!!
            System.arraycopy(elementData, index, elementData, index + 1,
                    size - index);
            elementData[index] = element;
            size++;
        }

        //addAll(Collection<? extends E> c) 和 addAll(int index, Collection<? extends E> c)：
        //将特定 Collection 中的元素添加到 Arraylist 末尾。

        /**
         * Appends all of the elements in the specified collection to the end of
         * this list, in the order that they are returned by the
         * specified collection's Iterator.  The behavior of this operation is
         * undefined if the specified collection is modified while the operation
         * is in progress.  (This implies that the behavior of this call is
         * undefined if the specified collection is this list, and this
         * list is nonempty.)
         *
         * @param c collection containing elements to be added to this list
         * @return <tt>true</tt> if this list changed as a result of the call
         * @throws NullPointerException if the specified collection is null
         */
        public boolean addAll(Collection<? extends E> c) {
            Object[] a = c.toArray();
            int numNew = a.length;
            ensureCapacityInternal(size + numNew);  // Increments modCount
            System.arraycopy(a, 0, elementData, size, numNew);
            size += numNew;
            return numNew != 0;
        }

        /**
         * Inserts all of the elements in the specified collection into this
         * list, starting at the specified position.  Shifts the element
         * currently at that position (if any) and any subsequent elements to
         * the right (increases their indices).  The new elements will appear
         * in the list in the order that they are returned by the
         * specified collection's iterator.
         *
         * @param index index at which to insert the first element from the
         *              specified collection
         * @param c     collection containing elements to be added to this list
         * @return <tt>true</tt> if this list changed as a result of the call
         * @throws IndexOutOfBoundsException {@inheritDoc}
         * @throws NullPointerException      if the specified collection is null
         */
        public boolean addAll(int index, Collection<? extends E> c) {
            //rangeCheckForAdd(index);

            Object[] a = c.toArray();
            int numNew = a.length;
            ensureCapacityInternal(size + numNew);  // Increments modCount

            int numMoved = size - index;
            if (numMoved > 0)
                System.arraycopy(elementData, index, elementData, index + numNew,
                        numMoved);

            System.arraycopy(a, 0, elementData, index, numNew);
            size += numNew;
            return numNew != 0;
        }


    }

    private <E> E elementData(int index) {
        return null;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
    }
}
