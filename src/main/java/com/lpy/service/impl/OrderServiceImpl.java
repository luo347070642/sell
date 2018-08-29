package com.lpy.service.impl;

import com.lpy.service.PayService;
import com.lpy.util.converter.OrderMarster2OrderDTOConverter;
import com.lpy.dao.OrderDetailDao;
import com.lpy.dao.OrderMasterDao;
import com.lpy.dto.CartDTO;
import com.lpy.dto.OrderDTO;
import com.lpy.entity.OrderDetail;
import com.lpy.entity.OrderMaster;
import com.lpy.entity.ProductInfo;
import com.lpy.enums.OrderStatusEnum;
import com.lpy.enums.PayStatusEnum;
import com.lpy.enums.RequestEnum;
import com.lpy.exception.SellException;
import com.lpy.service.OrderService;
import com.lpy.service.ProductService;
import com.lpy.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 罗鹏远
 * @description: 订单实现类
 * @Date: created in 22:11 2018/8/16
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Autowired
    private PayService payService;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.getUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        // 1.查询商品（数量，价格）
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if(productInfo == null){
                throw new SellException(RequestEnum.PRODUCT_NOT_EXIST);
            }
            // 2.计算订单总价 注意商品价格必须用数据库查出来的价格
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            // 3.新增订单详情
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            orderDetail.setOrderId(orderId);
            orderDetailDao.save(orderDetail);
        }
        // 4.新增订单 注意：BeanUtils.copyProperties先后顺序，会产生不同结果
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setBuyerAmount(orderAmount);
        orderMaster.setBuyerStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterDao.save(orderMaster);
        // 5.扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new CartDTO(e.getProductId(),e.getProductQuantity())
        ).collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterDao.findById(orderId).orElse(null);
        if(orderMaster == null){
            throw new SellException(RequestEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderId);
        if(orderDetailList == null){
            throw new SellException(RequestEnum.ORDER_DETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterDao.findByBuyerOpenid(buyerOpenid,pageable);
        List<OrderDTO> orderDTOList = OrderMarster2OrderDTOConverter.convert(orderMasterPage.getContent());
        return new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalElements());
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        // 1.判断订单状态
        if (!orderDTO.getBuyerStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【完结订单】 订单状态不正确，orderId={},buyerStatus={}",orderDTO.getOrderId(),orderDTO.getBuyerStatus());
            throw new SellException(RequestEnum.ORDER_STATUS_ERROR);
        }
        // 2.修改状态
        orderDTO.setBuyerStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = orderMasterDao.save(orderMaster);
        if(updateResult == null){
            log.error("【完结订单】 更新失败，orderMaster={}",updateResult);
            throw new SellException(RequestEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        // 1.判断订单状态
        if (!orderDTO.getBuyerStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【取消订单】 订单状态不正确，orderId={},buyerStatus={}",orderDTO.getOrderId(),orderDTO.getBuyerStatus());
            throw new SellException(RequestEnum.ORDER_STATUS_ERROR);
        }
        // 2.更改订单状态
        orderDTO.setBuyerStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = orderMasterDao.save(orderMaster);
        if(updateResult == null){
            log.error("【取消订单】 更新失败，orderMaster={}",updateResult);
            throw new SellException(RequestEnum.ORDER_UPDATE_FAIL);
        }
        // 3.返回库存
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【取消订单】 订单中无商品详情，orderDTO={}",orderDTO);
            throw new SellException(RequestEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new CartDTO(e.getProductId(),e.getProductQuantity())
        ).collect(Collectors.toList());
        productService.increaseStock(cartDTOList);
        // 4.如果已支付，需退款
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS)){
            payService.refund(orderDTO);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        // 1.判断订单状态
        if (!orderDTO.getBuyerStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【支付订单】 订单状态不正确，orderId={},buyerStatus={}",orderDTO.getOrderId(),orderDTO.getBuyerStatus());
            throw new SellException(RequestEnum.ORDER_STATUS_ERROR);
        }
        // 2.判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("【支付订单】 订单支付状态不正确，orderDTO={}",orderDTO);
            throw new SellException(RequestEnum.ORDER_PAY_STATUS_ERROR);
        }
        // 3.更改支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = orderMasterDao.save(orderMaster);
        if(updateResult == null){
            log.error("【支付订单】 更新失败，orderMaster={}",updateResult);
            throw new SellException(RequestEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }
}
