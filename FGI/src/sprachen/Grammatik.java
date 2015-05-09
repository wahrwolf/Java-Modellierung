package sprachen;

import java.util.Map;
import java.util.Set;

public class Grammatik {

	//Nonterminal (enthählt Nonterminale oder Terminale)
	//Terminale Menge an Zeichen
	private Set<Character> _nonTerminale;
	private Set<Character> _terminale;
	
	private Map<Character, Set<Character>> _ableitungen;
}
