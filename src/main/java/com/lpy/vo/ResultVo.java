package com.lpy.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 罗鹏远
 * @description: http请求返回的最外层对象
 * @Date: created in 20:30 2018/8/15
 */
@Data
public class ResultVo<T> implements Serializable {

    private static final long serialVersionUID = -7455267265518597924L;

    /** 提示码 */
    private Integer code;

    /** 提示信息 */
    private String msg;

    /** 具体内容 */
    private T data;
}
