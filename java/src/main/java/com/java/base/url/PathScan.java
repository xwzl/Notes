package com.java.base.url;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.io.ResolverUtil;
import org.apache.ibatis.io.VFS;
import org.junit.Test;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import static com.java.base.annotation.util.LogUtils.printLog;

/**
 * @author xuweizhi
 * @date 2019/04/09 20:50
 */
@Slf4j
public class PathScan {

    protected final Log logger = LogFactory.getLog(getClass());

    /**
     * {@link  ResolverUtil#find(ResolverUtil.Test, String)}
     */
    @Test
    public void test() throws IOException {
        String path = "com.java.base.io".replaceAll("\\.", "/");
        List<String> list = VFS.getInstance().list(path);
        list.forEach(System.out::println);
    }

    @Test
    public void test1() throws IOException {
        Set<Class<?>> set = packageScan("com.java", Service.class);
        log.info("包含注解的类个数：" + set.size());
        set.forEach(System.out::println);
    }


    /**
     * 获取指定注解的类们
     *
     * @param path            包路径
     * @param annotationClazz 指定注解类型
     */
    public Set<Class<?>> packageScan(String path, Class<?> annotationClazz) throws IOException {
        // 替换包
        path = path.replaceAll("\\.", "/");

        ArrayList<URL> list = Collections.list(Thread.currentThread().getContextClassLoader().getResources(path));

        List<String> result = new ArrayList<>();

        Set<Class<?>> set = new HashSet<>();

        String finalPath = path;
        list.forEach(url -> {
            result.addAll(list(url, finalPath).stream().filter(value -> value.endsWith(".class")).collect(Collectors.toList()));
        });

        printLog(logger, "扫描包功臣");

        result.forEach(className -> {
            // 获得全类名
            match(className, annotationClazz, set);
        });

        printLog(logger, "获取包路径下全名类");
        return set;
    }

    /**
     * 模拟Mybatis 匹配注解,当然Mybatis 真正的包扫描没有那么简单
     */
    public boolean match(Class clazz, Class<?> annotationClazz) {
        Annotation annotation = clazz.getAnnotation(annotationClazz);
        return annotation != null;
    }

    /**
     * 模拟 Mybatis 包扫描
     */
    public void match(String className, Class<?> annotationClazz, Set<Class<?>> set) {
        // 获取全雷鸣
        String externalName = className.substring(0, className.indexOf('.')).replace('/', '.');
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            Class<?> clazz = classLoader.loadClass(externalName);
            if (match(clazz, annotationClazz)) {
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
