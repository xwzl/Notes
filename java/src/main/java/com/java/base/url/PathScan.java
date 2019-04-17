package com.java.base.url;

import com.java.frame.auto.MyComponent;
import com.java.frame.auto.MyController;
import com.java.frame.auto.MyMapper;
import com.java.frame.auto.MyService;
import com.java.frame.util.LogUtils;
import com.java.frame.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xuweizhi
 * @date 2019/04/09 20:50
 */
@Slf4j
public class PathScan {

    protected final Log logger = LogFactory.getLog(getClass());

    public final static String INCLUDE_PACKAGE_PATTERN = "&";

    public final static String EXCLUDE_PACKAGE_PATTERN = "#";

    private List<String> exPath;

    private String regex;

    public Set<Class<?>> services = new HashSet<>();
    public Set<Class<?>> mappers = new HashSet<>();
    public Set<Class<?>> controllers = new HashSet<>();

    //{@link  ResolverUtil#find(ResolverUtil.Test, String)}
    //@Test
    //public void test() throws IOException {
    //    String path = "com.java.base.io".replaceAll("\\.", "/");
    //    //List<String> list = VFS.getInstance().list(path);
    //    //System.out.println(path);
    //    System.out.println(path);
    //}
    //
    //@Test
    //public void test1() throws IOException {
    //    Set<Class<?>> set = packageScan("com.java", MyComponent.class);
    //    log.info("包含注解的类个数：" + set.size());
    //    set.forEach(System.out::println);
    //}


    /**
     * 获取指定注解的类们
     *
     * @param path            包路径
     * @param annotationClazz 指定注解类型
     */
    public Set<Class<?>> packageScan(String path, Class<?> annotationClazz) throws IOException {
        // 替换包
        path = path.replaceAll("\\.", "/");
        String basePath = "";
        exPath = new ArrayList<>();
        List<String> inPath = new ArrayList<>();
        String remainPath = "";
        regex = EXCLUDE_PACKAGE_PATTERN;
        if (path.contains(regex)) {
            basePath = getPath(path, regex);
            remainPath = getExcludePath(getRemainPath(path, regex), exPath);
        }
        regex = INCLUDE_PACKAGE_PATTERN;
        if (path.contains(INCLUDE_PACKAGE_PATTERN)) {
            if (StringUtils.isEmpty(basePath)) {
                basePath = path.substring(0, path.indexOf(regex));
            }
            remainPath = getExcludePath(getRemainPath(path, regex), inPath);
        }
        if (StringUtils.isEmpty(remainPath)) {
            LogUtils.printLog(logger, "Start scanning package path !");
        }
        if (StringUtils.isEmpty(basePath)) {
            basePath = path;
        }
        inPath.add(basePath);
        Set<Class<?>> result = new HashSet<>();
        for (String packagePath : inPath) {
            result.addAll(getBeanClass(annotationClazz, packagePath));
            LogUtils.printLog(logger, packagePath + " Package scanning completed !");
        }
        return result;
    }

    /**
     * 获取 指定路径下的包们
     *
     * @param component 指定组件
     * @param basePath  扫描的基本包路径
     * @return 指定路径下的classes 们
     * @throws IOException IO 异常
     */
    private Set<Class<?>> getBeanClass(Class<?> component, String basePath) throws IOException {
        ArrayList<URL> list = Collections.list(Thread.currentThread().getContextClassLoader().getResources(basePath));

        List<String> result = new ArrayList<>();

        Set<Class<?>> set = new HashSet<>();

        list.forEach(url -> {
            result.addAll(
                    list(url, basePath).stream().filter(value -> value.endsWith(".class")).collect(Collectors.toList()));
        });

        result.forEach(className -> {
            // 获得全类名
            match(className, component, set);
        });
        return set;
    }

    /**
     * 获取包扫描路径
     */
    public String getPath(String path, String regex) {
        return path.substring(0, path.indexOf(regex));
    }

    /**
     * 递归添加 exPath
     */
    private String getExcludePath(String remainPath, List<String> exPath) {
        // 如果有不扫描的包路径，则添加
        if (remainPath.contains(regex)) {
            exPath.add(getPath(remainPath, regex));
            return getExcludePath(getRemainPath(remainPath, regex), exPath);
        } else {
            // 如果指定了额外的包扫描路径，返回额外包扫描路径的包
            if (remainPath.contains(INCLUDE_PACKAGE_PATTERN)) {
                exPath.add(getPath(remainPath, INCLUDE_PACKAGE_PATTERN));
                return getRemainPath(remainPath, INCLUDE_PACKAGE_PATTERN);
            } else {
                exPath.add(remainPath);
                // 否则返回空串
                return "";
            }
        }
    }

    private String getRemainPath(String remainPath, String regex) {
        return remainPath.substring(remainPath.indexOf(regex) + 1);
    }

    /**
     * 模拟Mybatis 匹配注解,当然Mybatis 真正的包扫描没有那么简单
     */
    public boolean match(Class clazz, Class<?> component) {
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            Class<? extends Annotation> type = annotation.annotationType();
            if (type.equals(component)) {
                return true;
            }
            if (type.isAnnotationPresent(MyComponent.class)) {
                if (clazz.getAnnotation(MyService.class) != null) {
                    services.add(clazz);
                }
                if (clazz.getAnnotation(MyMapper.class) != null) {
                    mappers.add(clazz);
                }
                if (clazz.getAnnotation(MyController.class) != null) {
                    controllers.add(clazz);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 模拟 Mybatis 包扫描
     */
    public void match(String className, Class<?> component, Set<Class<?>> set) {
        // 获取全雷鸣
        String externalName = className.substring(0, className.indexOf('.')).replace('/', '.');
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            Class<?> clazz = classLoader.loadClass(externalName);
            if (match(clazz, component)) {
                set.add(clazz);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected static List<URL> getResources(String path) throws IOException {
        return Collections.list(Thread.currentThread().getContextClassLoader().getResources(path));
    }

    /**
     * 获取指定包下的 URL .class 文件
     */
    public List<String> list(URL url, String path) {
        InputStream is = null;
        List<String> resources = new ArrayList<String>();
        for (String e : exPath) {
            if (url.getPath().contains(e)) {
                LogUtils.printLog(logger, e + " The file under the package path is not scanned and will be ignored!");

                return resources;
            }
        }
        List<String> children = new ArrayList<String>();
        try {
            //获取该路径下的资源
            is = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            List<String> lines = new ArrayList<String>();
            for (String line; (line = reader.readLine()) != null; ) {
                lines.add(line);
                if (getResources(path + "/" + line).isEmpty()) {
                    lines.clear();
                    break;
                }
            }
            children.addAll(lines);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String prefix = url.toExternalForm();
        if (!prefix.endsWith("/")) {
            prefix = prefix + "/";
        }

        // Iterate over immediate children, adding files and recursing into directories
        for (String child : children) {
            String resourcePath = path + "/" + child;
            resources.add(resourcePath);
            URL childUrl = null;
            try {
                childUrl = new URL(prefix + child);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            resources.addAll(list(childUrl, resourcePath));
        }
        return resources;
    }
}
