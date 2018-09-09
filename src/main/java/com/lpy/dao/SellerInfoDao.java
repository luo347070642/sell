package com.lpy.dao;

import com.lpy.entity.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: 罗鹏远
 * @description:
 * @Date: created in 22:30 2018/9/5
 */
public interface SellerInfoDao extends JpaRepository<SellerInfo,String> {
    SellerInfo findByOpenid(String openid);
}
