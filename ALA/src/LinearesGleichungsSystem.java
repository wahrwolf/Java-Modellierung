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

	public void gleichungenAddieren(int indexQuelle, double aFaktor, int indexZiel, double bFaktor) {
		LineareGleichung quelle = _gleichungen[indexQuelle].multipliziere(aFaktor,false);
		LineareGleichung ziel = _gleichungen[indexZiel].multipliziere(bFaktor,true);
		
		ziel.addiere(quelle);

	}

	public void Gauss() {
		double inverses;
		for (int i = 0; (i < _spalten - 1) && (i < _zeilen - 1); i++) {
			// sortiere so dass gleichung[0].getKoeeffizient(0) != 0
			for (int j = i; _gleichungen[0].getKoeffizient(i) == 0
					&& (j < _zeilen); j++) {
				gleichungenVertauschen(0, j);
			}

			// teile gleichung[0].get(0) so das ==1
			inverses = _gleichungen[i].getKoeffizient(i);
			inverses = Math.pow(inverses, -1);
			_gleichungen[i].multipliziere(inverses,true);

			for (int j = i + 1; j < _zeilen - 1; j++) {
				// subtrahiere
				// gleichung[0].get(0)*gleichung[i].getKoeefizient(j)
				gleichungenAddieren(i,_gleichungen[j].getKoeffizient(i),j,1);

			}
		}

	}

	public void gaussJordanVerfahren() {
		this.Gauss();

		for (int i = _zeilen - 1; i > 0; i--) {
			for (int j = i - 1; j > 0; j--) {
				// subrtahiere f�r alle gleichungen so dass in der spalte immer
				// 0 ist
			}
		}
	}
	public void printAll()
	{
	
		for (int i= 0; i< _zeilen; i++)
		{
			System.out.println(_gleichungen[i].toString());
		}
	}

}
