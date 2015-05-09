package automaten;


public class uebung {

	public static void main(String args[])
	{	
		
		AutomatenZustand z0 = new AutomatenZustand("z0");
		AutomatenZustand z1 = new AutomatenZustand("z1");
		AutomatenZustand z2 = new AutomatenZustand("z2");
		
		NFA auto = new NFA(z0,".*");
		
		auto.addKante(z0, '1', z0);
		auto.addKante(z0, '0', z0);

		auto.addKante(z0, '1', z1);

		auto.addKante(z1, '1', z2);
		
		auto.addKante(z2, '1', z2);
		auto.addKante(z2, '0', z2);

		
		auto.addEndZustand(z2);
		
		auto.verarbeiteWort("01111");
	}
	
	
}
