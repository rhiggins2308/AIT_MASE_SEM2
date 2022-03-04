package com.ait.game.integration;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import com.ait.game.PairOfDice;

class PairOfDiceTest {

	PairOfDice dice;
	
	@BeforeEach
	public void setUp() {
		dice = new PairOfDice();
	}
	
	@Test
	void testPairOfDiceInitialized() {
		assertEquals(0, dice.getValue1());
		assertEquals(0, dice.getValue2());
		assertEquals(0, dice.getSum());
	}
	
	@SuppressWarnings("deprecation")
	@RepeatedTest(20)
	void testDiceReturnValuesBetweenLimits() {
		dice.roll();
		assertThat(dice.getValue1(), allOf(greaterThan(0), lessThan(7)));
		assertThat(dice.getValue2(), allOf(greaterThan(0), lessThan(7)));
	}

	@Test
	void testDiceSumEqualsSumIndividualValues() {
		dice.roll();
		assertEquals(dice.getSum(), dice.getValue1()+dice.getValue2());
	}
}
