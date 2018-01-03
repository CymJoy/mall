package com.neusoft.mall.commodity;

import com.neusoft.mall.commodity.domain.CommodityImage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CommodityImageServiceTest {

    @Autowired
    private CommodityImageService commodityImageService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void add() throws Exception {
        CommodityImage commodityImage = new CommodityImage();
        commodityImage.setCommodityId("e20886db68c74928a541406db75b5579");
        commodityImage.setUrl("http://7mno4h.com2.z0.glb.qiniucdn.com/560a409eN35e252de.jpg");
        this.commodityImageService.add(commodityImage);
    }

}