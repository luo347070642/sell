package com.lpy.service;

import com.lpy.dto.OrderDTO;

/**
 * @Author: 罗鹏远
 * @description: 买家订单操作
 * @Date: created in 0:16 2018/8/19
 */
public interface BuyerService {
    /** 查询一个订单 */
    OrderDTO findOrderOne(String openid,String orderId);

    /** 取消一个订单 */
    OrderDTO cancelOrderOne(String openid,String orderId);
}
