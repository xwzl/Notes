package com.java.lambda.lamdasource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xuweizhi
 * @date 2018/11/28 11:10
 */
public class CollectSource {

    /**
     * 1. collect:收集器
     * 2. Collector作为collect方法的参数
     * 3. Collector是一个接口，它是一个可变的汇聚操作，将输入元素累计到一个可变容器中；它会在所有元素处理完毕之后，将累积的结果转换为一个最终表示结果（这是一个可选的操作）。
     * 它支持串行与并行两种方式执行。
     * 4. Collectors本身提供了Collector很多常见的汇聚实现，Collectors本身实际上是一个工厂。
     * 5. combiner函数，有4个线程同时执行，那么就会生成4个部分结果 针对并行流
     * 有两种状态，加入有1,2,3,4个线程同时去执行一个方法，
     * 那么 1,2 -> 1 or 2 or 5
     * 把其他线程的结果合并并返回一个新的结果5 或者把 2 的值赋值给1 然后返回1
     * 6. Collector 由四个函数组成，
     * <ul>
     * 创建一个新的容器
     * <li>creation of a new result container ({link #supplier()})</li>
     * 对容器中值进行操作
     * <li>incorporating a new data element into a result container ({link #accumulator()})</li>
     * 合并并行流的结果
     * <li>combining two result containers into one ({link #combiner()})</li>
     * 返回一个结果集
     * <li>performing an optional final transform on the container ({link #finisher()})</li>
     * </ul>
     * 7.为了确保串行与并行操作结果的等价性，Collector函数需要满足两个条件：identity(同一性)和associativity(结合性)
     * a == combiner.apply(a,supplier.get()) 同一性
     * 实现原理
     * <pre>{@code
     *       R container = collector.supplier().get(); 创建一个新的容器
     *       for (T t : data) 对容器中值进行操作
     *         collector.accumulator().accept(container, t);
     *       return collector.finisher().apply(container);
     *     }</pre>
     * <p>
     * 源码解读:一种使输入数据累计到可变容器中，可选的在处理在处理完所有输入元素后将累计结果转换为最终表示的可变简约操作。
     * 可以按照顺序执行，也可以并行
     */
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        List<String> list = Arrays.asList("as", "a", "c");

        //越具体的方式越好
        System.out.println(list.stream().count());
        System.out.println(list.stream().collect(Collectors.counting()));
    }

}
