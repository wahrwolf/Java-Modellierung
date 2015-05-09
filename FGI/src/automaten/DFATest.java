package automaten;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;


public class DFATest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		AutomatenZustand zs = new AutomatenZustand("StartZustand");
		AutomatenZustand z1 = new AutomatenZustand("Zustand1");
		AutomatenZustand ze = new AutomatenZustand("EndZustand");
		
		DFA _dfa = new DFA(zs);
		
		_dfa.addKante(zs, '0', z1);
		_dfa.addKante(z1, '1', ze);
		_dfa.addKante(ze, '0', z1);
		
		_dfa.addEndZustand(ze);
	}

	@Test
	public void testVerarbeiteWort() {
		fail("Not yet implemented");
	}

	@Test
	public void testWechsleZustand() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetKante() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddKante() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddEndZustand() {
		fail("Not yet implemented");
	}

}
