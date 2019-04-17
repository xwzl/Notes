package com.java.frame.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.java.frame.model.User;

import java.util.List;

/**
 * 封装的GSON解析工具类，提供泛型参数
 */
public class GsonUtil {
    /**
     * 将Json数据解析成相应的映射对象
     */

    public static <T> T parseJsonWithGson(String jsonData, Class<T> type) {
        Gson gson = new Gson();
        T result = gson.fromJson(jsonData, type);
        return result;
    }

    /**
     * 将Json数组解析成相应的映射对象列表
     */
    public static <T> List<T> parseJsonArrayWithGson(String jsonData,
                                                     Class<T> type) {
        Gson gson = new Gson();
        List<T> result = gson.fromJson(jsonData, new TypeToken<List<T>>() {
        }.getType());
        return result;
    }

    public static void main(String[] args) {
        String s1 = "{\"username\":\"test\"}";
        System.out.println(s1);
        //GsonUtil.parseJsonArrayWithGson(s, User.class);
        Gson gson = new Gson();
        User user = new User();
        user.setUsername("11");
        String s = gson.toJson(user);
        User user1 = GsonUtil.parseJsonWithGson(s1, User.class);
        System.out.println(s);
    }
}