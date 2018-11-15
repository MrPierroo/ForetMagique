package model;

import java.util.ArrayList;

import elements.Voisin;

public class MoteurInference {

	
	/*public ArrayList<Fait> chainageAvant(ArrayList<Fait> BF, ArrayList<Regle> BR, Fait F) {
		
		boolean regleApplicable = true;
		
		while(!BF.contains(F) && regleApplicable) {
			if(isRegleApplicable(BR, BF)) {
				Regle regle = choisirUneRegle(BR, BF);
				remove(regle, BR);
				BF.add(regle.getPredicat());
			}
			else regleApplicable = false;
		}
		if(BF.contains(F)) {
			// En gros notre fait a verifier serait portailVisible == true, donc on irait au portail dans cette condition
			int x = F.getX();
			int y = F.getY();
			Agent.goTo(x,y);
		}
		else {
			//TODO sinon il faut dire ce que l'on fait, ou est ce qu'on explore
		}
		
		return BF;
		
	}
	
	

	// Verifier qu'au moins une regle est applicable
	private boolean isRegleApplicable(ArrayList<Regle> BR, ArrayList<Fait> BF) {
		for(int i = 0 ; i<BR.size() ; i++) {
			for(int j = 0 ; j<BF.size() ; j++) {
				if(BR.get(i).getPremisse() == BF.get(j)) {
					return true;
				}
			}
		}
		return false;
	}

	// Choisir une regle a analyser suivant heuristique ou autre... => pour l'instant c'est la premiere qui vient
	private Regle choisirUneRegle(ArrayList<Regle> BR, ArrayList<Fait> BF) {
		for(int i = 0 ; i<BR.size() ; i++) {
			for(int j = 0 ; j<BF.size() ; j++) {
				if(BR.get(i).getPremisse() == BF.get(j)) {
					return BR.get(i);
				}
			}
		}
		return null;
	}
	
	// Enlever la regle choisie de BR
	private void remove(Regle regle, ArrayList<Regle> BR) {
		for(int i = 0 ; i<BR.size() ; i++) {
			if(BR.get(i) == regle) {
				BR.remove(i);
			}
		}
	}

	


*/

	public static void calculerScore(Elements e) {
		
		/**Base de fait*/ // a mettre surement autre part ...
	/*	int X = e.getX();
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
		
		// y a til une case vide proche
		if(Inference.isVide(e)) {
			for (Voisin voisin : voisinProche) {
				voisin.setScoreDanger(0);
			}
		}
		
		else {
			// inference � ajouter
			
		}*/
	}

}
