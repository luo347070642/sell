package com.lpy.vo.form;

import lombok.Data;

/**
 * @Author: 罗鹏远
 * @description: 类目
 * @Date: created in 22:31 2018/8/14
 * product_category
 */
@Data
public class CategoryForm {
    /** 类目id */
    private Integer categoryId;
    /** 类目名称 */
    private String categoryName;
    /** 类目编号 */
    private Integer categoryType;

}
