package com.java.base.array;

import com.java.mybatis.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuweizhi
 * @date 2019/04/09 18:22
 */
public class HashMapOrder {

    public static void main(String[] args) {

        Map<String, User> map = null;
        Map<String, Map<String, User>> maps = new HashMap<>();

        if (map == null) {
            map = new HashMap<>();
            maps.put("1", map);
        }

        //后添加居然能改变maps里面的值
        map.put("1", new User());

        System.out.println(maps);

        Map<String, User> userMap = maps.get("1");

        // 这样也能改变map里面的值，说明存的的是一份引用
        if (userMap != null) {
            userMap.put("2", new User());
        }

        // 完全指向同一块内存区域
        User user = userMap.get("1");
        user.setUId(111);

        System.out.println(maps);
    }
}
