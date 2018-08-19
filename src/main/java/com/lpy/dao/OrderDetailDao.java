package com.lpy.dao;

import com.lpy.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: 罗鹏远
 * @description: 订单详情
 * @Date: created in 20:26 2018/8/16
 */
public interface OrderDetailDao extends JpaRepository<OrderDetail,String> {
    List<OrderDetail> findByOrderId(String orderId);
}
