package de.hsma.informatik.pr1.darts.consoleUi;

import de.hsma.informatik.pr1.darts.DartsGame;

public class TextUi {
	private DartsGame game;

	public TextUi(DartsGame game) {
		super();
		this.game = game;
		
		System.out.println("Hello Darts!");
		System.out.println();
	}
	
	public void playGame() {
		System.out.println("A new game begins...");
		System.out.println();
		System.out.println(game.generateScoreboard());
	}
}