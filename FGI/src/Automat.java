import java.util.List;


public interface Automat
{
    public AutomatenZustand getStartZustand();
    public List<AutomatenZustand> getEndZustand();

}
