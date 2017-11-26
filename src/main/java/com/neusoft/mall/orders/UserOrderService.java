package com.neusoft.mall.orders;

import com.neusoft.mall.commodity.CommodityDAO;
import com.neusoft.mall.commodity.domain.Commodity;
import com.neusoft.mall.enums.OrderStatusEnum;
import com.neusoft.mall.exception.ExceptionEnumeration;
import com.neusoft.mall.exception.common.RestBadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chenyingmiao on 17/10/3.
 */
@Service
public class UserOrderService {

    @Autowired
    private CommodityDAO commodityDAO;


    /**
     * 取消订单
     *
     * @param userId
     * @param orderId
     * @return
     */
    public synchronized void cancelOrder(String userId, String orderId) {

    }

    /**
     * 创建用户订单
     *
     * @param userOrderDto
     * @return
     */
    public synchronized boolean createUserOrder(UserOrderDTO userOrderDto) {

        return true;
    }

    /**
     * 更新订单状态
     *
     * @param userId    用户id
     * @param orderId   订单id
     * @param oldStatus 原状态
     * @param newStatus 新状态
     * @return
     */
    public boolean updateStatus(String userId, String orderId, int oldStatus, int newStatus) {

        return false;
    }


    /**
     * 更新商品库存量
     *
     * @param counts 购买数量
     */
    public Commodity updateCounts(int status, int counts, String commodityId) {

        //获取指定id商品原库存量
        Commodity commodity = this.commodityDAO.selectById(commodityId);
        int commodityInventory = commodity.getCounts();
        //判断商品购买数量和原库存量
        if (status == OrderStatusEnum.UnShipped.getValue()) {        //订单创建成功，减库存
            if (counts > commodityInventory) {
                //如果商品购买数量 > 原库存量  -->  抛出异常;
                throw new RestBadRequestException(ExceptionEnumeration.CommodityInventoryNotEnough);
            } else {
                // 否则 新库存量 ＝ 原库存量 － 商品购买数量   -->  对gc_commodity表中的商品库存量进行update
                int newInventory = commodityInventory - counts;
                commodity.setCounts(newInventory);
            }
        } else if (status == OrderStatusEnum.Cancelled.getValue()) {      //已取消，返回库存
            if (counts > commodityInventory) {
                //如果商品购买数量 > 原库存量  -->  抛出异常;
                throw new RestBadRequestException(ExceptionEnumeration.CommodityInventoryNotEnough);
            } else {
                // 否则 新库存量 ＝ 原库存量 － 商品购买数量   -->  对gc_commodity表中的商品库存量进行update
                int newInventory = commodityInventory + counts;
                commodity.setCounts(newInventory);
            }
        }
        this.commodityDAO.updateById(commodity);
        return commodity;
    }
}
