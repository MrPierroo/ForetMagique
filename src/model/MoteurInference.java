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
		initBaseDeFait();
	}
	
public String chainageAvant(ArrayList<Fait> buts){
		
		ArrayList<Regle> reglesAsupprimer = new ArrayList<Regle>();
		boolean isAnyRuleApplicable = true;
		while(isAnyRuleApplicable) {
			isAnyRuleApplicable = false;
			for(Regle R : BaseRegle){
				if(R.isApplicable(BaseFait))
				{
					isAnyRuleApplicable = true;
					BaseFait.add(R.getConclusion());
					reglesAsupprimer.add(R);
					if(buts.contains(R.getConclusion()))
						break;
				}

			}
			BaseRegle.removeAll(reglesAsupprimer);
			reglesAsupprimer.clear();
		}
		
		ArrayList<Fait> mouvements = new ArrayList<Fait>(BaseFait);
		mouvements.retainAll(buts);
		return mouvements.toString();
	}
	
	public void  initBaseRegle() {
		BaseRegle.clear();
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

		//REgles concernant un danger général
		BaseRegle.add(new Regle("R17", new ArrayList<Fait>(){{add(Fait.cacaADroite);}}, Fait.dangerDroite));
		BaseRegle.add(new Regle("R18", new ArrayList<Fait>(){{add(Fait.cacaAGauche);}}, Fait.dangerGauche));
		BaseRegle.add(new Regle("R19", new ArrayList<Fait>(){{add(Fait.cacaEnBas);}}, Fait.dangerBas));
		BaseRegle.add(new Regle("R20", new ArrayList<Fait>(){{add(Fait.cacaEnHaut);}}, Fait.dangerHaut));
		
		BaseRegle.add(new Regle("R21", new ArrayList<Fait>(){{add(Fait.ventADroite);}}, Fait.dangerDroite));
		BaseRegle.add(new Regle("R22", new ArrayList<Fait>(){{add(Fait.ventAGauche);}}, Fait.dangerGauche));
		BaseRegle.add(new Regle("R23", new ArrayList<Fait>(){{add(Fait.ventEnBas);}}, Fait.dangerBas));
		BaseRegle.add(new Regle("R24", new ArrayList<Fait>(){{add(Fait.ventEnHaut);}}, Fait.dangerHaut));
	
		//héro entouré par deux dangers
		BaseRegle.add(new Regle("R25", new ArrayList<Fait>(){{add(Fait.dangerHaut); add(Fait.dangerDroite);}}, Fait.doubleDanger));
		BaseRegle.add(new Regle("R26", new ArrayList<Fait>(){{add(Fait.dangerDroite); add(Fait.dangerBas);}}, Fait.doubleDanger));
		BaseRegle.add(new Regle("R27", new ArrayList<Fait>(){{add(Fait.dangerBas); add(Fait.dangerGauche);}}, Fait.doubleDanger));
		BaseRegle.add(new Regle("R28", new ArrayList<Fait>(){{add(Fait.dangerGauche); add(Fait.dangerHaut);}}, Fait.doubleDanger));

		BaseRegle.add(new Regle("R29", new ArrayList<Fait>(){{add(Fait.doubleDanger); add(Fait.videAGauche);}}, Fait.decisionAllerGauche));
		BaseRegle.add(new Regle("R30", new ArrayList<Fait>(){{add(Fait.doubleDanger); add(Fait.videADroite);}}, Fait.decisionAllerDroite));
		BaseRegle.add(new Regle("R31", new ArrayList<Fait>(){{add(Fait.doubleDanger); add(Fait.videEnBas);}}, Fait.decisionAllerBas));
		BaseRegle.add(new Regle("R32", new ArrayList<Fait>(){{add(Fait.doubleDanger); add(Fait.videEnHaut);}}, Fait.decisionAllerHaut));
		
		
	}

	public void initBaseDeFait() {
		BaseFait.clear();
		int X = Environnement.agent.getX();
		int Y = Environnement.agent.getY();

		/** fait lies a la position*/
		if(Y > 0) BaseFait.add(Fait.pasSurBordHaut);
		if(X > 0) BaseFait.add(Fait.pasSurBordGauche);
		if(Y < (Parametres.getTAILLE_GRILLE()-1) ) BaseFait.add(Fait.pasSurBordBas);
		if(X < (Parametres.getTAILLE_GRILLE()-1) ) BaseFait.add(Fait.pasSurBordDroite);

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
			if(e.getX() == (X-1) && e.getY() == Y ) {
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

		if(Environnement.agent.getElementObsAt(X+1, Y).isEmpty() && X<(Parametres.getTAILLE_GRILLE() -1))  BaseFait.add( Fait.nonDecouvertADroite);
		if(Environnement.agent.getElementObsAt(X-1, Y).isEmpty() && X>0)  BaseFait.add( Fait.nonDecouvertAGauche);
		if(Environnement.agent.getElementObsAt(X, Y-1).isEmpty() && Y>0)  BaseFait.add( Fait.nonDecouvertEnHaut);
		if(Environnement.agent.getElementObsAt(X, Y+1).isEmpty() && Y<(Parametres.getTAILLE_GRILLE() -1))  BaseFait.add( Fait.nonDecouvertEnBas);

	}
}
