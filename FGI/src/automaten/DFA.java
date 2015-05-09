package automaten;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DFA implements Automat {
	private AutomatenZustand _startZustand;
	protected AutomatenZustand _aktuellerZustand;
	protected Set<AutomatenZustand> _endzustaende;
	protected Map<AutomatenZustand, Map<Character, AutomatenKante>> _uebergangsFunktion;
	private boolean _reportToSystemOut = true;
	protected String _sprache;

	/**
	 * @param _startZustand
	 * @param _sprache
	 * 
	 * @require startZustand != null
	 * @require !_sprache.isEmpty()
	 * 
	 * @ensure _aktuellerZustand != null
	 * @ensure _endzustaende != null
	 * @ensure _uebergangsFunktion != null
	 */
	public DFA(AutomatenZustand startZustand, String _sprache) {
		this._startZustand = startZustand;
		this._sprache = _sprache;
		_aktuellerZustand = _startZustand;
		_endzustaende = new HashSet<AutomatenZustand>();
		_uebergangsFunktion = new HashMap<AutomatenZustand, Map<Character, AutomatenKante>>();
	}

	public DFA(AutomatenZustand startZustand) {
		this(startZustand,".*");
		_startZustand = startZustand;
	}
	
	public DFA(String sprache)
	{
		this(new AutomatenZustand("zs"),sprache);
	}

	@Override
	public Set<AutomatenZustand> getStartZustaende() {
		Set<AutomatenZustand> puffer = new HashSet<AutomatenZustand>();
		puffer.add(_startZustand);
		return puffer;
	}

	/**
	 * Rekursive Methode die mithilfe eines Eingabewortes alle Zustände abläuft
	 * 
	 * @param wort
	 *            das Eingabewort
	 */
	public void verarbeiteWort(String wort) {
		List<String> stack = new LinkedList<String>();

		if (wort.matches(_sprache)) {
			System.out.println("Starte bei " + _aktuellerZustand);
			System.out.println("Formal: (" + _aktuellerZustand + ", " + wort + ")");

			wechsleZustand(wort,stack);
		} else {
			reportError("Wort nicht in definierter Sprache enthalten");
		}
		if (_endzustaende.contains(_aktuellerZustand)) {
			reportError("Automat nicht in einem Endzustand terminiert");
		} else {
			System.out.println(stack);
			System.out.println("Erfolgreich durchgelaufen");
			System.out.println("Terminiert bei: "
					+ _aktuellerZustand.toString());
			_aktuellerZustand = _startZustand;
		}

	}

	/**
	 * Rekursive Methode die mithilfe eines Eingabewortes alle Zustände abläuft
	 * 
	 * @param wort
	 *            das Eingabewort
	 */
	public void wechsleZustand(String wort, List<String> stack) {
		if (!wort.isEmpty()) {

			char kantenSymbol = wort.charAt(0);

			_aktuellerZustand = getKante(_aktuellerZustand, kantenSymbol).gibEnde();
			if (_aktuellerZustand == null) {
				reportError("Automat frühzeitig abgebrochen");
			} else {
				stack.add("("+_aktuellerZustand + "," + wort.substring(1) +")" + " via "+
						getKante(_aktuellerZustand, kantenSymbol));
				wechsleZustand(wort.substring(1), stack);
			}
		}

	}

	@Override
	public Set<AutomatenZustand> getEndZustaende() {
		return _endzustaende;
	}
	
	public AutomatenKante getKante(AutomatenZustand start, char kante)
	{
		return _uebergangsFunktion.get(start).get(kante);
	}

	/**
	 * Fügt eine neue Kante zum DFA hinzu
	 * @param start den Startzustand der Kante
	 * @param kante die Kantenbezeichung
	 * @param ende den Endzustand der Kante
	 */
	public void addKante(AutomatenZustand start, char kantenSymbol, AutomatenZustand ende)
	{
		if (!_uebergangsFunktion.containsKey(start)) {
			_uebergangsFunktion.put(start, new HashMap<Character, AutomatenKante>());
		}
		_uebergangsFunktion.get(start).put(kantenSymbol, new AutomatenKante(start, kantenSymbol, ende));
	}

	public void addEndZustand(AutomatenZustand zustand) {
		_endzustaende.add(zustand);
	}

	public String getSprache() {
		return _sprache;
	}

	/**
	 * Gibt in Abhängigkeit von _reportToSystemOut eine Fehlermeldung wieder
	 * 
	 * @param error
	 *            die auszugebende Fehlermeldung
	 */
	protected void reportError(String error) {
		if (_reportToSystemOut) {
			System.out.println(error);
		} else {
			throw new Error(error);
		}

	}
}
