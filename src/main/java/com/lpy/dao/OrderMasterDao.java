package com.lpy.dao;

import com.lpy.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: 罗鹏远
 * @description: 订单
 * @Date: created in 20:25 2018/8/16
 */
public interface OrderMasterDao extends JpaRepository<OrderMaster,String> {

    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

}
