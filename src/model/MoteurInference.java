package model;

import java.util.ArrayList;

import elements.Voisin;

public class MoteurInference {

	
	public ArrayList<Fait> chainageAvant(ArrayList<Fait> BF, ArrayList<Regle> BR, Fait F) {
		
		boolean regleApplicable = true;
		
		while(!BF.contains(F) && regleApplicable) {
			if(isRegleApplicable(BR)) {
				Regle regle = choisirUneRegle(BR);
				remove(regle, BR);
				BF.add(regle.getPredicat());
			}
			else regleApplicable = false;
		}
		if(BF.contains(F)) {
			//TODO 
		}
		
		return BF;
		
	}
	
	

	// Verifier qu'au moins une regle est applicable
	private boolean isRegleApplicable(ArrayList<Regle> BR) {
		
		return false;
	}


	private Regle choisirUneRegle(ArrayList<Regle> bR) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void remove(Regle regle, ArrayList<Regle> bR) {
		// TODO Auto-generated method stub
		
	}






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
			// inference � ajouter
			
		}
	}

}
