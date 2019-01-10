package de.hsma.informatik.pr1.darts.consoleUi;

import java.util.Scanner;

import de.hsma.informatik.pr1.darts.Board;
import de.hsma.informatik.pr1.darts.DartsGame;

public class TextUi {
	private DartsGame game;
	private Scanner kb;

	public TextUi(DartsGame game) {
		super();
		this.game = game;
		kb = new Scanner(System.in);
		
		System.out.println("Hello Darts!");
		System.out.println();
	}
	
	public void playGame() {
		System.out.println("A new game begins...");
		System.out.println();
		
		do {
			System.out.println(game.generateScoreboard());
			System.out.print("> ");
			
			String input = kb.nextLine().toLowerCase().trim();
			
			if (input.equals("exit"))
				break;
			
			int score = Board.parseInput(input);
			game.subtractPointsForCurrentPlayer(score);
			
		} while(true);
		
		kb.close();
		
		System.out.println("Good bye from the Darts Scoring App!");
	}
	
}