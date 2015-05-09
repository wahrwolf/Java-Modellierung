package automaten;
import java.util.Set;


public interface Automat
{
    public Set<AutomatenZustand> getEndZustaende();
    public Set<AutomatenZustand> getStartZustaende();
	public void verarbeiteWort(String wort);

}
