import java.util.ArrayList;
import java.util.List;

public class LineareGleichung {

	// Array mit
	private List<Term> _termeA;
	private double _ergebnis;

	public LineareGleichung(int breite) {
		_termeA = new ArrayList<Term>();
	}

	/**
	 * @param _termeA
	 * @param _ergebnis
	 */
	public LineareGleichung(List<Term> _termeA, double _ergebnis) {
		this._termeA = _termeA;
		this._ergebnis = _ergebnis;
	}

	public double getKoeffizient(int index) {
		return _termeA.get(index).getKoeffizient();
	}
	
	/**
	 * Multipliziert die Gleichung mit einem Faktor. 
	 * Entspricht Skalar Multiplikation in einem Vektorraum
	 * 
	 * @param faktor der Faktor mit dem die Gleichung multipliziert werden soll
	 * @param writeable wenn true verändere diese Gleichung, sonst gib neue Gleichung
	 * @return
	 */
	public LineareGleichung multipliziere(double faktor, boolean writeable) {
		if (!writeable) {
			LineareGleichung puffer = new LineareGleichung(new ArrayList<Term>(_termeA),_ergebnis);
			puffer.multipliziere(faktor, true);
			return puffer;
		} else {
			for (Term koeffizient : _termeA) {
				koeffizient.multiplizieren(faktor);
			}
			_ergebnis *= faktor;
			return this;
		}
	}

	public double getErgebnis() {
		return _ergebnis;
	}
	
	public List<Term> getKoeffizienten() {
		return _termeA;
	}

	public void addiere(LineareGleichung summanden) {
		_ergebnis += summanden.getErgebnis();
		
		for (Term summandQuelle : summanden.getKoeffizienten())
		{
			for(Term summandZiel: _termeA)
			{ 
				//todo termweise addition implentieren
			}
		}

	}

}
