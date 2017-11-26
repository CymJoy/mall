package com.neusoft.mall.orders;

import com.neusoft.mall.common.config.RouteConstants;
import com.neusoft.mall.exception.ExceptionEnumeration;
import com.neusoft.mall.exception.common.RestBadRequestException;
import com.neusoft.mall.utils.ResultUtil;
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

}
