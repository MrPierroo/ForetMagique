package model;

import java.util.ArrayList;

import Game.GameRun;
import elements.Vide;

public class Agent {

	//Mouvement de l'agent
	public final int NE_RIEN_FAIRE = 0;
	public final int HAUT = 1;
	public final int BAS = 2;
	public final int DROITE = 3;
	public final int GAUCHE = 4;
	public final int LANCER_CAILLOU = 5;
	public final int SORTIR = 6;

	private ArrayList<Elements> listElementObs = new ArrayList<Elements>();
	private ArrayList<Elements> baseDeFaits = new ArrayList<Elements>();
	private ArrayList<Integer> baseDeRegles = new ArrayList<Integer>();
	private int lastAction;
	private int direction = BAS;
	private int energieDepense = 0;
	private int X;
	private int Y;
	private int nombreCaillouxLances = 0;
	private int[] caseViseeAvecCaillou = new int[2];


	public Agent() {
		X = (int) (Math.random()*Parametres.getTAILLE_GRILLE());
		Y = (int) (Math.random()*Parametres.getTAILLE_GRILLE());
	}

	public Agent(int x, int y) {
		X=x;
		Y=y;
	}

	public void doCycle() {

	}
	/** ============================================== Observation =============================================================================*/
	public void actualiserObjectif() {

	}


	public void observerEnvironnement(){

	}

	public void ajouterVisionAgent() {

	    boolean elementAjoute = false;
		int x = Environnement.agent.getX();
		int y = Environnement.agent.getY();
		for (int i = 0; i < Environnement.ListEnvironement.size(); i++) {
			int a = Environnement.ListEnvironement.get(i).getX();
			int b = Environnement.ListEnvironement.get(i).getY();
			if(x==a && y==b) {
				listElementObs.add(Environnement.ListEnvironement.get(i));
				elementAjoute = true;
			}
		}

		if(elementAjoute == false) {
			listElementObs.add(new Vide(x,y));
		}

	}

	/** ============================================ Mise ajour Etat ===========================================================================*/
	public void ajoutPerformance() {

	}


	/** ================================================ Action ================================================================================*/
	public void goUp(){
		if(this.Y>0){
			this.Y--;
			this.energieDepense++;
			this.lastAction = HAUT;
			this.setDirection(HAUT);
			ajouterVisionAgent();
		}
	}

	public void goDown(){
		if(this.Y<Parametres.getTAILLE_GRILLE()-1){
			this.Y++;
			this.energieDepense++;
			this.lastAction = BAS;
			this.setDirection(BAS);
			ajouterVisionAgent();
		}
	}

	public void goRight(){
		if(this.X<Parametres.getTAILLE_GRILLE()-1){
			this.X++;
			this.energieDepense++;
			this.lastAction = DROITE;
			this.setDirection(DROITE);
			ajouterVisionAgent();
		}
	}

	public void goLeft(){
		if(this.X>0){
			this.X--;
			this.energieDepense++;
			this.lastAction = GAUCHE;
			this.setDirection(GAUCHE);
			ajouterVisionAgent();
		}
	}

	public void lancerCaillou() {
		this.energieDepense+=10;
		System.out.println("Agent : ma direction est "+direction);
		if(direction == Environnement.agent.BAS) {
			if(Y<Parametres.getTAILLE_GRILLE()-1) {
				System.out.println("Agent : vise en "+this.X+" , "+(this.Y+1));
				caseViseeAvecCaillou[0] = this.X;
				caseViseeAvecCaillou[1] = this.Y+1;
			}
		}
		else if(direction == Environnement.agent.HAUT) {
			if(Y>0) {
				System.out.println("Agent : vise en "+this.X+" , "+(this.Y-1));
				caseViseeAvecCaillou[0] = this.X;
				caseViseeAvecCaillou[1] = this.Y-1;
			}
		}
		else if(direction == Environnement.agent.DROITE) {
			if(X<Parametres.getTAILLE_GRILLE()-1) {
				System.out.println("Agent : vise en "+(this.X+1)+" , "+this.Y);
				caseViseeAvecCaillou[0] = this.X+1;
				caseViseeAvecCaillou[1] = this.Y;
			}
		}
		else if(direction == Environnement.agent.GAUCHE) {
			if(X>0) {
				System.out.println("Agent : vise en "+(this.X-1)+" , "+this.Y);
				caseViseeAvecCaillou[0] = this.X-1;
				caseViseeAvecCaillou[1] = this.Y;
			}
		}
		this.lastAction = LANCER_CAILLOU;
	}

	public void goSortir() {
		int x = this.getX();
		int y = this.getY();
		this.energieDepense++;
		this.lastAction = SORTIR;
	}


	/**=============================================== Reinitialisation ==============================================================================*/
	public void reinitialiserAgent() {
		Environnement.agent.listElementObs.removeAll(listElementObs);
	}


	/**============================================= Accesseurs et mutateurs ===========================================================================*/
	public ArrayList<Elements> getListElementObs() {
		return listElementObs;
	}

	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}

	public int getLastAction() {
		return lastAction;
	}

	public void setLastAction(int l) {
		this.lastAction = l;
	}

	public int getEnergieDepense() {
		return energieDepense;
	}

	public void setEnergieDepense(int e) {
		this.energieDepense = e;
	}

	public int getNombreCaillouxLances() {
		return nombreCaillouxLances;
	}

	public void setNombreCaillouxLances(int nombreCaillouxLances) {
		this.nombreCaillouxLances = nombreCaillouxLances;
	}

	public ArrayList<Elements> getBaseDeFaits() {
		return baseDeFaits;
	}

	public ArrayList<Integer> getBaseDeRegles() {
		return baseDeRegles;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public int[] getCaseViseeAvecCaillou() {
		return caseViseeAvecCaillou ;
	}
	
	public void setCaseViseeAvecCaillou(int [] CaseViseeAvecCaillou) {
		this.caseViseeAvecCaillou = CaseViseeAvecCaillou;
	}


}