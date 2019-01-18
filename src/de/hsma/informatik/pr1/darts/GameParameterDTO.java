package de.hsma.informatik.pr1.darts;

public class GameParameterDTO {
	private int points;
	private String[] names;
	
	public GameParameterDTO(int points, String[] names) {
		this.points = points;
		this.names = names;
	}
	
	public int getPoints() {
		return points;
	}
	
	public String[] getNames() {
		return names;
	}
	
}
