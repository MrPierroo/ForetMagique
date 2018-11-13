package model;

import java.util.ArrayList;

import elements.Voisin;

public class MoteurInference {

	public static void calculerScore(Elements e) {
		
		/**Base de fait*/ // a mettre surement autre part ...
		int X = e.getX();
		int Y = e.getY();
		
		ArrayList<Elements> elementObsVoisin = new ArrayList<>();
		ArrayList<Voisin> voisinProche = new ArrayList<>();
		
		for (Elements elements : Environnement.agent.getListElementObs()) {
			int x = elements.getX();
			int y = elements.getY();
			if( (x==X && Math.abs(Y-y)==1) || (y==Y && Math.abs(X-x)==1)) elementObsVoisin.add(elements);
		}
		for (Voisin v : Environnement.agent.getCaseVoisines()) {
			int x = v.getX();
			int y = v.getY();
			if( (x==X && Math.abs(Y-y)==1) || (y==Y && Math.abs(X-x)==1)) voisinProche.add(v);
		}
		/** */
		// y a til une case vide proche
		if(Inference.isVide(e)) {
			for (Voisin voisin : voisinProche) {
				voisin.setScoreDanger(0);
			}
		}
		
		else {
			// inference à ajouter
			
		}
	}

}
