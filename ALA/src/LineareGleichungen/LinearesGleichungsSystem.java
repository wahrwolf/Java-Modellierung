package LineareGleichungen;
public class LinearesGleichungsSystem {

	private LineareGleichung[] _gleichungen;
	private int _zeilen;
	private int _spalten;

	public LinearesGleichungsSystem(LineareGleichung[] _gleichungen) {
		this._gleichungen = _gleichungen;
		_zeilen = _gleichungen.length;
		_spalten = _gleichungen[0].size();
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
		LineareGleichung quelle = _gleichungen[indexQuelle].clone() ;
		quelle.multipliziere(aFaktor, true); //indexQuelle].multipliziere(aFaktor,false);
		LineareGleichung ziel = _gleichungen[indexZiel].multipliziere(bFaktor,true);
		
		ziel.addiere(quelle);

	}

	public void Gauss() {
		double inverses;
		double faktor;
		for (int i = 0; (i < _spalten ) && (i < _zeilen ); i++) {
			// sortiere so dass gleichung[0].getKoeeffizient(0) != 0
			for (int j = i; _gleichungen[i].getKoeffizient(i) == 0
					&& (j < _zeilen); j++) {
				gleichungenVertauschen(i, j);
			}
			System.out.println("Gleichungen tauschen");
			printAll();

			// teile gleichung[0].get(0) so das ==1
			inverses = _gleichungen[i].getKoeffizient(i);
			inverses = Math.pow(inverses, -1);
			System.out.println("Gleichungen normalisieren");
			System.out.println("["+i+"]"+_gleichungen[i].toString() + "| *" + inverses);
			_gleichungen[i].multipliziere(inverses,true);
			System.out.println("["+i+"]"+_gleichungen[i].toString());
			
			faktor = 0;
			System.out.println();
			System.out.println("Gleichungen verrechnen");
			for (int j = i + 1; j < _zeilen ; j++) {
				// subtrahiere
				// gleichung[0].get(0)*gleichung[i].getKoeefizient(j)
				faktor = -1* _gleichungen[j].getKoeffizient(i);
				System.out.println("["+j+"]"+_gleichungen[j].toString()+"| - gleichung["+i+"]"	+ " * "+faktor);
				gleichungenAddieren(i,faktor,j,1);
				System.out.println("["+j+"]"+_gleichungen[j].toString());
			}
			System.out.println();
		}
		System.out.println("End of Gauss");
		
		System.out.println();

	}

	public void gaussJordanVerfahren() {
		this.Gauss();

		for (int i = _zeilen - 1; i > 0; i--) {
			for (int j = i - 1; j > 0; j--) {
				// subrtahiere für alle gleichungen so dass in der spalte immer
				// 0 ist
			}
		}
	}
	public void printAll()
	{
	
		for (int i= 0; i< _zeilen; i++)
		{
			System.out.println("["+i+"]"+_gleichungen[i].toString());
		}
		System.out.println();
	}

}
