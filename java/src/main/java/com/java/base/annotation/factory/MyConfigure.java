package com.java.base.annotation.factory;

import com.java.base.annotation.auto.*;
import com.java.base.annotation.exception.MyRequestMappingException;
import com.java.base.annotation.ioc.MyAnnotationAssistant;
import com.java.base.annotation.ioc.MyLocalMethodMapping;
import com.java.base.annotation.ioc.MyRequestHandler;
import com.java.base.annotation.util.LogUtils;
import com.java.base.annotation.util.MyResourcesUtils;
import com.java.base.annotation.util.StringUntils;
import com.java.base.url.PathScan;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static com.java.base.annotation.exception.ComponentConstance.BEAN_KEY;
import static com.java.base.annotation.exception.ComponentConstance.CONTROLLER_KEY;
import static com.java.base.annotation.ioc.MyRequestHandler.PREFIX;
import static com.java.base.annotation.util.LogUtils.printLog;

/**
 * 解析注解，模拟 Mybatis 注解解析场景，但是实际上 Mybatis 的注解解析十分繁杂，且采用了递归手段
 *
 * @author xuweizhi
 * @date 2019/04/11 19:24
 */
@Slf4j
public class MyConfigure implements Serializable {

    /**
     * 包扫描
     */
    PathScan scan;

    /**
     * 注解协助
     */
    MyAnnotationAssistant assistant;

    /**
     * 组件 注解集合们
     */
    Map<String, Class<? extends Annotation>> component;

    /**
     * 启动类 注解集合们
     */
    Map<String, Class<? extends Annotation>> app;

    /**
     * 加载配置文件
     */
    MyResourcesUtils resources;

    /**
     * 用来存放扫描指定符合注解的类
     */
    Set<Class<?>> components = new HashSet<>();

    /**
     * 见名知意
     */
    Set<Class<?>> mappers = new HashSet<>();

    /**
     * 见名知意
     */
    Set<Class<?>> serviceLoaded = new HashSet<>();

    /**
     * 见名知意
     */
    Set<Class<?>> controllerLoaded = new HashSet<>();


    /**
     * 一个MySql注解对应一个 MyLocalMethodMapping 实例
     */
    final Map<String, Map<String, Object>> mapperMethods = new ConcurrentHashMap<>(256);

    /**
     * 一个MyRequestMapping注解对应一个MyRequesstHandler 实例
     */
    final Map<String, Map<String, MyRequestHandler>> controllerMethods = new ConcurrentHashMap<>(256);

    /**
     * 已经注册好了 bean 的 Class 对象
     */
    final Map<String, Class<?>> loaded = new ConcurrentHashMap<>(256);

    /**
     * 当前正在解析的 bean 类型
     */
    Class<?> bean;

    /**
     * 指定包扫描路径
     */
    String packagePath;

    /**
     * 配置文件路径
     */
    String loadResource;

    /**
     * 别名注册中心
     */
    SimpleAliasRegistry aliasRegistry;


    {
        assistant = new MyAnnotationAssistant();
        scan = new PathScan();
        resources = new MyResourcesUtils();
        aliasRegistry = new SimpleAliasRegistry();
        this.component = assistant.componentRegister;
        this.app = assistant.appRegister;
    }

    private MyConfigure() {

    }

    /**
     * 静态内置类可以达到线程安全问题，但如果遇到序列化对象时，使用默认的方式运行得到的结果还是多例的
     */
    private final static class SingletonConfigure {
        private static final MyConfigure MY_CONFIGURE = new MyConfigure();
    }


    public static MyConfigure builder() {
        return SingletonConfigure.MY_CONFIGURE;
    }

    public MyConfigure execute(String packagePath, String loadResource) {
        // 通用逻辑
        generalLogic(packagePath, loadResource);
        //初始化容器
        init();
        return this;
    }

    private void generalLogic(String packagePath, String loadResource) {
        this.packagePath = packagePath;
        this.loadResource = loadResource;
    }

    public void decorationBeanFactory(MyBeanFactory beanFactory) {
        beanFactory.configure = this;
        addInterface();
        beanFactory.loaded = loaded;
        beanFactory.resources = resources;
        beanFactory.serviceLoaded = getLoaded(serviceLoaded);
        beanFactory.controllerLoaded = getLoaded(controllerLoaded);
        beanFactory.aliasRegistry = aliasRegistry;
    }

    private void addInterface() {
        enhanceLoaded(serviceLoaded);
        enhanceLoaded(controllerLoaded);
    }

    private void enhanceLoaded(Set<Class<?>> loadeds) {
        for (Class<?> load : loadeds) {
            if (load.getInterfaces().length > 0) {
                loaded.put(load.getInterfaces()[0].getName(), load.getInterfaces()[0]);
            }
        }
    }

    private Map<String, Class<?>> getLoaded(Set<Class<?>> loaded) {
        Map<String, Class<?>> load = new HashMap<>();
        for (Class<?> clazz : loaded) {
            load.put(clazz.getName(), clazz);
        }
        return load;
    }


    /**
     * 初始化配置
     */
    public void init() {
        String[] split = packagePath.split(BEAN_KEY);
        try {
            components = scan.packageScan(packagePath, component.get("MyComponent"));
            //for (String packages : split) {
            //    LogUtils.printLog(log, packages + "packet scan success !");
            //}
            if (components != null && components.size() > 0) {
                // 解析 mapper,service,controller
                LogUtils.printLog(log, "Start parsing MyMapper 、MyService、MyController!");
                addConcreteComponent();
                LogUtils.printLog(log, "Start register MyComponent bean Class!");
                registerComponent();
            }
            parseResources();
        } catch (IOException e) {
            for (String packages : split) {
                LogUtils.printLog(log, packages + "packet scan failed !");
            }
        }
    }

    private void addConcreteComponent() {
        this.mappers = scan.mappers;
        this.controllerLoaded = scan.controllers;
        this.serviceLoaded = scan.services;
    }

    /**
     * 加载资源
     */
    private void parseResources() {
        try {
            resources.parse(loadResource);
            printLog(log, "Load the configuration file !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化 bean 们
     */
    private void registerComponent() {
        for (Class<?> bean : components) {
            synchronized (loaded) {
                this.bean = bean;
                parseComponent();
            }
        }
    }

    /**
     * 解析 bean 们
     */
    private void parseComponent() {
        // 接口则进行解析 mapper
        if (this.bean.getAnnotation(MyMapper.class) != null) {
            parseMapper();
            return;
        }
        //if (this.bean.getAnnotation(MyService.class) != null) {
        //    parseService();
        //    return;
        //}
        if (this.bean.getAnnotation(MyController.class) != null) {
            parseController();
            return;
        } else {
            // todo
            parseBean();
        }
    }

    /**
     * 解析 controller
     */
    private void parseController() {
        MyRequestMapping requestMapping = (MyRequestMapping) bean.getAnnotation(component.get("MyRequestMapping"));
        String baseUrl = "";
        if (requestMapping != null) {
            baseUrl = getUrlPath(requestMapping.value());
        }
        Method[] methods = bean.getDeclaredMethods();
        Map<String, MyRequestHandler> handlerMap = new HashMap<>();
        for (Method method : methods) {
            MyRequestMapping request = (MyRequestMapping) method.getAnnotation(component.get("MyRequestMapping"));
            if (request != null) {
                String keyId = bean.getName() + CONTROLLER_KEY + method.getName();
                String url = getUrlPath(request.value());
                MyRequestHandler handler = new MyRequestHandler();
                handler.setUrl(StringUntils.isNotEmpty(baseUrl) ? baseUrl + url : url);
                if (!handlerMap.containsKey(keyId)) {
                    handlerMap.put(keyId, handler);
                } else {
                    try {
                        throw new MyRequestMappingException("The URL mapping path cannot be repeated");
                    } catch (MyRequestMappingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        controllerMethods.put(bean.getName(), handlerMap);
        parseAlias();
        registerBeanClass();
    }

    private String getUrlPath(String value) {
        if (value.endsWith(PREFIX)) {
            return value;
        }
        return PREFIX + value;
    }

    private void parseService() {
    }


    /**
     * 解析 Mapper 组件的方法们
     */
    private void parseMapper() {

        Method[] methods = bean.getDeclaredMethods();

        Map<String, Object> mappingMap = null;
        String beanName = bean.getName();

        if (!mapperMethods.containsKey(beanName)) {
            mappingMap = new HashMap<>();
        }

        for (Method method : methods) {
            // 解析方法们
            Annotation myLocalMethod = method.getAnnotation(component.get("MyLocalMethod"));
            //用来存放 解析实体的建
            if (myLocalMethod != null) {
                registerMylocalMethodMapperMethod(mappingMap, method, myLocalMethod);
            }
            Annotation reinforce = method.getAnnotation(component.get("MyLocalMethodReinforce"));
            if (reinforce != null) {
                if (reinforce instanceof MyLocalMethodReinforce) {
                    String className = ((MyLocalMethodReinforce) reinforce).className();
                    MyLocalMethodMapping mapping = new MyLocalMethodMapping();
                    String methodName = ((MyLocalMethodReinforce) reinforce).methodName();
                    if (StringUntils.isNotEmpty(methodName)) {
                        mapping.setMethodName(methodName);
                    }
                    mapping.setClassName(className);
                    mappingMap.put(bean.getName() + "&" + method.getName(), mapping);
                }
            }
        }
        parseAlias();
        registerBeanClass();
        registerMapper(mappingMap);
    }

    /**
     * 注册......
     */
    private void registerMylocalMethodMapperMethod(Map<String, Object> mappingMap, Method method, Annotation myLocalMethod) {
        String id = this.bean.getName() + "#" + method.getName();
        if (myLocalMethod instanceof MyLocalMethod) {
            MyLocalMethod localMethod = (MyLocalMethod) myLocalMethod;
            String cn = localMethod.className();
            String mn = localMethod.methodName();
            String[] mpc = localMethod.methodParamClass();
            Class<?>[] cs = new Class[mpc.length];
            for (int i = 0; i < cs.length; i++) {
                try {
                    cs[i] = Class.forName(mpc[i]);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            String d = localMethod.description();
            String v = localMethod.value();
            String[] m = localMethod.methodParamValues();
            MyLocalMethodMapping myLocalMethodMapping = new MyLocalMethodMapping(v, mn, cn, cs, m, d);
            assert mappingMap != null;
            if (!mappingMap.containsKey(id)) {
                mappingMap.put(id, myLocalMethodMapping);
            }
        }
    }

    /**
     * 解析别名
     */
    private void parseAlias() {
        MyComponent myComponent = bean.getAnnotation(MyComponent.class);
        String alias = "";
        if (myComponent == null) {
            MyMapper mapper = bean.getAnnotation(MyMapper.class);
            if (mapper != null) {
                alias = mapper.alias();
            }
            MyService myService = bean.getAnnotation(MyService.class);
            if (myService != null) {
                alias = myService.alias();
            }
            MyController myController = bean.getAnnotation(MyController.class);
            if (myController != null) {
                alias = myController.alias();
            }
        }
        if (StringUntils.isNotEmpty(alias)) {
            aliasRegistry.registerAlias(bean.getName(), alias);
        }
    }

    /**
     * 注册 Mapper bean
     */
    public void registerMapper(Map<String, Object> mappingMap) {
        mapperMethods.put(bean.getName(), mappingMap);
    }

    /**
     * 注册 bean 对应的 Class 对象们
     */
    public void registerBeanClass() {
        if (loaded.containsKey(bean.getName())) {
            try {
                throw new Exception("解析类出错：" + bean.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        loaded.put(bean.getName(), bean);
    }

    /**
     * 解析 bean 们
     */
    private void parseBean() {
        try {
            parseAlias();
            registerBeanClass();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 移除
     */
    private void removeBean() {
        components.remove(this.bean);
    }
}
