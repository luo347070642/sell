package com.lpy.dao;

import com.lpy.entity.SellerInfo;
import com.lpy.util.JsonUtil;
import com.lpy.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @Author: 罗鹏远
 * @description:
 * @Date: created in 19:47 2018/9/6
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
@Slf4j
public class SellerInfoDaoTest {

    @Autowired
    private SellerInfoDao sellerInfoDao;

    @Test
    public void save(){
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setId(KeyUtil.getUniqueKey());
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("admin");
        sellerInfo.setOpenid("abc");
        SellerInfo result = sellerInfoDao.save(sellerInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOpenid() {
        SellerInfo sellerInfo = sellerInfoDao.findByOpenid("abc");
        Assert.assertEquals("abc",sellerInfo.getOpenid());
    }

}