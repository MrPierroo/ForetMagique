package elements;

import model.Parametres;

public class Portail extends model.Elements{	

	private final String NAME = Parametres.NOM_PORTAIL;
	
	public Portail(int x, int y) {
		X = x;
		Y = y;
		Nom = NAME;
	}

}
