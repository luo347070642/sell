package com.lpy.exception;

import com.lpy.enums.RequestEnum;

/**
 * @Author: 罗鹏远
 * @description: 自定义异常类
 * @Date: created in 22:22 2018/8/16
 */
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(RequestEnum requestEnum) {
        super(requestEnum.getMsg());
        this.code=requestEnum.getCode();
    }

    public SellException(Integer code, String msg) {
        super(msg);
        this.code=code;
    }
}
