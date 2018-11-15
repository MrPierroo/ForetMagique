package model;

import java.util.ArrayList;
import java.util.Collection;

public class essayInference {
	public static void main(String[] args) {
		ArrayList<Fait> BF = new ArrayList<Fait>();
		ArrayList<Regle> BR = new ArrayList<Regle>();

		//constitution de la base de fait:
		BF.addAll(initialiserBaseDeFait()); //remplir la base de fait en fonction de l'état de l'environnement
		BR.addAll(remplirBaseDeRegle());
		

	}

	private static ArrayList<Regle> remplirBaseDeRegle() {
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

	private static ArrayList<Fait> initialiserBaseDeFait() {
		int X = Environnement.agent.getX();
		int Y = Environnement.agent.getY();
		
		
		return null;
	}
}
