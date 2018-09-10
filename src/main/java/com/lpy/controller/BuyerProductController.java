package com.lpy.controller;

import com.lpy.entity.ProductCategory;
import com.lpy.entity.ProductInfo;
import com.lpy.service.CategoryService;
import com.lpy.service.ProductService;
import com.lpy.util.ResultVoUtil;
import com.lpy.vo.ProductInfoVo;
import com.lpy.vo.ProductVo;
import com.lpy.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 罗鹏远
 * @description: 买家商品
 * @Date: created in 20:24 2018/8/15
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productInfoService;

    @Autowired
    private CategoryService productCategoryService;

    /**
     * 查询所有上架商品信息
     * @return
     */
    @GetMapping("/list")
    @Cacheable(cacheNames = "product", key = "123") //新增redis缓存
    public ResultVo list() {
        // 1.查询所有上架商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        // 2.查询类目（一次性查询）
        List<Integer> categoryTypeList = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);
        // 3.数据拼装
        List<ProductVo> productVoList = new ArrayList<ProductVo>();
        for ( ProductCategory productCategory : productCategoryList) {
            ProductVo productVo = new ProductVo();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryType(productCategory.getCategoryType());
            //拼装商品详情信息
            List<ProductInfoVo> productInfoVoList = new ArrayList<ProductInfoVo>();
            for (ProductInfo productInfo : productInfoList) {
                if(productInfo.getCategoryType().equals(productVo.getCategoryType())){
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo,productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }
            productVo.setProductInfoVoList(productInfoVoList);
            productVoList.add(productVo);
        }
        return ResultVoUtil.success(productVoList);
    }
}
