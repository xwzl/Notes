package com.java.frame.factory;

import com.java.frame.auto.*;
import com.java.frame.exception.MyRequestMappingException;
import com.java.frame.handler.*;
import com.java.frame.util.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;

import static com.java.frame.exception.ComponentConstance.BEAN_KEY;
import static com.java.frame.exception.ComponentConstance.CONTROLLER_KEY;
import static com.java.frame.handler.MyRequestHandler.PREFIX;
import static com.java.frame.util.LogUtils.printLog;

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
    PathUtils scan;

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
    ResourcesUtils resources;

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
    final Map<String, Map< MyRequestHandler,String>> controllerMethods = new ConcurrentHashMap<>(256);

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
        scan = new PathUtils();
        resources = new ResourcesUtils();
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
        Map<MyRequestHandler, String> handlerMap = new HashMap<>();
        for (Method method : methods) {
            MyRequestMapping request = (MyRequestMapping) method.getAnnotation(component.get("MyRequestMapping"));
            if (request != null) {
                String keyId = bean.getName() + CONTROLLER_KEY + method.getName();
                String url = getUrlPath(request.value());
                MyRequestHandler handler = new MyRequestHandler();
                handler.setUrl(StringUntils.isNotEmpty(baseUrl) ? baseUrl + url : url);
                if (!handlerMap.containsKey(keyId)) {
                    handlerMap.put(handler, keyId);
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
            MyLocalMethod myLocalMethod = method.getAnnotation(MyLocalMethod.class);
            //用来存放 解析实体的建
            if (myLocalMethod != null) {
                registerMylocalMethodMapperMethod(mappingMap, method, myLocalMethod);
                continue;
            }
            MyLocalMethodReinforce reinforce = method.getAnnotation(MyLocalMethodReinforce.class);
            if (reinforce != null) {
                parseMyLocalMethod(mappingMap, method, reinforce);
                continue;
            }
            MySelect mySelect = method.getAnnotation(MySelect.class);
            if (mySelect != null) {
                parseMySelect(mappingMap, method, mySelect);
                continue;
            }
            MyInsert insert = method.getAnnotation(MyInsert.class);
            if (insert != null) {
                parseMyInsert(mappingMap, method, insert);
                continue;
            }
            MyUpdate update = method.getAnnotation(MyUpdate.class);
            if (update != null) {
                parseMyUpdate(mappingMap, method, update);
                continue;
            }
            MyDelete delete = method.getAnnotation(MyDelete.class);
            if (delete != null) {
                parseMyDelete(mappingMap, method, delete);
                continue;
            }

        }
        parseAlias();
        registerBeanClass();
        registerMapper(mappingMap);
    }

    private void parseMyLocalMethod(Map<String, Object> mappingMap, Method method, MyLocalMethodReinforce reinforce) {
        String className = ((MyLocalMethodReinforce) reinforce).className();
        MyLocalMethodMapping mapping = new MyLocalMethodMapping();
        String methodName = ((MyLocalMethodReinforce) reinforce).methodName();
        if (StringUntils.isNotEmpty(methodName)) {
            mapping.setMethodName(methodName);
        }
        mapping.setClassName(className);
        mappingMap.put(bean.getName() + "&" + method.getName(), mapping);
    }

    /**
     * 解析 mySelect 注解
     */
    private void parseMySelect(Map<String, Object> mappingMap, Method method, MySelect mySelect) {
        try {
            MySelectMapping select = new MySelectMapping(mySelect.value(), mySelect.nameSpace());

            Matcher matcher = PatternUtils.getSqlMatcher(mySelect.value());
            List<String> paramList = select.getParamList();
            List<String> paramNameList = select.getParamNameList();
            Class<?> model = Class.forName(mySelect.nameSpace());
            while (matcher.find()) {
                String fieldName = matcher.group().substring(2, matcher.group().length() - 1);
                paramList.add(model.getDeclaredField(fieldName).getType().getName());
                paramNameList.add(fieldName);
            }
            mappingMap.put(bean.getName() + "#" + method.getName(), select);
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析 MyDelete 注解
     */
    private void parseMyDelete(Map<String, Object> mappingMap, Method method, MyDelete myDelete) {
        MyDeleteMapping deleteMapping = new MyDeleteMapping(myDelete.value(), myDelete.nameSpace());
        mappingMap.put(bean.getName() + "#" + method.getName(), deleteMapping);
    }

    /**
     * 解析 MyInsert 注解
     */
    private void parseMyUpdate(Map<String, Object> mappingMap, Method method, MyUpdate update) {
        MyUpdateMapping updateMapping = new MyUpdateMapping(update.value(), update.nameSpace());
        mappingMap.put(bean.getName() + "#" + method.getName(), updateMapping);
    }
    /**
     * 解析 MyInsert 注解
     */
    private void parseMyInsert(Map<String, Object> mappingMap, Method method, MyInsert insert) {
        MyInsertMapping select = new MyInsertMapping(insert.value(), insert.nameSpace());
        mappingMap.put(bean.getName() + "#" + method.getName(), select);
    }

    /**
     * 注册......
     */
    private void registerMylocalMethodMapperMethod(Map<String, Object> mappingMap, Method method, MyLocalMethod localMethod) {
        String id = this.bean.getName() + "#" + method.getName();
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
