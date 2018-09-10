package com.lpy.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: 罗鹏远
 * @description: 商品详情
 * @Date: created in 20:57 2018/8/15
 */
@Data
public class ProductInfoVo implements Serializable {

    private static final long serialVersionUID = -6122043858412535515L;

    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;
}
