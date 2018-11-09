package elements;

import model.Parametres;

public class Monstre extends model.Elements{

	private final String NAME = Parametres.NOM_MONSTRE;
	
	public Monstre(int x, int y) {
		X = x;
		Y = y;
		Nom = NAME;
	}

}
