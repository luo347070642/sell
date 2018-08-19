package com.lpy.dto;

import lombok.Data;

/**
 * @Author: 罗鹏远
 * @description: 购物车数据传输对象
 * @Date: created in 22:55 2018/8/16
 */
@Data
public class CartDTO {
    /** 商品id */
    private String productId;

    /** 商品数量 */
    private Integer productQuantity;

    public CartDTO() {
    }

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
