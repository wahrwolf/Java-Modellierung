package Vektor;


public interface Vektor {
	//private List<Double> Vektor;
	public Skalar get(int index);
	
	public void add(Skalar s);
	
	public void set(Skalar s, int index);
	
	public Vektor getInverses();
	
	public Skalar getInveres(int index);
	
	public Skalar getNull();

}
