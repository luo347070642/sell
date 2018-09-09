package com.lpy.controller;

import com.lpy.util.converter.OrderForm2OrderDTOConverter;
import com.lpy.dto.OrderDTO;
import com.lpy.enums.ResultEnum;
import com.lpy.exception.SellException;
import com.lpy.vo.form.OrderForm;
import com.lpy.service.BuyerService;
import com.lpy.service.OrderService;
import com.lpy.util.ResultVoUtil;
import com.lpy.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 罗鹏远
 * @description: 买家订单
 * @Date: created in 22:26 2018/8/17
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    /**
     * 创建订单
     * @param orderForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/create")
    public ResultVo<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【创建订单】 参数不正确，orderForm={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】 购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO createResult = orderService.create(orderDTO);
        Map<String,String> map = new HashMap<String,String>();
        map.put("openId",createResult.getOrderId());
        return ResultVoUtil.success(map);
    }

    /**
     * 订单列表
     * @param openid
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public ResultVo<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page",defaultValue = "0") Integer page,
                                         @RequestParam(value = "size",defaultValue = "10") Integer size){
        if(StringUtils.isEmpty(openid)){
            log.error("【查询订单列表】 openid为空，openid={}",openid);
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        Page<OrderDTO> orderDTOPage = orderService.findList(openid,PageRequest.of(page,size));
        return ResultVoUtil.success(orderDTOPage.getContent());
    }

    /**
     * 订单详情
     * @param openid
     * @param orderId
     * @return
     */
    @GetMapping("/detail")
    public ResultVo<OrderDTO> detail(@RequestParam("openid") String openid,@RequestParam("orderId") String orderId){
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
        return ResultVoUtil.success(orderDTO);
    }

    /**
     * 取消订单
     * @param openid
     * @param orderId
     * @return
     */
    @PostMapping("/cancel")
    public ResultVo<Map<String,String>> cancel(@RequestParam("openid") String openid,@RequestParam("orderId") String orderId){
        buyerService.cancelOrderOne(openid, orderId);
        return ResultVoUtil.success();
    }
}
