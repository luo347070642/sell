package com.lpy.enums;

import lombok.Getter;

/**
 * @Author: 罗鹏远
 * @description: 商品状态
 * @Date: created in 19:46 2018/8/15
 */
@Getter
public enum ProductStatusEnum implements CodeEnum {
    UP(0,"在架"),
    DOWN(1,"下架")
    ;

    private Integer code;
    private String msg;

    ProductStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
