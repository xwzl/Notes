package com.java.jvm.GC;

/**
 * @author xuweizhi
 * @date 2019/02/28 11:15
 * @description 垃圾回收机制，可达性分析算法
 * <p>
 * 可达性分析算法
 * <p>
 * 通过一系列GC ROOTS的对象作为起点，从这些节点开始向下搜索，搜索走过的路径是引用链，一个对象到GC root之间没有可达路径，被认为是可回收对象。
 * 但是一个对象没有了指向GC roots的引用链，也不是非死不可，它可以自救。
 * <p>
 * 不可达对象的自救过程
 * <p>
 * 当发现一个对象没有可达路径，GC会对这个对象进行一次标记，并进行筛选，看该对象是否执行过finalize()方法或者对象是否覆盖了这个方法。如果已经执
 * 行或者没有覆盖finalize()方法，那么GC对该对象进行二次标记，它的状态就是要被回收了。那么如果该对象覆盖了finalize()方法还没有执行， 那么GC
 * 会触发对象去执行这个方法，也就是把这个对象放入F-QUEUE对列，但是GC不会等待队列中的对象执行完finalize()方法，把对象放入队列，过一会GC会在
 * 队列中选择对象进行二次标记。
 * <p>
 * 所以要想自救的对象，就必须在进入F-queue队列之后执行完finalize()方法，重新与引用链上的对象建立联系，这样等到GC再来二次标记时，就会把它移除
 * 即将回收的集合。
 * <p>
 * 那么什么样的对象可以是GC roots对象呢
 * <p>
 * （1）虚拟机栈中（局部变量表）中引用的对象
 * <p>
 * （2）方法区中类静态属性引用的对象
 * <p>
 * （3）方法区中常量引用的对象
 * <p>
 * （4）本地方法栈Native方法中引用的对象
 * <p>
 * 类的普通成员变量不能作为GC roots
 * <p>
 * 那么依据和原理是什么呢？
 * <p>
 * 要明确的是必须以当前存活的对象集为Roots，因此必须选取确定存活的引用类型对象。虚拟机栈、方法区和本地方法栈不被GC所管理，因此选用这些区域内引用
 * 的对象作为GC Roots，是不会被GC所回收的。其中虚拟机栈中的栈帧和本地方法栈都是线程私有的内存区域，只要线程没有终止，就能确保它们中引用的对象的
 * 存活。而方法区中类静态属性引用的对象是显然存活的（类还在）。常量引用的对象在当前可能存活，因此，也可能是GC roots的一部分。
 */
public class GcRoots {

    private int x = 1;

    private static int y = 2;

    /**
     * 首先类加载之后，类的信息、静态变量以及编译时期的字面量和符号引用都放在常量池中
     * root位于这里时属于普通对象引用存放在虚拟机栈，但是不在方法中且程序中没有方法引用他，所以不在栈帧中，那么该引用指向的对象
     * （new（root）放在堆中）一般不是活着的状态，所以也不用做GC roots
     */
    Root root = new Root();

    /**
     * why是类的静态变量，存放在常量池中，与类的生命周期一致
     */
    public static Root why;

    /**
     * 是类的普通变量，跟对象一起存放在堆中
     */
    int p = 10;

    /**
     * 静态内部类
     */
    static class StaticClass {

    }

    /**
     * 成员内部内部类
     * <p>
     * 这样看起来，类OrdinaryClass像是类GcRoots的一个成员，GcRoots称为外部类。
     * 成员内部类可以无条件访问外部类的所有成员属性和成员方法（包括private成员和静态成员）。
     * <p>
     * 不过要注意的是，当成员内部类拥有和外部类同名的成员变量或者方法时，会发生隐藏现象，即默认情况下访问的是成员内部类的成员。
     * 如果要访问外部类的同名成员，需要以下面的形式进行访问：
     * <p>
     * 外部类.this.成员变量
     * 外部类.this.成员方法
     * <p>
     * 虽然成员内部类可以无条件地访问外部类的成员，而外部类想访问成员内部类的成员却不是这么随心所欲了。在外部类中如果要访问成员
     * 内部类的成员，必须先创建一个成员内部类的对象，再通过指向这个对象的引用来访问：
     * <p>
     * 内部类可以拥有private访问权限、protected访问权限、public访问权限及包访问权限。比如上面的例子，如果成员内部类Inner
     * 用private修饰，则只能在外部类的内部访问，如果用public修饰，则任何地方都能访问；如果用protected修饰，则只能在同一个包
     * 下或者继承外部类的情况下访问；如果是默认访问权限，则只能在同一个包下访问。这一点和外部类有一点不一样，外部类只能被public
     * 和包访问两种权限修饰。我个人是这么理解的，由于成员内部类看起来像是外部类的一个成员，所以可以像类的成员一样拥有多种权限修饰
     * <p>
     * class前可加public private protect 权限修饰符
     * <p>
     * “每个内部类都能独立的继承一个接口的实现，所以无论外部类是否已经继承了某个(接口的)实现，对于内部类都没有影响。内部类使得多
     * 继承的解决方案变得完整，”在这句话里边，应该是每个内部类都能独立的继承一个类吧，因为每个类（包括内部类）都可以实现多个接口，
     * 但只能继承一个类，进而：每个内部类都可以继承一个类，进而使得多继承的方案变得完整。
     */
    class OrdinaryClass {

        public void printOutClassField() {
            System.out.println("OutClass X:" + x);
            System.out.println("OutClass y:" + y);
        }

    }

    /**
     * 返回普通内部类对象实例
     */
    public OrdinaryClass newInstance() {
        return new OrdinaryClass();
    }

    public static void main(String[] args) {

        //date是局部变量，且是基本数据类型，引用和值都放在栈帧中
        int date = 9;

        //test位于这里是局部引用，存放在栈帧中的局部变量表中，（new Test()）对象存放在堆中，可以作为GC roots，线程正在调用
        //每个实例对象拥有唯一的地址值，Java中通过对象的引用（或者句柄）root来操作位于堆内存中的独享实例
        Root root = new Root();

        //t1属于局部变量表引用，这个时候new Test()对象可以作为GC roots；
        root = new Root();

        //d1 为局部对象引用，存在栈帧中，对象(new BirthDate())存在堆中，其中d，m，y（方法参数）为局部变量存储在栈帧中，
        //且它们的类型为基础类型，因此它们的数据也存储在栈帧中。当BirthDate构造方法执行完之后，d,m,y将从栈中消失。
        //main方法执行完之后，date变量，d1引用将从栈中消失，new Test(),new BirthDate()将等待垃圾回收
        BirthDate d1 = new BirthDate(7, 7, 1970);

        //打印对象正在堆内存中的地址值,弱引用的hasCode值相同，则是指向同一个堆内对象
        System.out.println(root.hashCode());

        //答应对象中的成员变量a信息
        System.out.println(root.a);

        //验证Java参数传递过程中，传递的是Java虚拟机栈中的地址值（即对象的引用指向Java堆内存中对象，其值为堆内存中对象的地址）
        root.change(date, root);

        //执行change方法后发现root的地址未有改变
        System.out.println(root.hashCode());

        //哈哈，怎么理解自己办法，或者问老师
        System.out.println(root.a);

        //证明Java中方法传参数过程中，传递的是地址值而不是对象的实例。
        System.out.println(date);

        //BirthDate d1= new BirthDate(7,7,1970);

        System.out.println(root.hashCode());

        StaticClass staticClass1 = new StaticClass();
        StaticClass staticClass2 = new StaticClass();

        System.out.println(staticClass1.hashCode());
        System.out.println(staticClass2.hashCode());

        GcRoots gcRoots = new GcRoots();

        GcRoots.OrdinaryClass ordinaryClass1 = gcRoots.new OrdinaryClass();
        GcRoots.OrdinaryClass ordinaryClass2 = gcRoots.new OrdinaryClass();

        System.out.println(ordinaryClass1.hashCode());
        System.out.println(ordinaryClass2.hashCode());

    }

}

class BirthDate {

    private int day;

    private int month;

    private int year;

    /**
     * day,month,year为成员变量，它们存储在堆中(new BirthDate()里面) 
     */
    public BirthDate(int d, int m, int y) {
        day = d;
        month = m;
        year = y;
    }
}

class Root {

    int a = 10;

    /**
     * date为局部变量，引用和值存在栈中。当方法change执行完成后，i就会从栈中消失
     */
    public void change(int date, Root root) {

        date = 10;

        //若返回值是11，说明值改变引用对象内部的值，不改对象的地址，说明在Java方法中传递的是地址值而不是对象。
        root.a = 11;

        root = new Root();

        //若返回值13，且引用的地址值改变，说明Java方法中，传递的是对象实体而不是地址值。
        root.a = 13;

        System.out.println("Change 内部root的地址值：" + root.hashCode());

    }

    public void setA(int a) {
        this.a = a;
    }

}