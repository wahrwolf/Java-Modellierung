import java.util.Set;


public interface Automat
{
    public AutomatenZustand getStartZustand();
    public Set<AutomatenZustand> getEndZustand();
	public void verarbeiteWort(String wort);

}
