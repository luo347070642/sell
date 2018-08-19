package com.lpy.enums;

import lombok.Getter;

/**
 * @Author: 罗鹏远
 * @description: 商品状态
 * @Date: created in 19:46 2018/8/15
 */
@Getter
public enum ProductStatusEnum {
    UP(0,"在架"),
    DOWN(1,"下架")
    ;

    private Integer code;
    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
