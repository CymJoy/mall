package com.neusoft.mall.address;

import com.neusoft.mall.address.domain.Address;
import com.neusoft.mall.common.config.RouteConstants;
import com.neusoft.mall.exception.ExceptionEnumeration;
import com.neusoft.mall.exception.common.RestBadRequestException;
import com.neusoft.mall.exception.common.RestServiceUnavailableException;
import com.neusoft.mall.utils.ResultUtil;
import com.neusoft.mall.utils.WxUserUtil;
import com.neusoft.mall.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * 地址管理
 * Created by chenyingmiao on 17/7/6.
 */
@RestController
@RequestMapping(RouteConstants.PREFIX + "/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    /**
     * 查询用户地址列表
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<Result<List<Address>>> findUserShippingAddress() {
        List<Address> addressList = this.addressService.findUserShippingAddress(WxUserUtil.getUserId());
        return new ResponseEntity(ResultUtil.success(addressList), HttpStatus.OK);
    }

    /**
     * 通过 id 获取收运地址
     *
     * @param id
     * @return
     * @throws RestServiceUnavailableException
     */
    @GetMapping("/{id}")
    public ResponseEntity<Result<Address>> findAddressById(@PathVariable String id) throws RestServiceUnavailableException {
        return Optional
                .ofNullable(this.addressService.findById(id))
                .map((obj) -> new ResponseEntity(ResultUtil.success(obj), HttpStatus.OK))
                .orElseThrow(() -> new RestBadRequestException(ExceptionEnumeration.CommonEmptyResult));
    }

    /**
     * 添加用户收货地址
     *
     * @param address
     * @return
     */
    @PostMapping
    public ResponseEntity<Result<Address>> create(@RequestBody Address address) {
        if (ObjectUtils.isEmpty(address)) {
            throw new RestBadRequestException(ExceptionEnumeration.CommonBadRequest);
        }
        address.setUserId(WxUserUtil.getUserId());
        addressService.addAddress(address);
        return new ResponseEntity(ResultUtil.success(address), HttpStatus.OK);
    }

    /**
     * 删除用户收货地址
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Result<Address>> delete(@PathVariable String id) {
        String userId = WxUserUtil.getUserId();
        if (ObjectUtils.isEmpty(userId) || ObjectUtils.isEmpty(id)) {
            throw new RestBadRequestException(ExceptionEnumeration.CommonBadRequest);
        }
        this.addressService.deleteAddress(userId, id);
        return new ResponseEntity(ResultUtil.success(), HttpStatus.OK);
    }

    /**
     * 更新收货地址
     *
     * @param address
     * @return
     */
    @PutMapping
    public ResponseEntity<Result<Address>> update(@RequestBody Address address) {
        if (ObjectUtils.isEmpty(address.getUserId()) || ObjectUtils.isEmpty(address.getId())) {
            throw new RestBadRequestException(ExceptionEnumeration.CommonBadRequest);
        }
        this.addressService.updateAddress(address);
        return new ResponseEntity(ResultUtil.success(), HttpStatus.OK);
    }
}
