
public class LinearesGleichungsSystem {
	
	private LineareGleichung[] _gleichungen;

	public LinearesGleichungsSystem(LineareGleichung[] _gleichungen) {
		this._gleichungen = _gleichungen;
	}
	
	public Object getKoeffizent(int zeilenindex, int spaltenindex)
	{
		return _gleichungen[zeilenindex].getKoeffizient(spaltenindex);
	}
	
	
	public void gleichungenVertauschen(int a, int b)
	{
		LineareGleichung puffer = _gleichungen[b];
		
		_gleichungen[b] = _gleichungen[a];
		_gleichungen[a] = puffer;
		
	}
	
	public void gleichungenAddieren(int a, double aFaktor, int b, double bFaktor)
	{
		LineareGleichung pufferA = _gleichungen[a].multipliziere(aFaktor);
		LineareGleichung pufferB = _gleichungen[b].multipliziere(bFaktor);
		
	}

}
