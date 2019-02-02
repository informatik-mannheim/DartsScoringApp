package de.hsma.informatik.pr1.darts;

import de.hsma.informatik.pr1.darts.consoleUi.ParameterUi;
import de.hsma.informatik.pr1.darts.dto.GameParameterDTO;
import de.hsma.informatik.pr1.darts.consoleUi.GameUi;

/**
 * DartsScoringApp
 * A simple Java application for counting scores in Dart games.
 * 
 * Intended as a manageable real world example in Java programming 
 * for first semester computer science students at Hochschule Mannheim.
 * 
 * @author Oliver Hummel (https://www.informatik.hs-mannheim.de/hummel.html)
 *
 */
public class MainApp {

	public static void main(String[] args) {
		GameUi ui;
		DartsGame game;
		GameParameterDTO params = null;
		char continuePlaying = ' ';

		do {
			if (continuePlaying != 'r')
				params = new ParameterUi().enterGameParameters();
			
			game = new DartsGame(params);
			ui = new GameUi(game);
			
			do {
				if (!ui.playLeg())
					break;
				
				game.newLeg();
			} while (!game.isWon());
			
			continuePlaying = ui.playAgain();
		} while (continuePlaying == 'y' || continuePlaying == 'r');

		ui.printGoodbye();
		ParameterUi.kb.close();
	}

}