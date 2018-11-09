package elements;

import model.Parametres;

public class Caca extends model.Elements{	
	
	private final int POINT = 0;
	private final String NAME = Parametres.NOM_CACA;
	
	public Caca(int x, int y) {
		X = x;
		Y = y;
		Pts = POINT;
		Nom = NAME;
	}
	
}
