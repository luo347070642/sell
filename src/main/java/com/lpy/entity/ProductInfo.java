package com.lpy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lpy.enums.ProductStatusEnum;
import com.lpy.util.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: 罗鹏远
 * @description: 商品
 * @Date: created in 19:09 2018/8/15
 */
@Entity
@Data
@DynamicInsert
@DynamicUpdate
public class ProductInfo {

    /** 商品id */
    @Id
    private String productId;

    /** 商品名称 */
    private String productName;

    /** 商品价格 */
    private BigDecimal productPrice;

    /** 商品库存 */
    private Integer productStock;

    /** 商品简介 */
    private String productDescription;

    /** 商品图片 */
    private String productIcon;

    /** 商品状态,0正常1下架 */
    private Integer productStatus = ProductStatusEnum.UP.getCode();

    /** 商品类型 */
    private Integer categoryType;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    public ProductInfo() {
    }

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getByCode(productStatus,ProductStatusEnum.class);
    }
}
