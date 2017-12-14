package com.neusoft.mall.commodity;

import com.neusoft.mall.commodity.domain.Commodity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by chenyingmiao on 2017/12/6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional
public class CommodityServiceTest {

    @Autowired
    private CommodityService commodityService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void add() throws Exception {
        Commodity commodity = new Commodity();
        commodity.setCounts(10);
        commodity.setDescription("广告栏活动");
        commodity.setName("母婴萌宝");
        commodity.setType(1);
        this.commodityService.add(commodity);
    }

}