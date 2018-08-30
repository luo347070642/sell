package com.lpy.service.impl;

import com.lpy.dto.OrderDTO;
import com.lpy.entity.OrderDetail;
import com.lpy.enums.OrderStatusEnum;
import com.lpy.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: 罗鹏远
 * @description:
 * @Date: created in 23:16 2018/8/16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private String buyer_openid="110110";
    private String orderid="1534434308140554371";

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("李四");
        orderDTO.setBuyerAddress("上海");
        orderDTO.setBuyerPhone("1235646");
        orderDTO.setBuyerOpenid(buyer_openid);
        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("1");
        o1.setProductQuantity(4);
        orderDetailList.add(o1);
        OrderDetail o2 = new OrderDetail();
        o2.setProductId("12");
        o2.setProductQuantity(40);
        orderDetailList.add(o2);

        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO result = orderService.create(orderDTO);
        log.info("【创建订单】result:{}",result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() {
        OrderDTO orderDTO = orderService.findOne(orderid);
        log.info("【查询单个订单】result:{}",orderDTO);
        Assert.assertNotNull(orderDTO);
    }

    @Test
    public void findList() {
        Page<OrderDTO> orderDTOPage = orderService.findList(buyer_openid,PageRequest.of(1,3));
        Assert.assertNotEquals(0,orderDTOPage.getTotalElements());
    }

    @Test
    public void finish() {
        OrderDTO orderDTO = orderService.findOne(orderid);
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),result.getBuyerStatus());
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findOne("1535214602337151039");
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getBuyerStatus());
    }

    @Test
    public void paid() {
        OrderDTO orderDTO = orderService.findOne(orderid);
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),result.getPayStatus());
    }

    @Test
    public void findAll(){
        Page<OrderDTO> orderDTOPage = orderService.findList(PageRequest.of(1,3));
//        Assert.assertNotEquals(0,orderDTOPage.getTotalElements());
        Assert.assertTrue("查询所有订单信息",orderDTOPage.getTotalPages() > 0);
    }
}