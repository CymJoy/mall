package com.neusoft.mall.enums;

/**
 * 共用状态枚举类
 * Created by chenyingmiao on 17/7/17.
 */
public enum CommonStatusEnum {

    NORMAL(1),

    DELETE(0);

    private Integer value;

    CommonStatusEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
