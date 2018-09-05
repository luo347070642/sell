package com.lpy.controller;

import com.lpy.entity.ProductCategory;
import com.lpy.exception.SellException;
import com.lpy.service.CategoryService;
import com.lpy.util.KeyUtil;
import com.lpy.util.PageUtil;
import com.lpy.vo.form.CategoryForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @description: 卖家类目
 * @Date: created in 20:47 2018/9/5
 */
@Controller
@RequestMapping("/seller/category")
@Slf4j
public class SellerCategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 类目列表
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(Map<String,Object> map){
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList",categoryList);
        return new ModelAndView("category/list",map);
    }

    /**
     * 根据类目id获取类目信息
     * @param categoryId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId",required = false) Integer categoryId,
                              Map<String,Object> map){
        if (categoryId != null){
            ProductCategory category = categoryService.findById(categoryId);
            map.put("category",category);
        }
        return new ModelAndView("category/index");
    }

    /**
     * 新增类目
     * @param categoryForm
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm categoryForm, BindingResult bindingResult, Map<String, Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/category/list");
            return new ModelAndView("common/error",map);
        }
        ProductCategory productCategory = new ProductCategory();
        try {
            if (!StringUtils.isEmpty(categoryForm.getCategoryId())){
                productCategory = categoryService.findById(categoryForm.getCategoryId());
            }
            BeanUtils.copyProperties(categoryForm,productCategory);
            categoryService.save(productCategory);
        } catch (SellException e) {
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/category/list");
            return new ModelAndView("common/error",map);
        }
        map.put("url","/sell/seller/category/list");
        return new ModelAndView("common/success",map);
    }
}
