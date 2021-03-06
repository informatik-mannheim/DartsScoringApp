package de.hsma.informatik.pr1.darts;

public class Player {
	private final String NAME;
	private int currentPoints;
	private int startingPoints;
	private int numberOfDarts;
	private int numberOfLegsWon;
	
	public Player(String name, int points) {
		NAME = name;
		startingPoints = currentPoints = points;
		numberOfDarts = 0;
		numberOfLegsWon = 0;
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
		numberOfDarts++;
		currentPoints -= score;
		return currentPoints;
	}

	void resetPointsToPreviousValue(int previousPoints) {
		numberOfDarts++;
		currentPoints = previousPoints;
	}
	
	void resetPoints() {
		currentPoints = startingPoints;
		numberOfDarts = 0;
	}

	public double getThreeDartAverage() {
		double avg = 1.0 * (startingPoints - currentPoints) / numberOfDarts * 3;
		return avg;
	}
	
	void increaseWonLegs() {
		numberOfLegsWon++;
	}
	
	public int getLegsWon() {
		return numberOfLegsWon;
	}
	
}