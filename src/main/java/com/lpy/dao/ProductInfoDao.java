package com.lpy.dao;

import com.lpy.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: 罗鹏远
 * @description: 商品
 * @Date: created in 19:19 2018/8/15
 */
public interface ProductInfoDao extends JpaRepository<ProductInfo,String> {
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
