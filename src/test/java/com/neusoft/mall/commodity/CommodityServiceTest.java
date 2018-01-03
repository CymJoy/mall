package com.neusoft.mall.commodity;

import com.neusoft.mall.commodity.domain.Commodity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


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

    @Test
    public void delete() throws Exception {
        this.commodityService.removeById("1");
    }

    @Test
    public void updateById() throws Exception {
        Commodity commodity = this.commodityService.findCommodityById("1");
        System.out.println(commodity.getCounts()+commodity.getId()+"---------------");
        commodity.setCounts(10000);
        System.out.println("==========="+this.commodityService.updateById(commodity));
    }

    @Test
    public void select() throws Exception {
        Commodity commodity = this.commodityService.findCommodityById("1");
        System.out.print(commodity.getId()+commodity.getName()+"-----------------");
    }

}