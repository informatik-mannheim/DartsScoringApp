package de.hsma.informatik.pr1.darts.dto;

public class CalculationResultDTO {
	private final int score, remaining;
	private String reason = "";
	
	public CalculationResultDTO(int score, int remaining, String reason) {
		this.score = score;
		this.remaining = remaining;
		this.reason = reason;
	}

	public int getScore() {
		return score;
	}
	
	public int getRemaining() {
		return remaining;
	}
	
	public String getReason() {
		return reason;
	}
	
	public boolean isValid() {
		return "".equals(reason);
	}
	
}
