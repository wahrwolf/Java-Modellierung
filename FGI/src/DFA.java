import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DFA implements Automat {
	private AutomatenZustand _startZustand;
	private AutomatenZustand _aktuellerZustand;
	private Set<AutomatenZustand> _endzustaende;
	private Map<AutomatenZustand, Map<Character, AutomatenZustand>> _uebergangsFunktion;
	private boolean _reportToSystemOut = true;
	private String _sprache;

	/**
	 * @param _startZustand
	 * @param _sprache
	 */
	public DFA(AutomatenZustand startZustand, String _sprache) {
		this._startZustand = startZustand;
		this._sprache = _sprache;
		_aktuellerZustand = _startZustand;
		_endzustaende = new HashSet<AutomatenZustand>();
		_uebergangsFunktion = new HashMap<AutomatenZustand, Map<Character, AutomatenZustand>>();
	}

	public DFA(AutomatenZustand startZustand) {
		_startZustand = startZustand;
		_aktuellerZustand = _startZustand;
		_sprache = ".*";
	}

	@Override
	public AutomatenZustand getStartZustand() {
		// TODO Auto-generated method stub
		return _startZustand;
	}

	/**
	 * Rekursive Methode die mithilfe eines Eingabewortes alle Zust�nde abl�uft
	 * 
	 * @param wort
	 *            das Eingabewort
	 */
	public void verarbeiteWort(String wort) {
		if (wort.matches(_sprache)) {
			System.out.println("Starte bei " + _aktuellerZustand.toString());
			System.out.println("Formal: " + _aktuellerZustand.toString() + " "
					+ wort);

			wechsleZustand(wort);
		} else {
			reportError("Wort nicht in definierter Sprache enthalten");
		}
		if (_endzustaende.contains(_aktuellerZustand)) {
			reportError("Automat nicht in einem Endzustand terminiert");
		} else {
			System.out.println("Erfolgreich durchgelaufen");
			System.out.println("Terminiert bei: "
					+ _aktuellerZustand.toString());
			_aktuellerZustand = _startZustand;
		}

	}

	/**
	 * Rekursive Methode die mithilfe eines Eingabewortes alle Zust�nde abl�uft
	 * 
	 * @param wort
	 *            das Eingabewort
	 */
	public void wechsleZustand(String wort) {
		if (!wort.isEmpty()) {

			char kante = wort.charAt(0);

			//alternative zu getKante ist: _aktuellerZustand.folgeZustand(kante);
			_aktuellerZustand = getKante(_aktuellerZustand, kante);
			if (_aktuellerZustand == null) {
				reportError("Automat fr�hzeitig abgebrochen");
			} else {
				System.out.println("Betrete " + _aktuellerZustand.toString()
						+ " �ber Kante " + kante);
				System.out.println("Formal: " + _aktuellerZustand.toString()
						+ " " + wort.substring(1));
				wechsleZustand(wort.substring(1));
			}
		}

	}

	@Override
	public Set<AutomatenZustand> getEndZustand() {
		return _endzustaende;
	}
	
	public AutomatenZustand getKante(AutomatenZustand start, char kante)
	{
		return _uebergangsFunktion.get(start).get(kante);
	}

	/**
	 * F�gt eine neue Kante zum DFA hinzu
	 * @param start den Startzustand der Kante
	 * @param kante die Kantenbezeichung
	 * @param ende den Endzustand der Kante
	 */
	public void addKante(AutomatenZustand start, char kante,
			AutomatenZustand ende) {
		if (!_uebergangsFunktion.containsKey(start)) {
			_uebergangsFunktion.put(start,
					new HashMap<Character, AutomatenZustand>());
		}
		_uebergangsFunktion.get(start).put(kante, ende);
	}

	public void addEndZustand(AutomatenZustand zustand) {
		_endzustaende.add(zustand);
	}

	public String getSprache() {
		return _sprache;
	}

	/**
	 * Gibt in Abh�ngigkeit von _reportToSystemOut eine Fehlermeldung wieder
	 * 
	 * @param error
	 *            die auszugebende Fehlermeldung
	 */
	private void reportError(String error) {
		if (_reportToSystemOut) {
			System.out.println(error);
		} else {
			throw new Error(error);
		}

	}
}
