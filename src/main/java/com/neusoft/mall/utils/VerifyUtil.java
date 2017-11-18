package com.neusoft.mall.utils;


import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 验证工具类
 * Created by chenyingmiao on 17/10/30 AD.
 */
public class VerifyUtil extends StringUtils {

    /**
     * 验证不为空
     *
     * @param args
     * @return
     */
    public static boolean isNotEmpty(Object... args) {
        if (ObjectUtils.isArray(args) && args.length > 0) {
            for (Object arg : args) {
                if (StringUtils.isEmpty(arg)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 验证不为空，并且长度大于 0
     *
     * @param list
     * @return
     */
    public static boolean isLengthGreaterThan_0(List list) {
        if (VerifyUtil.isNotEmpty(list) && list.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断 o1 大于 o2
     *
     * @param o1
     * @param o2
     * @return
     */
    public static boolean isGreaterThan(int o1, int o2) {
        return o1 > o2 ? true : false;
    }

    /**
     * 判断 o1 小于 o2
     *
     * @param o1
     * @param o2
     * @return
     */
    public static boolean isLessThan(int o1, int o2) {
        return o1 < o2 ? true : false;
    }

    /**
     * 验证不为空，并且等于 0
     *
     * @param list
     * @return
     */
    public static boolean isLengthEquals_0(List list) {
        if (VerifyUtil.isNotEmpty(list) && list.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否的入参都为空，包括空字符串
     *
     * @param args
     * @return
     */
    public static boolean isEmpty(Object... args) {
        if (ObjectUtils.isArray(args) && args.length > 0) {
            for (Object arg : args) {
                if (!StringUtils.isEmpty(arg)) return false;
            }
        }
        return true;
    }

    /**
     * 是否相等
     *
     * @param o1
     * @param o2
     * @return
     */
    public static boolean isEquals(Object o1, Object o2) {
        return ObjectUtils.nullSafeEquals(o1, o2);
    }

    /**
     * 是否不相等
     *
     * @param o1
     * @param o2
     * @return
     */
    public static boolean isNotEquals(Object o1, Object o2) {
        return !isEquals(o1, o2);
    }

}
