package com.lpy.util.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lpy.dto.OrderDTO;
import com.lpy.entity.OrderDetail;
import com.lpy.enums.RequestEnum;
import com.lpy.exception.SellException;
import com.lpy.vo.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Author: 罗鹏远
 * @description: 订单表单实体 转换 订单数据传输对象
 * @Date: created in 22:50 2018/8/17
 */
@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm){
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        try {
            List<OrderDetail> orderDetailList = gson.fromJson(orderForm.getItems(),new TypeToken<List<OrderDetail>>(){}.getType());
            orderDTO.setOrderDetailList(orderDetailList);
            return orderDTO;
        }catch (Exception e){
            log.error("【对象转换】 错误，string={}",orderForm.getItems());
            throw new SellException(RequestEnum.PARAM_ERROR);
        }
    }
}
