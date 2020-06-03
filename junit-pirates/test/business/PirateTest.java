package business;

import static org.junit.Assert.*;

import org.junit.Test;

public class PirateTest {

	@Test
	public void pirateCreateTest() {
		Pirate p1 = new Pirate("Edward Teach");
		assertEquals("Edward Teach",p1.getName());
		assertEquals(4, p1.getLimbsRemaining());
		assertFalse(p1.isDead());
	}
	@Test
	public void octoPirateTest() {
		Pirate p1 = new Pirate("Octo Pete",8);
		assertEquals(8,p1.getLimbsRemaining());
	}

	@Test(timeout=1000)
	public void infiniteLoopTest() {
		while (true) {
			
		}
	}
	
}
