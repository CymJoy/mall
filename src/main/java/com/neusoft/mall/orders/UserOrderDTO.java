package com.neusoft.mall.orders;


import com.neusoft.mall.orders.domain.UserOrder;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * Created by chenyingmiao on 17/10/5.
 *
 * @author chenyingmiao
 */
@Data
public class UserOrderDTO {

    private String userId;                  //用户id

    private String addressId;               //地址id

    private Integer totalIntegral;          //订单总积分

    private String commodityId;             //商品id

    private Integer commodityCounts;             //商品数量

    public static UserOrder copy(UserOrderDTO dto){
        UserOrder userOrder = new UserOrder();
        BeanUtils.copyProperties(dto,userOrder);

        return userOrder;
    }
}
