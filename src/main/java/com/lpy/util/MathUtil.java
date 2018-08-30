package com.lpy.util;

/**
 * @Author: 罗鹏远
 * @description: 金额比较工具类
 * @Date: created in 20:24 2018/8/29
 */
public class MathUtil {

    private static final Double MONEY_RANGE = 0.01;
    /**
     * 比较两个金额是否相等
     * @param d1
     * @param d2
     * @return
     */
    public static Boolean equals(Double d1,Double d2) {
        Double result = Math.abs(d1-d2);
        if (result < MONEY_RANGE){
            return true;
        }else{
            return false;
        }
    }
}
