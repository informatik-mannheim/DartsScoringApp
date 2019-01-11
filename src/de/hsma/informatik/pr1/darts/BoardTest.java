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
	}

}
