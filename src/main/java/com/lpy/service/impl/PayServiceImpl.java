package com.lpy.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.lpy.dto.OrderDTO;
import com.lpy.enums.RequestEnum;
import com.lpy.exception.SellException;
import com.lpy.service.OrderService;
import com.lpy.service.PayService;
import com.lpy.util.JsonUtil;
import com.lpy.util.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @Author: 罗鹏远
 * @description:
 * @Date: created in 14:48 2018/8/25
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {

    private static final String ORDER_NAME = "微信点餐订单";

    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private OrderService orderService;

    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderDTO.getBuyerAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        log.info("【微信支付】发起支付 result={}",JsonUtil.toJson(payRequest));

        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付】发起支付 response ={}", JsonUtil.toJson(payResponse));
        return payResponse;
    }

    @Override
    public PayResponse notify(String nodiryData) {
        //1.验证签名和支付状态
        PayResponse payResponse = bestPayService.asyncNotify(nodiryData);
        log.info("【微信支付】异步通知 payResponse={}",JsonUtil.toJson(payResponse));
        //查询订单
        OrderDTO orderDTO = orderService.findOne(payResponse.getOrderId());
        if(orderDTO == null){
            log.info("【微信支付】异步通知，订单不存在 orderId={}",payResponse.getOrderId());
            throw new SellException(RequestEnum.ORDER_NOT_EXIST);
        }
        //2.判断金额是否一致
        if(!MathUtil.equals(payResponse.getOrderAmount(),orderDTO.getBuyerAmount().doubleValue())){
            log.info("【微信支付】异步通知，订单金额不一致 orderId={}，微信通知金额={}，系统订单金额={}",
                    payResponse.getOrderId(),payResponse.getOrderAmount(),orderDTO.getBuyerAmount());
            throw new SellException(RequestEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
        }
        //3.支付人（下单人==支付人） 看业务需求
        //4.修改订单支付状态
        OrderDTO orderDTO1 = orderService.paid(orderDTO);
        return payResponse;
    }

    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setOrderAmount(orderDTO.getBuyerAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信退款】request={}",refundRequest);
        RefundResponse refundResponse =  bestPayService.refund(refundRequest);
        log.info("【微信退款】response={}",refundResponse);
        return refundResponse;
    }
}
