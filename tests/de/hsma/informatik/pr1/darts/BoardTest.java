package de.hsma.informatik.pr1.darts;

import static de.hsma.informatik.pr1.darts.Board.parseInput;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import de.hsma.informatik.pr1.darts.dto.ParseResultDTO;

class BoardTest {

	@Test
	void testInputParsingForDoublesAndTriples() {
		ParseResultDTO res = parseInput("t20 ");
		assertEquals(60, res.getActualScore());
		assertEquals(3, res.getFactor());
		
		res = parseInput("t20");
		assertEquals(60, res.getActualScore());
		assertEquals(3, res.getFactor());
		
		res = parseInput(" T20");
		assertEquals(60, res.getActualScore());
		assertEquals(3, res.getFactor());
		
		res = parseInput("d2");
		assertEquals(4, res.getActualScore());
		assertEquals(2, res.getFactor());
		
		res = parseInput("D11");
		assertEquals(22, res.getActualScore());
		assertEquals(2, res.getFactor());
		
		res = parseInput("2");
		assertEquals(2, res.getActualScore());
		assertEquals(1, res.getFactor());
		
		res = parseInput("50");
		assertEquals(50, res.getActualScore());
		assertEquals(2, res.getFactor());
	}
	
	@Test
	void testInputParsingForBullAndBullsEye() {
		ParseResultDTO res = parseInput("bl");
		assertEquals(25, res.getActualScore());
		res = parseInput("BL");
		assertEquals(25, res.getActualScore());
		
		res = parseInput("be");
		assertEquals(50, res.getActualScore());
		res = parseInput("BE");
		assertEquals(50, res.getActualScore());
		
		res = parseInput("BULL");
		assertEquals(25, res.getActualScore());
		res = parseInput("Bull's Eye");
		assertEquals(50, res.getActualScore());
	}
	
	@Test
	void testInputParsingForSingleScores() {
		ParseResultDTO res = parseInput("25");
		assertEquals(25, res.getActualScore());
		assertEquals(1, res.getFactor());
	
		for (int i = 0; i <= 20; i++) {
			res = parseInput("" + i);
			assertEquals(i, res.getActualScore());
			assertEquals(1, res.getFactor());
		}
	}
	
	@Test
	void testInputParsingForNilScores() {
		ParseResultDTO res = parseInput("-");
		assertEquals(0, res.getActualScore());
		res = parseInput("--");
		assertEquals(0, res.getActualScore());
		res = parseInput("ms");
		assertEquals(0, res.getActualScore());
		res = parseInput("Bo");
		assertEquals(0, res.getActualScore());
	}

}
