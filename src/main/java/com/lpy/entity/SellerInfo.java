package com.lpy.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author: 罗鹏远
 * @description: 卖家信息
 * @Date: created in 22:23 2018/9/5
 */
@Entity
@Data
@DynamicInsert
@DynamicUpdate
public class SellerInfo {

    /** 卖家id */
    @Id
    private String id;

    /** 卖家名称 */
    private String username;

    /** 密码 */
    private String password;

    /** 微信openid */
    private String openid;

    public SellerInfo() {
    }
}
