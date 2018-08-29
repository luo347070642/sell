package com.lpy.service.impl;

import com.lpy.dto.OrderDTO;
import com.lpy.service.OrderService;
import com.lpy.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author: 罗鹏远
 * @description:
 * @Date: created in 23:40 2018/8/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PayServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @Test
    public void create() {
        OrderDTO orderDTO = orderService.findOne("1535297183759931414");
        payService.create(orderDTO);
    }

    @Test
    public void refund(){
        OrderDTO orderDTO = orderService.findOne("1535297183759931414");
        payService.refund(orderDTO);
    }
}