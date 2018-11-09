package elements;

import model.Parametres;

public class Monstre extends model.Elements{
	
	private final int POINT = Parametres.COUT_MORT;	
	private final String NAME = Parametres.NOM_MONSTRE;
	
	public Monstre(int x, int y) {
		X = x;
		Y = y;
		Pts = POINT;
		Nom = NAME;
	}

}
