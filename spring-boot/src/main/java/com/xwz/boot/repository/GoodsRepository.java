package com.xwz.boot.repository;

import com.xwz.boot.entity.GoodsInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @author xuweizhi
 */
@Component
public interface GoodsRepository extends ElasticsearchRepository<GoodsInfo, Long> {

}