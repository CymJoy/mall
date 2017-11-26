package com.neusoft.mall.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 用于获取微信用户相关的信息
 * Created by chenyingmiao on 10/21/2017 AD.
 */
public class WxUserUtil {

    public static final String RequestHeaderUserId = "userId"; // 请求头

    public static String getUserId() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        return request.getHeader(RequestHeaderUserId);
    }
}
