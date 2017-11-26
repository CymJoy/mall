package com.neusoft.mall.address;

import com.neusoft.mall.address.domain.Address;
import com.neusoft.mall.common.base.BaseDAO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商城地址
 * Created by chenyingmiao on 10/29/2017 AD.
 *
 * @author chenyingmiao
 */
@Mapper
public interface AddressDAO extends BaseDAO<Address, String> {
}
