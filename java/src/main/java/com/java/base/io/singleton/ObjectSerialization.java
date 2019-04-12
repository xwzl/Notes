package com.java.base.io.singleton;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <h2>序列化与反序列化</h2>
 * 序列化 (Serialization)是将对象的状态信息转换为可以存储或传输的形式的过程。一般将一个对象存储至一个储存媒介，例如档案或是记亿体缓冲等。
 * 在网络传输过程中，可以是字节或是XML等格式。而字节的或XML编码格式可以还原完全相等的对象。这个相反的过程又称为反序列化。
 * <p>
 * <h2>Java对象的序列化与反序列化</h2>
 * 在Java中，我们可以通过多种方式来创建对象，并且只要对象没有被回收我们都可以复用该对象。但是，我们创建出来的这些Java对象都是存在于JVM的堆
 * 内存中的。只有JVM处于运行状态的时候，这些对象才可能存在。一旦JVM停止运行，这些对象的状态也就随之而丢失了。
 * <p>
 * 使用Java对象序列化，在保存对象时，会把其状态保存为一组字节，在未来，再将这些字节组装成对象。<b>必须注意地是，对象序列化保存的是对象的”状态”，
 * 即它的成员变量。由此可知，对象序列化不会关注类中的静态变量</b>。
 * <p>
 * 但是在真实的应用场景中，我们需要将这些对象持久化下来，并且能够在需要的时候把对象重新读取出来。Java的对象序列化可以帮助我们实现该功能。
 * <p>
 * 对象序列化机制（object serialization）是Java语言内建的一种对象持久化方式，通过对象序列化，可以把对象的状态保存为字节数组，并且可以在
 * 有需要的时候将这个字节数组通过反序列化的方式再转换成对象。对象序列化可以很容易的在JVM中的活动对象和字节数组（流）之间进行转换。
 * <p>
 * 在Java中，对象的序列化与反序列化被广泛应用到RMI(远程方法调用)及网络传输中。
 * <p>
 * <h2>相关接口及类</h2>
 * Java为了方便开发人员将Java对象进行序列化及反序列化提供了一套方便的API来支持。其中包括以下接口和类：
 * <ul>
 * <li>java.io.Serializable</li>
 * <li>java.io.Externalizable</li>
 * <li>ObjectOutput</li>
 * <li>ObjectInput</li>
 * <li>ObjectOutputStream</li>
 * <li>ObjectInputStream</li>
 * </ul>
 *
 * <h2>Serializable 接口</h2>
 * 类通过实现 java.io.Serializable 接口以启用其序列化功能。未实现此接口的类将无法使其任何状态序列化或反序列化。可序列化类的所有子类
 * 型本身都是可序列化的。序列化接口没有方法或字段，仅用于标识可序列化的语义。 (该接口并没有方法和字段，为什么只有实现了该接口的类的对象
 * 才能被序列化呢？)
 * <p>
 * 当试图对一个对象进行序列化的时候，如果遇到不支持 Serializable 接口的对象。在此情况下，将抛出NotSerializableException。
 * <p>
 * 如果要序列化的类有父类，要想同时将在父类中定义过的变量持久化下来，那么父类也应该集成java.io.Serializable接口。
 *
 * <h2>ObjectOutput和ObjectInput 接口</h2>
 * ObjectInput接口 扩展自 DataInput 接口以包含对象的读操作。
 * <p>
 * DataInput 接口用于从二进制流中读取字节，并根据所有 Java 基本类型数据进行重构。同时还提供根据 UTF-8 修改版格式的数据重构 String 的工具。
 * <p>
 * 对于此接口中的所有数据读取例程来说，如果在读取所需字节数之前已经到达文件末尾 (end of file)，则将抛出 EOFException（IOException 的一种）。
 * 如果因为到达文件末尾以外的其他原因无法读取字节，则将抛出 IOException 而不是 EOFException。尤其是，在输入流已关闭的情况下，将抛出 IOException。
 * <p>
 * ObjectOutput 扩展 DataOutput 接口以包含对象的写入操作。
 * <p>
 * DataOutput 接口用于将数据从任意 Java 基本类型转换为一系列字节，并将这些字节写入二进制流。同时还提供了一个将 String 转换成 UTF-8
 * 修改版格式并写入所得到的系列字节的工具。
 * <p>
 * 对于此接口中写入字节的所有方法，如果由于某种原因无法写入某个字节，则抛出 IOException。
 *
 * <h2>ObjectOutputStream类和ObjectInputStream类</h2>
 * 通过前面的代码片段中我们也能知道，我们一般使用ObjectOutputStream的writeObject方法把一个对象进行持久化。再使用ObjectInputStream的readObject
 * 从持久化存储中把对象读取出来。
 * <p>
 * <h2>Transient 关键字</h2>
 * Transient 关键字的作用是控制变量的序列化，在变量声明前加上该关键字，可以阻止该变量被序列化到文件中，在被反序列化后，transient 变量的值被设为初始值，
 * 如 int 型的是 0，对象型的是 null。
 * <p>
 * 序列化ID<h2></h2>
 * 虚拟机是否允许反序列化，不仅取决于类路径和功能代码是否一致，一个非常重要的一点是两个类的序列化 ID 是否一致（就是 private static final long serialVersionUID)
 * <p>
 * 序列化 ID 在 Eclipse 下提供了两种生成策略，一个是固定的 1L，一个是随机生成一个不重复的 long 类型数据（实际上是使用 JDK 工具生成），在这里有一个建议，
 * 如果没有特殊需求，就是用默认的 1L 就可以，这样可以确保代码一致时反序列化成功。那么随机生成的序列化 ID 有什么作用呢，有些时候，通过改变序列化 ID 可以用来
 * 限制某些用户的使用。
 *
 * @author xuweizhi
 * @date 2019/03/18 10:39
 */
public class ObjectSerialization {

    /**
     * <ul>
     * <li>在Java中，只要一个类实现了java.io.Serializable接口，那么它就可以被序列化。</li>
     * <li>通过ObjectOutputStream和ObjectInputStream对对象进行序列化及反序列化</li>
     * <li>虚拟机是否允许反序列化，不仅取决于类路径和功能代码是否一致，一个非常重要的一点是两个类的序列化 ID 是否一致
     * （就是 private static final long serialVersionUID）</li>
     * <li>序列化并不保存静态变量。</li>
     * <li>要想将父类对象也序列化，就需要让父类也实现Serializable 接口。</li>
     * <li>Transient 关键字的作用是控制变量的序列化，在变量声明前加上该关键字，可以阻止该变量被序列化到文件中，在被反序列化后，transient
     * 变量的值被设为初始值，如 int 型的是 0，对象型的是 null。</li>
     * <li>服务器端给客户端发送序列化对象数据，对象中有一些数据是敏感的，比如密码字符串等，希望对该密码字段在序列化时，进行加密，而客户端
     * 如果拥有解密的密钥，只有在客户端进行反序列化时，才可以对密码进行读取，这样可以一定程度保证序列化对象的数据安全。</li>
     * </ul>
     */
    @Test
    public void serialization() {
        //Initializes The Object
        User user = new User();
        user.setName("hollis");
        user.setAge(23);
        System.out.println(user);

        //Write Obj to File
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("tempFile"));
            oos.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(oos);
        }

        //Read Obj from File
        File file = new File("tempFile");
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            User newUser = (User) ois.readObject();
            System.out.println(newUser);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(ois);
            try {
                FileUtils.forceDelete(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 通过上面的实例可以发现，对User1类进行序列化及反序列化之后得到的对象的所有属性的值都变成了默认值。也就是说，之前的
     * 那个对象的状态并没有被持久化下来。这就是Externalizable接口和Serializable接口的区别：
     * <p>
     * Externalizable继承了Serializable，该接口中定义了两个抽象方法：writeExternal()与readExternal()。当使用
     * Externalizable接口来进行序列化与反序列化的时候需要开发人员重写writeExternal()与readExternal()方法。由于上
     * 面的代码中，并没有在这两个方法中定义序列化实现细节，所以输出的内容为空。还有一点值得注意：在使用Externalizable进
     * 行序列化的时候，在读取对象时，会调用被序列化类的无参构造器去创建一个新的对象，然后再将被保存对象的字段的值分别填充到
     * 新对象中。所以，实现Externalizable接口的类必须要提供一个public的无参的构造器.
     * <p>
     * 重写方法对对象进行数据填充
     */
    @Test
    public void test2() throws IOException, ClassNotFoundException {
        //Write Obj to file
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tempFile"));
        User1 user = new User1();
        user.setName("hollis");
        user.setAge(23);
        oos.writeObject(user);
        //Read Obj from file
        File file = new File("tempFile");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        User1 newInstance = (User1) ois.readObject();
        //output
        System.out.println(newInstance);
    }

    /**
     * 了解ArrayList的人都知道，ArrayList底层是通过数组实现的。那么数组elementData其实就是用来保存列表中的元素的。
     * 通过该属性的声明方式我们知道，他是无法通过序列化持久化下来的。那么为什么code 4的结果却通过序列化和反序列化把List
     * 中的元素保留下来了呢？
     * <h2>writeObject和readObject方法</h2>
     * 在ArrayList中定义了来个方法： writeObject和readObject。
     * <p>
     * <h3>这里先给出结论:</h3>
     * <p>
     * 在序列化过程中，如果被序列化的类中定义了writeObject 和 readObject 方法，虚拟机会试图调用对象类里的 writeObject
     * 和 readObject 方法，进行用户自定义的序列化和反序列化。
     * <p>
     * 如果没有这样的方法，则默认调用是 ObjectOutputStream 的 defaultWriteObject 方法以及 ObjectInputStream 的 defaultReadObject 方法。
     * <p>
     * <h3>两个方法的具体实现</h3>
     * <blockquote><pre>
     * private void readObject(java.io.ObjectInputStream s)
     *         throws java.io.IOException, ClassNotFoundException {
     *         elementData = EMPTY_ELEMENTDATA;
     *
     *         // Read in size, and any hidden stuff
     *         s.defaultReadObject();
     *
     *         // Read in capacity
     *         s.readInt(); // ignored
     *
     *         if (size > 0) {
     *             // be like clone(), allocate array based upon size not capacity
     *             ensureCapacityInternal(size);
     *
     *             Object[] a = elementData;
     *             // Read in all elements in the proper order.
     *             for (int i=0; i<size; i++) {
     *                 a[i] = s.readObject();
     *             }
     *         }
     * }
     *
     * private void writeObject(java.io.ObjectOutputStream s)
     *         throws java.io.IOException{
     *         // Write out element count, and any hidden stuff
     *         int expectedModCount = modCount;
     *         s.defaultWriteObject();
     *
     *         // Write out size as capacity for behavioural compatibility with clone()
     *         s.writeInt(size);
     *
     *         // Write out all elements in the proper order.
     *         for (int i=0; i<size; i++) {
     *             s.writeObject(elementData[i]);
     *         }
     *
     *         if (modCount != expectedModCount) {
     *             throw new ConcurrentModificationException();
     *         }
     * }
     * </pre></blockquote>
     * 用户自定义的 writeObject 和 readObject 方法可以允许用户控制序列化的过程，比如可以在序列化的过程中动态改变序列化的数值。
     * <h2>那么为什么ArrayList要用这种方式来实现序列化呢？</h2>
     * <h3>why transient</h3>
     * ArrayList实际上是动态数组，每次在放满以后自动增长设定的长度值，如果数组自动增长长度设为100，而实际只放了一个元素，那就会序列化99个null元素。为了
     * 保证在序列化的时候不会将这么多null同时进行序列化，ArrayList把元素数组设置为transient。
     * <h3>why writeObject and readObject</h3>
     * <b>前面说过，为了防止一个包含大量空对象的数组被序列化，为了优化存储，所以，ArrayList使用transient来声明elementData</b>。但是，作为一个集合，
     * 在序列化过程中还必须保证其中的元素可以被持久化下来，所以，<b>通过重写writeObject 和 readObject方法的方式把其中的元素保留下来</b>。
     * <p>
     * writeObject方法把elementData数组中的元素遍历的保存到输出流（ObjectOutputStream）中。
     * <p>
     * readObject方法从输入流（ObjectInputStream）中读出对象并保存赋值到elementData数组中。
     * <p>
     * 至此，我们先试着来回答刚刚提出的问题：如何自定义的序列化和反序列化策略
     * <h3>可以通过在被序列化的类中增加writeObject 和 readObject方法。那么问题又来了：</h3>
     * <p>
     * 虽然ArrayList中写了writeObject 和 readObject 方法，但是这两个方法并没有显示的被调用啊。
     * <p>
     * 那么如果一个类中包含writeObject 和 readObject 方法，那么这两个方法是怎么被调用的呢?
     *
     * <h2>ObjectOutputStream</h2>
     * 我们可以看出，对象的序列化过程通过ObjectOutputStream和ObjectInputputStream来实现的，那么带着刚刚的问题，
     * 我们来分析一下ArrayList中的writeObject 和 readObject 方法到底是如何被调用的呢？
     * <p>
     * 为了节省篇幅，这里给出ObjectOutputStream的writeObject的调用栈：
     * <p>
     * <b>writeObject ---> writeObject0 --->writeOrdinaryObject--->writeSerialData--->invokeWriteObject</b>
     * <h3>这里看一下invokeWriteObject：</h3>
     * <blockquote><pre>
     *     void invokeWriteObject(Object obj, ObjectOutputStream out)
     *         throws IOException, UnsupportedOperationException
     *     {
     *         if (writeObjectMethod != null) {
     *             try {
     *                 writeObjectMethod.invoke(obj, new Object[]{ out });
     *             } catch (InvocationTargetException ex) {
     *                 Throwable th = ex.getTargetException();
     *                 if (th instanceof IOException) {
     *                     throw (IOException) th;
     *                 } else {
     *                     throwMiscException(th);
     *                 }
     *             } catch (IllegalAccessException ex) {
     *                 // should not occur, as access checks have been suppressed
     *                 throw new InternalError(ex);
     *             }
     *         } else {
     *             throw new UnsupportedOperationException();
     *         }
     *     }
     * </pre></blockquote>
     * <p>
     * 其中writeObjectMethod.invoke(obj, new Object[]{ out });是关键，通过反射的方式调用writeObjectMethod方法。
     * 官方是这么解释这个writeObjectMethod的：
     * <p>
     * class-defined writeObject method, or null if none
     * <p>
     * 在我们的例子中，这个方法就是我们在ArrayList中定义的writeObject方法。通过反射的方式被调用了。
     * <p>
     * 至此，我们先试着来回答刚刚提出的问题：
     * <p>
     * 如果一个类中包含writeObject 和 readObject 方法，那么这两个方法是怎么被调用的?
     * <p>
     * 答：在使用ObjectOutputStream的writeObject方法和ObjectInputStream的readObject方法时，会通过反射的方式调用。
     * <p>
     * 至此，我们已经介绍完了ArrayList的序列化方式。那么，不知道有没有人提出这样的疑问：
     * <p>
     * Serializable明明就是一个空的接口，它是怎么保证只有实现了该接口的方法才能进行序列化与反序列化的呢？
     * <p>
     * Serializable接口的定义：
     * <p>
     * 读者可以尝试把code 1中的继承Serializable的代码去掉，再执行code 2，会抛出java.io.NotSerializableException。
     * <p>
     * 其实这个问题也很好回答，我们再回到刚刚ObjectOutputStream的writeObject的调用栈：
     * <p>
     * writeObject ---> writeObject0 --->writeOrdinaryObject--->writeSerialData--->invokeWriteObject
     * <p>
     * writeObject0方法中有这么一段代码：
     *
     * <blockquote><pre>
     *             if (obj instanceof String) {
     *                 writeString((String) obj, unshared);
     *             } else if (cl.isArray()) {
     *                 writeArray(obj, desc, unshared);
     *             } else if (obj instanceof Enum) {
     *                 writeEnum((Enum<?>) obj, desc, unshared);
     *             } else if (obj instanceof Serializable) {
     *                 writeOrdinaryObject(obj, desc, unshared);
     *             } else {
     *                 if (extendedDebugInfo) {
     *                     throw new NotSerializableException(
     *                         cl.getName() + "\n" + debugInfoStack.toString());
     *                 } else {
     *                     throw new NotSerializableException(cl.getName());
     *                 }
     *             }
     * </pre></blockquote>
     * <p>
     * 在进行序列化操作时，会判断要被序列化的类是否是Enum、Array和Serializable类型，如果不是则直接抛出NotSerializableException。
     *
     * <h2>总结</h2>
     * <ul>
     * <li>如果一个类想被序列化，需要实现Serializable接口。否则将抛出NotSerializableException异常，这是因为，在序列化操作过程
     * 中会对类型进行检查，要求被序列化的类必须属于Enum、Array和Serializable类型其中的任何一种。</li>
     * <li>在变量声明前加上该关键字，可以阻止该变量被序列化到文件中。</li>
     * <li>在类中增加writeObject 和 readObject 方法可以实现自定义序列化策略</li>
     * </ul>
     */
    @Test
    public void arraySerialization() throws IOException, ClassNotFoundException {
        List<String> stringList = new ArrayList<String>();
        stringList.add("hello");
        stringList.add("world");
        stringList.add("hollis");
        stringList.add("chuang");
        System.out.println("init StringList" + stringList);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("stringlist"));
        objectOutputStream.writeObject(stringList);

        IOUtils.closeQuietly(objectOutputStream);
        File file = new File("stringlist");
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
        List<String> newStringList = (List<String>) objectInputStream.readObject();
        IOUtils.closeQuietly(objectInputStream);
        if (file.exists()) {
            file.delete();
        }
        System.out.println("new StringList" + newStringList);
    }
}

class User implements Serializable {

    private static final long serialVersionUID = -6257881824793621288L;

    private String name;

    private int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class User1 implements Externalizable {

    private String name;
    private int age;

    public User1() {
    }

    public User1(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 修改后
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeInt(age);
    }

    /**
     * 修改后
     */
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        age = in.readInt();
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
