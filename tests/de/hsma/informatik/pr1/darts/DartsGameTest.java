package de.hsma.informatik.pr1.darts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import de.hsma.informatik.pr1.darts.dto.CalculationResultDTO;
import de.hsma.informatik.pr1.darts.dto.GameParameterDTO;
import de.hsma.informatik.pr1.darts.dto.ParseResultDTO;
import de.hsma.informatik.pr1.darts.dto.ScoreBoardDTO;

class DartsGameTest {

	@Test
	void testNormalGame() {
		DartsGame game = new DartsGame(new GameParameterDTO(
				501, new String[] {"Player 1", "Player 2"}, false, false, 1));
		
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
		
		int points = game.calculatePointsForCurrentPlayer(new ParseResultDTO("t20", true, 20, 3)).getRemaining();
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
		
		points = game.calculatePointsForCurrentPlayer(new ParseResultDTO("1", true, 1, 1)).getRemaining();
		assertEquals(500, points);
		assertEquals(441, score.getPlayers()[1].getCurrentPoints());
		assertEquals(500, score.getPlayers()[0].getCurrentPoints());
		assertEquals(1, score.getPlayers()[1].getNumberOfDarts());
		assertEquals(1, score.getPlayers()[0].getNumberOfDarts());
		
		game.nextPlayer();   // -> Player 2
		score = game.getScoreBoardInfo();
		points = game.calculatePointsForCurrentPlayer(new ParseResultDTO("d20", true, 20, 2)).getRemaining();
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
		points = game.calculatePointsForCurrentPlayer(new ParseResultDTO("d210", true, 210, 2)).getRemaining();
		assertEquals(401, points );
	}
	
	@Test
	void testDoubleInGame() {
		DartsGame game = new DartsGame(new GameParameterDTO(501, new String[] {"1", "2"}, true, false, 1));
		
		CalculationResultDTO res = game.calculatePointsForCurrentPlayer(
												new ParseResultDTO("t20", true, 20, 3));
		assertEquals(501, res.getRemaining());
		assertEquals("double in", res.getReason());
		
		res = game.calculatePointsForCurrentPlayer(new ParseResultDTO("20", true, 20, 1));
		assertEquals(501, res.getRemaining());
		assertEquals("double in", res.getReason());
		
		res = game.calculatePointsForCurrentPlayer(new ParseResultDTO("d1", true, 1, 2));
		assertEquals(499, res.getRemaining());
		assertEquals("", res.getReason());
	}
	
	@Test
	void testDoubleOutGame() {
		DartsGame game = new DartsGame(new GameParameterDTO(101, new String[] {"1", "2"}, false, true, 1));
		
		CalculationResultDTO res = game.calculatePointsForCurrentPlayer(
											new ParseResultDTO("t20", true, 20, 3));
		assertEquals(41, res.getRemaining());
		assertEquals("", res.getReason());
		
		res = game.calculatePointsForCurrentPlayer(new ParseResultDTO("t20", true, 20, 3));
		assertEquals(101, res.getRemaining());
		assertEquals("busted", res.getReason());
		
		game.nextPlayer();
		
		res = game.calculatePointsForCurrentPlayer(new ParseResultDTO("t20", true, 20, 3));
		assertEquals(41, res.getRemaining());
		assertEquals("", res.getReason());
		
		res = game.calculatePointsForCurrentPlayer(new ParseResultDTO("d20", true, 20, 2));
		assertEquals(101, res.getRemaining());
		assertEquals("busted", res.getReason());
		
		game.nextPlayer();

		res = game.calculatePointsForCurrentPlayer(new ParseResultDTO("T20", true, 20, 3));
		assertEquals(41, res.getRemaining());
		
		res = game.calculatePointsForCurrentPlayer(new ParseResultDTO("1", true, 1, 1));
		assertEquals(40, res.getRemaining());
		
		res = game.calculatePointsForCurrentPlayer(new ParseResultDTO("D19", true, 19, 2));
		assertEquals(2, res.getRemaining());
		
		game.nextPlayer();
		game.nextPlayer();
		
		res = game.calculatePointsForCurrentPlayer(new ParseResultDTO("D1", true, 1, 2));
		assertEquals(0, res.getRemaining());
	}
	
	@Test
	void testMultiLegGame() {
		DartsGame game = new DartsGame(new GameParameterDTO(
				101, new String[] {"Player 1", "Player 2"}, false, false, 3));
		
		game.calculatePointsForCurrentPlayer(new ParseResultDTO("t20", true, 20, 3));
		game.calculatePointsForCurrentPlayer(new ParseResultDTO("d20", true, 20, 2));
		int points = game.calculatePointsForCurrentPlayer(new ParseResultDTO("1", true, 1, 1)).getRemaining();
		assertEquals(0, points);
		
		ScoreBoardDTO score = game.getScoreBoardInfo();
		assertEquals(0, score.getPlayers()[0].getCurrentPoints());
		assertEquals(101, score.getPlayers()[1].getCurrentPoints());
		assertEquals(3, score.getPlayers()[0].getNumberOfDarts());
		assertEquals(0, score.getPlayers()[1].getNumberOfDarts());
		assertEquals(1, score.getPlayers()[0].getLegsWon());
		assertEquals(0, score.getPlayers()[1].getLegsWon());
		
		game.newLeg();
		
		score = game.getScoreBoardInfo();
		assertEquals(101, score.getPlayers()[0].getCurrentPoints());
		assertEquals(101, score.getPlayers()[1].getCurrentPoints());
		assertEquals(0, score.getPlayers()[0].getNumberOfDarts());
		assertEquals(0, score.getPlayers()[1].getNumberOfDarts());
		assertEquals(1, score.getPlayers()[0].getLegsWon());
		assertEquals(0, score.getPlayers()[1].getLegsWon());
		
		// second player should begin second leg
		game.nextPlayer();
		
		game.calculatePointsForCurrentPlayer(new ParseResultDTO("t20", true, 20, 3));
		game.calculatePointsForCurrentPlayer(new ParseResultDTO("d20", true, 20, 2));
		points = game.calculatePointsForCurrentPlayer(new ParseResultDTO("1", true, 1, 1)).getRemaining();
		assertEquals(0, points);
		
		score = game.getScoreBoardInfo();
		assertEquals(0, score.getPlayers()[0].getCurrentPoints());
		assertEquals(101, score.getPlayers()[1].getCurrentPoints());
		assertEquals(3, score.getPlayers()[0].getNumberOfDarts());
		assertEquals(0, score.getPlayers()[1].getNumberOfDarts());
		assertEquals(2, score.getPlayers()[0].getLegsWon());
		assertEquals(0, score.getPlayers()[1].getLegsWon());
		
		assertTrue(game.isWon());

	}

}
