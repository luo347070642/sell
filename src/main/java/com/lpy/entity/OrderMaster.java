package com.lpy.entity;

import com.lpy.enums.OrderStatusEnum;
import com.lpy.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: 罗鹏远
 * @description: 买家订单
 * @Date: created in 20:12 2018/8/16
 */
@Entity
@Data
@DynamicInsert
@DynamicUpdate
public class OrderMaster {

    /** 订单id */
    @Id
    private String orderId;

    /** 买家姓名 */
    private String buyerName;

    /** 买家电话 */
    private String buyerPhone;

    /** 买家地址 */
    private String buyerAddress;

    /** 买家微信id */
    private String buyerOpenid;

    /** 订单总金额 */
    private BigDecimal buyerAmount;

    /** 订单状态，默认0新订单 */
    private Integer buyerStatus = OrderStatusEnum.NEW.getCode();

    /** 订单支付状态，默认0未支付 */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    public OrderMaster() {
    }
}
