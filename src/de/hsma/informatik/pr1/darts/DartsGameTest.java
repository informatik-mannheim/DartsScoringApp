package de.hsma.informatik.pr1.darts;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DartsGameTest {

	@Test
	void testGame() {
		DartsGame game = new DartsGame(501, 2);
		
		String name1 = game.getCurrentPlayerName();
		game.nextPlayer();
		String name2 = game.getCurrentPlayerName();
		
		String sb = game.generateScoreboard();
		assertTrue(sb.contains("501"));
		assertTrue(sb.contains(name1));
		assertTrue(sb.contains(name2));
		assertTrue(sb.contains("Round 1"));
		assertTrue(sb.contains("0 darts"));
		
		assertEquals(441, game.subtractPointsForCurrentPlayer(60));
	
		game.nextPlayer();  // -> Player 1
		sb = game.generateScoreboard();
		assertTrue(sb.contains("501"));
		assertTrue(sb.contains("441"));
		assertTrue(sb.contains("Round 2"));
		
		assertEquals(500, game.subtractPointsForCurrentPlayer(1));
		sb = game.generateScoreboard();
		assertTrue(sb.contains("500"));
		assertTrue(sb.contains("441"));
		assertTrue(sb.contains("Round 2"));
		
		game.nextPlayer();   // -> Player 2
		assertEquals(401, game.subtractPointsForCurrentPlayer(40));
		sb = game.generateScoreboard();
		assertTrue(sb.contains("401"));
		
		game.nextPlayer();  // -> Player 1
		assertEquals(name1, game.getCurrentPlayerName());
		game.nextPlayer();  // -> Player 2
		assertEquals(name2, game.getCurrentPlayerName());
		
		game.nextPlayer();
		game.nextPlayer();
		sb = game.generateScoreboard();
		assertTrue(sb.contains("Round 4"));
	}

}
