package com.lpy.dao;

import com.lpy.entity.OrderMaster;
import com.lpy.enums.OrderStatusEnum;
import com.lpy.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @Author: 罗鹏远
 * @description:
 * @Date: created in 21:12 2018/8/16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {

    @Autowired
    private OrderMasterDao orderMasterDao;

    private String openId = "321";
    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("12434");
        orderMaster.setBuyerName("张三");
        orderMaster.setBuyerAddress("asdf1");
        orderMaster.setBuyerAmount(new BigDecimal(26));
        orderMaster.setBuyerOpenid(openId);
        orderMaster.setBuyerPhone("458756123");
        OrderMaster result = orderMasterDao.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenIdTest() {
        Pageable pageable = PageRequest.of(0,4);
        Page<OrderMaster> orderMasterPage = orderMasterDao.findByBuyerOpenid(openId,pageable);
        Assert.assertNotEquals(0,orderMasterPage.getTotalElements());
    }
}