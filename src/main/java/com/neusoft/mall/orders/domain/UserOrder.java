package com.neusoft.mall.orders.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.neusoft.mall.common.base.BaseDO;
import lombok.Data;

import java.util.Date;

/**
 * 用户订单
 * Created by chenyingmiao on 17/10/3.
 *
 * @author chenyingmiao
 */
@Data
@TableName("t_order")
public class UserOrder extends BaseDO {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 订单号码,格式:LOC+时间+当前时间毫秒数后四位
     */
    private String orderNumber;

    /**
     * 商品数量
     */
    private Integer commodityCounts;

    /**
     * 总积分
     */
    private Integer totalIntegral;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 0:已取消;  1:待发货;  2:已发货;  3:已收货
     */
    private Integer status;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 地址id
     */
    private String addressId;

}
