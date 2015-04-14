import java.util.ArrayList;
import java.util.List;


public class DFA implements Automat {
	private AutomatenZustand _startZustand;
	private AutomatenZustand _aktuellerZustand;
	private List<AutomatenZustand> _endzustaende;
	private boolean _reportToSystemOut = true;
	private String _sprache;

	/**
	 * @param _startZustand
	 * @param _sprache
	 */
	public DFA(AutomatenZustand startZustand,String _sprache) {
		this._startZustand = startZustand;
		this._sprache = _sprache;
		_aktuellerZustand = _startZustand;
		_endzustaende = new ArrayList<AutomatenZustand>();
	}
	
	public DFA(AutomatenZustand startZustand)
	{
		_startZustand=startZustand;
		_aktuellerZustand=_startZustand;
		_sprache = ".*"; 
	}

	@Override
	public AutomatenZustand getStartZustand() {
		// TODO Auto-generated method stub
		return _startZustand;
	}

	/**
	 * Rekursive Methode die mithilfe eines Eingabewortes alle Zustände abläuft
	 * 
	 * @param wort das Eingabewort
	 */
	public void verarbeiteWort(String wort) {
		if (wort.matches(_sprache)) {
			System.out.println("Starte bei "+_aktuellerZustand.toString() );
			System.out.println("Formal: "+_aktuellerZustand.toString() +" " + wort );

			wechsleZustand(wort);
		}else{
			reportError("Wort nicht in definierter Sprache enthalten");
		}
		if (_endzustaende.contains(_aktuellerZustand))
		{
			reportError("Automat nicht in einem Endzustand terminiert");
		}else{
			System.out.println("Erfolgreich durchgelaufen");
			System.out.println("Terminiert bei: "+_aktuellerZustand.toString());
			_aktuellerZustand=_startZustand;
		}
		
	}

	/**
	 * Rekursive Methode die mithilfe eines Eingabewortes alle Zustände abläuft
	 * 
	 * @param wort das Eingabewort
	 */
	public void wechsleZustand(String wort) {
		if (!wort.isEmpty()) {

			char kante = wort.charAt(0);
			_aktuellerZustand = _aktuellerZustand.folgeZustand(kante);
			if (_aktuellerZustand == null) {
				reportError("Automat frühzeitig abgebrochen");
			} else {
				System.out.println("Betrete "+_aktuellerZustand.toString() + " über Kante " + kante);
				System.out.println("Formal: "+_aktuellerZustand.toString() +" " + wort.substring(1) );
				wechsleZustand(wort.substring(1));
			}
		} 

	}

	@Override
	public List<AutomatenZustand> getEndZustand() {
		return _endzustaende;
	}
	
	public void addEndZustand(AutomatenZustand zustand)
	{
		_endzustaende.add(zustand);
	}
	
	public String getSprache()
	{
		return _sprache;
	}
	
	/**
	 * Gibt in Abhängigkeit von _reportToSystemOut eine Fehlermeldung wieder
	 * @param error die auszugebende Fehlermeldung
	 */
	private void reportError(String error)
	{
		if(_reportToSystemOut)
		{
			System.out.println(error);
		}else{
			throw new Error(error);
		}
			
		
	}
}
