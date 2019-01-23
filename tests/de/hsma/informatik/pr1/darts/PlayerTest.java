package de.hsma.informatik.pr1.darts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTest {
	private Player p;
	
	@BeforeEach
	void setUp() throws Exception {
		p = new Player("Player1", 501);
	}

	@Test
	void testInitialization() {
		assertEquals("Player1", p.getName());
		assertEquals(501, p.getCurrentPoints());
		assertEquals(0, p.getNumberOfDarts());
		
		p = new Player("Phil Taylor", 901);
		assertEquals("Phil Taylor", p.getName());
		assertEquals(901, p.getCurrentPoints());
	}
	
	@Test
	void testScoreManagement() {
		assertEquals(490, p.subtractPoints(11));
		assertEquals(490, p.getCurrentPoints());
		assertEquals(1, p.getNumberOfDarts());
		
		assertEquals(489, p.subtractPoints(1));
		assertEquals(489, p.getCurrentPoints());
		assertEquals(2, p.getNumberOfDarts());
		
		assertEquals(489, p.subtractPoints(0));
		assertEquals(489, p.getCurrentPoints());
		assertEquals(3, p.getNumberOfDarts());
		
		assertEquals(429, p.subtractPoints(60));
		assertEquals(429, p.getCurrentPoints());
		assertEquals(4, p.getNumberOfDarts());
		
		p.resetPointsToPreviousValue(489);
		assertEquals(489, p.getCurrentPoints());
		assertEquals(5, p.getNumberOfDarts());
		
		assertEquals(439, p.subtractPoints(50));
		assertEquals(439, p.getCurrentPoints());
		assertEquals(6, p.getNumberOfDarts());
	}
	
	@Test
	void testThreeDartAverage() {
		p.subtractPoints(50);
		p.subtractPoints(0);
		p.subtractPoints(50);
		
		assertEquals(100, p.getThreeDartAverage(), 0.001);
		
		p.subtractPoints(10);
		p.subtractPoints(5);
		p.subtractPoints(5);

		assertEquals(60, p.getThreeDartAverage(), 0.001);
		
		p.subtractPoints(60);
		p.subtractPoints(60);
		p.subtractPoints(60);
		
		p.subtractPoints(0);
		
		assertEquals(90, p.getThreeDartAverage(), 0.001);
	}

}
