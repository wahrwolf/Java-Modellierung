public class LinearesGleichungsSystem {

	private LineareGleichung[] _gleichungen;
	private int _zeilen;
	private int _spalten;

	public LinearesGleichungsSystem(LineareGleichung[] _gleichungen) {
		this._gleichungen = _gleichungen;
	}

	public Object getKoeffizent(int zeilenindex, int spaltenindex) {
		return _gleichungen[zeilenindex].getKoeffizient(spaltenindex);
	}

	public void gleichungenVertauschen(int a, int b) {
		LineareGleichung puffer = _gleichungen[b];

		_gleichungen[b] = _gleichungen[a];
		_gleichungen[a] = puffer;

	}

	public void gleichungenAddieren(int a, double aFaktor, int b, double bFaktor) {
		LineareGleichung pufferA = _gleichungen[a].multipliziere(aFaktor);
		LineareGleichung pufferB = _gleichungen[b].multipliziere(bFaktor);

	}

	public void Gauss() {
		for (int i = 0; i < _spalten -1; i++) {
			// sortiere so dass gleichung[0].getKoeeffizient(0) != 0
			

			// teile gleichung[0].get(0) so das ==1

			for (int j = i + 1; i < _zeilen -1; j++) {
				// subtrahiere gleichung[0].get(0)*gleichung[i].get(0)
			}
		}

	}

	public void gaussJordanVerfahren() {
		this.Gauss();

		for (int i = _zeilen-1; i > 0; i--)
		{
			for (int j = i-1; j>0;j--)
			{
				
			}
		}
	}

}
