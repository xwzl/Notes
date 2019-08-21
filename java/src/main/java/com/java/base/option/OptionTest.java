package com.java.base.option;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.junit.Test;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Optional 用法
 *
 * @author xuweizhi
 * @since 2019-08-20
 */
public class OptionTest {

    /**
     * Optional.of()  Optional.ofNullAble() Optional.empty()
     */
    @Test
    public void test() {

        OptionDemo optionDemo = new OptionDemo();
        optionDemo.setName("张三");

        // of 方法不能传入空值，否则要报错
        Optional<OptionDemo> optional = Optional.of(optionDemo);

        OptionDemo optionDemoNull = null;
        // 这个可以传入 null 值，不会抛出异常，而只是返回一个空的Optional对象，如同我们用Optional.empty API:
        Optional<OptionDemo> optional1 = Optional.ofNullable(optionDemoNull);

    }

    /**
     * isPresent() isPresent(() -> {}) 检查一个Optional对象中是否有值，只有值非空才返回true
     */
    @Test
    public void testIsPresent() {

        OptionDemo optionDemo = new OptionDemo();
        optionDemo.setName("张三");

        // of 方法不能传入空值，否则要报错
        Optional<OptionDemo> optional = Optional.of(optionDemo);

        // 检查一个Optional对象中是否有值，只有值非空才返回true
        System.out.println(optional.isPresent());

        // 适当使用这个 api
        optional.ifPresent(print -> System.out.println(print));

        OptionDemo optionDemoNull = null;
        // 这个可以传入 null 值，不会抛出异常，而只是返回一个空的Optional对象，如同我们用Optional.empty API:
        Optional<OptionDemo> optional1 = Optional.ofNullable(optionDemoNull);
        System.out.println(optional1.isPresent());

    }

    /**
     * orElse() 这个API被用来检索Optional对象中的值，它被传入一个“默认参数‘。如果对象中存在一个值，则返回它，否则返回传入的“默认参数”
     */
    @Test
    public void testOrElse() {

        OptionDemo optionDemo = new OptionDemo();
        optionDemo.setName("张三");

        Optional<OptionDemo> optional = Optional.of(optionDemo);
        System.out.println(optional.orElse(new OptionDemo("默认值不会被打印")));

        OptionDemo optionDemoNull = null;
        Optional<OptionDemo> optional1 = Optional.ofNullable(optionDemoNull);

        System.out.println(optional1.orElse(new OptionDemo("默认值会被打印")));
    }

    /**
     * orElseGet() 与 orElse 类似，但是这个函数不接收一个“默认参数”，而是一个函数接口
     * <p>
     * 其值为那啥，那啥，那啥？就是函数式接口的返回值
     */
    @Test
    public void testOrElseGet() {
        Optional<OptionDemo> option = Optional.ofNullable(null);
        System.out.println(option.orElseGet(() -> new OptionDemo("测试 orElseGet 函数式接口")));
    }

    @Test
    public void whenOrElseGetAndOrElseOverlap_thenCorrect() {
        String text = null;

        System.out.println("Using orElseGet:");
        String defaultText = Optional.ofNullable(text).orElseGet(this::getMyDefault);
        System.out.println(defaultText);

        System.out.println("Using orElse:");
        defaultText = Optional.ofNullable(text).orElse(getMyDefault());
        System.out.println(defaultText);
    }

    /**
     * 可以看到，当使用orElseGet去检索值时，getMyDefault并不执行，因为Optional中含有值，而使用orElse时则照常执行。
     * 所以可以看到，当值存在时，orElse相比于orElseGet，多创建了一个对象，可能从这个实例中你感受不到影响有多大，但考
     * 虑当getDefalut不仅仅是个简单函数，而是一个web service之类的，则多创建一个代价是比较大的。
     */
    @Test
    public void whenOrElseGetAndOrElseDiffer_thenCorrect() {
        String text = "Text present";

        System.out.println("Using orElseGet:");
        String defaultText
                = Optional.ofNullable(text).orElseGet(this::getMyDefault);
        System.out.println(defaultText);

        System.out.println("Using orElse:");
        defaultText = Optional.ofNullable(text).orElse(getMyDefault());
        System.out.println(defaultText);
    }

    public String getMyDefault() {
        System.out.println("Getting Default Value");
        return "Default Value";
    }

    /**
     * orElseThrow当遇到一个不存在的值的时候，并不返回一个默认值，而是抛出异常
     */
    @Test/*(expected = IllegalArgumentException.class)*/
    public void whenOrElseThrowWorks_thenCorrect() {
        String nullName = null;
        String name = Optional.ofNullable(nullName).orElseThrow(
                IllegalArgumentException::new);
        System.out.println(name);
    }

    /**
     * 使用get() API 也可以返回被包裹着的值。但是必须是值存在时，当值不存在时，它会抛出一个NoSuchElementException异常
     * <p>
     * 因为这个方法与我们使用Optional的目的相违背，所以可以预见在不久将来它或许会被抛弃，建议还是使用其他的方法
     */
    @Test/*(expected = NoSuchElementException.class)*/
    public void givenOptionalWithNull_whenGetThrowsException_thenCorrect() {
        Optional<String> opt = Optional.ofNullable(null);
        String name = opt.get();
    }

    /**
     * filter() 接收一个函数式接口，当符合接口时，则返回一个Optional对象，否则返回一个空的Optional对象。
     */
    @Test
    public void whenOptionalFilterWorks_thenCorrect() {
        Integer year = 2016;
        Optional<Integer> yearOptional = Optional.of(year);
        boolean is2016 = yearOptional.filter(y -> y == 2016).isPresent();
        System.out.println(is2016);
        boolean is2017 = yearOptional.filter(y -> y == 2017).isPresent();
        System.out.println(is2017);
    }

    /**
     * map() flatmap()
     */
    @Test
    public void testMap() {
        boolean present = Optional.ofNullable(new OptionDemo("我们")).map(OptionDemo::getName)/*.filter(String::isEmpty)*/.map(s -> s.equals("我们")).isPresent();
        System.out.println(present);
    }
}

@Data
@Slf4j
@ToString
class OptionDemo {

    private String name;

    @Contract(pure = true)
    public OptionDemo() {
    }

    @Contract(pure = true)
    public OptionDemo(String name) {
        this.name = name;
    }
}
