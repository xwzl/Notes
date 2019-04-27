package com.xwz.boot.controller;

import com.xwz.boot.entity.Item;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ElasticSearch 全文检索测试
 *
 * @author xuweizhi
 * @date 2019/04/27 23:18
 */
@RestController
@RequestMapping("/item")
@Api
public class ItemController {

    @Autowired
    private ElasticsearchTemplate template;

    /**
     * 创建索引，会根据Item类的@Document注解信息来创建,仅用来创建索引
     * 其实项目启动的时候，会自动创建索引
     */
    @GetMapping()
    public void createItemIndex() {
        template.createIndex(Item.class);
    }

    @GetMapping()
    public void deleteItemIndex() {
        template.deleteIndex(Item.class);
    }


}
