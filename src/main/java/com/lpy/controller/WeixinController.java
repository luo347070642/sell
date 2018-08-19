package com.lpy.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 罗鹏远
 * @description: 微信支付
 * @Date: created in 21:10 2018/8/19
 */
@RestController
@RequestMapping(("/weixin"))
@Slf4j
public class WeixinController {

    /**
     * 1 第一步：用户同意授权，获取code
     *
     * 2 第二步：通过code换取网页授权access_token
     *
     * 3 第三步：刷新access_token（如果需要）
     *
     * 4 第四步：拉取用户信息(需scope为 snsapi_userinfo)
     *
     * 5 附：检验授权凭证（access_token）是否有效
     * @param code
     */
    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code){
        log.info("进入auth方法。。。");
        log.info("code={}",code);
        // 通过code换取网页授权access_token
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxff4cc3ad51490418&secret=2e8a6139ffb8cb348e2c7cdb25877bea&code="+code+"&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url,String.class);
        log.info("response={}",response);
        Gson gson = new Gson();
        Map<String,String> map = gson.fromJson(response,new TypeToken<HashMap<String,String>>(){}.getType());
        url = "https://api.weixin.qq.com/sns/userinfo?access_token="+map.get("access_token")+"&openid="+map.get("openid")+"&lang=zh_CN";
        response = restTemplate.getForObject(url,String.class);

        log.info("response={}",response);
    }
}
