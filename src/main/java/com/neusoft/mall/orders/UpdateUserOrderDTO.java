package com.neusoft.mall.orders;


import com.neusoft.mall.common.base.BaseDTO;
import com.neusoft.mall.orders.domain.UserOrder;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * Created by chenyingmiao on 17/10/5.
 *
 * @author chenyingmiao
 */
@Data
public class UpdateUserOrderDTO extends BaseDTO{

    /**
     * 用户id
     */
    private String userId;

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 订单状态
     */
    private Integer status;

    public static UserOrder copy(UpdateUserOrderDTO dto){
        UserOrder userOrder = new UserOrder();
        BeanUtils.copyProperties(dto,userOrder);
        userOrder.setId(dto.getOrderId());
        return userOrder;
    }
}
