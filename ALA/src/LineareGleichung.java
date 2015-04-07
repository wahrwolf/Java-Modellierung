import java.util.ArrayList;
import java.util.List;

public class LineareGleichung {
	
	//Array mit
	private ArrayList<Term> _termeA;
	private ArrayList<Term> _ergebnis;

	public LineareGleichung() 
	{
		
	}
	

	public Object getKoeffizient(int index)
	{
		return _termeA.get(index).getKoeffizient();
	}
	
	public LineareGleichung multipliziere(double faktor)
	{
		for(Term koeffizient : _termeA)
		{
			koeffizient.multiplizieren(faktor);
		}
		
		return this;
	}
	
	public List<Term> getErgebnis()
	{
		return _ergebnis;
	}
	
	public void addiere(LineareGleichung summanden)
	{
		for(Term teilergebnis: summanden.getErgebnis())
		{
			
		}
		
	}

}
