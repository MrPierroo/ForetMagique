package elements;

import model.Parametres;

public class Portail extends model.Elements{	
	
	private final int POINT = Parametres.POINT_PORTAIL;
	private final String NAME = Parametres.NOM_PORTAIL;
	
	public Portail(int x, int y) {
		X = x;
		Y = y;
		Pts = POINT;
		Nom = NAME;
	}

}
