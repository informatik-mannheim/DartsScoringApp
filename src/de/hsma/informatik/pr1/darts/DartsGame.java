package de.hsma.informatik.pr1.darts;

public class DartsGame {
	private Player[] players;
	private int counter;
	private int previousPoints;

	public DartsGame(int points, int numberOfPlayers) {
		counter = 0;
		players = new Player[numberOfPlayers];
		previousPoints = points;
		
		for (int p = 0; p < numberOfPlayers; p++) {
			players[p] = new Player("Player " + (p+1), points);
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