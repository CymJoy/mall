package com.neusoft.mall.user;

import com.neusoft.mall.MallApplication;
import com.neusoft.mall.enums.UserTypeEnum;
import com.neusoft.mall.exception.ExceptionEnumeration;
import com.neusoft.mall.exception.common.RestBadRequestException;
import com.neusoft.mall.user.dto.CreateUserDTO;
import com.neusoft.mall.user.dto.FindUserDTO;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by chenyingmiao on 2018/1/3.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MallApplication.class)
@Transactional(rollbackFor = Exception.class)
public class UserServiceTest extends Assertions{

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        mockUser();
    }

    /**
     * 用户注册，期望成功
     * @throws Exception
     */
    @Test
    public void registerExpectedSuccess() throws Exception {
        CreateUserDTO dto = new CreateUserDTO();
        dto.setAccount("testService");
        dto.setPassword("testService");
        assertTrue(userService.register(dto));
    }

    /**
     * 用户注册，期望已存在用户账号，无法注册
     * @throws Exception
     */
    @Test
    public void registerExpectedUserAccountNumberIsExisted() throws Exception {
        expectedException.expect(RestBadRequestException.class);
        expectedException.expectMessage(ExceptionEnumeration.UserAccountNumberIsExisted.getMessage());
        CreateUserDTO dto = new CreateUserDTO();
        dto.setAccount("testMockUser");
        dto.setPassword("testMockUser");
        userService.register(dto);
    }

    /**
     * 用户登录，期望成功
     * @throws Exception
     */
    @Test
    public void loginExpectedSucces() throws Exception {
        FindUserDTO dto = new FindUserDTO();
        dto.setAccount("testMockUser");
        dto.setPassword("testMockUser");
        assertTrue(userService.login(dto));
    }

    /**
     * 用户登录，期望找不到该用户
     * @throws Exception
     */
    @Test
    public void loginExpectedUserIsNotFound() throws Exception {
        expectedException.expect(RestBadRequestException.class);
        expectedException.expectMessage(ExceptionEnumeration.UserIsNotFound.getMessage());
        FindUserDTO dto = new FindUserDTO();
        dto.setAccount("test");
        dto.setPassword("test");
        userService.login(dto);
    }

    /**
     * 用户登录，期望用户密码不对
     * @throws Exception
     */
    @Test
    public void loginExpectedUserAccountOrPasswordIsNotCorrect() throws Exception {
        expectedException.expect(RestBadRequestException.class);
        expectedException.expectMessage(ExceptionEnumeration.UserAccountOrPasswordIsNotCorrect.getMessage());
        FindUserDTO dto = new FindUserDTO();
        dto.setAccount("testMockUser");
        dto.setPassword("test");
        userService.login(dto);
    }

    /**
     * mock user data
     */
    private void mockUser(){
        CreateUserDTO user = new CreateUserDTO();
        user.setAccount("testMockUser");
        user.setPassword("testMockUser");
        this.userService.register(user);
    }
}