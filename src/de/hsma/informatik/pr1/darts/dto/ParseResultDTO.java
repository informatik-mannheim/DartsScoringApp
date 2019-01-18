package de.hsma.informatik.pr1.darts.dto;

public class ParseResultDTO {
	private final String input;
	private final int points, factor;

	public ParseResultDTO(String input, int points, int factor) {
		this.input = input;
		this.points = points;
		this.factor = factor;
	}

	public String getInput() {
		return input;
	}

	public int getPoints() {
		return points;
	}

	public int getFactor() {
		return factor;
	}

	public int getActualScore() {
		return points * factor;
	}

}
