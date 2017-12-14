package com.neusoft.mall.commodity;

import com.neusoft.mall.commodity.domain.Commodity;
import com.neusoft.mall.common.base.BaseController;
import com.neusoft.mall.common.config.RouteConstants;
import com.neusoft.mall.domain.PageRequest;
import com.neusoft.mall.domain.PageResponse;
import com.neusoft.mall.exception.ExceptionEnumeration;
import com.neusoft.mall.exception.common.RestBadRequestException;
import com.neusoft.mall.utils.ResultUtil;
import com.neusoft.mall.utils.VerifyUtil;
import com.neusoft.mall.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品
 * Created by chenyingmiao on 17/10/4.
 */
@RestController
@RequestMapping(RouteConstants.PREFIX + "/commodities")
public class CommodityController implements BaseController<Commodity, String> {

    @Autowired
    private CommodityService commodityService;

    /**
     * 查询指定id商品详情
     *
     * @param commodityId
     * @return
     */
    @GetMapping(value = "/{commodityId}")
    public ResponseEntity<Result<Commodity>> findCommodityById(@PathVariable("commodityId") String commodityId) {
        if (ObjectUtils.isEmpty(commodityId)) {
            throw new RestBadRequestException(ExceptionEnumeration.CommonBadRequest);
        }
        Commodity commodity = this.commodityService.findCommodityById(commodityId);
        return new ResponseEntity(ResultUtil.success(commodity), HttpStatus.OK);
    }

    /**
     * 根据type查询商品
     *
     * @param type
     * @return
     */
    @GetMapping
    public ResponseEntity<Result<List<Commodity>>> findCommodityByType(int type) {
        if (ObjectUtils.isEmpty(type)) {
            throw new RestBadRequestException(ExceptionEnumeration.CommonBadRequest);
        }
        List<Commodity> commodity = this.commodityService.findByType(type);
        return new ResponseEntity(ResultUtil.success(commodity), HttpStatus.OK);
    }

    /**
     * 查询所有商品详情
     *
     *
     * @return
     */
//    @GetMapping
    public ResponseEntity<Result<Commodity>> findAllCommodity(PageRequest pageRequest, String commodityId) {
        PageResponse<Commodity> commodity = this.commodityService.findAllCommodity(commodityId);
        if (VerifyUtil.isEmpty(commodity)) {
            throw new RestBadRequestException(ExceptionEnumeration.CommonEmptyResult);
        }
        return new ResponseEntity(ResultUtil.success(), HttpStatus.OK);
    }
}
