package com.java.spring.bean;

import com.java.spring.ioc.LoadBean;
import com.java.spring.xml.AbstractApplicationContext;
import com.java.spring.xml.AbstractRefreshableApplicationContext;

/**
 * {@link ApplicationContextResolver}
 * <p>
 * ApplicationContext 最重要的方法:#refresh() 方法。
 * <p>
 * #refresh() 方法，是定义在 ConfigurableApplicationContext 类中的，作用就是:刷新 Spring 的应用上下文。
 * <p>
 * 其实现是在 AbstractApplicationContext 中实现,{@link AbstractApplicationContext#refresh()}
 *
 * <h2>2. obtainFreshBeanFactory()</h2>
 * 核心方法就在 #refreshBeanFactory() 方法中第四步{@link #loadBeanDefinitions()}
 * <h2>3. prepareBeanFactory(beanFactory)</h2>
 * 填充 BeanFactory 功能 {@link  AbstractApplicationContext#prepareBeanFactory}进行强化
 * <h2>4. postProcessBeanFactory()</h2>
 * 提供子类覆盖的额外处理，即子类处理自定义的BeanFactoryPostProcess
 * {@link AbstractApplicationContext#refresh()}#{@link AbstractApplicationContext#postProcessBeanFactory}
 * <h2>5. invokeBeanFactoryPostProcessors()</h2>
 * 查看文件总结
 * @author xuweizhi
 * @date 2019/03/27 16:55
 */
public class ContainFresh {

    /**
     * 核心方法就在 #refreshBeanFactory() 方法，该方法的核心任务就是创建 BeanFactory 并对其就行一番初始化。
     * {@link   AbstractRefreshableApplicationContext#refreshBeanFactory}
     * <h3>上面 5 个步骤，都是比较简单的，但是有必要讲解下第 4 步：</h3>
     * <p>
     * 加载 BeanDefinition。如果各位看过{@link LoadBean}的话，在刚刚开始分析源码的时候，就是以 BeanDefinitionReader#
     * loadBeanDefinitions(Resource resource) 方法，作为入口来分析的，示例如下：
     * <blockquote><pre>
     *  // 示例代码
     * ClassPathResource resource = new ClassPathResource("bean.xml");
     * DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
     * XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
     * reader.loadBeanDefinitions(resource);
     * </pre></blockquote>
     * 只不过这段代码的 BeanDefinitionReader#loadBeanDefinitions(Resource) 方法，是定义在 BeanDefinitionReader 中，
     * 而此处的 #loadBeanDefinitions则是定义在 AbstractRefreshableApplicationContext 中，如下
     * {@link AbstractRefreshableApplicationContext#loadBeanDefinitions}
     * <p>
     * 由具体的子类实现，我们以 AbstractXmlApplicationContext 为例，详情如下
     * {@link com.java.spring.xml.AbstractXmlApplicationContext#loadBeanDefinitions}
     * <p>
     * 继续{@link AbstractApplicationContext#refresh()}分析，第三步{@link  AbstractApplicationContext#prepareBeanFactory}
     */
    public void loadBeanDefinitions() {

    }
}

