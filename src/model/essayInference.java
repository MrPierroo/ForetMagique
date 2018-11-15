package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.omg.Messaging.SyncScopeHelper;

public class essayInference {
	public static void main(String[] args) {
		/*ArrayList<Fait> BF = new ArrayList<Fait>();
		ArrayList<Regle> BR = new ArrayList<Regle>();

		//constitution de la base de fait:
		BF.addAll(initialiserBaseDeFait()); //remplir la base de fait en fonction de l'état de l'environnement		
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
	
	public static String moteurInference(ArrayList<Fait> BF, ArrayList<Regle> BR, ArrayList<Fait> buts){
		
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
		return mouvements.toString();
	}

	public static ArrayList<Regle> remplirBaseDeRegle() {
		ArrayList<Regle> regles = new ArrayList<Regle>();

		//Il existe une case non teste au test 0 -> on test une case non teste
		regles.add(new Regle("R0", new ArrayList<Fait>(){{add(Fait.existeCaseNonTestSafe);add(Fait.NotExistCase0);}}, Fait.goTestSafe));

		//Il existe une case 0 -> on choisie une case 0 comme cible
		regles.add(new Regle("R1", new ArrayList<Fait>(){{add(Fait.existeCase0);}}, Fait.goCible0));
		
		//Il n existe pas de case 0 & toute Case ont subi le test 0 -> case min la plus sure de niveau 1
		regles.add(new Regle("R2", new ArrayList<Fait>(){{add(Fait.NotExistCase0); add(Fait.allCaseTestSafe);}}, Fait.caseMin1));
		
		// case min 1 
		

		return regles;
	}

	//public static ArrayList<Fait> initialiserBaseDeFait() {
		/*int X = Environnement.agent.getX();
		int Y = Environnement.agent.getY();

		ArrayList<Fait> baseDeFaitsInit = new ArrayList<>();

		/** fait lies a la position*/
		/*if(Y > 0) baseDeFaitsInit.add(Fait.pasSurBordHaut);
		if(X > 0) baseDeFaitsInit.add(Fait.pasSurBordGauche);
		if(Y < (Parametres.getTAILLE_GRILLE()-1) ) baseDeFaitsInit.add(Fait.pasSurBordBas);
		if(X < (Parametres.getTAILLE_GRILLE()-1) ) baseDeFaitsInit.add(Fait.pasSurBordDroite);

		/** fait lies au type des cases ou se trouve l agent*/
		/*for(Elements e : Environnement.agent.getListElementObs()) {
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

		return baseDeFaitsInit;*/
	}
}
