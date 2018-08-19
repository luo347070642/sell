package com.lpy.service.impl;

import com.lpy.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: 罗鹏远
 * @description:
 * @Date: created in 18:58 2018/8/15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {
    @Autowired
    private CategoryServiceImpl productCategoryService;

    @Test
    public void findById() {
        ProductCategory productCategory = productCategoryService.findById(1);
        Assert.assertEquals(new Integer(1),productCategory.getCategoryId());
    }

    @Test
    public void findAll() {
        List<ProductCategory> list = productCategoryService.findAll();
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> list = productCategoryService.findByCategoryTypeIn(Arrays.asList(1,2,3,4));
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory("吃货专享",10);
        ProductCategory result = productCategoryService.save(productCategory);
        Assert.assertNotNull(result);
    }
}