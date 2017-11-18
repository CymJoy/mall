package com.neusoft.mall.user;

import com.neusoft.mall.exception.ExceptionEnumeration;
import com.neusoft.mall.exception.common.RestBadRequestException;
import com.neusoft.mall.user.dto.CreateUserDTO;
import com.neusoft.mall.user.dto.FindUserDTO;
import com.neusoft.mall.utils.ResultUtil;
import com.neusoft.mall.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenyingmiao on 2017/11/5.
 */
@RestController
public class UserControllerImpl implements UserController{

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String hello() {
        return "hello,this is a springboot demo";
    }

    @Override
    public ResponseEntity<Result<Object>> create(CreateUserDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            throw new RestBadRequestException(ExceptionEnumeration.CommonCustomError,bindingResult.getFieldError().getDefaultMessage());
        }
        this.userService.register(dto);
        return new ResponseEntity(ResultUtil.success(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Result<User>> findByParam(FindUserDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            throw new RestBadRequestException(ExceptionEnumeration.CommonCustomError,bindingResult.getFieldError().getDefaultMessage());
        }
        this.userService.login(dto);
        return new ResponseEntity(ResultUtil.success(), HttpStatus.OK);
    }
}
