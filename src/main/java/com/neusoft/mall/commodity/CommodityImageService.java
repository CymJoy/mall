package com.neusoft.mall.commodity;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.neusoft.mall.commodity.domain.Commodity;
import com.neusoft.mall.commodity.domain.CommodityImage;
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
public class CommodityImageService implements BaseService<CommodityImage, String> {

    @Autowired
    private CommodityImageDAO commodityImageDAO;

    @Override
    public CommodityImage add(CommodityImage obj) {
        this.commodityImageDAO.insert(obj);
        return obj;
    }
}
