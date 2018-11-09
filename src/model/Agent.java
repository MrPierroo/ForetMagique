package model;

import java.util.ArrayList;

public class Agent {

	//Mouvement de l'agent
	public static final int NE_RIEN_FAIRE = 0;
	public static final int HAUT = 1;
	public static final int BAS = 2;
	public static final int DROITE = 3;
	public static final int GAUCHE = 4;
	public static final int LANCER_CAILLOU = 5;

	private ArrayList<Elements> listElementObs = new ArrayList<Elements>();
	private ArrayList<Elements> baseDeFaits = new ArrayList<Elements>();
	private ArrayList<Integer> baseDeRegles = new ArrayList<Integer>();
	private int lastAction;
	private int energieDepense = 0;
	private int X;
	private int Y;
	private int nombreCaillouxLances = 0;

	
	public Agent() {
		X = (int) (Math.random()*Parametres.getTAILLE_GRILLE());
		Y = (int) (Math.random()*Parametres.getTAILLE_GRILLE());
	}
	
	public Agent(int x, int y) {
		X=x;
		Y=y;
	}

	
	/** ============================================== Observation =============================================================================*/
	public void actualiserObjectif() {
	
	}

	
	public void observerEnvironnement(){
		
	}

	/** ============================================ Mise ajour Etat ===========================================================================*/
	public void ajoutPerformance() {
		
	}
	
	
	/** ================================================ Action ================================================================================*/
	public void goUp(){
		if(this.Y>0){
			this.Y--;
			this.lastAction = HAUT;
		}
	}

	public void goDown(){
		if(this.Y<Parametres.getTAILLE_GRILLE()-1){
			this.Y++;
			this.lastAction = BAS;
		}
	}

	public void goRight(){
		if(this.X<Parametres.getTAILLE_GRILLE()-1){
			this.X++;
			this.lastAction = DROITE;
		}
	}

	public void goLeft(){
		if(this.X>0){
			this.X--;
			this.lastAction = GAUCHE;
		}
	}
	
	public void lancerCaillou() {
		//TODO
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
	

}