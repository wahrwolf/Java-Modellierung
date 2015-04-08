

public class uebung {

	public static void main(String args[])
	{	
		
		AutomatenZustand zs = new AutomatenZustand("zs",false);
		AutomatenZustand ze = new AutomatenZustand("ze",false);
		AutomatenZustand z1 = new AutomatenZustand("z1",true);
		AutomatenZustand z2 = new AutomatenZustand("z2",false);

		

		zs.addFolgeZustand(z1, '1');
		zs.addFolgeZustand(ze, '0');
		ze.addFolgeZustand(ze, '1');
		ze.addFolgeZustand(ze, '0');
		z1.addFolgeZustand(ze, '1');
		z1.addFolgeZustand(z2, '0');
		z2.addFolgeZustand(z1, '1');
		z2.addFolgeZustand(ze, '0');
		
		EndlicherAutomat auto = new EndlicherAutomat(zs,"1(01)*");
		
		auto.verarbeiteWort("10110");
	}
	
	
}
