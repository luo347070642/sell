package com.lpy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Author: 罗鹏远
 * @description:
 * @Date: created in 18:16 2018/9/9
 */
//@Component
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter getServerEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
