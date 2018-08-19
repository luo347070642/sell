package com.lpy.dao;

import com.lpy.entity.ProductInfo;
import com.lpy.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: 罗鹏远
 * @description:
 * @Date: created in 19:23 2018/8/15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDaoTest {
    @Autowired
    private ProductInfoDao productInfoDao;

    @Test
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1");
        productInfo.setProductName("蛋炒饭");
        productInfo.setProductPrice(new BigDecimal(15.9));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("有蛋有饭");
        productInfo.setProductIcon("http://xxx.jpg");
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        productInfo.setCategoryType(1);
        ProductInfo result = productInfoDao.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> list = productInfoDao.findByProductStatus(0);
        Assert.assertNotEquals(0,list.size());
    }
}