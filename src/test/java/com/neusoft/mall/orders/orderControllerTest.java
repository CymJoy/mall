package com.neusoft.mall.orders;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by chenyingmiao on 2017/11/26.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class orderControllerTest {

    @Autowired
    UserOrderService userOrderService;
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findById() throws Exception {
        System.out.println(this.userOrderService.findAllOrder("1")+"-------------");
    }

    @Test
    public void addUserOrder() throws Exception {
        UserOrderDTO userOrderDTO = new UserOrderDTO();
        userOrderDTO.setUserId("1");
        userOrderDTO.setAddressId("1");
        userOrderDTO.setCommodityId("1");
        userOrderDTO.setCommodityCounts(1);
        this.userOrderService.createUserOrder(userOrderDTO);
    }

    @Test
    public void deleteUserOrder() throws Exception {

    }

    @Test
    public void updateUserOrder() throws Exception {

    }

}