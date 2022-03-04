package com.ait.game.unit;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import com.ait.game.Die;
//import com.ait.game.Player;

class DieTest {

	private Die die_1;
	private Die die_2;
	
	@BeforeEach
	public void setUp() {
		die_1 = new Die();
		die_2 = new Die();
	}
	
	@Test
	public void testInitialDieValue() {
		assertEquals(0, die_1.getValue());
		assertEquals(0, die_2.getValue());
	}
	
	@SuppressWarnings("deprecation")
	@RepeatedTest(200)
	void testDieReturnsValueBetweenLimits() {
		die_1.roll();
		assertThat(die_1.getValue(), allOf(greaterThan(0), lessThan(7)));
	}
}
