package com.lpy.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @Author: 罗鹏远
 * @description: Json转换类
 * @Date: created in 20:52 2018/8/28
 */
public class JsonUtil {

    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
