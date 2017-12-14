package com.neusoft.mall.orders;

import com.neusoft.mall.commodity.domain.Commodity;
import com.neusoft.mall.common.config.RouteConstants;
import com.neusoft.mall.domain.PageRequest;
import com.neusoft.mall.domain.PageResponse;
import com.neusoft.mall.exception.ExceptionEnumeration;
import com.neusoft.mall.exception.common.RestBadRequestException;
import com.neusoft.mall.orders.domain.UserOrder;
import com.neusoft.mall.utils.ResultUtil;
import com.neusoft.mall.utils.VerifyUtil;
import com.neusoft.mall.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 订单
 * Created by chenyingmiao on 17/10/3.
 */
@RestController
@RequestMapping(RouteConstants.PREFIX + "/user")
public class UserOrderController {

    @Autowired
    private UserOrderService userOrderService;

    /**
     * 添加订单
     *
     * @param userOrderDto
     * @return
     */
    @PostMapping(value = "/{userId}/order")
    public ResponseEntity<Result<UserOrderDTO>> create(@PathVariable("userId") String userId, @RequestBody UserOrderDTO userOrderDto) {
        if (ObjectUtils.isEmpty(userOrderDto)) {
            throw new RestBadRequestException(ExceptionEnumeration.CommonBadRequest);
        }
        this.userOrderService.createUserOrder(userOrderDto);
        return new ResponseEntity(ResultUtil.success(userOrderDto), HttpStatus.OK);
    }

    /**
     * 取消订单
     *
     * @param userId
     * @param orderId
     * @return
     */
    @PutMapping(value = "/{userId}/order/{orderId}")
    public ResponseEntity<Result<Object>> cancel(@PathVariable("userId") String userId, @PathVariable("orderId") String orderId) throws RestBadRequestException {
        this.userOrderService.cancelOrder(userId, orderId);
        return new ResponseEntity(ResultUtil.success(), HttpStatus.OK);
    }

    /**
     * 更新订单状态
     *
     * @param userOrderDto
     * @param oldStatus
     * @param newStatus
     * @return
     */
//    @PostMapping(value = "/{userId}/order")
    public ResponseEntity<Result<UserOrderDTO>> updateStatus(@PathVariable("oldStatus") Integer oldStatus, @PathVariable("newStatus") Integer newStatus, @RequestBody UserOrderDTO userOrderDto) {
        if (ObjectUtils.isEmpty(userOrderDto)) {
            throw new RestBadRequestException(ExceptionEnumeration.CommonBadRequest);
        }
        this.userOrderService.updateStatus(userOrderDto,oldStatus,newStatus);
        return new ResponseEntity(ResultUtil.success(userOrderDto), HttpStatus.OK);
    }

    /**
     * 查询所有订单
     *
     * @param pages 总页数
     * @param page  当前页数
     * @param size  大小
     * @param userId  用户id
     * @return
     */
    public ResponseEntity<Result<UserOrder>> findAllCommodity(PageRequest pageRequest, String userId) {
        PageResponse<UserOrder> commodity = this.userOrderService.findAllOrder(userId);
        if (VerifyUtil.isEmpty(commodity)) {
            throw new RestBadRequestException(ExceptionEnumeration.CommonEmptyResult);
        }
        return new ResponseEntity(ResultUtil.success(), HttpStatus.OK);
    }

    /**
     * 更新订单库存
     *
     * @param status
     * @param counts
     * @param commodityId
     * @return
     */
//    @PostMapping(value = "/{userId}/order")
    public ResponseEntity<Result<UserOrderDTO>> updateCounts(@PathVariable("status") Integer status, @PathVariable("counts") Integer counts, @PathVariable("commodityId") String commodityId) {
        if (ObjectUtils.isEmpty(commodityId)) {
            throw new RestBadRequestException(ExceptionEnumeration.CommonBadRequest);
        }
        Commodity commodity = this.userOrderService.updateCounts(status,counts,commodityId);
        if(ObjectUtils.isEmpty(commodity)){
            throw new RestBadRequestException(ExceptionEnumeration.CommodityIsNotFound);
        }
        return new ResponseEntity(ResultUtil.success(), HttpStatus.OK);
    }
}
