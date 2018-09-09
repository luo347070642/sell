package com.lpy.exception;

import com.lpy.enums.ResultEnum;
import lombok.Getter;

/**
 * @Author: 罗鹏远
 * @description: 自定义异常类
 * @Date: created in 22:22 2018/8/16
 */
@Getter
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code= resultEnum.getCode();
    }

    public SellException(Integer code, String msg) {
        super(msg);
        this.code=code;
    }
}
