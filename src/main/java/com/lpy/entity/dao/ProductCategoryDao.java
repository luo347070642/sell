package com.lpy.entity.dao;

import com.lpy.entity.mapper.ProductCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @Author: 罗鹏远
 * @description:
 * @Date: created in 19:49 2018/9/9
 */
public class ProductCategoryDao {
    @Autowired
    ProductCategoryMapper mapper;

    public int insertByMap(Map<String, Object> map) {
        return mapper.insertByMap(map);
    }
}
