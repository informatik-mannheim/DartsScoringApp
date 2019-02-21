package de.hsma.informatik.pr1.darts.consoleUi;

import java.util.Scanner;

import de.hsma.informatik.pr1.darts.dto.GameParameterDTO;

public class ParameterUi {
	public static Scanner kb = new Scanner(System.in);

	public GameParameterDTO enterGameParameters() {
		System.out.println("Hello Darts!");
		System.out.println("Start by entering the game parameters. Defaults can be selected by merely pressing enter.");
		System.out.println();
		
		int nop = readNumber("Please enter number of players", "2");
		String[] names = new String[nop];
		
		for (int i = 0; i < nop; i++) {
			System.out.print("Please enter name for player " + (i+1) + ": ");
			names[i] = kb.nextLine();
		}
		
		System.out.println();
		int points = readNumber("Please enter the amount of points you want to play", "501");
		
		boolean doubleIn = readYesNo("Do you want to play double in?", "n");
		boolean doubleOut = readYesNo("Do you want to play double out?", "y");

		System.out.println();
		int legsToPlay = readNumber("Please enter how many legs (best of ...) you want to play", "1");
		
		return new GameParameterDTO(points, doubleIn, doubleOut, legsToPlay, names);
	}

	private int readNumber(String prompt, String defaultValue) {
		System.out.print(prompt + " (default = " + defaultValue + "): ");
		
		String input = kb.nextLine();
		if (input.isEmpty()) {
			input = defaultValue;
			System.out.println("--> " + input);
		}
		
		return Integer.parseInt(input);
	}
	
	private boolean readYesNo(String question, String defaultValue) {
		System.out.print(question + " (y/n; default = " + defaultValue + "): ");
		
		String input = kb.nextLine().toLowerCase();
		
		if (input.isEmpty()) {
			input = defaultValue;
			System.out.println("--> " + input);
		}
			
		return input.equals("y");
	}
	
}
