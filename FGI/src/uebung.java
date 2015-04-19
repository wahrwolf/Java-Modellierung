

public class uebung {

	public static void main(String args[])
	{	
		
		AutomatenZustand zs = new AutomatenZustand("zs");
		AutomatenZustand ze = new AutomatenZustand("ze");
		AutomatenZustand z1 = new AutomatenZustand("z1");
		AutomatenZustand z2 = new AutomatenZustand("z2");
		AutomatenZustand zt = new AutomatenZustand("zt");
	
		DFA auto = new DFA(zs,".*");
		
		auto.addKante(zs, '1', ze);
		auto.addKante(zs, '0', z1);
		auto.addKante(z1, '1', ze);
		auto.addKante(z1, '0', zt);
		auto.addKante(z2, '1', ze);
		auto.addKante(z2, '0', zt);
		auto.addKante(ze, '1', ze);
		auto.addKante(ze, '0', z2);
		auto.addKante(zt, '1', zt);
		auto.addKante(zt, '0', zt);
		
		auto.addEndZustand(z2);
		
		auto.verarbeiteWort("101101");
	}
	
	
}
