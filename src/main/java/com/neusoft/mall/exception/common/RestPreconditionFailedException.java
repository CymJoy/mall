package com.neusoft.mall.exception.common;


import com.neusoft.mall.exception.ExceptionEnumeration;

/**
 * 412
 * Created by chenyingmiao on 17/10/30.
 *
 * @author chenyingmiao
 */
public class RestPreconditionFailedException extends CommonCustomException {
    public RestPreconditionFailedException(String message) {
        super(message);
    }

    /**
     * 替换错误消息里面的占位符
     *
     * @param enumeration
     * @param placeholderValues 占位符
     */
    public RestPreconditionFailedException(ExceptionEnumeration enumeration, String... placeholderValues) {
        String m = super.handleException(enumeration, placeholderValues);
        throw new RestPreconditionFailedException(m);
    }
}
