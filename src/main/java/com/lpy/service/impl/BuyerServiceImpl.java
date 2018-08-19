package com.lpy.service.impl;

import com.lpy.dto.OrderDTO;
import com.lpy.enums.RequestEnum;
import com.lpy.exception.SellException;
import com.lpy.service.BuyerService;
import com.lpy.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 罗鹏远
 * @description: 买家订单操作实现类
 * @Date: created in 0:19 2018/8/19
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrderOne(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if(orderDTO == null){
            log.error("【取消订单】 查不到该订单，orderId={}",orderId);
            throw new SellException(RequestEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid, String orderId){
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            return null;
        }
        if (orderDTO.getBuyerOpenid().equals(openid)){
            log.error("【查询订单】 订单中的openid不一致，openid={},orderDTO={}",openid,orderDTO);
            throw new SellException(RequestEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
