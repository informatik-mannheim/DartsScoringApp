package de.hsma.informatik.pr1.darts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import de.hsma.informatik.pr1.darts.dto.GameParameterDTO;
import de.hsma.informatik.pr1.darts.dto.ParseResultDTO;
import de.hsma.informatik.pr1.darts.dto.ScoreBoardDTO;

class DartsGameTest {

	@Test
	void testGame() {
		DartsGame game = new DartsGame(new GameParameterDTO(
				501, new String[] {"Player 1", "Player 2"}, false, false));
		
		String name1 = game.getCurrentPlayerName();
		game.nextPlayer();
		String name2 = game.getCurrentPlayerName();
		
		ScoreBoardDTO score = game.getScoreBoardInfo();
		assertEquals(501, score.getPlayers()[0].getCurrentPoints());
		assertEquals(501, score.getPlayers()[1].getCurrentPoints());
		assertEquals(0, score.getPlayers()[1].getNumberOfDarts());
		assertEquals(0, score.getPlayers()[1].getNumberOfDarts());
		assertEquals(name1, score.getPlayers()[0].getName());
		assertEquals(name2, score.getPlayers()[1].getName());
		assertEquals(1, score.getPlayerCounter());
		
		int points = game.calculatePointsForCurrentPlayer(new ParseResultDTO("t20", 20, 3)).getRemaining();
		assertEquals(441, points);
		
		score = game.getScoreBoardInfo();
		assertEquals(441, score.getPlayers()[1].getCurrentPoints());
		assertEquals(501, score.getPlayers()[0].getCurrentPoints());
		assertEquals(1, score.getPlayers()[1].getNumberOfDarts());
		assertEquals(0, score.getPlayers()[0].getNumberOfDarts());
		assertEquals(name1, score.getPlayers()[0].getName());
		assertEquals(name2, score.getPlayers()[1].getName());

		game.nextPlayer();  // -> Player 1
		score = game.getScoreBoardInfo();
		assertEquals(2, score.getPlayerCounter());
		
		points = game.calculatePointsForCurrentPlayer(new ParseResultDTO("1", 1, 1)).getRemaining();
		assertEquals(500, points);
		assertEquals(441, score.getPlayers()[1].getCurrentPoints());
		assertEquals(500, score.getPlayers()[0].getCurrentPoints());
		assertEquals(1, score.getPlayers()[1].getNumberOfDarts());
		assertEquals(1, score.getPlayers()[0].getNumberOfDarts());
		
		game.nextPlayer();   // -> Player 2
		score = game.getScoreBoardInfo();
		points = game.calculatePointsForCurrentPlayer(new ParseResultDTO("d20", 20, 2)).getRemaining();
		assertEquals(401, points);
		assertEquals(401, score.getPlayers()[1].getCurrentPoints());
		
		game.nextPlayer();  // -> Player 1
		assertEquals(name1, game.getCurrentPlayerName());
		game.nextPlayer();  // -> Player 2
		assertEquals(name2, game.getCurrentPlayerName());
		
		game.nextPlayer();
		game.nextPlayer();
		score = game.getScoreBoardInfo();
		assertEquals(7, score.getPlayerCounter());
		
		// Test for bust
		points = game.calculatePointsForCurrentPlayer(new ParseResultDTO("d210", 210, 2)).getRemaining();
		assertEquals(401, points );
	}

}
