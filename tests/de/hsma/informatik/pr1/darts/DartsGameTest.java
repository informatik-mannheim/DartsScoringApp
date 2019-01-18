package de.hsma.informatik.pr1.darts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DartsGameTest {

	@Test
	void testGame() {
		DartsGame game = new DartsGame(
				new GameParameterDTO(501, new String[] {"Player 1", "Player 2"}));
		
		String name1 = game.getCurrentPlayerName();
		game.nextPlayer();
		String name2 = game.getCurrentPlayerName();
		
		ScoreDTO score = game.getScore();
		assertEquals(501, score.getPlayers()[0].getCurrentPoints());
		assertEquals(501, score.getPlayers()[1].getCurrentPoints());
		assertEquals(0, score.getPlayers()[1].getNumberOfDarts());
		assertEquals(0, score.getPlayers()[1].getNumberOfDarts());
		assertEquals(name1, score.getPlayers()[0].getName());
		assertEquals(name2, score.getPlayers()[1].getName());
		assertEquals(1, score.getPlayerCounter());
		
		assertEquals(441, game.subtractPointsForCurrentPlayer(60));
		
		score = game.getScore();
		assertEquals(441, score.getPlayers()[1].getCurrentPoints());
		assertEquals(501, score.getPlayers()[0].getCurrentPoints());
		assertEquals(1, score.getPlayers()[1].getNumberOfDarts());
		assertEquals(0, score.getPlayers()[0].getNumberOfDarts());
		assertEquals(name1, score.getPlayers()[0].getName());
		assertEquals(name2, score.getPlayers()[1].getName());

		game.nextPlayer();  // -> Player 1
		score = game.getScore();
		assertEquals(2, score.getPlayerCounter());
		
		assertEquals(500, game.subtractPointsForCurrentPlayer(1));
		assertEquals(441, score.getPlayers()[1].getCurrentPoints());
		assertEquals(500, score.getPlayers()[0].getCurrentPoints());
		assertEquals(1, score.getPlayers()[1].getNumberOfDarts());
		assertEquals(1, score.getPlayers()[0].getNumberOfDarts());
		
		game.nextPlayer();   // -> Player 2
		score = game.getScore();
		assertEquals(401, game.subtractPointsForCurrentPlayer(40));
		assertEquals(401, score.getPlayers()[1].getCurrentPoints());
		
		game.nextPlayer();  // -> Player 1
		assertEquals(name1, game.getCurrentPlayerName());
		game.nextPlayer();  // -> Player 2
		assertEquals(name2, game.getCurrentPlayerName());
		
		game.nextPlayer();
		game.nextPlayer();
		score = game.getScore();
		assertEquals(7, score.getPlayerCounter());
		
		// Testen auf Überwerfen
		assertEquals(-401, game.subtractPointsForCurrentPlayer(405));
	}

}
