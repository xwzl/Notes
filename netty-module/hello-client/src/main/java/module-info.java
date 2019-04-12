/**
 * @author maple 2018.09.27 上午11:43
 */
module hello.client {
    //导入需要依赖的模块名
    requires hello.api;
    requires io.netty.all;
    requires slf4j.api;
    requires hello.common;

    // 导入所有的依赖
    requires gson;
    //使用某一个具体的类
    uses com.google.gson.Gson;
}