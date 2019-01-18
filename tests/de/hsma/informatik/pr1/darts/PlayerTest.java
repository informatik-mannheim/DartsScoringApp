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
		
		assertEquals(489, p.subtractPoints(1));
		assertEquals(489, p.getCurrentPoints());
		
		assertEquals(489, p.subtractPoints(0));
		assertEquals(489, p.getCurrentPoints());
		
		assertEquals(429, p.subtractPoints(60));
		assertEquals(429, p.getCurrentPoints());
		
		p.resetPointsToPreviousValue(489);
		assertEquals(489, p.getCurrentPoints());
		
		assertEquals(439, p.subtractPoints(50));
		assertEquals(439, p.getCurrentPoints());
	}
	
	@Test
	void testDartsCounting() {
		assertEquals(0, p.getNumberOfDarts());
		
		p.addDart();
		assertEquals(1, p.getNumberOfDarts());
		
		p.addDart();
		p.addDart();
		assertEquals(3, p.getNumberOfDarts());
		
		p.addDart();
		p.addDart();
		p.addDart();
		assertEquals(6, p.getNumberOfDarts());
		
		for (int i = 7; i < 65; i++) {
			p.addDart();
			assertEquals(i, p.getNumberOfDarts());
		}
	}

}
