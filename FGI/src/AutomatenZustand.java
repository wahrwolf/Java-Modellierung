import java.util.ArrayList;
import java.util.List;



public class AutomatenZustand
{
	private List<AutomatenZustand> _folgeZustaende;
	private List<Character> _folgeKanten;
	private String _bezeichung;
	
	public AutomatenZustand(String bezeichung)
	{
		_folgeZustaende = new ArrayList<AutomatenZustand>();
		_folgeKanten = new ArrayList<Character>();
		_bezeichung = bezeichung;
	}
	
   
    /**
     * Gibt den Folgezustand eines Automaten wieder indem an den Ausgangskanten gesucht wird
     * 
     * @param die Kante über den der Zustand errreichbar ist
     * @return gibt den Folgezustand oder wenn nicht vorhanden null zurück
     */
    public AutomatenZustand folgeZustand(char kante)
    {
    	for (char gegenkante: _folgeKanten)
    	{
    		if (kante ==gegenkante)
    		{
    			return _folgeZustaende.get(_folgeKanten.indexOf(gegenkante));
    		}
    	}
    	return null;
    }
    
    /**
     * Gibt den Namen des Knotens zurück
     */
    @Override
    public String toString()
    {
    	return _bezeichung;
    }
    
    public boolean equals(Object object)
    {
    	if (object instanceof AutomatenZustand)
    	{
			AutomatenZustand zustand = (AutomatenZustand) object;
			return _bezeichung.equals(zustand.toString());
		}else{
			return false;
		}
		
    }
    
    /**
     * fügt einen weiteren Folgezustand hinzu
     * @param zustand der nächste Zustand
     * @param kante die Kante über den er erreichbar ist
     */
    public void addFolgeZustand(AutomatenZustand zustand, char kante)
    {
    	_folgeKanten.add(kante);
    	_folgeZustaende.add(zustand);
    }
    

}
