package com.neusoft.mall.commodity;

import com.neusoft.mall.commodity.domain.Commodity;
import com.neusoft.mall.commodity.domain.CommodityImage;
import com.neusoft.mall.common.base.BaseDAO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品图片 dao
 * Created by chenyingmiao on 10/29/2017 AD.
 *
 * @author chenyingmiao
 */
@Mapper
public interface CommodityImageDAO extends BaseDAO<CommodityImage, String> {
}
