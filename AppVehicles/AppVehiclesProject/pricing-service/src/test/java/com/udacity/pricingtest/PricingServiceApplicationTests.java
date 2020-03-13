package com.udacity.pricingtest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.udacity.pricing.PricingServiceApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PricingServiceApplication.class})
public class PricingServiceApplicationTests {

	@Test
	public void contextLoads() {
	}

}
