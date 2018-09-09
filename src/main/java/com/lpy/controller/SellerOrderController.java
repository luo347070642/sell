package com.lpy.controller;

import com.lpy.dto.OrderDTO;
import com.lpy.enums.ResultEnum;
import com.lpy.exception.SellException;
import com.lpy.service.OrderService;
import com.lpy.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @Author: 罗鹏远
 * @description: 卖家订单
 * @Date: created in 19:35 2018/8/30
 */
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 查询订单列表
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String,Object> map){
        Page<OrderDTO> orderDTOPage = orderService.findList(PageRequest.of(page-1,size));
        PageUtil pageUtil = new PageUtil((int) orderDTOPage.getTotalElements(),orderDTOPage.getTotalPages(),page,size);
        map.put("orderDTOPage",orderDTOPage);
        map.put("pageUtil",pageUtil);
        return new ModelAndView("order/list");
    }

    /**
     * 取消订单
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam(value = "orderId") String orderId,
                             Map<String,Object> map){
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        }catch (SellException e){
            log.error("【卖家端取消订单】，发生异常{}",e);
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg",ResultEnum.ORDER_CANCEL_SUCCESS.getMsg());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success",map);
    }

    /**
     * 订单详情
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam(value = "orderId") String orderId,
                               Map<String,Object> map){
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            map.put("orderDTO",orderDTO);
        } catch (SellException e) {
            log.error("【卖家端查询订单详情】，发生异常{}",e);
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        return new ModelAndView("order/detail",map);
    }

    /**
     * 完结订单
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam(value = "orderId") String orderId,
                       Map<String,Object> map){
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        } catch (SellException e) {
            log.error("【卖家端完结订单】，发生异常{}",e);
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg",ResultEnum.ORDER_FINISH_SUCCESS.getMsg());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success",map);
    }
}
