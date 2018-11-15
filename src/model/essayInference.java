package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class essayInference {
	public static void main(String[] args) {
		ArrayList<Fait> BF = new ArrayList<Fait>();
		ArrayList<Regle> BR = new ArrayList<Regle>();

		//constitution de la base de fait:
		//BF.addAll(initialiserBaseDeFait()); //remplir la base de fait en fonction de l'état de l'environnement
		BF.add(Fait.estSurVide);
		BF.add(Fait.nonDecouvertEnHaut);
		BF.add(Fait.nonDecouvertADroite);
		BF.add(Fait.nonDecouvertEnBas);
		BF.add(Fait.nonDecouvertAGauche);
		BF.add(Fait.pasSurBordBas);
		BF.add(Fait.pasSurBordDroite);
		BF.add(Fait.pasSurBordGauche);
		
		BR.addAll(remplirBaseDeRegle());
		
		ArrayList<Fait> faitsElementaires = new ArrayList<Fait>();
		faitsElementaires.add(Fait.haut);
		faitsElementaires.add(Fait.droite);
		faitsElementaires.add(Fait.bas);
		faitsElementaires.add(Fait.gauche);
		faitsElementaires.add(Fait.tirer);
		faitsElementaires.add(Fait.sortir);
		
		System.out.println(BF);
		System.out.println(BR);
		ArrayList<Regle> reglesAsupprimer = new ArrayList<Regle>();
		while(Collections.disjoint(BF, faitsElementaires)) {
			for(Regle R : BR){
				if(R.isApplicable(BF))
				{
					System.out.println(R.getNom() +" est applicable");
					BF.add(R.getConclusion());
					reglesAsupprimer.add(R);
					if(faitsElementaires.contains(R.getConclusion()))
						break;
				}
			}
			BR.removeAll(reglesAsupprimer);
			reglesAsupprimer.clear();
		}
		System.out.println(BF);
		System.out.println(BR);



	}

	public static ArrayList<Regle> remplirBaseDeRegle() {
		ArrayList<Regle> regles = new ArrayList<Regle>();

		//regle principale
		regles.add(new Regle("R0", new ArrayList<Fait>(){{add(Fait.estSurPortail);}}, Fait.sortir));

		//regles de mouvements
		regles.add(new Regle("R1", new ArrayList<Fait>(){{add(Fait.decisionAllerHaut); add(Fait.pasSurBordHaut);}}, Fait.haut));
		regles.add(new Regle("R2", new ArrayList<Fait>(){{add(Fait.decisionAllerDroite); add(Fait.pasSurBordDroite);}}, Fait.droite));
		regles.add(new Regle("R3", new ArrayList<Fait>(){{add(Fait.decisionAllerBas); add(Fait.pasSurBordBas);}}, Fait.bas));
		regles.add(new Regle("R4", new ArrayList<Fait>(){{add(Fait.decisionAllerGauche); add(Fait.pasSurBordGauche);}}, Fait.gauche));

		//regles impliquant un déplacement en haut
		regles.add(new Regle("R5", new ArrayList<Fait>(){{add(Fait.estSurVide); add(Fait.nonDecouvertEnHaut);}}, Fait.decisionAllerHaut));
		regles.add(new Regle("R6", new ArrayList<Fait>(){{add(Fait.estSurCaca); add(Fait.videEnHaut);}}, Fait.decisionAllerHaut));
		regles.add(new Regle("R7", new ArrayList<Fait>(){{add(Fait.estSurVent); add(Fait.videEnHaut);}}, Fait.decisionAllerHaut));

		//regles impliquant un déplacement en bas
		regles.add(new Regle("R8", new ArrayList<Fait>(){{add(Fait.estSurVide); add(Fait.nonDecouvertADroite);}}, Fait.decisionAllerDroite));
		regles.add(new Regle("R9", new ArrayList<Fait>(){{add(Fait.estSurCaca); add(Fait.videADroite);}}, Fait.decisionAllerDroite));
		regles.add(new Regle("R10", new ArrayList<Fait>(){{add(Fait.estSurVent); add(Fait.videADroite);}}, Fait.decisionAllerDroite));

		//regles impliquant un déplacement à droite
		regles.add(new Regle("R11", new ArrayList<Fait>(){{add(Fait.estSurVide); add(Fait.nonDecouvertEnBas);}}, Fait.decisionAllerBas));
		regles.add(new Regle("R12", new ArrayList<Fait>(){{add(Fait.estSurCaca); add(Fait.videEnBas);}}, Fait.decisionAllerBas));
		regles.add(new Regle("R13", new ArrayList<Fait>(){{add(Fait.estSurVent); add(Fait.videEnBas);}}, Fait.decisionAllerBas));

		//Regles impliquant un déplacement à gauche
		regles.add(new Regle("R14", new ArrayList<Fait>(){{add(Fait.estSurVide); add(Fait.nonDecouvertAGauche);}}, Fait.decisionAllerGauche));
		regles.add(new Regle("R15", new ArrayList<Fait>(){{add(Fait.estSurCaca); add(Fait.videAGauche);}}, Fait.decisionAllerGauche));
		regles.add(new Regle("R16", new ArrayList<Fait>(){{add(Fait.estSurVent); add(Fait.videAGauche);}}, Fait.decisionAllerGauche));

		return regles;
	}

	public static ArrayList<Fait> initialiserBaseDeFait() {
		int X = Environnement.agent.getX();
		int Y = Environnement.agent.getY();

		ArrayList<Fait> baseDeFaitsInit = new ArrayList<>();

		/** fait lies a la position*/
		if(Y > 0) baseDeFaitsInit.add(Fait.pasSurBordHaut);
		if(X > 0) baseDeFaitsInit.add(Fait.pasSurBordGauche);
		if(Y < (Parametres.getTAILLE_GRILLE()-1) ) baseDeFaitsInit.add(Fait.pasSurBordBas);
		if(X < (Parametres.getTAILLE_GRILLE()-1) ) baseDeFaitsInit.add(Fait.pasSurBordDroite);

		/** fait lies au type des cases ou se trouve l agent*/
		for(Elements e : Environnement.agent.getListElementObs()) {
			if(e.getX() == X && e.getY() == Y) {
				switch (e.getNom()) {

				case Parametres.NOM_CACA: baseDeFaitsInit.add(Fait.estSurCaca);
				break;

				case Parametres.NOM_VENT: baseDeFaitsInit.add(Fait.estSurVent);
				break;

				case Parametres.NOM_PORTAIL: baseDeFaitsInit.add(Fait.estSurPortail);
				break;

				case Parametres.NOM_VIDE: baseDeFaitsInit.add(Fait.estSurVide);
				break;

				default:
					break;
				}
			}
			if(e.getX() == X && e.getY() == (Y-1) ) {
				switch (e.getNom()) {

				case Parametres.NOM_CACA: baseDeFaitsInit.add(Fait.cacaEnHaut);
				break;

				case Parametres.NOM_VENT: baseDeFaitsInit.add(Fait.ventEnHaut);
				break;
				
				case Parametres.NOM_VIDE: baseDeFaitsInit.add(Fait.videEnHaut);
				break;

				default:
					break;
				}
			}
			if(e.getX() == X && e.getY() == (Y+1) ) {
				switch (e.getNom()) {

				case Parametres.NOM_CACA: baseDeFaitsInit.add(Fait.cacaEnBas);
				break;

				case Parametres.NOM_VENT: baseDeFaitsInit.add(Fait.ventEnBas);
				break;
				
				case Parametres.NOM_VIDE: baseDeFaitsInit.add(Fait.videEnBas);
				break;

				default:
					break;
				}
			}
			if(e.getX() == (X+1) && e.getY() == Y) {
				switch (e.getNom()) {

				case Parametres.NOM_CACA: baseDeFaitsInit.add(Fait.cacaADroite);
				break;

				case Parametres.NOM_VENT: baseDeFaitsInit.add(Fait.ventADroite);
				break;
				
				case Parametres.NOM_VIDE: baseDeFaitsInit.add(Fait.videADroite);
				break;

				default:
					break;
				}
			}
			if(e.getX() == (X-1) && e.getY() == Y ) {
				switch (e.getNom()) {

				case Parametres.NOM_CACA: baseDeFaitsInit.add(Fait.cacaAGauche);
				break;

				case Parametres.NOM_VENT: baseDeFaitsInit.add(Fait.ventAGauche);
				break;
				
				case Parametres.NOM_VIDE: baseDeFaitsInit.add(Fait.videAGauche);
				break;

				default:
					break;
				}
			}

		}

		if(Environnement.agent.getElementObsAt(X+1, Y).isEmpty() && X<(Parametres.getTAILLE_GRILLE() -1))  baseDeFaitsInit.add( Fait.nonDecouvertADroite);
		if(Environnement.agent.getElementObsAt(X-1, Y).isEmpty() && X>0)  baseDeFaitsInit.add( Fait.nonDecouvertAGauche);
		if(Environnement.agent.getElementObsAt(X, Y-1).isEmpty() && Y>0)  baseDeFaitsInit.add( Fait.nonDecouvertEnHaut);
		if(Environnement.agent.getElementObsAt(X, Y+1).isEmpty() && Y<(Parametres.getTAILLE_GRILLE() -1))  baseDeFaitsInit.add( Fait.nonDecouvertEnBas);

		return baseDeFaitsInit;
	}
}
