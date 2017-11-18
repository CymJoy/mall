package com.neusoft.mall.exception.common;


import com.neusoft.mall.exception.ExceptionEnumeration;

/**
 * 422
 * Created by chenyingmiao on 17/10/30.
 *
 * @author chenyingmiao
 */
public class RestUnprocessableEntityException extends CommonCustomException {
    public RestUnprocessableEntityException(String message) {
        super(message);
    }

    /**
     * 替换错误消息里面的占位符
     *
     * @param enumeration
     * @param placeholderValues 占位符
     */
    public RestUnprocessableEntityException(ExceptionEnumeration enumeration, String... placeholderValues) {
        String m = super.handleException(enumeration, placeholderValues);
        throw new RestUnprocessableEntityException(m);
    }
}
