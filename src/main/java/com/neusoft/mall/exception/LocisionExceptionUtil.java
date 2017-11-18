package com.neusoft.mall.exception;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by chenyingmiao on 17/10/30.
 *
 * @author chenyingmiao
 */
public final class LocisionExceptionUtil {

    public static String makeExceptionStr(ExceptionEnumeration enumeration, String message) {
        return makeExceptionStr(enumeration.getCode(), message);
    }

    public static String makeExceptionStr(ExceptionEnumeration enumeration) {
        return makeExceptionStr(enumeration.getCode(), enumeration.getMessage());
    }

    private static String makeExceptionStr(int code, String message) {
        Map<String, Serializable> exceptionMap = new HashMap<String, Serializable>();
        exceptionMap.put("code", code);
        exceptionMap.put("message", message);
        return JSON.toJSONString(exceptionMap);
    }
}
