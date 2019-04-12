package com.java.base.common;

/**
 * Final 定义、用法、优点、注意点复习
 *
 * <h2>final关键字的含义?</h2>
 * <p>
 * final在Java中是一个保留的关键字，可以声明成员<b>变量、方法、类以及本地变量</b>。一旦你将引用声明作final，
 * 你将不能改变这个引用了，编译器会检查代码，如果你试图将变量再次初始化的话，编译器会报编译错误。
 *
 * <h2>什么是final变量？</h2>
 * <p>
 * 凡是对成员变量或者本地变量(在方法中的或者代码块中的变量称为本地变量)声明为final的都叫作final变量。final变
 * 量经常和static关键字一起使用，作为常量。下面是final变量的例子：
 *
 * <blockquote><pre>
 *     public static final String LOAN = "loan";
 *     LOAN = new String("loan") //invalid compilation error
 * </pre></blockquote>
 *
 * <b>final变量是只读的。</b>
 *
 * <h2>什么是final方法?</h2>
 * final也可以声明方法。方法前面加上final关键字，代表这个方法不可以被子类的方法重写。如果你认为一个方法的功能已经
 * 足够完整了，子类中不需要改变的话，你可以声明此方法为final。final方法比非final方法要快，因为在编译的时候已经静
 * 态绑定了，不需要在运行时再动态绑定。下面是final方法的例子：
 *
 * <blockquote><pre>
 *   class PersonalLoan{
 *     public final String getName(){
 *         return "personal loan";
 *     }
 *   }
 *
 *   class CheapPersonalLoan extends PersonalLoan{
 *     public final String getName(){
 *         return "cheap personal loan"; //compilation error: overridden method is final
 *     }
 *   }
 * </pre></blockquote>
 *
 * <h2>什么是final类？</h2>
 * 使用final来修饰的类叫作final类。final类通常功能是完整的，它们不能被继承。Java中有许多类是final的，譬如String,
 * Integer以及其他包装类。下面是final类的实例：
 *
 * <h2>final关键字的好处</h2>
 *
 * 下面总结了一些使用final关键字的好处
 *  <ol>
 *      <li>final关键字提高了性能。JVM和Java应用都会缓存final变量。</li>
 *      <li>final变量可以安全的在多线程环境下进行共享，而不需要额外的同步开销。</li>
 *      <li>使用final关键字，JVM会对方法、变量及类进行优化。</li>
 *  </ol>
 *
 * <h2>创建不可变类</h2>
 *
 * 要使用final关键字。不可变类是指它的对象一旦被创建了就不能被更改了。String是不可变类的代表。不可变类
 * 有很多好处，譬如它们的对象是只读的，可以在多线程环境下安全的共享，不用额外的同步开销等等。
 *
 * @author xuweizhi
 * @date 2019/03/16 20:26
 */
public class FinalAnalysis {

    /**
     * <h2>关于final的重要知识点</h2>
     * <ol>
     *     <li>final关键字可以用于成员变量、本地变量、方法以及类。</li>
     *     <li>final成员变量必须在声明的时候初始化或者在构造器中初始化，否则就会报编译错误。</li>
     *     <li>你不能够对final变量再次赋值。</li>
     *     <li>本地变量必须在声明时赋值。</li>
     *     <li>在匿名类中所有变量都必须是final变量。</li>
     *     <li>final方法不能被重写。</li>
     *     <li>final类不能被继承。</li>
     *     <li>final关键字不同于finally关键字，后者用于异常处理。</li>
     *     <li>final关键字容易与finalize()方法搞混，后者是在Object类中定义的方法，是在垃圾回收之前被JVM调用的方法。</li>
     *     <li>接口中声明的所有变量本身是final的。</li>
     *     <li>final和abstract这两个关键字是反相关的，final类就不可能是abstract的。</li>
     *     <li>final方法在编译阶段绑定，称为静态绑定(static binding)。</li>
     *     <li>没有在声明时初始化final变量的称为空白final变量(blank final variable)，它们必须在构造器中初始化，或者调用this()初始化。不这么做的话，编译器会报错“final变量(变量名)需要进行初始化”。</li>
     *     <li>将类、方法、变量声明为final能够提高性能，这样JVM就有机会进行估计，然后优化。</li>
     *     <li>按照Java代码惯例，final变量就是常量，而且通常常量名要大写：</li>
     *     <li>对于集合对象声明为final指的是引用不能被更改，但是你可以向其中增加，删除或者改变内容。譬如：</li>
     * </ol>
     */
    public final void say() {

    }


}
