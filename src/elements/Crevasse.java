package elements;

import model.Parametres;

public class Crevasse extends model.Elements{	
	
	private final int POINT = Parametres.COUT_MORT;
	private final String NAME = Parametres.NOM_CREVASSE;
	
	public Crevasse(int x, int y) {
		X = x;
		Y = y;
		Pts = POINT;
		Nom = NAME;
	}
}
