package com.lpy.dao;

import com.lpy.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: 罗鹏远
 * @description: 商品类目
 * @Date: created in 23:19 2018/8/14
 */
public interface ProductCategoryDao extends JpaRepository<ProductCategory,Integer> {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
