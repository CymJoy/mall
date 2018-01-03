package com.neusoft.mall.orders;

import com.alibaba.fastjson.JSON;
import com.neusoft.mall.MallApplication;
import com.neusoft.mall.common.config.RouteConstants;
import com.neusoft.mall.enums.OrderStatusEnum;
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
public class UserOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void create() throws Exception {
        UserOrderDTO userOrderDTO = new UserOrderDTO();
        userOrderDTO.setUserId("ff0795d7ea2343cfb68b49952704dc63");
        userOrderDTO.setCommodityCounts(1);
        userOrderDTO.setCommodityId("19f758ec7a2a4624977503aefa8c6883");
        this.mockMvc.perform(MockMvcRequestBuilders.post(RouteConstants.PREFIX + "/user/"+userOrderDTO.getUserId()+"/order")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSON(userOrderDTO).toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateStatus() throws Exception {
        UpdateUserOrderDTO updateUserOrderDTO = new UpdateUserOrderDTO();
        updateUserOrderDTO.setUserId("ff0795d7ea2343cfb68b49952704dc63");
        updateUserOrderDTO.setOrderId("23115a90ec3d40b7b63fe72f20d7dcc1");
        updateUserOrderDTO.setStatus(OrderStatusEnum.Shipped.getValue());
        this.mockMvc.perform(MockMvcRequestBuilders.put(RouteConstants.PREFIX + "/user/"+updateUserOrderDTO.getUserId()+"/order")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSON(updateUserOrderDTO).toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void findAllUserOrder() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(RouteConstants.PREFIX + "/user/ff0795d7ea2343cfb68b49952704dc63/order")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void delete() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete(RouteConstants.PREFIX + "/user/ff0795d7ea2343cfb68b49952704dc63/order/23115a90ec3d40b7b63fe72f20d7dcc1")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}