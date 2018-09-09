package com.lpy.handler;

import com.lpy.config.ProjectUrlConfig;
import com.lpy.exception.ResponseBankException;
import com.lpy.exception.SellException;
import com.lpy.exception.SellerAuthorizeException;
import com.lpy.util.ResultVoUtil;
import com.lpy.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: 罗鹏远
 * @description: 拦截卖家自定义异常
 * @Date: created in 16:52 2018/9/9
 */
@ControllerAdvice
public class SellExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException(){
        return new ModelAndView("redirect:"
        .concat(projectUrlConfig.getWechatOpenAuthorize())
        .concat("/sell/wechat/qrAuthorize")
        .concat("?returnUrl=")
        .concat(projectUrlConfig.getSell())
        .concat("/sell/seller/login"));
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVo handlerSellException(SellException e){
        return ResultVoUtil.error(e.getCode(),e.getMessage());
    }

    /**
     * 更改http响应状态
     */
    @ExceptionHandler(value = ResponseBankException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handleResponseBankException() {

    }
}
