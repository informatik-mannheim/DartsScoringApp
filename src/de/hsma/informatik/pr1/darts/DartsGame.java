package de.hsma.informatik.pr1.darts;

public class DartsGame {
	private Player[] players;
	private int counter;
	private int previousPoints;

	public DartsGame(GameParameterDTO params) {
		counter = 0;
		players = new Player[params.getNames().length];
		previousPoints = params.getPoints();
		
		for (int p = 0; p < players.length; p++) {
			players[p] = new Player(params.getNames()[p], previousPoints);
		}
	}

	public ScoreDTO getScore() {
		return new ScoreDTO(players, counter);
	}

	public int subtractPointsForCurrentPlayer(int score) {
		Player player = players[counter % players.length];
		
		player.addDart();
		int points = player.subtractPoints(score);
		
		if (points < 0) {
			player.resetPointsToPreviousValue(previousPoints);
			points = player.getCurrentPoints() * (-1);
		}
			
		return points;
	}
	
	public void nextPlayer() {
		counter++;
		
		previousPoints = players[counter % players.length].getCurrentPoints();
	}
	
	public String getCurrentPlayerName() {
		return players[counter % players.length].getName();
	}
 
}