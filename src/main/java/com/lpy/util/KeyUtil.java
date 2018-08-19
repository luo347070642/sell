package com.lpy.util;

import java.util.Random;

/**
 * @Author: 罗鹏远
 * @description: id生成类
 * @Date: created in 22:40 2018/8/16
 */
public class KeyUtil {

    /**
     * 生成唯一主键
     * 格式：时间+随机数
     * @return
     */
    public static synchronized String getUniqueKey(){
        Random random = new Random();
        Integer numbers = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(numbers);
    }
}
