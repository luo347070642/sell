package com.lpy.util;

import com.lpy.enums.CodeEnum;
import com.lpy.enums.OrderStatusEnum;

/**
 * @Author: 罗鹏远
 * @description: 枚举工具类
 * @Date: created in 20:46 2018/8/30
 */
public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Object code, Class<T> enumClass){
        for (T each : enumClass.getEnumConstants()) {
            if(code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}
