package model;

import java.util.ArrayList;

import elements.Voisin;

public class MoteurInference {
	
	public static ArrayList<Regle>  initBaseRegle() {
		ArrayList<Regle> regles = new ArrayList<Regle>();

		//Il existe une case non teste au test 0 -> on test une case non teste
		regles.add(new Regle("R0", new ArrayList<Fait>(){{add(Fait.existeCaseNonTest0);add(Fait.NotExistCase0);}}, Fait.goTest0));

		//Il existe une case 0 -> on choisie une case 0 comme cible
		regles.add(new Regle("R1", new ArrayList<Fait>(){{add(Fait.existeCase0);}}, Fait.goCible0));
		
		//Il n existe pas de case 0 & toute Case ont subi le test 0 -> case min la plus sure de niveau 1
		regles.add(new Regle("R2", new ArrayList<Fait>(){{add(Fait.NotExistCase0); add(Fait.allCaseTest0);}}, Fait.caseMin1));
		
		//Il existe une case 1 & case Min 1 -> on choisie une case 1 comme cible
		regles.add(new Regle("R3", new ArrayList<Fait>(){{add(Fait.existeCase1);add(Fait.caseMin1);}}, Fait.goCible1));
		
		//Il n existe pas de case 1 & toute Case ont subi le test 1 -> case min la plus sure de niveau 2
		regles.add(new Regle("R4", new ArrayList<Fait>(){{add(Fait.NotExisteCase1); add(Fait.caseMin1);}}, Fait.caseMin2));
		
		//Il existe une case non teste au test 2 && on est au test 2-> on test une case non teste
		regles.add(new Regle("R5", new ArrayList<Fait>(){{add(Fait.existeCaseNonTest2);add(Fait.NotExisteCase2);add(Fait.caseMin2);}}, Fait.goTest2));
		
		//Il existe une case 2 & case Min 2 -> on choisie une case 1 comme cible
		regles.add(new Regle("R6", new ArrayList<Fait>(){{add(Fait.existeCase2);add(Fait.caseMin2);}}, Fait.goCible2));
				
		//Il n existe pas de case 2 & toute Case ont subi le test 2 -> case min la plus sure de niveau 2
		regles.add(new Regle("R7", new ArrayList<Fait>(){{add(Fait.NotExisteCase2); add(Fait.caseMin2);}}, Fait.goCible3));
		
		return regles;
	}

	public static ArrayList<Fait> initBaseDeFait() {
		ArrayList<Fait> baseDeFaitsInit = new ArrayList<>();
		
		//Est ce qu il y a des case sure de niveau 0 ou non
		if(Environnement.agent.getCase0().isEmpty()) baseDeFaitsInit.add(Fait.NotExistCase0);
		else baseDeFaitsInit.add(Fait.existeCase0);
		//Est ce qu il y a des case sure de niveau 1 ou non
		if(Environnement.agent.getCase1().isEmpty()) baseDeFaitsInit.add(Fait.NotExisteCase1);
		else baseDeFaitsInit.add(Fait.existeCase1);
		//Est ce qu il y a des case sure de niveau 2 ou non
		if(Environnement.agent.getCase2().isEmpty()) baseDeFaitsInit.add(Fait.NotExisteCase2);
		else baseDeFaitsInit.add(Fait.existeCase2);
		if(Environnement.agent.getCase2().isEmpty()) baseDeFaitsInit.add(Fait.NotExisteCase3);
		else baseDeFaitsInit.add(Fait.existeCase3);
		if(Environnement.agent.getCase2().isEmpty()) baseDeFaitsInit.add(Fait.NotExisteCase4);
		else baseDeFaitsInit.add(Fait.existeCase4);
		
		//Est ce qu il y a des case non teste ou non
		if(Environnement.agent.getCaseVoisinesFrontiere().isEmpty()) baseDeFaitsInit.add(Fait.allCaseTest0);
		else baseDeFaitsInit.add(Fait.existeCaseNonTest0);
		if(Environnement.agent.getCaseMin3().isEmpty()) baseDeFaitsInit.add(Fait.allCaseTest3);
		else baseDeFaitsInit.add(Fait.existeCaseNonTest3);
		if(Environnement.agent.getCaseMin2().isEmpty()) baseDeFaitsInit.add(Fait.allCaseTest2);
		else baseDeFaitsInit.add(Fait.existeCaseNonTest2);
		
		return baseDeFaitsInit;
	}
	
public static ArrayList<Fait> moteurInference(ArrayList<Fait> BF, ArrayList<Regle> BR, ArrayList<Fait> buts){
		
		ArrayList<Regle> reglesAsupprimer = new ArrayList<Regle>();
		boolean isAnyRuleApplicable = true;
		while(isAnyRuleApplicable) {
			isAnyRuleApplicable = false;
			for(Regle R : BR){
				if(R.isApplicable(BF))
				{
					isAnyRuleApplicable = true;
					BF.add(R.getConclusion());
					reglesAsupprimer.add(R);
					if(buts.contains(R.getConclusion()))
						break;
				}

			}
			BR.removeAll(reglesAsupprimer);
			reglesAsupprimer.clear();
		}
		
		ArrayList<Fait> mouvements = new ArrayList<Fait>(BF);
		mouvements.retainAll(buts);
		return mouvements;
	}
	
public static ArrayList<Fait> getBut(){
	ArrayList<Fait> buts = new ArrayList<>();
	buts.add(Fait.goCible0);
	buts.add(Fait.goCible1);
	buts.add(Fait.goCible3);
	buts.add(Fait.goCible3);
	buts.add(Fait.goTest0);
	buts.add(Fait.goTest2);
	buts.add(Fait.goTest3);
	return buts;
}
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
		
	}*/
	
	

	// Verifier qu'au moins une regle est applicable
	/*private boolean isRegleApplicable(ArrayList<Regle> BR, ArrayList<Fait> BF) {
		for(int i = 0 ; i<BR.size() ; i++) {
			for(int j = 0 ; j<BF.size() ; j++) {
				if(BR.get(i).getPremisse() == BF.get(j)) {
					return true;
				}
			}
		}
		return false;
	}*/

	// Choisir une regle a analyser suivant heuristique ou autre... => pour l'instant c'est la premiere qui vient
	/*private Regle choisirUneRegle(ArrayList<Regle> BR, ArrayList<Fait> BF) {
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

}
