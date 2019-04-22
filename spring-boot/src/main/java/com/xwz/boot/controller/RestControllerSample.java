package com.xwz.boot.controller;

import com.xwz.boot.model.Book;
import com.xwz.boot.model.Goods;
import com.xwz.boot.model.ValueSample;
import com.xwz.boot.property.ReadProperties;
import com.xwz.boot.until.LogUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuweizhi
 * @date 2019/04/22 14:10
 */
@RestController
@RequestMapping("/rest")
public class RestControllerSample {

    private static final Logger logger = LoggerFactory.getLogger(RestControllerSample.class);

    /**
     * 条件注入判断
     */
    private final Book book;

    /**
     * 条件注入判断
     */
    private final Goods goods;

    /**
     * 配置文件注入
     */
    private final ValueSample valueSample;

    /**
     * 读取配置文件中注入Redis 类中的信息
     */
    private final ReadProperties readProperties;

    public RestControllerSample(Book book, Goods goods, ValueSample valueSample, ReadProperties readProperties) {
        this.book = book;
        this.goods = goods;
        this.valueSample = valueSample;
        this.readProperties = readProperties;
    }

    @GetMapping("/condition")
    public Book getBook() {
        logger.info("我们都是好孩子!");
        return book;
    }


    @GetMapping("/goods")
    public Goods getGoods() {
        return goods;
    }

    @GetMapping("/value")
    public ValueSample getValue() {
        return valueSample;
    }


    @GetMapping("/redis")
    public void getRedis() {
        readProperties.read();
    }

    /**
     * 日志打印，必须配合 log4j2.yml 使用
     */
    @GetMapping("/log")
    public String log() throws Exception {
        Logger log = LogUtils.getExceptionLogger();
        Logger log1 = LogUtils.getBusinessLogger();
        Logger log2 = LogUtils.getDBLogger();
        log.error("getExceptionLogger===日志测试");
        log1.info("getBussinessLogger===日志测试");
        log2.debug("getDBLogger===日志测试");

        logger.trace("I am trace log.");
        logger.debug("I am debug log.");
        logger.warn("I am warn log.");
        logger.error("I am error log.");

        return "helloworld";
    }
}
