package com.neusoft.mall.user;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

/**
 * Created by chenyingmiao on 2017/11/5.
 */
@Data
@TableName("t_user")
public class User {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     *  用户账号
     */
    private String accountNumber;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户昵称
     */
    private String name;

    /**
     * 用户性别，1男0女
     */
    private Integer gender;

    /**
     *
     */
    private String birthday;

    /**
     * 用户常住地区
     */
    private String region;

    /**
     * 用户个人简介
     */
    private String profile;

    /**
     * 用户头像图片url
     */
    private String headUrl;

    /**
     * 用户类型，默认u，u表是普通用户，a表示管理员
     */
    private String type;

    private Date createTime;

    private Date updateTime;

}
