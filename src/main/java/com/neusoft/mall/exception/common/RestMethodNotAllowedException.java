package com.neusoft.mall.exception.common;

/**
 * Created by ivanyip on 14/4/2017.
 */


import com.neusoft.mall.exception.ExceptionEnumeration;

/**
 * 405
 * Created by chenyingmiao on 17/10/30.
 *
 * @author chenyingmiao
 */
public class RestMethodNotAllowedException extends CommonCustomException {
    public RestMethodNotAllowedException(String message) {
        super(message);
    }

    /**
     * 替换错误消息里面的占位符
     *
     * @param enumeration
     * @param placeholderValues 占位符
     */
    public RestMethodNotAllowedException(ExceptionEnumeration enumeration, String... placeholderValues) {
        String m = super.handleException(enumeration, placeholderValues);
        throw new RestMethodNotAllowedException(m);
    }
}