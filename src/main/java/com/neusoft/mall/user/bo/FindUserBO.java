package com.neusoft.mall.user.bo;

import com.neusoft.mall.common.base.BaseBO;
import com.neusoft.mall.common.base.BaseDTO;
import com.neusoft.mall.user.User;
import com.neusoft.mall.user.dto.FindUserDTO;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;

/**
 * Created by chenyingmiao on 2017/11/11.
 */
@Data
public class FindUserBO extends BaseBO{

    /**
     * 用戶id
     */
    @NotNull(message = "用户id不能为空")
    private String id;

    /**
     * 用戶名
     */
    @NotNull(message = "用户名不能为空")
    private String accountNumber;

    /**
     * 密碼
     */
    @NotNull(message = "密碼不能为空")
    private String password;

    public static User copy(FindUserBO bo){
        User obj = new User();
        BeanUtils.copyProperties(bo,obj);
        return obj;
    }
}
