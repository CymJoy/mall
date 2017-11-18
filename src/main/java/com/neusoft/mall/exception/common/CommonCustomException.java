package com.neusoft.mall.exception.common;


import com.neusoft.mall.exception.ExceptionEnumeration;
import com.neusoft.mall.exception.LocisionExceptionUtil;
import com.neusoft.mall.utils.VerifyUtil;

/**
 * 通用的自定义异常类
 * Created by chenyingmiao on 17/10/30.
 *
 * @author chenyingmiao
 */
public class CommonCustomException extends RuntimeException {

    public CommonCustomException() {
    }

    public CommonCustomException(String message) {
        super(message);
    }

    /**
     * 替换错误消息里面的占位符
     *
     * @param enumeration
     * @param placeholderValues 占位符
     */
    public String handleException(ExceptionEnumeration enumeration, String... placeholderValues) {
        String message = enumeration.getMessage();
        if (VerifyUtil.isNotEmpty(placeholderValues) && placeholderValues.length > 0) {
            for (int i = 0; i < placeholderValues.length; i++) {
                // 替换错误消息里面的占位符
                message = message.replace("{" + (i + 1) + "}", placeholderValues[i]);
            }
        }
        String msg = LocisionExceptionUtil.makeExceptionStr(enumeration, message);
        return msg;
    }
}
