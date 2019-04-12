package com.java.jvm.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * @author xuweizhi
 * @date 2019/02/22 17:28
 * @description
 */
public class GsonTypeToken {

    /**
     * 例如：JSON字符串数组：["Android","Java","PHP"]
     * 当要通过Gson解析这个json时，一般有两种方式：使用数组，使用List；而List对于增删都是比较方便的，所以实际使用是还是List比较多
     * 数组比较简单：
     * <p>
     * Gson gson = new Gson();
     * String jsonArray = "[\"Android\",\"Java\",\"PHP\"]";
     * String[] strings = gson.fromJson(jsonArray, String[].class);
     *
     * 对于List将上面的代码中的 String[].class 直接改为 List<String>.class 是不行的，对于Java来说List<String> 和List<User>
     * 这俩个的字节码文件只一个那就是List.class，这是Java泛型使用时要注意的问题 泛型擦除为了解决的上面的问题，Gson提供了TypeToken来
     * 实现对泛型的支持，所以将以上的数据解析为List<String>时需要这样写
     */
    public static void main(String[] args) {
        Gson gson = new Gson();
        String jsonArray = "[\"Android\",\"Java\",\"PHP\"]";
        String[] strings = gson.fromJson(jsonArray, String[].class);
        // TypeToken的构造方法是protected修饰的,所以上面才会写成new TypeToken<List<String>>() {}.getType()
        // 而不是 new TypeToken<List<String>>().getType()
        List<String> stringList = gson.fromJson(jsonArray, new TypeToken<List<String>>() {}.getType());
    }


}
