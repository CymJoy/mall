package com.neusoft.mall.enums;

/**
 * 订单状态枚举类
 * Created by chenyingmiao on 17/7/12.
 */
public enum OrderStatusEnum {

    Cancelled(0),       //已取消

    UnShipped(1),       //待发货

    Shipped(2),         //已发货

    Received(3);        //已收货

    private Integer value;

    OrderStatusEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
