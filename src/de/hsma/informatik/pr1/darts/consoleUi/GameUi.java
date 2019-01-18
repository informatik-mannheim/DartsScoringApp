package de.hsma.informatik.pr1.darts.consoleUi;

import java.util.Scanner;

import de.hsma.informatik.pr1.darts.Board;
import de.hsma.informatik.pr1.darts.DartsGame;
import de.hsma.informatik.pr1.darts.Player;
import de.hsma.informatik.pr1.darts.ScoreDTO;

public class GameUi {
	private DartsGame game;
	private Scanner kb;

	public GameUi(DartsGame game) {
		super();
		this.game = game;
		kb = new Scanner(System.in);

		System.out.println();
	}

	public void playGame() {
		System.out.println("A new game begins...");
		System.out.println();

		gameLoop:
		do {
			System.out.println(generateScoreboard(game.getScore()));
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

		System.out.println();
		System.out.println("Good bye from the Darts Scoring App!");
	}
	
	private String generateScoreboard(ScoreDTO score) {
		StringBuilder sb = new StringBuilder(" " + "-".repeat(10) + "\n");
		sb.append(String.format("| Round %-2d | \n", (score.getPlayerCounter() / 2 + 1)));
		
		int longest = 6;
		for (Player p : score.getPlayers()) {
			longest = Math.max(longest, p.getName().length());
		}
		
		String separation = " " + "-".repeat(19 + longest) + "\n";
		sb.append(separation);
		sb.append(String.format("| Player%" + (Math.max(longest - 5, 1)) 
												+ "s| Score | Darts |\n", ""));
		sb.append(separation);
		for (Player p : score.getPlayers()) {
			sb.append(String.format("| %" + longest + "s ", p.getName()));
			sb.append(String.format("| %5d ", p.getCurrentPoints()));
			sb.append(String.format("| %5d |", p.getNumberOfDarts()));
			sb.append("\n");
		}
		sb.append(separation);
		
		return sb.toString();
	}

}