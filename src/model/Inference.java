package model;

import elements.*;

public class Inference {

	/** inference basic */
	public static boolean videProche(Voisin v) {
		for (Elements e : v.getElementsObsVoisin()) {
			if(e.getNom().equals(Parametres.NOM_VIDE)) return true;
		}
		return false;
	}
	
	public static boolean entourerCaca(Voisin v) {
		int nb = 0;
		for (Elements e : v.getElementsObsVoisin()) {
			if(e.getNom().equals(Parametres.NOM_CACA)) nb++;
		}
		if(nb == 4) return true;
		return false;
	}
	
	public static boolean entourerVent(Voisin v) {
		int nb = 0;
		for (Elements e : v.getElementsObsVoisin()) {
			if(e.getNom().equals(Parametres.NOM_CREVASSE)) nb++;
		}
		if(nb == 4) return true;
		return false;
	}

}
