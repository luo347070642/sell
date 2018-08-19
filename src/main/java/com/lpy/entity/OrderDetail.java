package com.lpy.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @Author: 罗鹏远
 * @description: 订单详情
 * @Date: created in 20:12 2018/8/16
 */
@Entity
@Data
public class OrderDetail {

    /** 订单详情id */
    @Id
    private String detailId;

    /** 订单id */
    private String orderId;

    /** 商品id */
    private String productId;

    /** 商品名称 */
    private String productName;

    /** 商品单价 */
    private BigDecimal productPrice;

    /** 商品数量 */
    private Integer productQuantity;

    /** 商品图片 */
    private String productIcon;

    public OrderDetail() {
    }

    public OrderDetail(String detailId, String orderId, String productId, String productName, BigDecimal productPrice, Integer productQuantity, String productIcon) {
        this.detailId = detailId;
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productIcon = productIcon;
    }
}
