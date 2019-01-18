package de.hsma.informatik.pr1.darts;

import de.hsma.informatik.pr1.darts.consoleUi.TextUi;

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
		DartsGame game = new DartsGame(501, 2);
		TextUi ui = new TextUi(game);
		
		ui.playGame();
	}

}