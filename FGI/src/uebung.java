

public class uebung {

	public static void main(String args[])
	{	
		
		AutomatenZustand zs = new AutomatenZustand("zs");
		AutomatenZustand ze = new AutomatenZustand("ze");
		AutomatenZustand z1 = new AutomatenZustand("z1");
		AutomatenZustand z2 = new AutomatenZustand("z2");
		

		

		zs.addFolgeZustand(z1, '1');
		zs.addFolgeZustand(ze, '0');
		ze.addFolgeZustand(ze, '1');
		ze.addFolgeZustand(ze, '0');
		z1.addFolgeZustand(ze, '1');
		z1.addFolgeZustand(z2, '0');
		z2.addFolgeZustand(z1, '1');
		z2.addFolgeZustand(ze, '0');
		
		DFA auto = new DFA(zs,".*");
		
		auto.addEndZustand(z2);
		
		auto.verarbeiteWort("10110");
	}
	
	
}
