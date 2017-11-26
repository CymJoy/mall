package com.neusoft.mall.orders;


import lombok.Data;

/**
 * Created by chenyingmiao on 17/10/5.
 *
 * @author chenyingmiao
 */
@Data
public class UserOrderDTO {

    private String userID;                  //用户id

    private String addressId;               //地址id

    private Integer totalIntegral;          //订单总积分

}
