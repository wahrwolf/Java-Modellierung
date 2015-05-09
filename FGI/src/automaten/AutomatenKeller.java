package automaten;
import java.util.Stack;


public class AutomatenKeller{
	public static final char SYMBOL_BOTTOM = (char) 0x22a5;
	public static final char SYMBOL_LAMDA = (char) 0x03bb;
	private Stack<Character> _stack;
	private char _kellerBoden;
	
	
	public AutomatenKeller(char kellerBoden)
	{
		_kellerBoden = kellerBoden;
		_stack = new Stack<>();
		_stack.push(_kellerBoden);
	}
	
	/**
	 * Erzeugt einen Keller mit individuellem BodenSymbol
	 * @param utfKellerBoden UTF-Codierung für letzes Zeichen
	 */
	public AutomatenKeller(int utfKellerBoden)
	{
		this((char) utfKellerBoden);
	}
	
	/**
	 * Erzeugt einen Keller mit UTF:0x0000 als unterstes Zeichen
	 */
	public AutomatenKeller()
	{
		this(SYMBOL_BOTTOM);
	}
	

	
	public char push(char zeichen)
	{
		if (zeichen != SYMBOL_LAMDA)
		{
			return _stack.push(zeichen);
		}else if (isEmpty()){
			return SYMBOL_LAMDA;
		}
		return head();
	}
	
	public char pop()
	{
		if (!isEmpty()){
			return _stack.pop();
		}
		return SYMBOL_LAMDA;
	}
	
	public boolean isEmpty()
	{
		return _stack.isEmpty();
	}
	
	public char head()
	{
		if (!isEmpty()){
			return _stack.peek();
		}
		return SYMBOL_LAMDA;
	}

}
