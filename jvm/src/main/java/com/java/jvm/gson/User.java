package com.java.jvm.gson;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author xuweizhi
 * @date 2019/02/22 17:28
 * @description 对象转换为Json字符串
 * @SerializedName("phone_number")用法
 */
@Data
public class User {

    /**
     * 也许会出现这样的需求，在将某个对象序列化时，对象中的某些变量是不需要的。有可能在反序列化某个JSON字符串时，
     * 某些变量的值也是不需要的。这时就可以使用Gson提供的@Expose注解方法。使用方法如下：
     * public class User {
     *     @Expose()
     *     String name; // 参与序列化/反序列化
     *
     *     @Expose(serialize = false, deserialize = false)
     *     String email; // 不参与序列化，也不参与反序列化
     *
     *     @Expose(serialize = false)
     *     int age; // 只参与反序列化
     *
     *     @Expose(deserialize = false)
     *     boolean isDeveloper; // 只参与序列化
     * }
     *
     * 使用这个方法，可以非常灵活地控制对象的某个/某些变量参不参与序列化/反序列化。
     *
     * 然而！ 要使用这个注解来控制序列化/反序列化，就不能使用默认的Gson对象，新建Gson对象的方法如下：
     *
     * GsonBuilder builder = new GsonBuilder();
     * builder.excludeFieldsWithoutExposeAnnotation();
     * Gson gson = builder.create();
     *
     * 另一个选择，transient关键字 ，使用这个关键字，可以直接让变量不参与序列化/反序列化，如下：
     *
     * public class User {
     *     String name;
     *     String email;
     *     int age;
     *     boolean transient isDeveloper; //不参与序列化/反序列化
     *
     */
    @Expose()
    public String name;

    public int age;

    /**
     * 指定转换为字符后为指定Key
     * <p>
     * Gson在序列化和反序列化时需要使用反射，一般各类库都将注解放到annotations包下，打开源码在com.google.gson包下有一个annotations，
     * 里面有一个SerializedName的注解类。对于json中email_address这个属性对应POJO的属性则变成：
     * <p>
     * 为POJO字段提供备选属性名：SerializedName注解提供了两个属性，上面用到了其中一个，别外还有一个属性alternate，接收一个String数组
     * <p>
     * 当多种情况同时出时，以最后一个出现的值为准。
     */
    @SerializedName(value = "emailAddress", alternate = {"email", "email_address"})
    public String emailAddress;

    public User() {
    }

    public User(String name, int age, String emailAddress) {
        this.name = name;
        this.age = age;
        this.emailAddress = emailAddress;
    }

    public static void main(String[] args) {

        User user = new User("AA", 1, "CC");
        Gson gson = new Gson();
        String s = gson.toJson(user);
        System.out.println(s);

        //当多种情况同时出时，以最后一个出现的值为准。
        String json = "{\"name\":\"张三kidou\",\"age\":24,\"emailAddress\":\"zhangsan@ceshi.com\",\"email\":\"zhangsan_2@ceshi.com\",\"email_address\":\"zhangsan_3@ceshi.com\"}";
        User user1 = gson.fromJson(json, User.class);
        // zhangsan_3@controller.com
        System.out.println(user1.emailAddress);
    }

}
