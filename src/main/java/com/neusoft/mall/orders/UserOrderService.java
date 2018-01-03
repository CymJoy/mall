package com.neusoft.mall.orders;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.neusoft.mall.commodity.CommodityDAO;
import com.neusoft.mall.commodity.domain.Commodity;
import com.neusoft.mall.common.MybatisPlusServiceImpl;
import com.neusoft.mall.domain.PageRequest;
import com.neusoft.mall.domain.PageResponse;
import com.neusoft.mall.enums.OrderStatusEnum;
import com.neusoft.mall.exception.ExceptionEnumeration;
import com.neusoft.mall.exception.common.RestBadRequestException;
import com.neusoft.mall.orders.domain.UserOrder;
import com.neusoft.mall.user.User;
import com.neusoft.mall.user.UserDAO;
import com.neusoft.mall.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by chenyingmiao on 17/10/3.
 */
@Service
public class UserOrderService extends MybatisPlusServiceImpl<OrderDAO, UserOrder> {

    @Autowired
    private OrderDAO orderDao;

    @Autowired
    private CommodityDAO commodityDAO;

    @Autowired
    private UserDAO userDAO;

    /**
     * 取消订单
     *
     * @param userId
     * @param orderId
     * @return
     */
    public synchronized void cancelOrder(String userId, String orderId) {

        UserOrder isOrderExisted = new UserOrder();
        isOrderExisted.setId(orderId);
        isOrderExisted.setUserId(userId);

        EntityWrapper<UserOrder> entityOrderWrapper = new EntityWrapper();
        entityOrderWrapper.setEntity(isOrderExisted);
        if (VerifyUtil.isEmpty(this.selectOne(entityOrderWrapper))) {
            throw new RestBadRequestException(ExceptionEnumeration.OrderIsNotFound);
        }
        //如果订单存在则设置状态为0，即已取消状态；
        isOrderExisted.setStatus(OrderStatusEnum.Cancelled.getValue());
        this.orderDao.updateById(isOrderExisted);
    }

    /**
     * 创建用户订单
     *
     * @param userOrderDTO
     * @return
     */
    @Transactional
    public synchronized boolean createUserOrder(UserOrderDTO userOrderDTO) {
        User user = userDAO.selectById(userOrderDTO.getUserId());
        if (VerifyUtil.isEmpty(user)) {
            throw new RestBadRequestException(ExceptionEnumeration.UserIsNotFound);
        }
        Commodity commodity = commodityDAO.selectById(userOrderDTO.getCommodityId());
        if (VerifyUtil.isEmpty(commodity)) {
            throw new RestBadRequestException(ExceptionEnumeration.CommodityIsNotFound);
        }
        UserOrder order = UserOrderDTO.copy(userOrderDTO);
        order.setCreateTime(new Date());
        order.setStatus(OrderStatusEnum.UnShipped.getValue());
        //设置订单号
        String currentTime = String.valueOf(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateNowStr = sdf.format(new Date());
        order.setOrderNumber("LOC" + dateNowStr + currentTime.substring(currentTime.length() - 4));
        //调用插入订单的方法
        this.orderDao.insertAllColumn(order);
        //订单创建成功，更新商品
        Commodity updateCommodity = updateCounts(order.getStatus(), userOrderDTO.getCommodityCounts(), userOrderDTO.getCommodityId());
        if (VerifyUtil.isEmpty(updateCommodity)){
            return false;
        }
        return true;
    }

    /**
     * 更新订单
     *
     * @param updateUserOrderDTO
     * @return
     */
    public boolean updateStatus(UpdateUserOrderDTO updateUserOrderDTO) {
        UserOrder order = new UserOrder();
        order.setUserId(updateUserOrderDTO.getUserId());
        order.setId(updateUserOrderDTO.getOrderId());
        UserOrder isExistedOrder = orderDao.selectOne(order);
        if (VerifyUtil.isEmpty(isExistedOrder)){
            throw new RestBadRequestException(ExceptionEnumeration.OrderIsNotFound);
        }
        UserOrder userOrder = updateUserOrderDTO.copy(updateUserOrderDTO);
        isExistedOrder.setUpdateTime(new Date());
        isExistedOrder.setStatus(userOrder.getStatus());
        this.updateAllColumnById(isExistedOrder);
        return true;
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

    /**
     * 查询用户所有订单
     *
     * @param userId 用户id
     * @return
     */
    public PageResponse<UserOrder> findAllUserOrder(PageRequest pageRequest , String userId) {
        if (VerifyUtil.isEmpty(userDAO.selectById(userId))){
            throw new RestBadRequestException(ExceptionEnumeration.UserIsNotFound);
        }
        EntityWrapper<UserOrder> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id",userId);
       PageResponse<UserOrder> pageResponse =  this.selectPage(pageRequest, wrapper);
        return pageResponse;
    }
}
