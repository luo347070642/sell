package com.lpy.service;

import com.lpy.entity.SellerInfo;

/**
 * @Author: 罗鹏远
 * @description:
 * @Date: created in 22:32 2018/9/5
 */
public interface SellerInfoService {
    /**
     * 通过openid查询卖家端信息
     * @param openid
     * @return
     */
    SellerInfo findByOpenid(String openid);
}
