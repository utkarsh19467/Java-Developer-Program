package com.example.demo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;

/**
 * The Class EntityTest.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class EntityTest {

	/**
	 * Test item getter setter.
	 */
	@Test
	public void testItemGetterSetter() {
		Item item = new Item();
		item.setId(1L);
		item.setName("Comb");
		item.setDescription("It is a original comb");
		item.setPrice(new BigDecimal(30.50));

		assertTrue(item.getId() == 1L);
		assertTrue(item.getName() == "Comb");
		assertFalse(item.getDescription() == "It is a original commb");
	}

	/**
	 * Test car getter setter.
	 */
	@Test
	public void testCarGetterSetter() {
		Cart cart = new Cart();
		cart.setId(1L);
		cart.setItems(new ArrayList<Item>());
		cart.setTotal(new BigDecimal(350.00));

		assertTrue(cart.getId() == 1L);
		assertTrue(cart.getItems().isEmpty());
	}
}
