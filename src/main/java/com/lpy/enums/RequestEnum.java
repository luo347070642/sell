package com.lpy.enums;

import lombok.Getter;

/**
 * @Author: 罗鹏远
 * @description: Exception状态
 * @Date: created in 22:24 2018/8/16
 */
@Getter
public enum RequestEnum {
    PARAM_ERROR(1,"参数不正确"),

    PRODUCT_NOT_EXIST(10,"商品不存在"),

    PRODUCT_STOCK_ERROR(11,"库存不足"),

    ORDER_NOT_EXIST(12,"订单不存在"),

    ORDER_DETAIL_NOT_EXIST(13,"订单详情不存在"),

    ORDER_STATUS_ERROR(14,"订单状态不正确"),

    ORDER_UPDATE_FAIL(15,"订单更新失败"),

    ORDER_DETAIL_EMPTY(16,"订单详情为空"),

    ORDER_PAY_STATUS_ERROR(17,"订单支付状态不正确"),

    CART_EMPTY(18,"购物车为空"),

    ORDER_OWNER_ERROR(19,"该订单不属于当前用户"),

    WECHAT_MP_ERROR(20,"微信公众号方面错误")
    ;
    private Integer code;
    private String msg;

    RequestEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}