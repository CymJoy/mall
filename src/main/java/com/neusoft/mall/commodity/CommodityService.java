package com.neusoft.mall.commodity;

import com.neusoft.mall.commodity.domain.Commodity;
import com.neusoft.mall.common.base.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by chenyingmiao on 17/10/4.
 *
 * @author chenyingmiao
 */
@Service
public class CommodityService implements BaseService<Commodity, String> {

    @Resource
    private CommodityDAO commodityDAO;

    public Commodity findCommodityById(String id) {
        return this.commodityDAO.selectById(id);
    }


}
