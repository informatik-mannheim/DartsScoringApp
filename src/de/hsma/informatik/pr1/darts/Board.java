package de.hsma.informatik.pr1.darts;

import de.hsma.informatik.pr1.darts.dto.ParseResultDTO;

public class Board {
	
	public static ParseResultDTO parseInput(String input) {
		int points = 0;
		int factor = 1;
		
		input = input.toLowerCase().trim();
		
		if (input.startsWith("d")) {
			factor = 2;
			input = input.substring(1);
		} else if (input.startsWith("t")) {
			factor = 3;
			input = input.substring(1);
		}
		
		switch (input) {
			case "bl": case "bull": points = 25; break;
			case "be": case "bull's eye": points = 25; factor = 2; break;
			case "ms": case "bo": case "-": case "--": break;
			default: 
				points = Integer.parseInt(input);
				if (points > 20 && points % 2 == 0) {
					points /= 2;
					factor = 2;
				}
		}
		
		return new ParseResultDTO(input, points, factor);
	}

}
