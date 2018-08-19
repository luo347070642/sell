package com.lpy.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @Author: 罗鹏远
 * @description: 商品
 * @Date: created in 19:09 2018/8/15
 */
@Entity
@Data
public class ProductInfo {
    /** 商品id */
    @Id
    private String productId;
    /** 商品名称 */
    private String productName;
    /** 商品价格 */
    private BigDecimal productPrice;
    /** 商品库存 */
    private Integer productStock;
    /** 商品简介 */
    private String productDescription;
    /** 商品图片 */
    private String productIcon;
    /** 商品状态,0正常1下架 */
    private Integer productStatus;
    /** 商品类型 */
    private Integer categoryType;

    public ProductInfo() {
    }

    public ProductInfo(String productId, String productName, BigDecimal productPrice, Integer productStock, String productDescription, String productIcon, Integer productStatus, Integer categoryType) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productDescription = productDescription;
        this.productIcon = productIcon;
        this.productStatus = productStatus;
        this.categoryType = categoryType;
    }
}
