package de.hsma.informatik.pr1.darts;

public class Player {
	private final String NAME;
	private int currentPoints;
	private int numberOfDarts;
	
	public Player(String name, int points) {
		NAME = name;
		currentPoints = points;
		numberOfDarts = 0;
	}

	public String getName() {
		return NAME;
	}

	public int getCurrentPoints() {
		return currentPoints;
	}
	
	public int getNumberOfDarts() {
		return numberOfDarts;
	}

	int subtractPoints(int score) {
		currentPoints -= score;
		return currentPoints;
	}

	void resetPointsToPreviousValue(int previousPoints) {
		currentPoints = previousPoints;
	}
	
	void addDart() {
		numberOfDarts++;
	}
	
}