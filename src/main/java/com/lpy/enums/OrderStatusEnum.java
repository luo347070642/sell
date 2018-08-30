package com.lpy.enums;

import lombok.Getter;

/**
 * @Author: 罗鹏远
 * @description: 订单状态
 * @Date: created in 20:26 2018/8/16
 */
@Getter
public enum OrderStatusEnum implements CodeEnum {
    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CANCEL(2,"已取消")
    ;

    private Integer code;
    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
