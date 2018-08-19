package com.lpy.dao;

import com.lpy.entity.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: 罗鹏远
 * @description:
 * @Date: created in 23:20 2018/8/14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryDaoTest {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void findOneTest(){
        ProductCategory productCategory = productCategoryDao.findById(1).get();
        System.out.println(productCategory.toString());
    }

    @Test
    @Transactional
    public void saveTest(){
        ProductCategory productCategory = new ProductCategory("222",4);
        ProductCategory result = productCategoryDao.save(productCategory);
        Assert.assertNotNull(result);
    }

    @Test
    public void updateTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(2);
        productCategory.setCategoryName("111");
        productCategory.setCategoryType(2);
        productCategoryDao.save(productCategory);
    }
    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(1,2,3,4);
        List<ProductCategory> result = productCategoryDao.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,result.size());
    }
}