package com.lpy.dao;

import com.lpy.entity.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: 罗鹏远
 * @description:
 * @Date: created in 21:40 2018/8/16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {

    @Autowired
    private OrderDetailDao orderDetailDao;

    public String orderId="1243";

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("2");
        orderDetail.setOrderId("1243");
        orderDetail.setProductIcon("http://xxx.jpg");
        orderDetail.setProductId("1");
        orderDetail.setProductName("牛肉饭");
        orderDetail.setProductPrice(new BigDecimal(15.90));
        orderDetail.setProductQuantity(4);
        OrderDetail result = orderDetailDao.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrderIdTest() {
        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderId);
        Assert.assertNotEquals(0,orderDetailList.size());
    }
}