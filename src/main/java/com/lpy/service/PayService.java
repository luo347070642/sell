package com.lpy.service;

import com.lpy.dto.OrderDTO;

/**
 * @Author: 罗鹏远
 * @description: 支付
 * @Date: created in 14:47 2018/8/25
 */
public interface PayService {

    void create(OrderDTO orderDTO);
}
