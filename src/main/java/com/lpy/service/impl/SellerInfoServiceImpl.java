package com.lpy.service.impl;

import com.lpy.dao.SellerInfoDao;
import com.lpy.entity.SellerInfo;
import com.lpy.service.SellerInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 罗鹏远
 * @description:
 * @Date: created in 22:32 2018/9/5
 */
@Service
@Slf4j
public class SellerInfoServiceImpl implements SellerInfoService {

    @Autowired
    private SellerInfoDao sellerInfoDao;

    @Override
    public SellerInfo findByOpenid(String openid) {
        return sellerInfoDao.findByOpenid(openid);
    }
}
