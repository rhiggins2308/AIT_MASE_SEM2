package com.ait.game.unit;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ait.game.Player;

class PlayerTest {

	private Player player_1;
	private Player player_2;
	
	@BeforeEach
	public void setUp() {
		player_1 = new Player("testPlayer_1");
		player_2 = new Player("testPlayer_2");
	}
	
	@Test
	public void testPlayerName() {
		assertEquals("testPlayer_1", player_1.getName());
		assertEquals("testPlayer_2", player_2.getName());
	}

	@Test
	public void testPlayerInitialScoreZero() {
		assertEquals(0, player_1.getTotalScore());
		assertEquals(0, player_2.getTotalScore());
	}
	
	@Test
	public void testRetrievePlayerScore() {
		assertEquals("Current score for testPlayer_1 is 0", player_1.toString());
		assertEquals("Current score for testPlayer_2 is 0", player_2.toString());
	}
	
	@Test
	public void testScoreUpdate() {
		player_1.setTotalScore(5);
		player_2.setTotalScore(3);
		
		assertEquals(5, player_1.getTotalScore());
		assertEquals(3, player_2.getTotalScore());
	}
	
	@Test
	public void testScoreUpdate2() {
		player_1.setTotalScore(5);
		player_1.setTotalScore(3);
		assertEquals(8, player_1.getTotalScore());
	}
}
