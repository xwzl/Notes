package com.java.base.jvm;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * <h2>什么是拷贝?</h2>
 * 开始之前，我要先强调一下 Java 中的拷贝是什么。首先，让我们对引用拷贝和对象拷贝进行一下区分。
 *
 * <b>引用拷贝:</b> 正如它的名称所表述的意思, 就是创建一个指向对象的引用变量的拷贝。如果我们有
 * 一个 Car 对象，而且让myCar变量指向这个变量，这时候当我们做引用拷贝，那么现在就会有两个myCar
 * 变量，但是对象仍然只存在一个。
 *
 * <b>对象拷贝:</b>会创建对象本身的一个副本。因此如果我们再一次服务我们 car 对象，就会创建这个
 * 对象本身的一个副本, 同时还会有第二个引用变量指向这个被复制出来的对象。
 *
 *
 * <h2>什么是对象?</h2>
 * <p>
 * 深拷贝和浅拷贝都是对象拷贝, 但一个对象实际是什么呢? 当我们谈论到对象时，我们经常会说它就像一粒
 * 浑圆的咖啡豆，已经是一个不能够被进一步分解的单位了，但这种说法太过于简化了。
 * <p>
 * 比方说我们有一个 Person 对象。这个 Person 对象实际上是由其它的对象组合而成的。如示例 4 所示，
 * Person 对象包含了一个 Name 对象和一个 Address 对象。Name 对象又包含了一个 FirstName 对象
 * 和一个 LastName 对象；Address 对象又是由一个 Street 对象以及一个 City 对象组合而成的。那么
 * 当我们讨论本文中的这个 Person 时，实际上我是在讨论这些个对象所组成的整个的对象联系网络。
 *
 * @author xuweizhi
 * @date 2019/03/17 10:53
 */
public class DeepCopy {

    private List<String> list;

    @Before
    public void before() {
        list = Arrays.asList("张三", "李四", "王五");
    }

    /**
     * <h2>浅拷贝</h2>
     * <p>
     * 首先让我们来说说浅拷贝。对象的浅拷贝会对<B>“主”对象进行拷贝，但不会复制主对象里面的对象</B>。
     * <b>"里面的对象“会在原来的对象和它的副本之间共享</b>。例如，我们会为一个 Person对象创建第二个
     * Person 对象, 而两个 Person 会共享相同的 Name 和 Address 对象。
     * <p>
     * 我们有一个类{@link Person}，类里面包含了一个 {@link Name} 和 {@link Address} 对象。拷贝构造器会拿到
     * originalPerson 对象，然后对其应用变量进行复制。
     *
     * <b>浅拷贝的问题就是两个对象并非独立的</b>。如果你修改了其中一个 Person 对象的 Name 对象，那么这次修改也会影响
     * 另外一个 Person 对象。
     */
    @Test
    public void shallowCopy() {

        //创建person对象
        Person person = new Person(new Name("1", "2"), new Address("3", "4"));
        //浅拷贝
        Person person1 = new Person(person, false);
        //直接赋值
        //Person person2 = person;

        //System.out.println(person2 == person);
        System.out.println(person == person1);
        System.out.println(person.equals(person1));

        //测试浅拷贝之间变量共享
        person1.name.setFirstName("我们");

        System.out.println(person.name.getFirstName());
        System.out.println(person1.name.getFirstName());
    }

    /**
     * <h2>深拷贝</h2>
     *
     * <b>不同于浅拷贝，深拷贝是一个整个独立的对象拷贝。如果我们对整个 Person对象进行深拷贝，
     * 我们会对整个对象的结构都进行拷贝。</b>
     * <p>
     * 对一个 Person 的Address对象进行了修改并不会对另外一个对象造成影响。当我们]的代码，会发现我们
     * 不单单对 Person 对象使用了拷贝构造器，同时也会对里面的对象使用拷贝构造器。
     * <p>
     * 不过，故事到这儿并没有结束。要创建一个真正的深拷贝，就需要我们一直这样拷贝下去，一直覆盖到 Person
     * 对象所有的内部元素, 最后只剩下原始的类型以及“不可变对象（Immutables）”。让我们观察下如下这个
     * Street 类以获得更好的理解:
     * <p>
     * Street 对象有两个实体变量组成 – String 类型的 name 以及 int 类型的 number。int  类型的 number
     * 是一个原始类型，并非对象。它只是一个简单的值，不能共享, 因此在创建第二个实体变量时，我们可以自动创建
     * 一个独立的拷贝。String 是一个不可变对象（Immutable）。简言之，不可变对象也是对象，可一旦创建好了以
     * 后就再也不能被修改了。因此，你可以不用为其创建深拷贝就能对其进行共享。
     */
    @Test
    public void deepCopy() {
        //创建person对象
        Person person = new Person(new Name("11", "22"), new Address("3", "4"));
        //深拷贝
        Person person1 = new Person(person, true);
        //测试深拷贝之间变量共享
        person1.name.setFirstName("我们");

        System.out.println(person == person1);
        System.out.println(person.equals(person1));

        System.out.println(person.name.getFirstName());
        System.out.println(person1.name.getFirstName());
    }

    /**
     * https://marcus-biel.com/object-clone-method/
     *
     * <h2>{@link Object#clone()}</h2>
     * <p>
     * 但是，有些事情仍然是错误的。该克隆（）方法是红色的。问题是类中的clone（）方法是对象保护的。
     * 每个对象都可以调用受保护从Object类继承的方法本身。但是，它永远不能在其他对象上调用这样的方法。
     * 虽然我们的Porsche类和PorscheTest类都可以访问clone（），但PorscheTest永远不能调用Porsche
     * 对象的继承clone（）方法。
     * <p>
     * 要解决此问题，我们必须覆盖Porsche类中的clone（）方法并提高其可见性。首先，我们将clone（）方法
     * 的访问修饰符更改为public，并将方法返回类型从Object更改为Porsche，这使得我们的代码更清晰，因为
     * 我们不必将克隆对象转换为Porsche 。要实现clone方法，我们调用super.clone（）。super关键字意味
     * 着我们从超类调用一个方法，在这种情况下是超类。
     * <p>
     * 另请注意，Object类的clone方法声明了一个已检查的CloneNotSupportedException，因此我们必须决定
     * 是声明它还是处理它。在实现（支持）clone（）方法时声明CloneNotSupportedException是相互矛盾的。
     * 因此，您应该省略它。所有可能的错误情况都是严重错误，因此您可能做的最好的事情就是抛出错误。
     * <p>
     * 但是，当我们运行上面的代码时，我们遇到另一个问题：CloneNotSupportedException。为了纠正这个问题，
     * 我们必须实现Cloneable接口。该Cloneable的接口不包含任何方法，它是一个标记接口-用于标记实现它的类
     * 的某些属性的空接口。
     * <p>
     * 深克隆
     */
    @Test
    public void cloneTest() throws CloneNotSupportedException {

        Porsche porsche = new Porsche("法拉利", new Name("张三丰", "栈"));

        Porsche clone = porsche.clone();
        System.out.println(porsche == porsche);

        porsche.name = new Name("周树人", "鲁迅");
        porsche.flag = "保时捷";


        System.out.println(porsche);
        System.out.println(clone);
    }

    /**
     * <h2>在Array上使用clone（）</h2>
     * <p>
     * 正如我之前所说的，通常不建议使用克隆方法。然而，一个情况下我不建议将其用途是用于克隆阵列，
     * 如图实施例8在这里，在中，我们创建类型的数组的字符串。我们使用我们的数组调用array.clone（）
     * 方法，并将新的克隆数组分配给名为copiedArray的引用变量。为了证明这不仅是对同一个对象的引用，
     * 而是一个新的独立对象，我们使用原始数组和新创建的copiedArray调用assertNotSame（）。两个数
     * 组都具有相同的内容，正如我们在for-each循环中打印copiedArray时所看到的那样。
     * <p>
     * 该克隆（）方法复制每个字符串对象阵列中到含有完全新的字符串对象的新阵列对象。如果运行上面的代
     * 码，clone（）方法将按预期工作，测试将为绿色。在这种情况下，clone（）方法是复制数组的首选技术。
     * Clone（）适用于原始值和“ 不可变 ”的数组，但它对于对象数组也不起作用。作为旁注，请参阅Java
     * Specialists的Newsletter Issue 124，Heinz进行的测试显示clone（）方法对于复制非常小的数组要
     * 慢一些，但对于性能最重要的大型数组，它实际上比任何其他复制数组的方法都要快。
     */
    @Test
    public void copyArray() {
        String[] array = {"one", "two", "three"};
        String[] copiedArray = array.clone();
        for (String str : copiedArray) {
            System.out.println(str);
        }
    }

    /**
     * Array 底层克隆实现 或者ArrayList自动扩容探究
     * <p>
     * 逐层进入{@link Arrays#copyOf(int[], int)}或者{@link ArrayList#clone()}
     *
     * <blockquote><pre>
     *    // Arrays copyOf静态方法
     *     public static <T,U> T[] copyOf(U[] original, int newLength, Class<? extends T[]> newType) {
     *         T[] copy = ((Object)newType == (Object)Object[].class)
     *             ? (T[]) new Object[newLength]
     *             : (T[]) Array.newInstance(newType.getComponentType(), newLength);
     *         System.arraycopy(original, 0, copy, 0,
     *                          Math.min(original.length, newLength));
     *         return copy;
     *     }
     *     //ArrayList 自动扩容，监测集合长度是否足够，不足够，进行扩容grow，调用Arrays.copy方法进行扩容
     *     public void ensureCapacity(int minCapacity) {
     *         if (minCapacity > elementData.length
     *             && !(elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA
     *                  && minCapacity <= DEFAULT_CAPACITY)) {
     *             modCount++;
     *             grow(minCapacity);
     *         }
     *     }
     *
     *    //返回扩容后的数组
     *    private Object[] grow(int minCapacity) {
     *         return elementData = Arrays.copyOf(elementData,newCapacity(minCapacity));
     *     }
     *
     *    //返回扩容后的集合大小，默认扩容1.5倍
     *    private int newCapacity(int minCapacity) {
     *         // overflow-conscious code
     *         int oldCapacity = elementData.length;
     *         int newCapacity = oldCapacity + (oldCapacity >> 1);
     *         if (newCapacity - minCapacity <= 0) {
     *             if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
     *                 return Math.max(DEFAULT_CAPACITY, minCapacity);
     *             if (minCapacity < 0) // overflow
     *                 throw new OutOfMemoryError();
     *             return minCapacity;
     *         }
     *         return (newCapacity - MAX_ARRAY_SIZE <= 0)
     *             ? newCapacity
     *             : hugeCapacity(minCapacity);
     *     }
     *
     * </pre></blockquote>
     * <p>
     * 最后得出结论，无论是Arrays还是ArrayList进行数组克隆，最终调用的都是{@link System#arraycopy(Object, int, Object, int, int)}方法
     */
    @Test
    public void cloneSystem() {
        String[] src = new String[list.size()];
        String[] dest = new String[2];
        list.toArray(src);
        System.arraycopy(src, 1, dest, 0, 2);
        System.out.println(dest[0]);
        System.out.println(dest[1]);
    }

    /**
     * 遍历演示
     */
    @Test
    public void traversingList() {
        //list.forEach(System.out::println);
        Object[] objects = Arrays.copyOf(list.toArray(), list.size());

        //第一种遍历方式
        System.out.println("\n第一种遍历方式");
        for (int i = 0; i < objects.length; i++) {
            System.out.println(objects);
        }

        //第二种遍历方式
        System.out.println("\n第二种遍历方式");
        for (Object object : objects) {
            System.out.println(object);
        }

        //第三种遍历方式
        System.out.println("\n第三种遍历方式");
        Arrays.asList(objects).forEach(o -> System.out.println(o));

        //第四种遍历方式
        System.out.println("\n第四种遍历方式");
        Stream.of(objects).forEach(System.out::println);

        //遍历的同时对值进行替换
        System.out.println("\n遍历的同时对值进行替换");
        Stream.of(objects).map(o -> UUID.randomUUID().toString()).forEach(System.out::println);

    }

}


/**
 * clone 演示
 */
//@Data
class Porsche implements Cloneable {

    String flag;

    Name name;

    Porsche(String flag, Name name) {
        this.flag = flag;
        this.name = name;
    }

    @Override
    protected Porsche clone() throws CloneNotSupportedException {
        return (Porsche) super.clone();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Porsche{" +
                "flag='" + flag + '\'' +
                ", name=" + name +
                '}';
    }
}

//@Data
class Person {

    Name name;

    private Address address;

    /**
     * 拷贝构造器代码示例
     *
     * @param flag true 深拷贝，false 浅拷贝
     */
    Person(Person originPerson, boolean flag) {
        if (flag) {
            this.name = new Name(originPerson.name.firstName, originPerson.name.lastName);
            this.address = new Address(originPerson.address.city, originPerson.address.Street);
        } else {
            this.name = originPerson.name;
            this.address = originPerson.address;
        }
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    Person(Name name, Address address) {
        this.name = name;
        this.address = address;
    }
}

//@Data
class Name {

    String firstName;

    String lastName;

    Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    String getFirstName() {
        return firstName;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

//@Data
class Address {

    String city;

    String Street;

    Address(String city, String street) {
        this.city = city;
        Street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

}