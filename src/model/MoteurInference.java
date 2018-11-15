package model;

import java.util.ArrayList;

import elements.Voisin;

public class MoteurInference {

	private ArrayList<Fait> BaseFait;
	private ArrayList<Regle> BaseRegle;
	
	public MoteurInference() {
		BaseFait = new ArrayList<>();
		BaseRegle = new ArrayList<>();
		initBaseRegle();
		initBaseRegle();
	}
	
	public void  initBaseRegle() {

		//regle principale
		BaseRegle.add(new Regle("R0", new ArrayList<Fait>(){{add(Fait.estSurPortail);}}, Fait.sortir));

		//regles de mouvements
		BaseRegle.add(new Regle("R1", new ArrayList<Fait>(){{add(Fait.decisionAllerHaut); add(Fait.pasSurBordHaut);}}, Fait.haut));
		BaseRegle.add(new Regle("R2", new ArrayList<Fait>(){{add(Fait.decisionAllerDroite); add(Fait.pasSurBordDroite);}}, Fait.droite));
		BaseRegle.add(new Regle("R3", new ArrayList<Fait>(){{add(Fait.decisionAllerBas); add(Fait.pasSurBordBas);}}, Fait.bas));
		BaseRegle.add(new Regle("R4", new ArrayList<Fait>(){{add(Fait.decisionAllerGauche); add(Fait.pasSurBordGauche);}}, Fait.gauche));

		//regles impliquant un déplacement en haut
		BaseRegle.add(new Regle("R5", new ArrayList<Fait>(){{add(Fait.estSurVide); add(Fait.nonDecouvertEnHaut);}}, Fait.decisionAllerHaut));
		BaseRegle.add(new Regle("R6", new ArrayList<Fait>(){{add(Fait.estSurCaca); add(Fait.videEnHaut);}}, Fait.decisionAllerHaut));
		BaseRegle.add(new Regle("R7", new ArrayList<Fait>(){{add(Fait.estSurVent); add(Fait.videEnHaut);}}, Fait.decisionAllerHaut));

		//regles impliquant un déplacement en bas
		BaseRegle.add(new Regle("R8", new ArrayList<Fait>(){{add(Fait.estSurVide); add(Fait.nonDecouvertADroite);}}, Fait.decisionAllerDroite));
		BaseRegle.add(new Regle("R9", new ArrayList<Fait>(){{add(Fait.estSurCaca); add(Fait.videADroite);}}, Fait.decisionAllerDroite));
		BaseRegle.add(new Regle("R10", new ArrayList<Fait>(){{add(Fait.estSurVent); add(Fait.videADroite);}}, Fait.decisionAllerDroite));

		//regles impliquant un déplacement à droite
		BaseRegle.add(new Regle("R11", new ArrayList<Fait>(){{add(Fait.estSurVide); add(Fait.nonDecouvertEnBas);}}, Fait.decisionAllerBas));
		BaseRegle.add(new Regle("R12", new ArrayList<Fait>(){{add(Fait.estSurCaca); add(Fait.videEnBas);}}, Fait.decisionAllerBas));
		BaseRegle.add(new Regle("R13", new ArrayList<Fait>(){{add(Fait.estSurVent); add(Fait.videEnBas);}}, Fait.decisionAllerBas));

		//Regles impliquant un déplacement à gauche
		BaseRegle.add(new Regle("R14", new ArrayList<Fait>(){{add(Fait.estSurVide); add(Fait.nonDecouvertAGauche);}}, Fait.decisionAllerGauche));
		BaseRegle.add(new Regle("R15", new ArrayList<Fait>(){{add(Fait.estSurCaca); add(Fait.videAGauche);}}, Fait.decisionAllerGauche));
		BaseRegle.add(new Regle("R16", new ArrayList<Fait>(){{add(Fait.estSurVent); add(Fait.videAGauche);}}, Fait.decisionAllerGauche));
	}

	public void initBaseDeFait() {
		BaseFait.clear();
		int X = Environnement.agent.getX();
		int Y = Environnement.agent.getY();

		/** fait lies a la position*/
		if(X > 0) BaseFait.add(Fait.pasSurBordHaut);
		if(Y > 0) BaseFait.add(Fait.pasSurBordGauche);
		if(X < (Parametres.getTAILLE_GRILLE()-1) ) BaseFait.add(Fait.pasSurBordBas);
		if(Y < (Parametres.getTAILLE_GRILLE()-1) ) BaseFait.add(Fait.pasSurBordDroite);

		/** fait lies au type des cases ou se trouve l agent*/
		for(Elements e : Environnement.agent.getListElementObs()) {
			if(e.getX() == X && e.getY() == Y) {
				switch (e.getNom()) {

				case Parametres.NOM_CACA: BaseFait.add(Fait.estSurCaca);
				break;

				case Parametres.NOM_VENT: BaseFait.add(Fait.estSurVent);
				break;

				case Parametres.NOM_PORTAIL: BaseFait.add(Fait.estSurPortail);
				break;

				case Parametres.NOM_VIDE: BaseFait.add(Fait.estSurVide);
				break;

				default:
					break;
				}
			}
			if(e.getX() == X && e.getY() == (Y-1) ) {
				switch (e.getNom()) {

				case Parametres.NOM_CACA: BaseFait.add(Fait.cacaEnHaut);
				break;

				case Parametres.NOM_VENT: BaseFait.add(Fait.ventEnHaut);
				break;
				
				case Parametres.NOM_VIDE: BaseFait.add(Fait.videEnHaut);
				break;

				default:
					break;
				}
			}
			if(e.getX() == X && e.getY() == (Y+1) ) {
				switch (e.getNom()) {

				case Parametres.NOM_CACA: BaseFait.add(Fait.cacaEnBas);
				break;

				case Parametres.NOM_VENT: BaseFait.add(Fait.ventEnBas);
				break;
				
				case Parametres.NOM_VIDE: BaseFait.add(Fait.videEnBas);
				break;

				default:
					break;
				}
			}
			if(e.getX() == (X+1) && e.getY() == Y) {
				switch (e.getNom()) {

				case Parametres.NOM_CACA: BaseFait.add(Fait.cacaADroite);
				break;

				case Parametres.NOM_VENT: BaseFait.add(Fait.ventADroite);
				break;
				
				case Parametres.NOM_VIDE: BaseFait.add(Fait.videADroite);
				break;

				default:
					break;
				}
			}
			if(e.getX() == X && e.getY() == (Y-1) ) {
				switch (e.getNom()) {

				case Parametres.NOM_CACA: BaseFait.add(Fait.cacaAGauche);
				break;

				case Parametres.NOM_VENT: BaseFait.add(Fait.ventAGauche);
				break;
				
				case Parametres.NOM_VIDE: BaseFait.add(Fait.videAGauche);
				break;

				default:
					break;
				}
			}

		}

		if(Environnement.agent.getElementObsAt(X-1, Y).isEmpty() && X>0)  BaseFait.add( Fait.nonDecouvertADroite);
		if(Environnement.agent.getElementObsAt(X+1, Y).isEmpty() && X<(Parametres.getTAILLE_GRILLE() -1))  BaseFait.add( Fait.nonDecouvertAGauche);
		if(Environnement.agent.getElementObsAt(X, Y-1).isEmpty() && Y>0)  BaseFait.add( Fait.nonDecouvertEnHaut);
		if(Environnement.agent.getElementObsAt(X, Y+1).isEmpty() && Y<(Parametres.getTAILLE_GRILLE() -1))  BaseFait.add( Fait.nonDecouvertEnBas);
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
}
