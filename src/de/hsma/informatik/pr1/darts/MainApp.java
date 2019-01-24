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
		GameParameterDTO params = new ParameterUi().enterGameParameters();
		
		DartsGame game = new DartsGame(params);
		
		GameUi ui = new GameUi(game);
		ui.playGame();
		
		ParameterUi.kb.close();
	}

}