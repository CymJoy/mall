package com.neusoft.mall.address;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.neusoft.mall.address.domain.Address;
import com.neusoft.mall.enums.CommonStatusEnum;
import com.neusoft.mall.exception.ExceptionEnumeration;
import com.neusoft.mall.exception.common.RestBadRequestException;
import com.neusoft.mall.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 地址管理
 * Created by chenyingmiao on 17/7/6.
 */
@Service
public class AddressService {

    @Autowired
    private AddressDAO addressDAO;

    /**
     * List 查询收获地址
     *
     * @param userId
     * @return
     */
    public List<Address> findUserShippingAddress(String userId) {
        Address param = new Address();
        param.setUserId(userId);
        EntityWrapper wrapper = new EntityWrapper(param);
        return this.addressDAO.selectList(wrapper);
    }

    /**
     * 通过 id 查询收获地址
     *
     * @param id
     * @return
     */
    public Address findById(String id) {
        return this.addressDAO.selectById(id);
    }

    /**
     * 创建收货地址
     *
     * @param address
     * @return
     */
    public boolean addAddress(Address address) {
        if (address != null) {
            this.addressDAO.insert(address);
            return true;
        }
        return false;
    }

    /**
     * 更新收货地址状态，1为正常，0为删除
     *
     * @param userId
     * @param id
     * @return
     */
    public boolean deleteAddress(String userId, String id) {
        Address address = this.findByUserIdAndId(id, userId);
        if (VerifyUtil.isEquals(address.getStatus(), CommonStatusEnum.DELETE.getValue())) {
            throw new RestBadRequestException(ExceptionEnumeration.ShippingAddressIsDeleted);
        } else {
            address.setStatus(CommonStatusEnum.DELETE.getValue());
            this.addressDAO.updateById(address);
            return true;
        }
    }

    public Address updateAddress(Address a) {
        Address address = this.findByUserIdAndId(a.getId(), a.getUserId());
        address.setName(a.getName());
        address.setProvince(a.getProvince());
        address.setCity(a.getCity());
        address.setArea(a.getArea());
        address.setAddress(a.getAddress());
        address.setCellphoneNumber(a.getCellphoneNumber());
        address.setPostCode(a.getPostCode());
        address.setIsDefault(a.getIsDefault());
        address.setUpdateTime(new Date());
        address.setStatus(CommonStatusEnum.NORMAL.getValue());
        this.addressDAO.updateById(address);
        return address;
    }

    /**
     * 通过 id 和 userId 查询
     *
     * @param id
     * @param userId
     * @return
     */
    public Address findByUserIdAndId(String id, String userId) {
        Address param = new Address();
        param.setId(id);
        param.setUserId(userId);
        Address address = this.addressDAO.selectOne(param);
        return address;
    }
}
