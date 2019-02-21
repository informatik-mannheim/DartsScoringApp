package de.hsma.informatik.pr1.darts.dto;

public class GameParameterDTO {
	private int points, legsToPlay;
	private String[] names;
	private boolean doubleIn, doubleOut;
	
	public GameParameterDTO(int points, boolean doubleIn, boolean doubleOut, int legsToPlay, String... names) {
		this.points = points;
		this.names = names;
		this.doubleIn = doubleIn;
		this.doubleOut = doubleOut;
		this.legsToPlay = legsToPlay;
	}
	
	public int getPoints() {
		return points;
	}
	
	public String[] getNames() {
		return names;
	}

	public boolean isDoubleIn() {
		return doubleIn;
	}

	public boolean isDoubleOut() {
		return doubleOut;
	}

	public int getLegsToPlay() {
		return legsToPlay;
	}
	
}
