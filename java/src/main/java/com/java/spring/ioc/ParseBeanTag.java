package com.java.spring.ioc;

import com.java.spring.xml.BeanDefinition;
import org.springframework.beans.BeanMetadataAttributeAccessor;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.MethodOverrides;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.Element;

/**
 * import 标签解析完毕了，我们一起来看看 Spring 中最复杂也是最重要的标签 bean 标签的解析过程。
 *
 * <h2>processBeanDefinition</h2>
 * <p>
 * 在方法 {@link com.java.spring.xml.DefaultBeanDefinitionDocumentReader#parseDefaultElement(Element, BeanDefinitionParserDelegate)}
 * 方法中，如果遇到标签为 bean 时，则调用 processBeanDefinition方法，进行 bean 标签的解析。
 * {@link com.java.spring.xml.DefaultBeanDefinitionDocumentReader#processBeanDefinition(Element, BeanDefinitionParserDelegate)}
 * <p>
 * {@link com.java.spring.xml.BeanDefinitionParserDelegate#parseBeanDefinitionElement(Element)}进行 <bean> 元素解析
 *
 * <b>BeanDefinition</b>
 * <p>
 * org.springframework.beans.factory.config.BeanDefinition ，是一个接口，它描述了一个 Bean 实例的定义，包括属性值、构造方法值和继承自它的类的更多信息
 * <p>
 * {@link com.java.spring.xml.BeanDefinition}
 * <p>
 * <h1>{@link com.java.spring.xml.BeanDefinitionParserDelegate#parseBeanDefinitionElement(Element, String, BeanDefinition)}</h1>
 * <h2>解析 Bean 标签</h2>
 * {@link ParseBeanTag#bean()}
 *
 * <h2>meta、lookup-method、replace-method</h2>
 * {@link  ParseBeanTag#meta()}
 * {@link  ParseBeanTag#lookupMethod()}
 * {@link  ParseBeanTag#replacedMethod()}
 *
 * <h2>constructor-arg、property、qualifier</h2>
 * {@link ParseBeanTag#constructor()}
 * {@link ParseBeanTag#property()}
 * {@link ParseBeanTag#qualifier()}
 *
 * <h2>小结</h2>
 * <p>
 * {@link ParseBeanTag#myTag()}
 *  下一个分析{@link ParseDefineTag}
 * @date 2019/03/23 12:41
 */
public class ParseBeanTag {

    /**
     * BeanDefinition 继承 AttributeAccessor 和 BeanMetadataElement 接口。两个接口定义如下：
     * <p>
     * org.springframework.cor.AttributeAccessor 接口，定义了与其它对象的（元数据）进行连接和访问的约定，即对属性的修改，
     * 包括获取、设置、删除。{@link com.java.spring.xml.AttributeAccessor}
     * <p>
     * org.springframework.beans.BeanMetadataElement 接口，Bean 元对象持有的配置元素可以通过 #getSource() 方法来获取。
     * <h2>BeanDefinition 的子关系</h2>
     * 如图image/bean.png
     * 我们常用的三个实现类有：
     * <ul>
     * <li>org.springframework.beans.factory.support.ChildBeanDefinition</li>
     * <li>org.springframework.beans.factory.support.RootBeanDefinition</li>
     * <li>org.springframework.beans.factory.support.GenericBeanDefinition</li>
     * <li>ChildBeanDefinition、RootBeanDefinition、GenericBeanDefinition 三者都继承 AbstractBeanDefinition 抽象类，即 AbstractBeanDefinition 对三个子类的共同的类信息进行抽象。</li>
     * <li>如果配置文件中定义了父 bean 和 子 bean ，则父 bean 用 RootBeanDefinition 表示，子 bean 用 ChildBeanDefinition 表示，而没有父 bean 的就使用RootBeanDefinition 表示。</li>
     * <li>GenericBeanDefinition 为一站式服务类。</li>
     * </ul>
     * <h2>解析 Bean 标签</h2>
     * <p>
     * 在{@link com.java.spring.xml.BeanDefinitionParserDelegate#parseBeanDefinitionElement(Element, String, BeanDefinition)}
     * 方法中，完成解析后，返回的是一个已经完成对 bean> 标签解析的 BeanDefinition 实例
     *
     * <h3>createBeanDefinition</h3>
     * <p>
     * 在该方法内部，首先调用 {@link BeanDefinitionParserDelegate#createBeanDefinition(String, String)} 方法，
     * 创建 AbstractBeanDefinition 对象。
     * <p>
     * 委托 BeanDefinitionReaderUtils 创建{@link com.java.spring.util.BeanDefinitionReaderUtils#createBeanDefinition(String, String, ClassLoader)}
     * 该方法主要是，创建 GenericBeanDefinition 对象，并设置 parentName、className、beanClass 属性
     * <h3>parseBeanDefinitionAttributes</h3>
     * {@link com.java.spring.xml.BeanDefinitionParserDelegate#parseBeanDefinitionAttributes(Element, String, BeanDefinition, AbstractBeanDefinition)}
     * 创建完 GenericBeanDefinition 实例后，再调用 #parseBeanDefinitionAttributes(Element ele, String beanName,
     * BeanDefinition containingBean, AbstractBeanDefinition bd) 方法，该方法将创建好的 GenericBeanDefinition
     * 实例当做参数，对 bean 标签的所有属性进行解析
     */
    void bean() {

    }

    /**
     * 完成 bean 标签的基本属性解析后，会依次调用 BeanDefinitionParserDelegate 的
     * {@link com.java.spring.xml.BeanDefinitionParserDelegate#parseMetaElements(Element, BeanMetadataAttributeAccessor)},
     * {@link com.java.spring.xml.BeanDefinitionParserDelegate#parseLookupOverrideSubElements(Element, MethodOverrides)},
     * {@link com.java.spring.xml.BeanDefinitionParserDelegate#parseReplacedMethodSubElements(Element, MethodOverrides)},
     * 分别对子元素 meta、lookup-method、replace-method 元素完成解析。下篇博文将会对这三个子元素进行详细说明
     * <p>
     * 三个子元素的作用如下
     * <ul>
     * <li>meta> ：元数据</li>
     * <li>lookup-method> ：Spring 动态改变 bean 里方法的实现。方法执行返回的对象，使用 Spring 内原有的这类对象替换，通过改变方法返回值来动态改变方法。内部实现为使用 cglib 方法，重新生成子类，重写配置的方法和返回对象，达到动态改变的效果。</li>
     * <li>replace-method> ：Spring 动态改变 bean 里方法的实现。需要改变的方法，使用 Spring 内原有其他类（需要继承接口org.springframework.beans.factory.support.MethodReplacer）的逻辑，替换这个方法。通过改变方法执行逻辑来动态改变方法。/li>
     * </ul>
     * 依然在{@link com.java.spring.xml.BeanDefinitionParserDelegate#parseBeanDefinitionElement(Element, String, BeanDefinition)}方法内
     * <h2> meta ：元数据。当需要使用里面的信息时可以通过 key 获取。</h2>
     * {@link com.java.spring.xml.BeanDefinitionParserDelegate#parseMetaElements(Element, BeanMetadataAttributeAccessor)}
     * <h2></h2>
     */
    void meta() {

    }

    /**
     * lookup-method ：获取器注入，是把一个方法声明为返回某种类型的 bean 但实际要返回的 bean 是在配
     * 置文件里面配置的。该方法可以用于设计一些可插拔的功能上，解除程序依赖。如{@link Display}
     * <p>
     * {@link com.java.spring.xml.BeanDefinitionParserDelegate#parseLookupOverrideSubElements(Element beanEle, MethodOverrides overrides)}
     */
    void lookupMethod() {

    }

    /**
     * eplaced-method ：可以在运行时调用新的方法替换现有的方法，还能动态的更新原有方法的逻辑。
     * {@link com.java.spring.xml.BeanDefinitionParserDelegate#parseReplacedMethodSubElements(Element beanEle, MethodOverrides overrides)}
     */
    void replacedMethod() {

    }

    /**
     * {@link StudentService}
     * StudentService 定义一个构造函数，配置文件中使用 constructor-arg 元素对其配置，该元素可以实现对
     * StudentService 自动寻找对应的构造函数，并在初始化的时候将值当做参数进行设置。
     * <p>
     * {@link com.java.spring.xml.BeanDefinitionParserDelegate#parseConstructorArgElements(Element beanEle, BeanDefinition bd)}
     * {@link com.java.spring.xml.BeanDefinitionParserDelegate#parseConstructorArgElement(Element ele, BeanDefinition bd)}
     */
    void constructor() {

    }

    /**
     * {@link com.java.spring.xml.BeanDefinitionParserDelegate#parsePropertyElements(Element beanEle, BeanDefinition bd)}
     */
    void property() {
        //<bean id="studentService" class="org.springframework.core.service.StudentService">
        //    <property name="name" value="chenssy"/>
        //    <property name="age" value="18"/>
        //</bean>
    }

    /**
     * @Qualifier：限定描述符，用于细粒度选择候选者 Autowired默认是根据类型进行注入的，因此如果有多个类型一样的Bean候选者，则需要限定其中一个候选者，否则将抛出异常
     * @Qualifier限定描述符除了能根据名字进行注入，更能进行更细粒度的控制如何选择候选者，具体使用方式如下：
     * @Qualifier(value = "限定标识符")
     * 字段、方法、参数
     * {@link com.java.spring.xml.BeanDefinitionParserDelegate#parseQualifierElement(Element ele, AbstractBeanDefinition bd)}
     */
    void qualifier() {
        //http://www.voidcn.com/article/p-vdgrbkrm-bqu.html 或者Qulifier.txt
        //根据基于XML配置中的<qualifier>标签指定的名字进行注入，使用如下方式指定名称：
        //其中type属性可选，指定类型，默认就是Qualifier注解类，name就是给Bean候选者指定限定标识符，一个Bean定义中只允许指定类型不同的<qualifier>，
        //如果有多个相同type后面指定的将覆盖前面的。
        //<qualifier  type="org.springframework.beans.factory.annotation.Qualifier"  value="限定标识符"/>
    }

    /**
     * <p>
     * 分析 Bean 默认标签的解析过程，包括 基本属性、六个子元素（meta、lookup-method、replaced-method、constructor-arg、
     * property、qualifier），涉及内容较多，拆分成了四篇文章，导致我们已经忘记从哪里出发的了。所以，我们先来回顾下。
     * <p>
     * DefaultBeanDefinitionDocumentReader 的 #processBeanDefinition(Element ele, BeanDefinitionParserDelegate delegate)
     * 方法，负责 bean> 标签的解析：
     * <p>
     * 在解析过程中，首先调用 {@link com.java.spring.xml.DefaultBeanDefinitionDocumentReader#parseBeanDefinitions
     * (Element root, BeanDefinitionParserDelegate delegate)} 方法，完成默认标签的解析。
     * <p>
     * 如果解析成功（返回的 bdHolder != null ），则调用 BeanDefinitionParserDelegate#decorateBeanDefinitionIfRequired
     * (Element ele, BeanDefinitionHolder definitionHolder) 方法，完成自定义标签元素的解析。
     *
     * <h2>自定义标签的解析</h2>
     * <p>
     * 前面已经分析了默认标签的解析，所以这篇文章分析自定义标签的解析。
     * {@link com.java.spring.xml.BeanDefinitionParserDelegate#decorateBeanDefinitionIfRequired(Element, BeanDefinitionHolder, BeanDefinition)}
     * <h2>总结</h2>
     * 至此，BeanDefinition 的解析过程已经全部完成了，下面做一个简要的总结：
     * <p>
     * 解析 BeanDefinition 的入口在 DefaultBeanDefinitionDocumentReader 的#parseBeanDefinitions(Element root, BeanDefinitionParserDelegate delegate) 方法。\
     * 该方法会根据命令空间来判断标签是默认标签还是自定义标签，其中：
     * <ul>
     * <li>默认标签，由 #parseDefaultElement(Element ele, BeanDefinitionParserDelegate delegate) 方法来实现</li>
     * <li>自定义标签，由 BeanDefinitionParserDelegate 的 #parseCustomElement(Element ele,
     *
     * @Nullable BeanDefinition containingBd) 方法来实现。</li>
     * </ul>
     * 在默认标签解析中，会根据标签名称的不同进行 import、alias、bean、beans 四大标签进行处理。其中 bean 标签的解析为核心，它
     * 由 processBeanDefinition(Element ele, BeanDefinitionParserDelegate delegate) 方法实现。
     * <p>
     * processBeanDefinition(Element ele, BeanDefinitionParserDelegate delegate) 方法，开始进入解析核心工作，分为三步：
     *
     * <ul>
     * <li>解析默认标签的默认标签：BeanDefinitionParserDelegate#parseBeanDefinitionElement(Element ele, ...) 方法。
     * 该方法会依次解析 <bean> 标签的属性、各个子元素，解析完成后返回一个 GenericBeanDefinition 实例对象。</li>
     * <li>解析默认标签下的自定义标签：BeanDefinitionParserDelegate#decorateBeanDefinitionIfRequired(Element ele,
     * BeanDefinitionHolder definitionHolder) 方法。</li>
     * <li>注册解析的 BeanDefinition：BeanDefinitionReaderUtils#registerBeanDefinition(BeanDefinitionHolder
     * definitionHolder, BeanDefinitionRegistries registry) 方法。</li>
     * </ul>
     */
    void myTag() {

    }

}

interface Car {

    void display();

}

class Bmw implements Car {

    @Override
    public void display() {
        System.out.println("我是 BMW");
    }

}

class Hongqi implements Car {

    @Override
    public void display() {
        System.out.println("我是 hongqi");
    }

}

abstract class Display {

    public void display() {
        getCar().display();
    }

    public abstract Car getCar();

    public static void main(String[] args) {
        //<bean id="display" class="org.springframework.core.test1.Display">
        //    <lookup-method name="getCar" bean="hongqi"/>
        //</bean>
        //运行结果为：
        //我是 hongqi
        //如果将 bean="hognqi" 替换为 bean="bmw"，则运行结果变成：
        //我是 BMW
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        Display display = (Display) context.getBean("display");
        display.display();
    }
}

class Method {

    public void display() {
        System.out.println("我是原始方法");
    }

}

interface MethodReplacer {
    public Object reimplement(Object obj, Method method, Object[] args) throws Throwable;
}

class MethodReplace implements MethodReplacer {

    @Override
    public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
        System.out.println("我是替换方法");
        return null;
    }

    public static void main(String[] args) {
        //如果 spring.xml 文件如下：
        //<bean id="methodReplace" class="org.springframework.core.test1.MethodReplace"/>
        //<bean id="method" class="org.springframework.core.test1.Method"/>
        //则运行结果为：
        //我是原始方法
        //增加 replaced-method 子元素：
        //<bean id="methodReplace" class="org.springframework.core.test1.MethodReplace"/>
        //<bean id="method" class="org.springframework.core.test1.Method">
        //
        //</bean>
        //运行结果为：
        //我是替换方法
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        Method method = (Method) context.getBean("method");
        method.display();
    }

}

class BookService {

}

//<bean id="bookService" class="org.springframework.core.service.BookService"/>
//
//<bean id="studentService" class="org.springframework.core.service.StudentService">
//    <constructor-arg index="0" value="chenssy"/>
//    <constructor-arg name="age" value="100"/>
//    <constructor-arg name="bookService" ref="bookService"/>
//</bean>
class StudentService {

    private String name;

    private Integer age;

    private BookService bookService;

    StudentService(String name, Integer age, BookService bookService) {

        this.name = name;

        this.age = age;

        this.bookService = bookService;

    }

}
