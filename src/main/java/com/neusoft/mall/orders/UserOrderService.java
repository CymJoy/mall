package com.neusoft.mall.orders;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.neusoft.mall.commodity.CommodityDAO;
import com.neusoft.mall.commodity.domain.Commodity;
import com.neusoft.mall.common.MybatisPlusServiceImpl;
import com.neusoft.mall.domain.PageResponse;
import com.neusoft.mall.enums.OrderStatusEnum;
import com.neusoft.mall.exception.ExceptionEnumeration;
import com.neusoft.mall.exception.common.RestBadRequestException;
import com.neusoft.mall.orders.domain.UserOrder;
import com.neusoft.mall.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        if (VerifyUtil.isNotEmpty(this.selectOne(entityOrderWrapper))){
            throw new RestBadRequestException(ExceptionEnumeration.OrderIsNotFound);
        }
        //如果订单存在则设置状态为0，即已取消状态；
        isOrderExisted.setStatus(0);
        int number = this.orderDao.updateById(isOrderExisted);
        if(number>0){
            throw new RestBadRequestException(ExceptionEnumeration.OrderUpdateFailed);
        }

    }

    /**
     * 创建用户订单
     *
     * @param userOrderDTO
     * @return
     */
    public synchronized boolean createUserOrder(UserOrderDTO userOrderDTO) {

        SimpleDateFormat simple = new SimpleDateFormat("yyyyMMddHHssmmSSSS");
        String currentTime = simple.format(new Date());
        if(!(userOrderDTO.getCommodityCounts()>0)){
            throw new RestBadRequestException(ExceptionEnumeration.CommodityInventoryNotEnough);
        }
        UserOrder order = UserOrderDTO.copy(userOrderDTO);
        order.setCreateTime(new Date());
        order.setStatus(1);
        order.setCommodityCounts(userOrderDTO.getCommodityCounts()-1);
        //设置订单号
        order.setOrderNumber("LOC"+currentTime);
        //调用插入订单的方法
        this.orderDao.insertAllColumn(order);
        return true;

    }

    /**
     * 更新订单状态
     *
     * @param userOrderDTO
     * @param oldStatus 原状态
     * @param newStatus 新状态
     * @return
     */
    public boolean updateStatus(UserOrderDTO userOrderDTO, int oldStatus, int newStatus) {

        UserOrder order = UserOrderDTO.copy(userOrderDTO);
        order.setStatus(oldStatus);
        EntityWrapper<UserOrder> entityWrapper = new EntityWrapper<UserOrder>();
        entityWrapper.setEntity(order);
        if (VerifyUtil.isEmpty(this.orderDao.selectById(entityWrapper))){
            throw new RestBadRequestException(ExceptionEnumeration.OrderIsNotFound);
        }
        order.setStatus(newStatus);
        order.setUpdateTime(new Date());
        this.updateAllColumnById(order);
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
     * 查询所有订单
     *
     *
     *
     * @param userId 用户id
     * @return
     */
    public PageResponse<UserOrder> findAllOrder(String userId) {
        List<UserOrder> list = this.orderDao.select();
        UserOrder order = new UserOrder();
        EntityWrapper<UserOrder> entity = new EntityWrapper<UserOrder>(order);
        if(ObjectUtils.isEmpty(list)){
            throw  new RestBadRequestException(ExceptionEnumeration.OrderIsNotFound);
        }
        PageResponse<UserOrder> pageResponse = new PageResponse<UserOrder>();
        pageResponse.setTotal(this.orderDao.selectCount(entity));
        pageResponse.setList(list);
        return pageResponse;
    }
}
