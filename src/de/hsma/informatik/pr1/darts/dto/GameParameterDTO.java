package de.hsma.informatik.pr1.darts.dto;

public class GameParameterDTO {
	private int points;
	private String[] names;
	private boolean doubleIn, doubleOut;
	
	public GameParameterDTO(int points, String[] names, boolean doubleIn, boolean doubleOut) {
		this.points = points;
		this.names = names;
		this.doubleIn = doubleIn;
		this.doubleOut = doubleOut;
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
	
}
