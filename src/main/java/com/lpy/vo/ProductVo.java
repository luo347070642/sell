package com.lpy.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: 罗鹏远
 * @description: 商品（包含类目）
 * @Date: created in 20:52 2018/8/15
 */
@Data
public class ProductVo<T> implements Serializable {

    private static final long serialVersionUID = 8942749810195042499L;

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVo> productInfoVoList;

}
