package com.java;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author xuweizhi
 * @date 2019/02/22 16:25
 */
@SpringBootApplication
@ServletComponentScan
@MapperScan(basePackages = "com.java.mybatis.mapper")
public class Io {

    public static void main(String[] args) {
        SpringApplication.run(Io.class, args);
    }

}
