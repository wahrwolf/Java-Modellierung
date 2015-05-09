package automaten;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class PDA extends NFA {
	
	
	protected AutomatenKeller _keller;
	

	public PDA(Set<AutomatenZustand> startZustand, String sprache) {
		super(startZustand, sprache);
		// TODO Auto-generated constructor stub
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
	


}
