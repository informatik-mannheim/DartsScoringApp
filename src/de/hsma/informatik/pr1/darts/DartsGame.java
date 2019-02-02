package de.hsma.informatik.pr1.darts;

import de.hsma.informatik.pr1.darts.dto.CalculationResultDTO;
import de.hsma.informatik.pr1.darts.dto.GameParameterDTO;
import de.hsma.informatik.pr1.darts.dto.ParseResultDTO;
import de.hsma.informatik.pr1.darts.dto.ScoreBoardDTO;

public class DartsGame {
	private Player[] players;
	private int counter;
	private int pointsBeforeThisRound;
	private int startingPoints;
	private boolean doubleIn, doubleOut;
	private int legsToPlay, currentLeg;

	public DartsGame(GameParameterDTO params) {
		counter = 0;
		
		startingPoints = pointsBeforeThisRound = params.getPoints();
		doubleIn = params.isDoubleIn() ;
		doubleOut = params.isDoubleOut();
		legsToPlay = params.getLegsToPlay();
		currentLeg = 1;
		
		players = new Player[params.getNames().length];
		for (int p = 0; p < players.length; p++) {
			players[p] = new Player(params.getNames()[p], pointsBeforeThisRound);
		}
	}
	
	public void newLeg() {
		for (Player p : players) {
			p.resetPoints();
		}
		currentLeg++;
		counter = 0;
	}

	public ScoreBoardDTO getScoreBoardInfo() {
		return new ScoreBoardDTO(players, counter);
	}

	private Player getCurrentPlayer() {
		return players[(counter + currentLeg - 1) % players.length];
	}
	
	public CalculationResultDTO calculatePointsForCurrentPlayer(ParseResultDTO parsed) {
		Player player = getCurrentPlayer();
		int oldPoints = player.getCurrentPoints();
		
		int score = parsed.getActualScore();
		CalculationResultDTO result;
		
		if (doubleIn && oldPoints == startingPoints && parsed.getFactor() != 2) {
			result = new CalculationResultDTO(0, startingPoints, "double in");
			player.subtractPoints(0);
		} else if (oldPoints - score < 0
				|| doubleOut && oldPoints - score == 1
				|| doubleOut && oldPoints - score == 0 && parsed.getFactor() != 2) {
			result = new CalculationResultDTO(0, pointsBeforeThisRound, "busted");
			player.resetPointsToPreviousValue(pointsBeforeThisRound);
		} else {
			int remaining = player.subtractPoints(score);
			if (remaining == 0)
				currentPlayerWinsLeg();
			result = new CalculationResultDTO(score, remaining, "");
		}
		
		return result;
	}
	
	public void nextPlayer() {
		counter++;
		pointsBeforeThisRound = getCurrentPlayer().getCurrentPoints();
	}
	
	public boolean isWon() {
		for (Player p : players) {
			if (p.getLegsWon() == (legsToPlay / 2 + 1)) 
				return true;
		}
		
		return false;
	}
	
	private void currentPlayerWinsLeg() {
		getCurrentPlayer().increaseWonLegs();
	}
	
	public String getCurrentPlayerName() {
		return getCurrentPlayer().getName();
	}
 
	public String toString() {
		return players.length + " players, " + startingPoints + " points," 
					+ (doubleIn? "":" no") + " double in and" + (doubleOut? "":" no") + " double out, "
					+ "best of " + legsToPlay + " legs.";
	}
	
}