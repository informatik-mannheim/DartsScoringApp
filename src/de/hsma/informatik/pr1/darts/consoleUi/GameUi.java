package de.hsma.informatik.pr1.darts.consoleUi;

import java.util.Scanner;

import de.hsma.informatik.pr1.darts.Board;
import de.hsma.informatik.pr1.darts.DartsGame;
import de.hsma.informatik.pr1.darts.Player;
import de.hsma.informatik.pr1.darts.dto.CalculationResultDTO;
import de.hsma.informatik.pr1.darts.dto.ParseResultDTO;
import de.hsma.informatik.pr1.darts.dto.ScoreBoardDTO;

public class GameUi {
	private DartsGame game;
	private Scanner kb;

	public GameUi(DartsGame game) {
		super();
		this.game = game;
		kb = ParameterUi.kb;

		System.out.println("A new game begins: " + game.toString());
		System.out.println("Type 'exit' to end the game or 'help' to get a brief usage information.");
		System.out.println();
	}

	public void playLeg() {
		System.out.println("A new leg begins.");
		
		gameLoop:
			do {
				printScoreboard();
				printCurrentPlayer();

				int sum = 0;
				for (int d = 1; d <= 3; d++) {
					System.out.print(d + "> ");

					String input = kb.nextLine();

					switch (input) {
					case "exit":
						break gameLoop;
					case "help":
						printHelp();
						d--;
						continue;
					case "info":
						System.out.println(game.toString());
						d--;
						continue;
					}
			
					ParseResultDTO parseResult = Board.parseInput(input);
					if (!parseResult.isSuccessfullyParsed()) {
						d--;
						System.out.println("\t-> Incorrect input!");
						continue;
					}

					CalculationResultDTO calcResult 
							= game.calculatePointsForCurrentPlayer(parseResult);

					if (calcResult.isValid()) {
						sum += calcResult.getScore();
						System.out.println("\t-> " + calcResult.getRemaining());

						if (calcResult.getRemaining() == 0) {
							System.out.println("Player wins leg!");
							printScoreboard();
							
							break gameLoop;
						} 
					} else {
						System.out.println(calcResult.getReason() 
								+ " -> " + calcResult.getRemaining() + " remaining");

						if (calcResult.getReason().equals("busted")) {
							sum = 0;
							break;
						}
					}

				} // for 
				System.out.println("Sum: " + sum);
				System.out.println();
				
				game.nextPlayer();
			} while(true);
	}

	private void printScoreboard() {
		System.out.println();
		System.out.println(generateScoreboard(game.getScoreBoardInfo()));
	}
	
	private void printCurrentPlayer() {
		System.out.println("Current Player: " + game.getCurrentPlayerName());
	}
	
	private void printHelp() {
		System.out.println();
		System.out.println("How to use the Darts Scoring App:");
		System.out.println("Enter the scores thrown by the user when asked for them.");
		System.out.println("You can use numbers for single scores. Mark double fields");
		System.out.println("with a leading D and triple fields with a T, e.g. T20.");
		System.out.println("Bull can be entered with BL, Bull's Eye with BE. You can type");
		System.out.println("- or MS for a missed dart and BO for a bouncer.");
		System.out.println();
		System.out.println("Type 'Exit' to leave the program or 'info' for details about the current game.");
		System.out.println();
	}

	private String generateScoreboard(ScoreBoardDTO score) {
		StringBuilder sb = new StringBuilder(" " + "-".repeat(10) + "\n");
		sb.append(String.format("| Round %-2d | \n", 
				(score.getPlayerCounter() / score.getPlayers().length + 1)));

		int longest = 6;
		for (Player p : score.getPlayers()) {
			longest = Math.max(longest, p.getName().length());
		}

		String separation = " " + "-".repeat(36 + longest) + "\n";
		sb.append(separation);
		sb.append(String.format("| Player%" + (Math.max(longest - 5, 1)) 
							+ "s| Score | Darts | Legs | 3 D.Avg. |\n", ""));
		sb.append(separation);

		for (Player p : score.getPlayers()) {
			sb.append(String.format("| %" + longest + "s ", p.getName()));
			sb.append(String.format("| %5d ", p.getCurrentPoints()));
			sb.append(String.format("| %5d ", p.getNumberOfDarts()));
			sb.append(String.format("| %4d ", p.getLegsWon()));
			sb.append(String.format("| %8.2f |", p.getThreeDartAverage()));
			sb.append("\n");
		}
		sb.append(separation);

		return sb.toString();
	}
	

	public boolean playAgain(boolean isWon) {
		if (isWon)
			System.out.println("Player wins game!");

		System.out.println();

		System.out.print("Do you want to play another game? (y/n): ");
		return kb.nextLine().toLowerCase().equals("y");
	}

	public void printGoodbye() {
		System.out.println();
		System.out.println("Good bye from the Darts Scoring App!");
	}

}