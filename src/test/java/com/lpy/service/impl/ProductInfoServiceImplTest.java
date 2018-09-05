package com.lpy.service.impl;

import com.lpy.entity.ProductInfo;
import com.lpy.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: 罗鹏远
 * @description:
 * @Date: created in 19:52 2018/8/15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductServiceImpl productInfoService;

    @Test
    public void findById() {
        ProductInfo productInfo = productInfoService.findOne("111");
        if(productInfo != null){
        Assert.assertEquals("1",productInfo.getProductId());
        }
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> list = productInfoService.findUpAll();
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void findAll() {
        PageRequest pageRequest = PageRequest.of(0,2);
        Page<ProductInfo> productInfoPage =  productInfoService.findAll(pageRequest);
        Assert.assertNotEquals(0,productInfoPage.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("12");
        productInfo.setProductName("牛肉炒饭");
        productInfo.setProductPrice(new BigDecimal(15.9));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("有蛋有饭还有牛肉哟");
        productInfo.setProductIcon("http://xxx.jpg");
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfo.setCategoryType(2);
        ProductInfo result = productInfoService.save(productInfo);
        Assert.assertNotNull(result);
    }
    @Test
    public void increaseStock() {
    }

    @Test
    public void decreaseStock() {
    }

    @Test
    public void onSale() {
    }

    @Test
    public void offSale() {
    }
}