package com.lpy.enums;

import lombok.Getter;

/**
 * @Author: 罗鹏远
 * @description: 支付状态
 * @Date: created in 20:33 2018/8/16
 */
@Getter
public enum  PayStatusEnum implements CodeEnum {
    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功")
    ;
    private Integer code;
    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
