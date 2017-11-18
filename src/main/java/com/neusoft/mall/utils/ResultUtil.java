package com.neusoft.mall.utils;

import com.neusoft.mall.vo.Result;

/**
 * controller 统一的响应结果工具类
 * Created by chenyingmiao on 17/10/30 AD.
 */
public class ResultUtil {

    /**
     * 成功，需要将数据响应到前端
     *
     * @param data 响应数据
     * @return
     */
    public static Result success(Object data) {
        return new Result(1, data);
    }

    /**
     * 成功，不需要将数据响应到前端
     *
     * @return
     */
    public static Result success() {
        return new Result(1, null);
    }
}
