package com.lpy.controller;

import com.lpy.entity.ProductCategory;
import com.lpy.entity.ProductInfo;
import com.lpy.exception.SellException;
import com.lpy.service.CategoryService;
import com.lpy.service.ProductService;
import com.lpy.util.KeyUtil;
import com.lpy.util.PageUtil;
import com.lpy.vo.form.ProductForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @Author: 罗鹏远
 * @description: 卖家商品
 * @Date: created in 19:35 2018/8/30
 */
@Controller
@RequestMapping("/seller/product")
@Slf4j
public class SellerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 查询商品列表
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String,Object> map){
        Page<ProductInfo> productInfoPage = productService.findAll(PageRequest.of(page-1,size));
        PageUtil pageUtil = new PageUtil((int) productInfoPage.getTotalElements(),productInfoPage.getTotalPages(),page,size);
        map.put("productInfoPage",productInfoPage);
        map.put("pageUtil",pageUtil);
        return new ModelAndView("product/list");
    }

    /**
     * 商品上架
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/on_sale")
    public ModelAndView onSale(@RequestParam(value = "productId") String productId,
                             Map<String,Object> map){
        try {
            productService.onSale(productId);
        } catch (Exception e) {
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }

    /**
     * 商品下架
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/off_sale")
    public ModelAndView offSale(@RequestParam(value = "productId") String productId,
                                Map<String,Object> map){
        try {
            productService.offSale(productId);
        } catch (Exception e) {
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }

    /**
     * 根据商品id获取商品信息
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId",required = false) String productId,
                                Map<String,Object> map){
        if(!StringUtils.isEmpty(productId)){
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo",productInfo);
        }
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("product/index",map);
    }

    @PostMapping("/save")
//    @CachePut(cacheNames = "product", key = "123") //更新redis缓存 使用前提：缓存数据格式要一致
    @CacheEvict(cacheNames = "product", key = "123") //清除redis缓存
    public ModelAndView save(@Valid ProductForm productForm, BindingResult bindingResult, Map<String, Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        ProductInfo productInfo = new ProductInfo();
        try {
            //如果商品id不为空，则进行更新操作
            if (!StringUtils.isEmpty(productForm.getProductId())){
                productInfo = productService.findOne(productForm.getProductId());
            }else{
                productForm.setProductId(KeyUtil.getUniqueKey());
            }
            BeanUtils.copyProperties(productForm,productInfo);
            productService.save(productInfo);
        }catch (SellException e){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }
}
