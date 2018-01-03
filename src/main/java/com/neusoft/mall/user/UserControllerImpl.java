package com.neusoft.mall.user;

import com.neusoft.mall.exception.ExceptionEnumeration;
import com.neusoft.mall.exception.common.RestBadRequestException;
import com.neusoft.mall.user.dto.CreateUserDTO;
import com.neusoft.mall.user.dto.FindUserDTO;
import com.neusoft.mall.utils.ResultUtil;
import com.neusoft.mall.utils.VerifyUtil;
import com.neusoft.mall.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

//    @PostMapping
    @Deprecated
    public ResponseEntity<Result<Object>> create(@RequestBody User user) {
        if (VerifyUtil.isEmpty(user.getPhoneNumber())){
            throw new RestBadRequestException("手机号码不能为空");
        }
        this.userService.register(user);
        return new ResponseEntity(ResultUtil.success(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Result<Object>> create(@RequestBody @Valid CreateUserDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            throw new RestBadRequestException(ExceptionEnumeration.CommonCustomError,bindingResult.getFieldError().getDefaultMessage());
        }
        this.userService.register(dto);
        return new ResponseEntity(ResultUtil.success(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Result<User>> findByParam(@ModelAttribute @Valid FindUserDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            throw new RestBadRequestException(ExceptionEnumeration.CommonCustomError,bindingResult.getFieldError().getDefaultMessage());
        }
        this.userService.login(dto);
        return new ResponseEntity(ResultUtil.success(), HttpStatus.OK);
    }
}
