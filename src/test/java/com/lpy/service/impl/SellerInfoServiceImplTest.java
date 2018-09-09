package com.lpy.service.impl;

import com.lpy.entity.SellerInfo;
import com.lpy.service.SellerInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author: 罗鹏远
 * @description:
 * @Date: created in 22:49 2018/9/6
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
@Slf4j
public class SellerInfoServiceImplTest {
    @Autowired
    private SellerInfoService sellerInfoService;

    private static final String openid="abc";

    @Test
    public void findByOpenid() {
        SellerInfo sellerInfo = sellerInfoService.findByOpenid(openid);
        Assert.assertEquals("abc",sellerInfo.getOpenid());
    }
}