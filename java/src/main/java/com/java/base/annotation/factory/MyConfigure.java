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
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

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
public class MyConfigure {

    /**
     * 包扫描
     */
    PathScan scan = new PathScan();

    /**
     * 注解协助
     */
    MyAnnotationAssistant assistant = new MyAnnotationAssistant();

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
    MyResourcesUtils resources = new MyResourcesUtils();

    /**
     * 用来存放扫描指定符合注解的类
     */
    Set<Class<?>> components = new HashSet<>();

    Set<Class<?>> mappers = new HashSet<>();

    Set<Class<?>> services = new HashSet<>();

    Set<Class<?>> controllers = new HashSet<>();


    /**
     * 一个MySql注解对应一个 MyLocalMethodMapping 实例
     */
    final Map<String, Map<String, MyLocalMethodMapping>> mapperBeans = new ConcurrentHashMap<>(256);

    final Map<String, Map<String, MyRequestHandler>> controllerBeans = new ConcurrentHashMap<>(256);

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
    protected String loadResource;

    SimpleAliasRegistry aliasRegistry = new SimpleAliasRegistry();

    public MyConfigure(String packagePath, String loadResource) {
        // 通用逻辑
        generalLogic(packagePath, loadResource);
        //初始化容器
        init();
    }

    private void generalLogic(String packagePath, String loadResource) {
        this.component = assistant.componentRegister;
        this.app = assistant.appRegister;
        this.packagePath = packagePath;
        this.loadResource = loadResource;
    }


    /**
     * 初始化配置
     */
    public void init() {
        try {
            components = scan.packageScan(packagePath, component.get("MyComponent"));
            if (components != null && components.size() > 0) {
                // 解析 mapper,service,controller
                addConcreteComponent();
                registerComponent();
                LogUtils.printLog(log, "  ：组件注册完毕");
            }
            parseResources();
        } catch (IOException e) {
            System.out.println(" : 包扫描失败");
        }
    }

    private void addConcreteComponent() {
        this.mappers = scan.mappers;
        this.controllers = scan.controllers;
        this.services = scan.services;
    }

    /**
     * 加载资源
     */
    private void parseResources() {
        try {
            resources.parse(loadResource);
            printLog(log, "加载完配置文件");
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
        if (this.bean.getAnnotation(MyService.class) != null) {
            parseService();
            return;
        }
        if (this.bean.getAnnotation(MyController.class) != null) {
            parseController();
            return;
        } else {
            // todo
            parseBean();
        }
    }

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
                        throw new MyRequestMappingException("URL映射路径不能重复");
                    } catch (MyRequestMappingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        controllerBeans.put(bean.getName(), handlerMap);
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

        Map<String, MyLocalMethodMapping> mappingMap = null;
        String beanName = bean.getName();

        if (!mapperBeans.containsKey(beanName)) {
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

    private void registerMylocalMethodMapperMethod(Map<String, MyLocalMethodMapping> mappingMap, Method method, Annotation myLocalMethod) {
        String id = this.bean.getName() + "#" + method.getName();
        if (myLocalMethod instanceof MyLocalMethod) {
            MyLocalMethod sql = (MyLocalMethod) myLocalMethod;
            String cn = sql.className();
            String mn = sql.methodName();
            String[] mpc = sql.methodParamClass();
            Class<?>[] cs = new Class[mpc.length];
            for (int i = 0; i < cs.length; i++) {
                try {
                    cs[i] = Class.forName(mpc[i]);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            String d = sql.description();
            String v = sql.value();
            String[] m = sql.methodParamValues();
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
        if (myComponent == null) {
            return;
        }
        if (StringUntils.isNotEmpty(myComponent.alias())) {
            aliasRegistry.registerAlias(bean.getName(), myComponent.alias());
        }
    }

    /**
     * 注册 Mapper bean
     */
    public void registerMapper(Map<String, MyLocalMethodMapping> mappingMap) {
        mapperBeans.put(bean.getName(), mappingMap);
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
