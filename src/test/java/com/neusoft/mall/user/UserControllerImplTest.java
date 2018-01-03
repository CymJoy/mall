package com.neusoft.mall.user;

import com.alibaba.fastjson.JSON;
import com.neusoft.mall.MallApplication;
import com.neusoft.mall.common.config.RouteConstants;
import com.neusoft.mall.user.dto.CreateUserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

/**
 * Created by chenyingmiao on 2018/1/3.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MallApplication.class)
@AutoConfigureMockMvc
public class UserControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * 注册接口测试 期望成功
     * @throws Exception
     */
    @Test
    public void create() throws Exception {
        CreateUserDTO user = new CreateUserDTO();
        user.setAccount("testAccount");
        user.setPassword("testpassword");
        this.mockMvc.perform(MockMvcRequestBuilders.post(RouteConstants.PREFIX + "/users")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSON(user).toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 登录接口测试，期望成功
     * @throws Exception
     */
    @Test
    public void findByParam() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(RouteConstants.PREFIX + "/users")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).param("account","testAccount")
        .param("password","testpassword"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}