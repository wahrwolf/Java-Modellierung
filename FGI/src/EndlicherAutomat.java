import java.util.List;
import java.util.Scanner;


public class EndlicherAutomat implements Automat {
	private AutomatenZustand _startZustand;
	private AutomatenZustand _aktuellerZustand;
	private boolean _reportToSystemOut;
	private String _sprache;

	public static void main(String args[])
	{	String line;
		
		AutomatenZustand z0 = new AutomatenZustand("z0",false);
		AutomatenZustand z1 = new AutomatenZustand("z1",false);
		AutomatenZustand z2 = new AutomatenZustand("z2",true);
		
		z0.addFolgeZustand(z1, '1');
		z0.addFolgeZustand(z0, '0');
		z1.addFolgeZustand(z2, '1');
		z1.addFolgeZustand(z0, '0');
		z2.addFolgeZustand(z2, '1');
		z2.addFolgeZustand(z2, '0');
		
		EndlicherAutomat auto = new EndlicherAutomat(z0,"[01]*11[01]*");
		
		System.out.println("Eingabewort:");
		line = readLine();
		while(!line.isEmpty())
		{
			auto.verarbeiteWort(line);
			System.out.println();
			line=readLine();
		}
	}
	
	private static String readLine() {
		String line;
		System.out.print("$ ");
        Scanner scanner = new Scanner(System.in);
        line = scanner.nextLine();
        scanner.close();
        return line;
    
	}

	/**
	 * @param _startZustand
	 * @param _sprache
	 */
	public EndlicherAutomat(AutomatenZustand startZustand,String _sprache) {
		this._startZustand = startZustand;
		this._sprache = _sprache;
		_aktuellerZustand = _startZustand;
	}
	
	public EndlicherAutomat(AutomatenZustand startZustand)
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
	 * Rekursive Methode die mithilfe eines Eingabewortes alle Zust�nde abl�uft
	 * 
	 * @param wort das Eingabewort
	 */
	public void verarbeiteWort(String wort) {
		if (wort.matches(_sprache)) {
			wechsleZustand(wort);
		}else{
			reportError("Wort nicht in definierter Sprache enthalten");
		}
		if (!_aktuellerZustand.istEndZustand())
		{
			reportError("Automat nicht in einem Endzustand terminiert");
		}else{
			System.out.println("Erfolgreich durchgelaufen");
			System.out.println("Terminiert bei: "+_aktuellerZustand.toString());
			_aktuellerZustand=_startZustand;
		}
		
	}

	/**
	 * Rekursive Methode die mithilfe eines Eingabewortes alle Zust�nde abl�uft
	 * 
	 * @param wort das Eingabewort
	 */
	public void wechsleZustand(String wort) {
		if (!wort.isEmpty()) {

			char kante = wort.charAt(0);
			_aktuellerZustand = _aktuellerZustand.folgeZustand(kante);
			if (_aktuellerZustand == null) {
				reportError("Automat fr�hzeitig abgebrochen");
			} else {
				wechsleZustand(wort.substring(1));
			}
		}

	}

	@Override
	public List<AutomatenZustand> getEndZustand() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Gibt in Abh�ngigkeit von _reportToSystemOut eine Fehlermeldung wieder
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
