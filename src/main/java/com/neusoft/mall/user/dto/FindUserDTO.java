package com.neusoft.mall.user.dto;

import com.neusoft.mall.common.base.BaseDTO;
import com.neusoft.mall.user.User;
import com.neusoft.mall.user.bo.FindUserBO;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;

/**
 * Created by chenyingmiao on 2017/11/11.
 */
@Data
public class FindUserDTO extends BaseDTO{

    /**
     * 账号
     */
    @NotNull(message = "用户名不能为空")
    @Length(max = 16, message = "用户名不能超过16位")
    private String account;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    private String password;

    public static User copy(FindUserDTO dto){
        User obj = new User();
        BeanUtils.copyProperties(dto,obj);
        obj.setAccountNumber(dto.getAccount());
        return obj;
    }
}
