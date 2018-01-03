package com.neusoft.mall.commodity;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.neusoft.mall.commodity.domain.Commodity;
import com.neusoft.mall.common.base.BaseService;
import com.neusoft.mall.domain.PageResponse;
import com.neusoft.mall.exception.ExceptionEnumeration;
import com.neusoft.mall.exception.common.RestBadRequestException;
import com.neusoft.mall.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * Created by chenyingmiao on 17/10/4.
 *
 * @author chenyingmiao
 */
@Service
public class CommodityService implements BaseService<Commodity, String> {

    @Autowired
    private CommodityDAO commodityDAO;

    @Override
    public Commodity add(Commodity obj) {
        this.commodityDAO.insert(obj);
        return obj;
    }


    public boolean updateById(Commodity obj) {
        Integer i = this.commodityDAO.updateById(obj);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 返回指定id商品详情
     *
     * @param id
     * @return
     */
    public Commodity findCommodityById(String id) {
        return this.commodityDAO.selectById(id);
    }

    public List<Commodity> findByType(int type) {
        List<Commodity> list = this.commodityDAO.selectList(new EntityWrapper<Commodity>().eq("type", type));
        if (VerifyUtil.isLengthEquals_0(list)) {

            return list;
        }
        return null;
    }

    public PageResponse<Commodity> findAllCommodity(String commodityId) {
        Commodity commodity = this.commodityDAO.selectById(commodityId);
        EntityWrapper<Commodity> entityWrapper = new EntityWrapper<Commodity>();
        if (ObjectUtils.isEmpty(this.commodityDAO.select())) {
            throw new RestBadRequestException(ExceptionEnumeration.CommodityIsNotFound);
        }
        PageResponse<Commodity> pageResponse = new PageResponse<Commodity>();
        pageResponse.setList(this.commodityDAO.select());
        pageResponse.setTotal(this.commodityDAO.selectCount(entityWrapper));
        return pageResponse;
    }


}
