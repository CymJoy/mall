package com.neusoft.mall.user;

import com.neusoft.mall.common.base.BaseDAO;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by chenyingmiao on 2017/11/5.
 */
@Mapper
public interface UserDAO extends BaseDAO<User, String> {

}
