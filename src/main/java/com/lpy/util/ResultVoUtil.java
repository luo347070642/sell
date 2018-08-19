package com.lpy.util;

import com.lpy.vo.ResultVo;

/**
 * @Author: 罗鹏远
 * @description:
 * @Date: created in 21:39 2018/8/15
 */
public class ResultVoUtil {
    public static ResultVo success(Object object){
        ResultVo resultVo = new ResultVo();
        resultVo.setData(object);
        resultVo.setCode(0);
        resultVo.setMsg("成功");
        return resultVo;
    }

    public static ResultVo success(){
        return success("null");
    }

    public static ResultVo error(Integer code,String msg){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(code);
        resultVo.setMsg(msg);
        return resultVo;
    }
}
