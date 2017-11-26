package com.neusoft.mall.commodity;

import com.neusoft.mall.commodity.domain.Commodity;
import com.neusoft.mall.common.base.BaseDAO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品 dao
 * Created by chenyingmiao on 10/29/2017 AD.
 *
 * @author chenyingmiao
 */
@Mapper
public interface CommodityDAO extends BaseDAO<Commodity, String> {
}
