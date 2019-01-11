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
	}

}
