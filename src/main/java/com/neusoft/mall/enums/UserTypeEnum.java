package com.neusoft.mall.enums;

/**
 * 用戶類型枚舉類
 * Created by chenyingmiao on 2017/11/11.
 */
public enum UserTypeEnum {

    /**
     * 普通用戶
     */
    GeneralUser("u"),

    /**
     * 管理員
     */
    Admin("a");

    private String value;

    UserTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
