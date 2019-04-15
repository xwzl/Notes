package com.java.base.annotation.util;

import com.java.base.annotation.auto.MyValue;
import com.java.base.annotation.exception.MyValueException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 解析Resources 文件
 *
 * @author xuweizhi
 * @date 2019/04/11 23:06
 */
public class MyResourcesUtils {

    Map<String, List<String>> map = new HashMap<>();

    final static List<String> filedType = new ArrayList<>();

    private static Pattern pattern;

    static {
        String regex = "\\$\\{(.+?)\\}";
        pattern = Pattern.compile(regex);
    }

    static {
        filedType.add("Date");
        filedType.add("Map");
        filedType.add("List");
        filedType.add("Set");
        filedType.add("Integer");
        filedType.add("Char");
        filedType.add("Double");
    }

    /**
     * 解析文件
     */
    public void parse(String path) throws IOException {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));

        for (String line; (line = reader.readLine()) != null; ) {
            int i = line.indexOf(".");
            int i1 = line.indexOf("=");
            List<String> list = map.computeIfAbsent(line.substring(0, i).toUpperCase(), k -> new ArrayList<>());
            list.add(line.substring(i + 1, i1).trim() + "#" + line.substring(i1 + 1).trim());
        }
        in.close();
        reader.close();

    }

    public String resolver(Class<?> clazz, Field field, Object instance) {
        List<String> list = map.get(clazz.getSimpleName().toUpperCase());
        MyValue myValue = field.getAnnotation(MyValue.class);
        String param = myValue.value();
        if (checkFieldName(param)) {
            param = param.substring(param.indexOf("{") + 1, param.indexOf("}"));
        }else {
            throw new MyValueException(clazz.getName()+"."+field.getName()+" syntax error，Please use ${"+field.getName()+"}");
        }
        if (list != null) {
            for (String value : list) {
                try {
                    int i = value.indexOf("#");
                    String propertyName = value.substring(0, i);
                    if (!propertyName.equalsIgnoreCase(param)) {
                        continue;
                    }
                    if (field.getType().getName().endsWith("Date")) {
                        field.set(instance, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value.substring(i + 1, value.length())));
                        continue;
                    }
                    if (field.getType().getName().endsWith("Map")) {
                        //todo
                        continue;
                    }
                    if (field.getType().getName().endsWith("Set")) {
                        //todo
                        continue;
                    }
                    if (field.getType().getName().endsWith("List")) {
                        // todo
                        continue;
                    }
                    field.set(instance, value.substring(i + 1));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    public boolean checkFieldName(String value) {
        return pattern.matcher(value).find();
    }

    public String getFieldName(String value) {
        return value.substring(1, value.length() - 1);
    }

    //public static void main(String[] args) {
    //    String value = "# {1212},# {adfa},#{dfadf}";
    //
    //    Pattern pattern = Pattern.compile(regex);
    //    Matcher match2 = pattern.matcher(value);
    //    while (match2.find()) {
    //        System.out.println(match2.group());
    //    }
    //}
}
