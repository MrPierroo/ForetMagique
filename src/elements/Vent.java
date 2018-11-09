package elements;

import model.Parametres;

public class Vent extends model.Elements{	
	
	private final String NAME = Parametres.NOM_VENT;
	
	public Vent(int x, int y) {
		X = x;
		Y = y;
		Nom = NAME;
	}
}
