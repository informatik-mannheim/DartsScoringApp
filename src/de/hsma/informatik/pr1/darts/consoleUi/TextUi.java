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

		gameLoop:
		do {
			System.out.println(game.generateScoreboard());
			System.out.println("Next Player: " + game.getCurrentPlayerName());

			int sum = 0;
			for (int d = 1; d <= 3; d++) {
				System.out.print(d + "> ");

				String input = kb.nextLine();

				if (input.equals("exit"))
					break gameLoop;

				int score = Board.parseInput(input);
				sum += score;
				
				int remaining = game.subtractPointsForCurrentPlayer(score);
				System.out.println("\t-> " + Math.abs(remaining));
				
				if (remaining == 0) {
					System.out.println("Player wins!");
					break gameLoop;
				} else if (remaining < 0) {
					System.out.println("Overthrown!");
					sum = 0;
					break;
				}
			}
			System.out.println("Sum: " + sum);
			
			System.out.println();
			game.nextPlayer();
		} while(true);

		kb.close();

		System.out.println();
		System.out.println("Good bye from the Darts Scoring App!");
	}

}