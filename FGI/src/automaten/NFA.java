package automaten;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NFA extends DFA {

	protected Map<AutomatenZustand, Map<Character, Set<AutomatenKante>>> _uebergangsFunktion;

	protected List<List<String>> _commentList = new LinkedList<List<String>>();

	protected Set<AutomatenZustand> _startZustaende;

	/**
	 * @param _startZustaende
	 * @param _sprache
	 */
	public NFA(Set<AutomatenZustand> startZustand, String sprache) {
		super(sprache);
		_uebergangsFunktion = new HashMap<AutomatenZustand, Map<Character, Set<AutomatenKante>>>();
		this._startZustaende = startZustand;
		_endzustaende = new HashSet<AutomatenZustand>();
	}

	public NFA(AutomatenZustand startZustand, String sprache) {
		super(startZustand, sprache);
		_uebergangsFunktion = new HashMap<AutomatenZustand, Map<Character, Set<AutomatenKante>>>();
		_startZustaende = new HashSet<AutomatenZustand>();
		_startZustaende.add(startZustand);
	}

	public NFA(Set<AutomatenZustand> startZustand) {
		super(".*");
		_startZustaende = startZustand;
	}

	public Set<AutomatenZustand> getStartZustaende() {
		return _startZustaende;
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
			System.out.println("Formal: " + _aktuellerZustand + " " + wort);

			for (AutomatenZustand zustand : _startZustaende) {
				stack.clear();
				_aktuellerZustand = zustand;
				wechsleZustand(wort, stack);
				if (_endzustaende.contains(_aktuellerZustand)) {
					System.out.println(stack);
					_commentList.add(stack);
					stack = new LinkedList<String>();
				}
			}
			for (List<String> msg : _commentList) {
				if (!msg.isEmpty()) System.out.println(msg);
			}
			System.out.println("Wortverarbeitung abgeschlossen. Mögliche Pfade sind oben angegeben");

		} else {
			reportError("Wort nicht in definierter Sprache enthalten");
		}

	}

	/**
	 * Rekursive Methode die mithilfe eines Eingabewortes alle Zustände abläuft
	 * 
	 * @param wort
	 *            das Eingabewort
	 */
	@Override
	public void wechsleZustand(String wort, List<String> stack) {
		if (!wort.isEmpty()) {
			AutomatenZustand pufferZustand = _aktuellerZustand;
			char kantenSymbol = wort.charAt(0);
			if (!getErreichbareKanten(pufferZustand, kantenSymbol).isEmpty()) {
				for (AutomatenKante kante : getErreichbareKanten(pufferZustand, kantenSymbol)) 
				{
					_aktuellerZustand = kante.gibEnde();
					//prüft ob aktueller Zustand gültig ist.
					if (_aktuellerZustand == null) reportError("Automat frühzeitig abgebrochen");
					else {
						stack.add("(" 	+ _aktuellerZustand + ","	+ wort.substring(1) + ")" + " via "+ kante);
						wechsleZustand(wort.substring(1), stack);
						stack.remove(stack.size()-1);
						_aktuellerZustand = pufferZustand;
					}
				}

			}		
		}else if(_endzustaende.contains(_aktuellerZustand))
		{
			List<String>puffer = new LinkedList<String>();
			puffer.addAll(stack);
			_commentList.add(puffer);
		}
	}

	public Set<AutomatenKante> getErreichbareKanten(AutomatenZustand start,
			char kantenSymbol) {
		Set<AutomatenKante> puffer = new HashSet<AutomatenKante>();
		if(_uebergangsFunktion.get(start)!=null && _uebergangsFunktion.get(start).get(kantenSymbol)!=null){
			puffer.addAll(_uebergangsFunktion.get(start).get(kantenSymbol));
		}
		if(_uebergangsFunktion.get(start)!=null &&_uebergangsFunktion.get(start).get(AutomatenKante.SYMBOL_LAMDA)!= null){
			puffer.addAll(_uebergangsFunktion.get(start).get(AutomatenKante.SYMBOL_LAMDA));
		}
		return puffer;
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
			_uebergangsFunktion.put(start, new HashMap<Character,Set<AutomatenKante>>());
			
		}
		if(!_uebergangsFunktion.get(start).containsKey(kantenSymbol)){
				_uebergangsFunktion.get(start).put(kantenSymbol, new HashSet<AutomatenKante>());
		}
		_uebergangsFunktion.get(start).get(kantenSymbol).add(new AutomatenKante(start, kantenSymbol, ende));
	}

}