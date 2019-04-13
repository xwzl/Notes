package com.java.base.annotation.factory;

import com.java.base.annotation.auto.MyComponent;
import com.java.base.annotation.auto.MyLocalMethod;
import com.java.base.annotation.auto.MyLocalMethodReinforce;
import com.java.base.annotation.ioc.MyAnnotationAssistant;
import com.java.base.annotation.ioc.MyLocalMethodMapping;
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
    Set<Class<?>> scanClasses = new HashSet<>();

    /**
     * 一个MySql注解对应一个 MyLocalMethodMapping 实例
     */
    final Map<String, Map<String, MyLocalMethodMapping>> mapperBeans = new ConcurrentHashMap<>(256);

    /**
     * 已经注册好了 bean 的 Class 对象
     */
    final Map<String, Class<?>> loaded = new ConcurrentHashMap<>(256);

    /**
     * 当前正在解析的 bean 类型
     */
    Class<?> currentBean;

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
            scanClasses = scan.packageScan(packagePath, component.get("MyComponent"));
            if (scanClasses != null && scanClasses.size() > 0) {
                registerComponent();
                LogUtils.printLog(log, "组件注册完毕");
            }
            parseResources();
        } catch (IOException e) {
            System.out.println("包扫描失败");
        }
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
        for (Class<?> bean : scanClasses) {
            synchronized (loaded) {
                currentBean = bean;
                parseComponent(bean);
            }
        }
    }

    /**
     * 解析 bean 们
     */
    private void parseComponent(Class<?> bean) {
        // 接口则进行解析 mapper
        if (currentBean.isInterface()) {
            parseMethod();
            // 如果不是接口则进行bean解析
        } else {
            // todo
            parseBean();

        }
    }


    /**
     * 解析 Mapper 组件的方法们
     */
    private void parseMethod() {

        Method[] methods = currentBean.getDeclaredMethods();

        Map<String, MyLocalMethodMapping> mappingMap = null;
        String beanName = currentBean.getName();

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
                    mappingMap.put(currentBean.getName() + "&" + method.getName(), mapping);
                }
            }


        }
        parseAlias();
        registerBeanClass();
        registerMapper(mappingMap);
    }

    private void registerMylocalMethodMapperMethod(Map<String, MyLocalMethodMapping> mappingMap, Method method, Annotation myLocalMethod) {
        String id = this.currentBean.getName() + "#" + method.getName();
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
        MyComponent annotation = currentBean.getAnnotation(MyComponent.class);
        if (StringUntils.isNotEmpty(annotation.alias())) {
            aliasRegistry.registerAlias(currentBean.getName(), annotation.alias());
        }
    }

    /**
     * 注册 Mapper bean
     */
    public void registerMapper(Map<String, MyLocalMethodMapping> mappingMap) {
        mapperBeans.put(currentBean.getName(), mappingMap);
    }

    /**
     * 注册 bean 对应的 Class 对象们
     */
    public void registerBeanClass() {
        if (loaded.containsKey(currentBean.getName())) {
            try {
                throw new Exception("解析类出错：" + currentBean.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        loaded.put(currentBean.getName(), currentBean);
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
        scanClasses.remove(this.currentBean);
    }
}
