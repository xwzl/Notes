package com.java.jvm.base;

/**
 * @author xuweizhi
 * @date 2019/03/06 0:01
 * <p>
 * 继承的好处是代码复用。继承的子类自动拥有父类中的所有属性和方法。所以继承已存在的类就是复用这些类的方法和域。
 * <p>
 * 在此基础上，子类还可以添加一些新的方法和域，以满足新的需求。
 */
public class Parent {

    //static 修饰的变量为类成员变量，共享值，类名.a调用 Parent.a
    //static 修饰的变量，静态代码块按照代码顺序顺序执行
    static int x = 1;

    //final 修饰符修饰的变量为常量，只可赋值一次
    final static int y = 2;

    //初始化次数技术
    static int count = 0;

    //可见：可以调用方法或者属性
    //private同一个类中可见，同包、不同包子类、不同包非子类不可见
    private String val1 = "val1";

    //同一个类中，同包可见,不同包子类、不同包非子类不可见
    //默认权限修饰符，friendly
    String val2 = "val2";

    //同一个类中、同包,不同包子类可见、不同包非子类不可见
    protected String val3 = "val3";

    //同一个类中可见、同包、不同包子类、不同包非子类不可见
    public String val4 = "vla4";

    //静态代码块，此类第一次被主动使用的时候，才会执行，其它情况皆视为被动使用,主动使用的情况下如下所示
    // 1.创建类的实例：new Object()
    // 2.创建某个类或接口的静态变量，或者对该静态变量赋值
    // 3.调用类的静态方法
    // 4.反射: Class.forName
    // 5.初始化一个类的子类，也表示对其父类的主动使用
    // 6.Java虚拟机启动是被标明为启动类的类（Java Test）
    static {
        System.out.println("Parent Static code init");
    }

    //构造代码块，每一次有的新的实例就会被执行
    {
        System.out.println("Parent Constructor code init" + (++count));
    }

    //构造方法无返回值，无参数（Java文件），虚拟机默认实现。反编译.class文件可知，Java中每一个非静态方法即
    //不被static修饰的方法，第一个入参皆为this,因此构造方法中其实带有一个this局部变量存入了
    // <init>方法参数列表中,在.class文件中，this指向com/java/base/Parent即this代表这个类本身即调用者。
    public Parent() {
        //super();//隐式调用父类的方法，虚拟机默认生成，只可以写在第一排
        System.out.println("Parent init");
    }

    //实现多态的三种方式
    //1.方法重载
    //2.方法重写
    //3.实现接口
    //方法重载，与权限修饰符、返回值、变量值无关，至于参数个数、参数类型、参数类型的排列顺序有关
    public Parent(String val2, String val3) {
        this.val2 = val2;
        this.val3 = val3;
    }

    public Parent(String val2, String val3, String val4) {
        //显示调用 public Parent(String val2, String val3){} 方法，只可写在一排
        this(val2, val3);
        this.val4 = val4;
    }

    public void do1(String val1, int val2) {
        System.out.println("do1 1");
    }

    public void do1(String val1, int val2, int val3) {
        System.out.println("do1 2");
    }

    public void do1(int val2, String val1, int val3) {
        System.out.println("do 3");
    }

    //静态方法类层次方法，Parent.do4调用，实例对象无法调用
    public static void do4() {
        System.out.println("do4 111");
    }

    //演示private权限修饰符的作用
    private void say() {

    }

    public void say1() {

    }

}

class Child extends Parent {

    public static int a = 1;

    public final static int b = 1;

    static int index = 0;

    static {
        System.out.println("Child Static code init");
    }

    {
        System.out.println("Child Constructor code init" + (++index));
    }

    //子类默认继承父类的无惨构造方法，有参构造方法需要重写才可使用
    public Child() {
        System.out.println("Child init");
    }


}

class Test {


    public static void main(String[] args) {

        //m1();

        //m2();

        //m3();
    }

    //验证private 修饰的变量，子类无法访问
    private static void m3() {
        Child child = new Child();
        //System.out.println(child.val1);编译器报错，无法访问
        //反射打破private修饰符，获取private对象
        //try {
        //    Class<Child> clazz = (Class<Child>) Class.forName("com.java.jvm.base.Child");
        //    Field field = clazz.getDeclaredField("val1");
        //    field.setAccessible(true);
        //    System.out.println(field.toString());
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}
    }

    //验证static修饰变量，在实例对象中值共享
    private static void m2() {
        Parent parent = new Parent();
        Parent parent1 = new Parent();


        parent.val2 = "1";
        parent1.val2 = "2";
        Parent.x = 22;


        //System.out.println(parent.x);
        //System.out.println(parent1.x);
        System.out.println(parent.val2);
        System.out.println(parent1.val2);
    }

    //父类初始化代码顺序，特别需要注意的是静态代码块只执行一次
    //1.父类静态代码块
    //2.子类静态代码块
    //3.父类构造代码块
    //4.子类构造代码块
    //5.父类构造方法
    //6.子类构造方法
    private static void m1() {
        Child child = new Child();

        Parent parent = new Parent();
    }


}
