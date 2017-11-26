package com.neusoft.mall.commodity.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.neusoft.mall.common.base.BaseDO;
import lombok.Data;

import java.util.Date;

/**
 * 商品信息
 * Created by chenyingmiao on 17/10/3.
 *
 * @author chenyingmiao
 */
@Data
@TableName("t_commodity")
public class Commodity extends BaseDO {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 商品名字
     */
    private String name;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 商品所需积分
     */
    private Integer integral;

    /**
     * 商品库存数量
     */
    private Integer counts;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @Override
    public String toString() {
        return "Commodity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", integraldonation=" + integral +
                ", counts=" + counts +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
