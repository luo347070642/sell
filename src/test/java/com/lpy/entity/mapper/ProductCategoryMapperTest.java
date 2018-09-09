package com.lpy.entity.mapper;

import com.lpy.entity.ProductCategory;
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
 * @Date: created in 20:21 2018/9/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper categoryMapper;

    @Test
    public void insertByMap() {
    }

    @Test
    public void insertByObject() {
    }

    @Test
    public void findByCategoryType() {
    }

    @Test
    public void findByCategoryName() {
    }

    @Test
    public void updateByCategoryType() {
    }

    @Test
    public void updateByObject() {
    }

    @Test
    public void deleteByCategoryType() {
    }

    @Test
    public void selectByCategoryType() {
        ProductCategory productCategory = categoryMapper.selectByCategoryType(1);
        Assert.assertNotNull(productCategory);
    }
}