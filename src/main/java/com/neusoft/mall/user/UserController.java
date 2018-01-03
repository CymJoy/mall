package com.neusoft.mall.user;

import com.neusoft.mall.common.config.RouteConstants;
import com.neusoft.mall.user.dto.CreateUserDTO;
import com.neusoft.mall.user.dto.FindUserDTO;
import com.neusoft.mall.vo.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by chenyingmiao on 2017/11/11.
 */
@RequestMapping(RouteConstants.PREFIX + "/users")
public interface UserController {

    @PostMapping
    ResponseEntity create(@RequestBody @Valid CreateUserDTO dto, BindingResult bindingResult);

    @GetMapping
    ResponseEntity<Result<User>> findByParam(@ModelAttribute @Valid FindUserDTO dto, BindingResult bindingResult);
}
