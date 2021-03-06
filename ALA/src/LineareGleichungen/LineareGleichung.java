package LineareGleichungen;
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
	 * Multipliziert die Gleichung mit einem Faktor. Entspricht Skalar
	 * Multiplikation in einem Vektorraum
	 * 
	 * @param faktor
	 *            der Faktor mit dem die Gleichung multipliziert werden soll
	 * @param writeable
	 *            wenn true ver�ndere diese Gleichung, sonst gib neue Gleichung
	 * @return
	 */
	public LineareGleichung multipliziere(double faktor, boolean writeable) {
		if (!writeable) {
			ArrayList<Term> pufferListe = new ArrayList<Term>();
			for(Term term: _termeA)
			{
				pufferListe.add(new Term(term.getKoeffizient(),term.getVariable()));
			}
			LineareGleichung puffer = new LineareGleichung(pufferListe, _ergebnis);
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

	/**
	 * 
	 * @param summanden
	 */
	public void addiere(LineareGleichung summanden) {
		_ergebnis += summanden.getErgebnis();
		boolean termVorhanden;

		// iteriert �ber alle Terme der Quelle durch
		for (Term summandQuelle : summanden.getKoeffizienten()) {
			termVorhanden = false;
			// und rechnet sie gegen alle Terme des Zieles
			for (Term summandZiel : _termeA) {

				// Solange wie der Term noch nicht hinzugef�gt wurde
				if (!termVorhanden) {
					// pr�fe ob er addiert werden kann
					if (summandZiel.addiere(summandQuelle)) {
						termVorhanden = true;
					}
				}
			}
			// wenn der term nicht addiert werden kann, f�ge ihn einfach ans
			// ende der Liste
			if (!termVorhanden) {
				_termeA.add(summandQuelle);
			}
		}
	}
	
	public LineareGleichung(String gleichung)
	{
	
	}
	

	public String toString() {
		if (!_termeA.isEmpty()) {
			String puffer = _termeA.get(0).toString();
			for (Term term : _termeA) {
				if (term != _termeA.get(0)) {
					puffer = puffer + "+" + term.toString();
				}
			}
			return puffer + " = " + _ergebnis;
		}
		return "";
	}
	
	public int size()
	{
		return _termeA.size();
	}
	
	public LineareGleichung clone()
	{
		LineareGleichung clone = new LineareGleichung(_termeA, _ergebnis);
		
		return clone;
	}
}
