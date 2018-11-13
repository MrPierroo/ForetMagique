package model;

import elements.*;

public class Inference {

	/** inference basic */
	public static boolean isVide(Elements e) {
		if(e.getNom().equals(Parametres.NOM_VIDE)) return true;
		return false;
	}
	

}
