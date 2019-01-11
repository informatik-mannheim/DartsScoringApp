package de.hsma.informatik.pr1.darts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static de.hsma.informatik.pr1.darts.Board.parseInput;

import org.junit.jupiter.api.Test;

class BoardTest {

	@Test
	void testInputParsingForDoublesAndTriples() {
		assertEquals(60, parseInput("t20"));
		assertEquals(60, parseInput("T20"));
		assertEquals(60, parseInput(" t20 "));
		
		assertEquals(4, parseInput("d2"));
		assertEquals(22, parseInput("D11"));
		assertEquals(2, parseInput(" d1 "));
		
		assertEquals(50, parseInput("50"));
	}
	
	@Test
	void testInputParsingForBullAndBullsEye() {
		assertEquals(25, parseInput("bl"));
		assertEquals(25, parseInput("BL"));
		assertEquals(50, parseInput("be"));
		assertEquals(50, parseInput("BE"));
	}
	
	@Test
	void testInputParsingForSingleScores() {
		assertEquals(25, parseInput("25"));

		for (int i = 0; i < 1; i++) {
			assertEquals(i, parseInput("" + i));
		}
	}
	
	@Test
	void testInputParsingForNilScores() {
		assertEquals(0, parseInput("-"));
		assertEquals(0, parseInput("--"));
		assertEquals(0, parseInput("ms"));
		assertEquals(0, parseInput("bo"));
	}

}
