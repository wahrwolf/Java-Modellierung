package LineareGleichungen;
public class Term {

	private double _koeffizient;
	private String _variable;

	public Term(double _koeffizient, String _variable) {
		this._koeffizient = _koeffizient;
		this._variable = _variable;
	}

	public double getKoeffizient() {
		return _koeffizient;
	}

	public void setKoeffizient(double value) {
		_koeffizient = value;
	}

	public String getVariable() {
		return _variable;
	}

	public String toString() {
		return "(" + _koeffizient + " * " + _variable + ")";
	}

	public void multiplizieren(double faktor) {
		_koeffizient *= faktor;
	}

	public boolean addiere(Term summand) {
		if (_variable.equals(summand.getVariable())) {
			_koeffizient += summand.getKoeffizient();
			return true;
		} else {
			return false;
		}
	}
}
