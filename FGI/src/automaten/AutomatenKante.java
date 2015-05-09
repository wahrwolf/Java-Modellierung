package automaten;
public class AutomatenKante {
	public static final char SYMBOL_LAMDA = (char) 0x3bb;

	AutomatenZustand _startZustand;
	AutomatenZustand _endZustand;

	char _eingabeSymbolBand;
	char _eingabeSymbolKeller;
	String _ausgabeSymboleKeller;

	/**
	 * 
	 * @param startZustand
	 * @param kante
	 * @param eingabeKeller
	 * @param ausgabeKeller
	 * @param endZustand
	 */
	public AutomatenKante(AutomatenZustand startZustand, char kante,
			char eingabeKeller, String ausgabeKeller,
			AutomatenZustand endZustand) {
		this._startZustand = startZustand;
		this._endZustand = endZustand;
		this._eingabeSymbolBand = kante;
		this._eingabeSymbolKeller = eingabeKeller;
		this._ausgabeSymboleKeller = ausgabeKeller;

	}

	public AutomatenKante(AutomatenZustand start, char symbol,
			AutomatenZustand ende) {
		this(start, symbol, SYMBOL_LAMDA, SYMBOL_LAMDA + "", ende);
	}
	
	public AutomatenKante(AutomatenZustand start, AutomatenZustand ende)
	{
		this(start, SYMBOL_LAMDA, SYMBOL_LAMDA, SYMBOL_LAMDA + "", ende);
	}

	public String gibKeller() {
		return _ausgabeSymboleKeller;
	}

	public AutomatenZustand gibEnde() {
		return _endZustand;
	}
	
	@Override
	public String toString()
	{
		return "("+ _startZustand + ", -[" +
				_eingabeSymbolBand + _eingabeSymbolKeller + "|" + _ausgabeSymboleKeller +
				"]-> "+_endZustand +")";
	}

}
