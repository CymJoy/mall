package com.neusoft.mall.user;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.neusoft.mall.common.MybatisPlusServiceImpl;
import com.neusoft.mall.common.base.BaseService;
import com.neusoft.mall.enums.UserTypeEnum;
import com.neusoft.mall.exception.ExceptionEnumeration;
import com.neusoft.mall.exception.common.RestBadRequestException;
import com.neusoft.mall.user.bo.FindUserBO;
import com.neusoft.mall.user.dto.CreateUserDTO;
import com.neusoft.mall.user.dto.FindUserDTO;
import com.neusoft.mall.utils.JSR303ManualValidator;
import com.neusoft.mall.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by chenyingmiao on 2017/11/5.
 */
@Service
public class UserService extends MybatisPlusServiceImpl<UserDAO, User> {

    public boolean register(User user) {
        User isExisted = new User();
        isExisted.setPhoneNumber(user.getPhoneNumber());
        EntityWrapper<User> entityWrapper = new EntityWrapper();
        entityWrapper.setEntity(isExisted);
        if (VerifyUtil.isNotEmpty(this.selectOne(entityWrapper))){
            throw new RestBadRequestException(ExceptionEnumeration.UserAccountNumberIsExisted);
        }
        user.setType(UserTypeEnum.GeneralUser.getValue());
        user.setCreateTime(new Date());
        super.insertAllColumn(user);
        return true;
    }


    /**
     * 註冊新用戶
     * @param dto
     * @return
     */
    public boolean register(CreateUserDTO dto) {
        User isExisted = new User();
        isExisted.setAccountNumber(dto.getAccount());
        EntityWrapper<User> entityWrapper = new EntityWrapper();
        entityWrapper.setEntity(isExisted);
        if (VerifyUtil.isNotEmpty(this.selectOne(entityWrapper))){
            throw new RestBadRequestException(ExceptionEnumeration.UserAccountNumberIsExisted);
        }
        User user = CreateUserDTO.copy(dto);
        user.setType(UserTypeEnum.GeneralUser.getValue());
        user.setCreateTime(new Date());
        super.insertAllColumn(user);
        return true;
    }

    /**
     * 用戶登録
     * @param dto
     * @return
     */
    public boolean login(FindUserDTO dto){
        User isLogin = FindUserDTO.copy(dto);
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.setEntity(isLogin);
        User user = this.selectOne(entityWrapper);
        if (VerifyUtil.isEmpty(user)){
            throw new RestBadRequestException(ExceptionEnumeration.UserIsNotFound);
        }
        if (!user.getPassword().equals(dto.getPassword()) || !user.getAccountNumber().equals(dto.getAccount())){
            throw new RestBadRequestException(ExceptionEnumeration.UserAccountOrPasswordIsNotCorrect);
        }
        return true;
    }
}
