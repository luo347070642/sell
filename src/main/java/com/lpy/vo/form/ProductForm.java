package com.lpy.vo.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: 罗鹏远
 * @description:
 * @Date: created in 21:49 2018/9/4
 */
@Data
public class ProductForm {

    /** 商品id */
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

    /** 商品类型 */
    private Integer categoryType;

}
