package com.java.base.common;

/**
 * @author xuweizhi
 * @date 2019/03/13 21:15
 */
public class OneDay {

    /**
     * 1、&和&&的区别？
     * 答：&运算符有两种用法：(1)按位与；(2)逻辑与。&&运算符是短路与运算。逻辑与跟短路与的差别是非常巨大的，虽然二者
     * 都要求运算符左右两端的布尔值都是true整个表达式的值才是true。&&之所以称为短路运算是因为，如果&&左边的表达式的
     * 值是false，右边的表达式会被直接短路掉，不会进行运算。很多时候我们可能都需要用&&而不是&，例如在验证用户登录时判
     * 定用户名不是null而且不是空字符串，应当写为：username != null && !username.equals("")，二者的顺序不能交换，
     * 更不能用&运算符，因为第一个条件如果不成立，根本不能进行字符串的equals比较，否则会产生NullPointerException异
     * 常。注意：逻辑或运算符（|）和短路或运算符（||）的差别也是如此。
     **/
    public void stringEmptyUntil(String value) {
        //因为第一个条件如果不成立，根本不能进行字符串的equals比较，否则会产生NullPointerException异常
        if (value != null && value.equals("")) {

        }
    }

    /**
     * 2、switch是否能作用在byte上，是否能作用在long上，是否能作用在String上？
     * 答：在Java 5以前，switch(expr)中，expr只能是byte、short、char、int；从Java 5开始，Java中引入了枚举类型，
     * expr也可以是enum类型；从Java 7开始，expr还可以是字符串（String），但是长整型（long）在目前所有的版本中都是
     * 不可以的。
     */
    public void switchCondition() {

    }

    /**
     * 数组有没有length()方法？String有没有length()方法？
     * <p>
     * 答：数组没有length()方法，有length的属性。
     * String有length()方法。在JavaScript中，获得字符串的长度是通过length属性得到的，这一点容易和Java混淆
     */
    public void length() {
        int[] a = new int[]{1, 2, 3, 4};
        int length = a.length;
        "dd".length();

        flag:
        for (int i = 0; i < 10; i++) {
        }
    }

    /**
     * 构造器（constructor）是否可被重写（override）？
     */
    public void constructor() {
        //构造器无法被继承，因此无法被重写，可以重载。
    }

    /**
     * 16、两个对象值相同(x.equals(y) == true)，但却可有不同的hash code，这句话对不对？
     * 答：不对，如果两个对象x和y满足x.equals(y) == true，它们的哈希码（hash code）应当相同。
     * Java对于eqauls方法和hashCode方法是这样规定的：
     *  (1) 如果两个对象相同（equals方法返回true），那么它们的hashCode值一定要相同；
     *  (2) 如果两个对象的hashCode相同，它们并不一定相同。
     * 当然，你未必要按照要求去做，但是如果你违背了上述原则就会发现在使用容器时，相同的对象可以出现在
     * Set集合中，同时增加新元素的效率会大大下降（对于使用哈希存储的系统，如果哈希码频繁的冲突将会造
     * 成存取性能急剧下降）。
     */
    public void hascodeAndEquals() {

    }

    /**
     * 相同：
     * 1. 都不可以实例化，但是可以有引用
     * 2.
     * 不同点
     * 1. 抽象类中可以有构造方法，但是可以实例化
     * 2. 抽象类的成员可以有其它修饰符
     * 3. 抽象类可以有非抽象方法
     * 4. 如果你往抽象类中添加新的方法，你可以给它提供默认的实现。因此你不需要改变你现在的代码。
     * 如果你往接口中添加方法，那么你必须改变实现该接口的类。(JDK1.8有所改变)
     * 5. 接口是稍微有点慢的，因为它需要时间去寻找在类中实现的方法。
     * 6. 接口只可以继承一个或多个其它接口,抽象方法可以继承一个类和实现多个接口.
     * 7. 接口没有main方法，因此我们不能运行它。
     */
    abstract class AA {

        public int a;

        public AA() {

        }

        private int haha;

    }

    /**
     * Java8 对接口的改变
     * 1.增加了default方法和static方法,这两种方法完全可以有方法体
     * <p>
     * 2.default方法属于实例,static方法属于类(接口)
     * 3.接口中的静态方法不会被继承,接口中的静态变量会被继承
     */
    interface BB {

        public static final int A = 1;

        public abstract void y();

        static void AA() {
            System.out.println("AA");
        }

        default void BB() {
            System.out.println("BB");
        }

    }

    interface CC extends BB {

    }

    /**
     * 不能有实例对象
     *
     * @return
     */
    public AA getAA() {
        return new AA() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        };
    }

    /**
     * 测试 Long 和 long性能
     */
    public static void main(String[] args) {
        BB bb = () -> System.out.println("aa");
        bb.BB();
        BB.AA();

        //接口中的静态方法不能继承
        //如果一个实现类 继承了两个接口(这两个接口没有继承关系,有同名的默认方法),
        //那么必须在实现类,显示声明,否则编译器不知道调用哪个而报错
        CC cc = () -> System.out.println("cc");
        cc.BB();
        cc.y();
    }

    public static void modifyInt(int value) {
        value = 2;
    }

    public static void reference(String value) {
        value = "2222";
    }

    class Inner {

    }

    public static Inner getInner() {
        //return new Inner();
        //不能创建的原因是因为静态方法不能传入this参数
        return new OneDay().new Inner();
    }

    public Inner getInners() {
        //return this.new Inner();
        return new Inner();
    }



}
