package com.neusoft.mall.orders;

import com.neusoft.mall.MallApplication;
import com.neusoft.mall.orders.domain.UserOrder;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by chenyingmiao on 2018/1/3.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MallApplication.class)
@Transactional(rollbackFor = Exception.class)
public class UserOrderServiceTest extends Assertions{

    @Autowired
    private UserOrderService userOrderService;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void createUserOrderExptced() throws Exception {
        UserOrderDTO userOrderDTO = new UserOrderDTO();
        userOrderDTO.setUserId("ff0795d7ea2343cfb68b49952704dc63");
        userOrderDTO.setCommodityCounts(1);
        userOrderDTO.setCommodityId("19f758ec7a2a4624977503aefa8c6883");
        assertTrue(userOrderService.createUserOrder(userOrderDTO));
    }

    @Test
    public void updateStatus() throws Exception {

    }

}