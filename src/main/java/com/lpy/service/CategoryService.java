package com.lpy.service;

import com.lpy.entity.ProductCategory;

import java.util.List;

/**
 * @Author: 罗鹏远
 * @description: 类目接口
 * @Date: created in 18:48 2018/8/15
 */
public interface CategoryService {

    /**
     * 查询单个类目
     * @param categroryId
     * @return
     */
    ProductCategory findById(Integer categroryId);

    /**
     * 查询所有类目
     * @return
     */
    List<ProductCategory> findAll();

    /**
     * 根据类目类型查询
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    /**
     * 新增类目
     * @param productCategory
     * @return
     */
    ProductCategory save(ProductCategory productCategory);

}
