package de.hsma.informatik.pr1.darts;

public class Board {
	
	public static int parseInput(String input) {
		int points = 0;
		int factor = 1;
		
		if (input.startsWith("d")) {
			factor = 2;
			input = input.substring(1);
		} else if (input.startsWith("t")) {
			factor = 3;
			input = input.substring(1);
		}
		
		switch (input) {
			case "bl": points = 25; break;
			case "be": points = 25; factor = 2; break;
			case "ms": case "bo": break;
			default: points = Integer.parseInt(input);
		}
		
		return points * factor;
	}

}
