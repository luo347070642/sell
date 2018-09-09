package com.lpy.controller;

import com.lpy.config.ProjectUrlConfig;
import com.lpy.constant.CookieConstant;
import com.lpy.constant.RedisConstant;
import com.lpy.entity.SellerInfo;
import com.lpy.enums.ResultEnum;
import com.lpy.service.SellerInfoService;
import com.lpy.util.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 罗鹏远
 * @description: 卖家用户
 * @Date: created in 14:54 2018/9/9
 */
@Controller
@RequestMapping("/seller")
@Slf4j
public class SellerUserController {
    @Autowired
    private SellerInfoService sellerInfoService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    /**
     * 登录
     * @param openid
     * @param response
     * @param map
     * @return
     */
    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid,
                              HttpServletResponse response,
                              Map<String,Object> map){
        //1.根据openid查询用户是否存在
        SellerInfo sellerInfo = sellerInfoService.findByOpenid(openid);
        if(sellerInfo == null){
            map.put("msg",ResultEnum.LOGIN_FAIL.getMsg());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        //2.设置token到redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX,token),openid,expire,TimeUnit.SECONDS);
        //3.设置token到cookie
        CookieUtil.set(response,CookieConstant.TOKEN,token,expire);
        return new ModelAndView("redirect:"+projectUrlConfig.getSell()+"/sell/seller/order/list");
    }

    /**
     * 登出
     * @param request
     * @param response
     * @param map
     * @return
     */
    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                               HttpServletResponse response,
                               Map<String,Object> map){
        //1.从cookie查询
        Cookie cookie = CookieUtil.get(request,CookieConstant.TOKEN);
        if(cookie != null){
            //2.清除redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
            //3.清除cookie
            CookieUtil.set(response,CookieConstant.TOKEN,null,0);
        }
        map.put("msg",ResultEnum.LOGOUT_SUCCESS.getMsg());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success",map);
    }
}
