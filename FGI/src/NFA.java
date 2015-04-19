import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class NFA implements Automat{


	private Set<AutomatenZustand> _startZustand;
	private AutomatenZustand _aktuellerZustand;
	private Set<AutomatenZustand> _endzustaende;
	private boolean _reportToSystemOut = true;
	private String _sprache;

	/**
	 * @param _startZustand
	 * @param _sprache
	 */
	public NFA(Set<AutomatenZustand> startZustand,String _sprache) {
		this._startZustand = startZustand;
		this._sprache = _sprache;
		_endzustaende = new HashSet<AutomatenZustand>();
	}
	
	public NFA(Set<AutomatenZustand> startZustand)
	{
		_startZustand=startZustand;
		_sprache = ".*"; 
	}

	public Set<AutomatenZustand> getStartZustaend() {
		// TODO Auto-generated method stub
		return _startZustand;
	}
	
	public AutomatenZustand getStartZustand(){
		return null;
	}

	/**
	 * Rekursive Methode die mithilfe eines Eingabewortes alle Zustände abläuft
	 * 
	 * @param wort das Eingabewort
	 */
	public void verarbeiteWort(String wort) 
	{
		List<String> stack = new LinkedList<String>();
		if (wort.matches(_sprache)) {
			System.out.println("Starte bei "+_aktuellerZustand.toString() );
			System.out.println("Formal: "+_aktuellerZustand.toString() +" " + wort );

			for(AutomatenZustand zustand: _startZustand)
				{
					stack.clear();
					_aktuellerZustand = zustand;
					wechsleZustand(wort, stack);
					if ( _endzustaende.contains(_aktuellerZustand) )
					{
						break;
					} else {
						stack.add("Automat terminiert nicht in Endzustand");
					}
					
					for (String comment: stack)
					{
						System.out.println(comment);
					}
				}
			
		}else{
			reportError("Wort nicht in definierter Sprache enthalten");
		}
		
		
	}

	/**
	 * Rekursive Methode die mithilfe eines Eingabewortes alle Zustände abläuft
	 * 
	 * @param wort das Eingabewort
	 */
	public void wechsleZustand(String wort, List<String> stack) {
		if (!wort.isEmpty()) {

			char kante = wort.charAt(0);
			_aktuellerZustand = _aktuellerZustand.folgeZustand(kante);
			if (_aktuellerZustand == null) {
				reportError("Automat frühzeitig abgebrochen");
			} else {
				stack.add("Betrete "+_aktuellerZustand.toString() + " über Kante " + kante);
				stack.add("Formal: "+_aktuellerZustand.toString() +" " + wort.substring(1) );
				wechsleZustand(wort.substring(1), stack);
			}
		} 

	}

	@Override
	public Set<AutomatenZustand> getEndZustand() {
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
