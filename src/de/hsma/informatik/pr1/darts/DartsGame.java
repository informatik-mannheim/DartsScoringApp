package de.hsma.informatik.pr1.darts;

public class DartsGame {
	private Player[] players;

	public DartsGame(int points, int numberOfPlayers) {
		players = new Player[numberOfPlayers];
		
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

}