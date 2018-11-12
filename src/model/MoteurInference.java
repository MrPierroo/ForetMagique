package model;

import elements.Voisin;

public class MoteurInference {

	public static void calculerScore(Voisin v) {
		
		v.analyseElementsVoisinObs();
		
		// y a til une case vide proche
		if(Inference.videProche(v)) v.setScoreDanger(0);		
		
		else {
			// case entourer de caca (fausse)
			if(Inference.entourerCaca(v)) v.setScoreDanger(9);// 9: monstre a tuer
			
			// case entourer de vent (fausse)
			if(Inference.entourerVent(v)) v.setScoreDanger(10);
		}
	}

}
