package com.lpy.service;

import com.lpy.dto.OrderDTO;

/**
 * @Author: 罗鹏远
 * @description: 推送消息
 * @Date: created in 17:30 2018/9/9
 */
public interface PushMessageService {

    /**
     * 订单状态变更消息
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);
}
