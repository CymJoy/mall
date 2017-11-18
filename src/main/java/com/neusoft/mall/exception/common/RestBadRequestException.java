package com.neusoft.mall.exception.common;


import com.neusoft.mall.exception.ExceptionEnumeration;
import com.neusoft.mall.exception.LocisionExceptionUtil;

/**
 * 400
 * Created by chenyingmiao on 17/10/30.
 *
 * @author chenyingmiao
 */
public class RestBadRequestException extends CommonCustomException {

    public RestBadRequestException(String message) {
        super(message);
    }

    /**
     * @param enumeration 用于 ExceptionEnumeration 的自定义错误消息
     */
    public RestBadRequestException(ExceptionEnumeration enumeration) {
        String m = LocisionExceptionUtil.makeExceptionStr(enumeration);
        throw new RestBadRequestException(m);
    }

    /**
     * 替换错误消息里面的占位符
     *
     * @param enumeration
     * @param placeholderValues 占位符
     */
    public RestBadRequestException(ExceptionEnumeration enumeration, String... placeholderValues) {
        String m = super.handleException(enumeration, placeholderValues);
        throw new RestBadRequestException(m);
    }
}
