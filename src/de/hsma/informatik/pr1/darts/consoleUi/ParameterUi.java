package de.hsma.informatik.pr1.darts.consoleUi;

import java.util.Scanner;

import de.hsma.informatik.pr1.darts.dto.GameParameterDTO;

public class ParameterUi {
	public static Scanner kb = new Scanner(System.in);

	public GameParameterDTO enterGameParameters() {
		
		System.out.println("Hello Darts!");
		System.out.println();
		
		System.out.print("Please enter number of players: ");
		int nop = Integer.parseInt(kb.nextLine());
		String[] names = new String[nop];
		
		for (int i = 0; i < nop; i++) {
			System.out.print("Please enter name for player " + (i+1) + ": ");
			names[i] = kb.nextLine();
		}
		
		System.out.println();
		System.out.print("Please enter the amount of points you want to play: ");
		int points = Integer.parseInt(kb.nextLine());
		
		System.out.print("Do you want to play double in? (y/n): ");
		boolean doubleIn = kb.nextLine().toLowerCase().equals("y");
		System.out.print("Do you want to play double out? (y/n): ");
		boolean doubleOut = kb.nextLine().toLowerCase().equals("y");

		System.out.println();
		
		return new GameParameterDTO(points, names, doubleIn, doubleOut);
	}

}
