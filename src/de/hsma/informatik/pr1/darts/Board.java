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
				if (input.length() == 0 || input.length() > 2 || !isNumeric(input)) {
					return new ParseResultDTO(input, false, 0, 0);
				}
					
				points = Integer.parseInt(input);
				if (points > 20 && points % 2 == 0) {
					points /= 2;
					factor = 2;
				}
		}
		
		return new ParseResultDTO(input, true, points, factor);
	}
	
	private static boolean isNumeric(String input) {
		for (int c = 0; c < input.length(); c++) {
			if (!(input.charAt(c) >= '0' && input.charAt(c) <= '9')) 
				return false;
		}
		
		return true;
	}

}
