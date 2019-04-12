package com.java.base.jdk12;

/**
 * 类型推断：根据变量或者代码的语义，推断出弱类型变量或者Lambda表示式表示的类
 * <p>
 * Java 10 后引入 var 定义变量静态类型， 属于弱类型；
 * <p>
 * 与 Java 8 Lambda 表达式 一样，拥有类型推断的功能。
 * <p>
 * Java 9 对 Lambda 表达式类型推断进行增强增强，同理 var 在 Java 11 中类型推断也进行了增强。
 * <p>
 * 增强在 JVM 领域，暂时与我们无关，一起来爽一下新特性
 *
 * @author xuweizhi
 * @date 2019/04/03 20:50
 */
public class TypeInference {

    static void say(Woman woman, String msg) {
        System.out.println("类名：" + woman.getClass());
        woman.say(msg);
    }

    static void say(Woman woman) {
        System.out.println("静态分派Woman");
    }

    static void say(Women women) {
        System.out.println("静态分派Women");
    }

    static void say(Man man) {
        System.out.println("静态分派Man");
    }

    public static void main(String[] args) {

        // 类型推断，推断 System.out::println 为 Woman 接口的默认实现
        // 其实Lambda表达式生成的类，JVM会在内存中动态创建，而不是编译到本地磁盘
        TypeInference.say(System.out::println, "男人");

        // var 类型 类型推断
        var str = "11";
        System.out.println(str.getClass());

        //str = 1; 显然不行，因为str在进行赋值的时候已经确定了其静态变量类型为java.lang.String.

        str = String.valueOf(Integer.valueOf(str));

        System.out.println(str.getClass());

        // 首先声明 Woman 为静态类型，编译期确定；
        // new Women() 和 new Man()为动态类型，运行期采取动态链接确定其真正类型。
        //
        // 动态分派 PK 静态分派
        //
        // 典型的动态分派： 重写与实现方法为动态分派；Woman.say()会调用真正类型实现或者重写的方法。
        // 典型的静态分派： 方法重载，只看静态类型，不看真正的类。
        Woman women = new Women();

        Woman man = new Man();

        Woman woman = new Woman() {
            @Override
            public void say(String something) {
                System.out.println("动态分派woman");
            }
        };

        System.out.println(women.getClass());
        System.out.println(man.getClass());
        System.out.println(woman.getClass());

        //动态分派的实现，看接受者
        woman.say("11");
        man.say("11");
        women.say("11");

        //静态分派，方法重载，看静态类型
        TypeInference.say(man);
        TypeInference.say(woman);
        TypeInference.say(women);
    }

}

/**
 * 函数式变成接口
 */
@FunctionalInterface
interface Woman {

    void say(String something);

    default void sayToPeople(String something) {
        if (null != something && !something.endsWith("") && something.length() > 0) {
            System.out.println("Java 8 新特性，新增默认方法，向下兼容Java 7、Java 6 等等；");
            System.out.println("具体特性式新加的接口方法，子类实现不用必须实现这个方法");
            System.out.println("如Woman的实现类women只实现了say方法");
            this.say(something);
        }
    }

    private static void hello() {
        System.out.println("Java  9 新特性，接口使用静态方法，添加自己的默认实现；");
    }
}


class Women implements Woman {

    @Override
    public void say(String something) {
        System.out.println("动态分派women");
    }
}

class Man implements Woman {

    @Override
    public void say(String something) {
        System.out.println("动态分派man");
    }
}