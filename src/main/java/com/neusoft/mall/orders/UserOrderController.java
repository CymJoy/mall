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
        if (ObjectUtils.isEmpty(userOrderDto) || ObjectUtils.isEmpty(userId)) {
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
    @DeleteMapping(value = "/{userId}/order/{orderId}")
    public ResponseEntity<Result<Object>> delete(@PathVariable("userId") String userId, @PathVariable("orderId") String orderId) throws RestBadRequestException {
        if (VerifyUtil.isEmpty(userId) || VerifyUtil.isEmpty(orderId)){
            throw new RestBadRequestException(ExceptionEnumeration.CommonBadRequest);
        }
        this.userOrderService.cancelOrder(userId, orderId);
        return new ResponseEntity(ResultUtil.success(), HttpStatus.OK);
    }

    /**
     * 更新订单状态
     *
     * @return
     */
    @PutMapping(value = "/{userId}/order")
    public ResponseEntity<Result<UpdateUserOrderDTO>> updateStatus(@RequestBody UpdateUserOrderDTO updateUserOrderDTO) {
        if (ObjectUtils.isEmpty(updateUserOrderDTO)) {
            throw new RestBadRequestException(ExceptionEnumeration.CommonBadRequest);
        }
        this.userOrderService.updateStatus(updateUserOrderDTO);
        return new ResponseEntity(ResultUtil.success(updateUserOrderDTO), HttpStatus.OK);
    }

    /**
     * 查询用户所有订单
     *
     * @param userId  用户id
     * @return
     */
    @GetMapping(value = "/{userId}/order")
    public ResponseEntity<Result<UserOrder>> findAllUserOrder(PageRequest pageRequest, @PathVariable String userId) {
        PageResponse<UserOrder> commodity = this.userOrderService.findAllUserOrder(pageRequest, userId);
        if (VerifyUtil.isEmpty(commodity)) {
            throw new RestBadRequestException(ExceptionEnumeration.CommonEmptyResult);
        }
        return new ResponseEntity(ResultUtil.success(), HttpStatus.OK);
    }
}
