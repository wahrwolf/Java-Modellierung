package Vektor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Vektorraum extends Koerper{
	private Koerper _koerper;
	private int _dimensionen;
	private Set<Vektor> _basis;
	
	
	/**
	 * erzeugt einen Vektorraum nach Basisergaängzussatz
	 * @param v
	 * @param w
	 */
	public Vektorraum(Set<Vektor> v, Set<Vektor> w)
	{
		for (Vektor v1: v)
		{
			for (Vektor v2: v)
			{
				assert (v1!=v2) && istLinearAbhaengig(v1, v2): "Vektoren sind linear Abhängig, kann keinen Vektorraum erzeugen";
			}
			for (Vektor v2: w)
			{
				assert (v1!=v2) && istLinearAbhaengig(v1, v2): "Vektoren sind linear Abhängig, kann keinen Vektorraum erzeugen";
			}
			
		}
		
		for (Vektor v1: w)
		{
			for (Vektor v2: w)
			{
				assert (v1!=v2) && istLinearAbhaengig(v1, v2): "Vektoren sind linear Abhängig, kann keinen Vektorraum erzeugen";
			}
			
		}
		_basis = new HashSet <Vektor>();
		_basis.addAll(v);
		_basis.addAll(w);
		_dimensionen = _basis.size();
	}
	
	/**
	 * Erzeugt einen Vektorraum mithilfe der Basis
	 * @param basis
	 */
	public Vektorraum(Set<Vektor> erzeugendenSystem)
	{
		for (Vektor v1: erzeugendenSystem)
		{
			for (Vektor v2: erzeugendenSystem)
			{
				assert (v1!=v2) && istLinearAbhaengig(v1, v2): "Vektoren sind linear Abhängig, kann keinen Vektorraum erzeugen";
			}
		}
		_basis = new HashSet <Vektor>();
		_basis.addAll(erzeugendenSystem);
		_dimensionen = _basis.size();
	}
	
	public void plus(Element v1, Vektor v2)
	{
		
	}
	
	public void plus(Vektor v, Skalar s)
	{
		
	}
	
	public void mal(Vektor v, Skalar s)
	{
		
	}
	
	public boolean istLinearAbhaengig(Vektor v1, Vektor v2)
	{
		return false;
	}
	
	public void lin(List<Vektor> vektoren)
	{
		
	}
	
	 

}
