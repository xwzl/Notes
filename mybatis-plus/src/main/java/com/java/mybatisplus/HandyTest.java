package com.java.mybatisplus;

import com.java.mybatisplus.old.MybatisHandy;

/**
 * @author xuweizhi
 * @date 2019/04/03 19:13
 */
public class HandyTest {

    public static void main(String[] args) {
        MybatisHandy handy = new MybatisHandy();
        //如果是子模块，必须设置
        handy.setChildModule(true);
        handy.setChildModuleName("java");
        handy.setBasePackageName("com.java");
        handy.setAuthor("徐伟智");
       GeneratorUntil.generatorCode(handy);
    }
}
