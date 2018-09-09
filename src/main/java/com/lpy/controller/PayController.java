package com.lpy.controller;

import com.lly835.bestpay.model.PayResponse;
import com.lpy.dto.OrderDTO;
import com.lpy.enums.ResultEnum;
import com.lpy.exception.SellException;
import com.lpy.service.OrderService;
import com.lpy.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @Author: 罗鹏远
 * @description:
 * @Date: created in 14:41 2018/8/25
 */
@Controller
@RequestMapping("/pay")
@Slf4j
public class PayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    /**
     * 创建订单，并发起支付
     * @param orderId
     * @param returnUrl
     * @param map
     * @return
     */
    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String ,Object> map){
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        PayResponse payResponse = payService.create(orderDTO);
        map.put("payResponse",payResponse);
        map.put("returnUrl",returnUrl);
        return new ModelAndView("pay/create",map);
    }

    /**
     * 微信异步通知商户支付结果
     * @param notifyData
     * @return 告知微信通知处理结果
     */
    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData){
        payService.notify(notifyData);
        return new ModelAndView("pay/success");
    }
}
