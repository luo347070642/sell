package com.lpy.vo.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Author: 罗鹏远
 * @description: 订单表单实体
 * @Date: created in 22:35 2018/8/17
 */
@Data
public class OrderForm {

    /** 买家姓名 */
    @NotEmpty(message = "姓名必填")
    private String name;

    /** 买家电话 */
    @NotEmpty(message = "电话必填")
    private String phone;

    /** 买家地址 */
    @NotEmpty(message = "地址必填")
    private String address;

    /** 买家微信id */
    @NotEmpty(message = "openid必填")
    private String openid;

    /** 购物车 */
    @NotEmpty(message = "购物车不能为空")
    private String items;
}
