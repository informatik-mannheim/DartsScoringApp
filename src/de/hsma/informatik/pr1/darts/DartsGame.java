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

	public String generateScoreboard() {
		StringBuilder sb = new StringBuilder();
		
		for (Player p : players) {
			sb.append(p.getName() + ": ");
			sb.append(p.getCurrentPoints() + " points");
			sb.append("\n");
		}
		
		return sb.toString();
	}

	public int subtractPointsForCurrentPlayer(int score) {
		
		int points = players[counter % players.length].subtractPoints(score);
		
		if (points < 0) {
			players[counter % players.length].resetPointsToPreviousValue(previousPoints);
			points = players[counter % players.length].getCurrentPoints() * (-1);
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