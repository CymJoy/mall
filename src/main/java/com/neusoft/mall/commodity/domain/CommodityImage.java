package com.neusoft.mall.commodity.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.neusoft.mall.common.base.BaseDO;
import lombok.Data;

import java.util.Date;

/**
 * 商品图片
 * Created by chenyingmiao on 17/10/3.
 *
 * @author chenyingmiao
 */
@Data
@TableName("t_commodity_image")
public class CommodityImage extends BaseDO {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 商品id
     */
    private String commodityId;

    /**
     * 商品图片地址
     */
    private String url;

    /**
     * 创建时间
     */
    private Date createTime;
}
