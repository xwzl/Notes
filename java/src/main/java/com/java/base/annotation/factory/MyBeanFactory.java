package com.java.base.annotation.factory;

import com.java.base.annotation.auto.*;
import com.java.base.annotation.exception.MyApplicationException;
import com.java.base.annotation.ioc.MyLocalMethodMapping;
import com.java.base.annotation.proxy.MyMapperProxy;
import com.java.base.annotation.util.ClassUtils;
import com.java.base.annotation.util.MyResourcesUtils;
import com.java.base.annotation.util.StringUntils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.core.NamedThreadLocal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xuweizhi
 * @date 2019/04/11 22:38
 */
@Slf4j
public class MyBeanFactory {

    /**
     * 加载配置
     */
    MyConfigure configure;

    /**
     * 加载资源
     */
    MyResourcesUtils resources = new MyResourcesUtils();

    /**
     * Bean 对应的 class 对象们
     */
    Map<String, Class<?>> loaded;

    /**
     * 类加载器
     */
    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    /**
     * 别名维护对象
     */
    private SimpleAliasRegistry aliasRegistry = new SimpleAliasRegistry();

    /**
     * 单例 bean
     */
    private final Map<String, Object> singletonObject = new ConcurrentHashMap<>(256);

    private final Map<String, Object> singletonFactory = new HashMap<>(16);

    private final Map<String, Object> earlySingletonObject = new HashMap<>(16);

    private final Set<String> registeredSingleton = new LinkedHashSet<>(256);

    private final Map<String, Object> tempObject = new ConcurrentHashMap<>();

    /**
     * 用来存放不称职的 bean men
     */
    private final Map<String, Object> convertObject = new ConcurrentHashMap<>();

    private final Set<String> singletonsCurrentlyInCreation =
            Collections.newSetFromMap(new ConcurrentHashMap<>(16));

    private final Set<String> inCreationCheckExclusions =
            Collections.newSetFromMap(new ConcurrentHashMap<>(16));

    /**
     * 正在创建 bean 属性
     */
    private final ThreadLocal<Object> prototypesCurrentlyInCreation =
            new NamedThreadLocal<>("Prototype beans currently in creation");


    protected static MyBeanFactory builder() {
        return new MyBeanFactory();
    }

    public <T> T getBean(String name) throws BeansException {
        return doGetBean(name, null, null, false);
    }

    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return doGetBean(name, requiredType, null, false);
    }

    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, null, args, false);
    }

    public <T> T getBean(String name, Class<T> requiredType, Object... args)
            throws BeansException {
        return doGetBean(name, requiredType, args, false);
    }

    protected <T> T doGetBean(final String name, final Class<T> requiredType,
                              final Object[] args, boolean typeCheckOnly) throws BeansException {
        //Object bean = getToDo(name);
        Object bean;
        final String beanName = transformedBeanName(name);
        bean = singletonObject.get(beanName);
        return (T) bean;
    }

    /**
     * 获取 bean 的全类名
     */
    private String transformedBeanName(String name) {
        return aliasRegistry.canonicalName(name);
    }

    public static MyBeanFactory run(Class<?> clazz, Object... args) {
        return builder().init(clazz, args);
    }

    /**
     * 初始化容器
     */
    public MyBeanFactory init(Class<?> clazz, Object... args) {
        MyApplication application = clazz.getAnnotation(MyApplication.class);
        if (application != null) {
            // 获取包扫描、额外的包扫描空间、以及排除的包扫描空间
            String packageName = getPackageName(clazz);
            // 1. todo 初始化配置
            configure = new MyConfigure(packageName, application.loadResources());
            loaded = configure.loaded;
            resources = configure.resources;
            // 2. todo 初始化 bean 容器
            initBean();
            // 3. todo 为初始化 bean 赋值
            assignmentBean();
            // 4. 注册 mapper 代理对象
            registerMapperProxy();
            return this;
        } else {
            try {
                throw new MyApplicationException(clazz.getName() + "类不是MyApplication启动类，无法解析");
            } catch (MyApplicationException e) {
                e.printStackTrace();
            }
        }
        return null;

    }

    /**
     * 获取包扫描空间
     */
    private String getPackageName(Class<?> clazz) {
        String packageName = null;
        MyComponentScan scan = clazz.getAnnotation(MyComponentScan.class);
        if (StringUntils.isEmpty(scan.packageName())) {
            packageName = scan.packageName();
        }
        //如果未设置包扫描路径，默认解析当前路径及子目录
        if (StringUntils.isEmpty(packageName)) {
            packageName = clazz.getPackage().getName();
        }
        if(scan.includeFilters().length>0){
            for (int i = 0; i < scan.includeFilters().length; i++) {
                Class<?>[] typePath = scan.includeFilters()[i].classTypePath();
            }
        }
        if(scan.excludeFilters().length>0){
            for (int i = 0; i < scan.excludeFilters().length; i++) {
                MyFilter myFilter = scan.excludeFilters()[i];
                Class<?>[] classes = myFilter.classTypePath();
                System.out.println(classes[i]);
            }
        }
        return packageName;
    }

    /**
     * 注册 mapper 的动态代理 bean 们
     */
    private void registerMapperProxy() {
        Map<String, Map<String, MyLocalMethodMapping>> mapperBeans = configure.mapperBeans;
        for (Map.Entry<String, Map<String, MyLocalMethodMapping>> entry : mapperBeans.entrySet()) {
            Map<String, MyLocalMethodMapping> value = entry.getValue();
            try {
                Class<?> mapperProxy = Class.forName(entry.getKey());
                Object proxyMapperBean = getProxyMapperBean(mapperProxy, entry.getValue());
                this.singletonObject.put(mapperProxy.getName(), proxyMapperBean);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public <T> T getProxyMapperBean(Class<T> mapperProxy, Map<String, MyLocalMethodMapping> mapper) {
        Enhancer enhancer = new Enhancer();
        // 设置enhancer对象的父类
        enhancer.setSuperclass(mapperProxy);
        // 设置enhancer的回调对象
        enhancer.setCallback(new MyMapperProxy(mapper, mapperProxy));
        // 创建代理对象
        return (T) enhancer.create();
    }

    public void initBean() {
        for (Map.Entry<String, Class<?>> entry : loaded.entrySet()) {
            registerBean(entry.getValue());
        }
    }

    /**
     * 向容器中注册 bean
     */
    private void registerBean(Class<?> clazz) {
        try {
            if (!clazz.isInterface()) {
                Constructor<?> constructor = clazz.getDeclaredConstructor();
                constructor.setAccessible(true);
                Object newInstance = constructor.newInstance();
                for (Field field : clazz.getDeclaredFields()) {
                    MyValue myValue = field.getAnnotation(MyValue.class);
                    field.setAccessible(true);
                    if (myValue != null) {
                        resources.resolver(clazz, field, newInstance);
                    }
                }
                tempObject.put(clazz.getName(), newInstance);
            } else {
                // todo 接口采用动态进行编织
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 为 bean 赋值
     */
    private void assignmentBean() {
        for (Map.Entry<String, Class<?>> entry : loaded.entrySet()) {
            doAssignmentBean(entry.getValue(), entry.getKey());
        }
    }

    /**
     * 为 bean 们赋值
     *
     * @param clazz
     */
    private <T> T doAssignmentBean(Class<T> clazz, String keyId) {
        synchronized (singletonObject) {
            if (clazz.isInterface()) {
                return null;
            }
            Object instance = null;
            try {
                instance = tempObject.get(clazz.getName());
                if (instance == null) {
                    Constructor<?> constructor = clazz.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    instance = constructor.newInstance();
                }

                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    MyAutowired myAutowired = field.getAnnotation(MyAutowired.class);
                    field.setAccessible(true);
                    if (myAutowired != null) {
                        MyComponent myComponent = field.getType().getAnnotation(MyComponent.class);
                        // 解决组件之间 bean 的注入，未解决 bean 的循环注入
                        if (myComponent != null) {
                            Object newInstance = null;
                            if (!keyId.contains("#")) {
                                newInstance = convertObject.get("#" + field.getName());
                            } else {
                                newInstance = convertObject.get(keyId.substring(keyId.indexOf("#")));
                            }
                            if (newInstance != null) {
                                field.set(instance, newInstance);
                            } else {
                                field.set(instance, doAssignmentBean(field.getType(), keyId + "#" + field.getName()));
                            }
                            continue;
                        }
                        // 不存在组件之间的相互依赖，则从最原始的 bean 中获取值
                        if (!field.getType().isPrimitive()) {
                            field.set(instance, tempObject.get(field.getType().getName()));
                        }
                    }
                }
                //System.out.println(keyId.contains("#") + "            " + keyId);
                if (!keyId.contains("#")) {
                    singletonObject.put(keyId, instance);
                } else {
                    convertObject.put(keyId.substring(keyId.indexOf("#")), instance);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return (T) instance;
        }

    }

    public <T> T getBean(Class<T> clazz) {
        return getBean(clazz.getName());
    }

    /**
     * 暂时放弃，还没有想通怎样解决循环bean
     */
    private Object getToDo(String name) {
        Object bean = null;
        final String beanName = transformedBeanName(name);
        Object sharedInstance = getSingleton(beanName);
        // 如果不等于null，说明已经被创建，则进行复制操作，一定是从二级缓存中获取的值
        if (sharedInstance != null) {
            if (isSingletonCurrentlyInCreation(beanName)) {
                log.debug("Returning eagerly cached instance of singleton bean '" + beanName +
                        "' that is not fully initialized yet - a consequence of a circular reference");
            } else {
                log.debug("Returning cached instance of singleton bean '" + beanName + "'");
            }
            // 赋值
            bean = getObjectForBeanInstance(sharedInstance, beanName);
            // 如果为 null ，则递归检查循环依赖
        } else {
            try {
                // 判断当前的属性的 bean 是否正在被创建
                if (isPrototypeCurrentlyInCreation(beanName)) {
                    throw new Exception("Circular depends-on relationship between" + beanName);
                }

                singletonFactory.put(name, Class.forName(name).getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bean;
    }

    /**
     * 获取单例的 bean 对象
     */
    public Object getSingleton(String beanName) {
        return getSingleton(beanName, true);
    }

    /**
     * 获取单列 bean
     * 使用三级缓存的策略，解决循环依赖
     */
    protected Object getSingleton(String beanName, boolean allowEarlyReference) {
        Object singletonObject = this.singletonObject.get(beanName);
        if (singletonObject == null && isSingletonCurrentlyInCreation(beanName)) {
            synchronized (this.singletonObject) {
                // 从二级缓存中获取对象
                singletonObject = this.earlySingletonObject.get(beanName);
                if (singletonObject == null && allowEarlyReference) {
                    Object object = this.singletonFactory.get(beanName);
                    if (object != null) {
                        singletonObject = object;
                        // 从一级缓存中获取对象，并升级到二级缓存
                        this.earlySingletonObject.put(beanName, singletonObject);
                        // 移除以及缓存中的对象
                        this.singletonFactory.remove(beanName);
                    }
                }
            }
        }
        return singletonObject;
    }

    public boolean isSingletonCurrentlyInCreation(String beanName) {
        return this.singletonsCurrentlyInCreation.contains(beanName);
    }

    protected boolean isPrototypeCurrentlyInCreation(String beanName) {
        Object curVal = this.prototypesCurrentlyInCreation.get();
        return (curVal != null &&
                (curVal.equals(beanName) || (curVal instanceof Set && ((Set<?>) curVal).contains(beanName))));
    }


    protected Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        try {
            Class<?> clazz = Class.forName(beanName);
            beanInstance = clazz.getDeclaredConstructor().newInstance();
            if (!singletonFactory.containsKey(beanName)) {
                this.singletonFactory.put(beanName, beanInstance);
            } else {
                throw new Exception(beanName + "单例正在创建ing");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
