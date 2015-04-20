package Vektor;

import java.util.List;

public class Vektor {
	private Skalar[] _vektor;
	private Koerper _koerper;
	private int _size;

	public Vektor(Skalar[] skalare) {
		if (skalare.length > 0) {
			_size = skalare.length;
			_vektor = skalare;
			_koerper = skalare[0].getKoerper();
		}
	}

	public Skalar get(int index) {
		return null;
	}

	public void add(Skalar s) {
	}

	public void set(Skalar s, int index) {
	}

	public Vektor getInverses() {
		return null;
	}

	public Skalar getInveres(int index) {
		return null;
	}

	public Skalar getNull() {
		return null;
	}

}
