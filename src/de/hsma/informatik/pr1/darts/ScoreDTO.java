package de.hsma.informatik.pr1.darts;

public class ScoreDTO {
	private final int playerCounter;
	private final Player[] players;
	
	public ScoreDTO(Player[] players, int counter) {
		this.players = players;
		playerCounter = counter;
	}

	public int getPlayerCounter() {
		return playerCounter;
	}

	public Player[] getPlayers() {
		return players;
	}
	
	

}
