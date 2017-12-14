package com.neusoft.mall;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MallApplicationTests {

	@Test
	public void contextLoads() {
		Assertions.assertThat("123").isEqualTo("123");
	}

}
