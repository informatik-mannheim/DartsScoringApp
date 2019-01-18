package de.hsma.informatik.pr1.darts.dto;

import de.hsma.informatik.pr1.darts.Player;

public class ScoreBoardDTO {
	private final int playerCounter;
	private final Player[] players;
	
	public ScoreBoardDTO(Player[] players, int counter) {
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
