package com.lpy.service;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import com.lpy.dto.OrderDTO;

/**
 * @Author: 罗鹏远
 * @description: 支付
 * @Date: created in 14:47 2018/8/25
 */
public interface PayService {

    /**
     * 发起支付，创建订单
     * @param orderDTO
     * @return
     */
    PayResponse create(OrderDTO orderDTO);

    /**
     * 微信异步通知
     * @param nodiryData
     * @return
     */
    PayResponse notify(String nodiryData);

    /**
     * 退款
     * @param orderDTO
     */
    RefundResponse refund(OrderDTO orderDTO);
}
