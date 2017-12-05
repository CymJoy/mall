package com.neusoft.mall.commodity;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.neusoft.mall.commodity.domain.Commodity;
import com.neusoft.mall.common.base.BaseService;
import com.neusoft.mall.domain.PageResponse;
import com.neusoft.mall.exception.ExceptionEnumeration;
import com.neusoft.mall.exception.common.RestBadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chenyingmiao on 17/10/4.
 *
 * @author chenyingmiao
 */
@Service
public class CommodityService implements BaseService<Commodity, String> {

    @Resource
    private CommodityDAO commodityDAO;

    /**
     * 返回指定id商品详情
     *
     * @param id
     * @return
     */
    public Commodity findCommodityById(String id) {

        return this.commodityDAO.selectById(id);
    }

    public PageResponse<Commodity> findAllCommodity(String commodityId) {
        Commodity commodity = this.commodityDAO.selectById(commodityId);
        EntityWrapper<Commodity> entityWrapper = new EntityWrapper<Commodity>();
        if(ObjectUtils.isEmpty(this.commodityDAO.select())){
          throw new RestBadRequestException(ExceptionEnumeration.CommodityIsNotFound);
        }
        PageResponse<Commodity> pageResponse = new PageResponse<Commodity>();
        pageResponse.setList(this.commodityDAO.select());
        pageResponse.setTotal(this.commodityDAO.selectCount(entityWrapper));
        return pageResponse;
    }


}
