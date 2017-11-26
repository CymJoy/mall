package com.neusoft.mall.orders;

import com.neusoft.mall.common.base.BaseDAO;
import com.neusoft.mall.orders.domain.UserOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * Created by chenyingmiao on 10/29/2017 AD.
 *
 * @author chenyingmiao
 */
@Mapper
public interface OrderDAO extends BaseDAO<UserOrder, String> {
}
