package com.neusoft.mall.address.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.neusoft.mall.common.base.BaseDO;
import lombok.Data;

import java.util.Date;

/**
 * 用户收货地址表信息
 * Created by chenyingmiao on 17/7/6.
 *
 * @author chenyingmiao
 */
@Data
@TableName("t_address")
public class Address extends BaseDO {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 收货人姓名
     */
    private String name;

    /**
     * 收货人手机号码
     */
    private String cellphoneNumber;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String area;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 邮政编码
     */
    private String postCode;

    /**
     * 默认地址，1:是；0:否
     */
    private Integer isDefault;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 状态：正常1；删除0
     */
    private Integer status;

}
