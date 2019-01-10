package de.hsma.informatik.pr1.darts;

public class Player {
	private final String NAME;
	private int currentPoints;
	
	public Player(String name, int points) {
		NAME = name;
		currentPoints = points;
	}

	public String getName() {
		return NAME;
	}

	public int getCurrentPoints() {
		return currentPoints;
	}

	public int subtractPoints(int score) {
		currentPoints -= score;
		return currentPoints;
	}
}