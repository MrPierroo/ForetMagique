package model;

import java.util.ArrayList;

import Game.GameRun;
import elements.Vide;
import elements.Voisin;

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
	private ArrayList<Fait> BF = new ArrayList<Fait>();
	private ArrayList<Regle> BR = new ArrayList<Regle>();
	private ArrayList<Voisin> caseVoisines = new ArrayList<>();
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
	
	public void observer() {
		ajouterVisionAgent();
		observerVoisin();
	}
	
	public void observerVoisin() {
		int X = this.X; int Y = this.Y;
		if(X-1>=0) {
			if(!isVoisinObs(X-1, Y) && !isElementObs(X-1, Y)) caseVoisines.add(new Voisin(X-1, Y));
		}
		if(Y-1>=0) {
			if(!isVoisinObs(X, Y-1) && !isElementObs(X, Y-1)) caseVoisines.add(new Voisin(X, Y-1));
		}
		if(X<Parametres.getTAILLE_GRILLE()) {
			if(!isVoisinObs(X+1, Y) && !isElementObs(X+1, Y)) caseVoisines.add(new Voisin(X+1, Y));
		}
		if(Y<Parametres.getTAILLE_GRILLE()) {
			if(!isVoisinObs(X, Y+1) && !isElementObs(X, Y+1)) caseVoisines.add(new Voisin(X, Y+1));
		}
		deleteVoisin(X, Y);
	}

	public void ajouterVisionAgent() {
	    boolean elementAjoute = false;
		int x = this.getX();
		int y = this.getY();
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
	
	public boolean isVoisinObs(int x, int y) {
		for (Voisin v : caseVoisines) {
			if(v.getX() == x && v.getY() == y) return true;
		}
		return false;
	}
	
	public boolean isElementObs(int x, int y) {
		for (Elements e : listElementObs) {
			if(e.getX() == x && e.getY() == y) return true;
		}
		return false;
	}
	
	public void deleteVoisin(int x, int y) {
		int position = -1;
		for (int i = 0; i < caseVoisines.size(); i++) {
			Voisin v = caseVoisines.get(i);
			if(v.getX() == x && v.getY() == y) position = i;
		}
		if(position>-1) caseVoisines.remove(position);	
	}
	
	public ArrayList<Elements> getElementObsAt(int x, int y){
		ArrayList<Elements> listElement = new ArrayList<>();
		
		for (Elements elements : listElementObs) {
			if(elements.getX() == x && elements.getY() == y) listElement.add(elements);
		}
		if(listElement.isEmpty()) listElement.add(new Vide(x,y));
		
		return listElement;
	}

	/** ============================================ Mise ajour Etat ===========================================================================*/
	
	public void calculScore() {
		for (Elements e : listElementObs) {
			MoteurInference.calculerScore(e);
		}
	}

	/** ================================================ Actions ================================================================================*/
	public void goUp(){
		if(this.Y>0){
			this.Y--;
			this.energieDepense++;
			this.lastAction = HAUT;
			this.setDirection(HAUT);
			
		}
	}

	public void goDown(){
		if(this.Y<Parametres.getTAILLE_GRILLE()-1){
			this.Y++;
			this.energieDepense++;
			this.lastAction = BAS;
			this.setDirection(BAS);
		}
	}

	public void goRight(){
		if(this.X<Parametres.getTAILLE_GRILLE()-1){
			this.X++;
			this.energieDepense++;
			this.lastAction = DROITE;
			this.setDirection(DROITE);
		}
	}

	public void goLeft(){
		if(this.X>0){
			this.X--;
			this.energieDepense++;
			this.lastAction = GAUCHE;
			this.setDirection(GAUCHE);
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
		this.energieDepense++;
		this.lastAction = SORTIR;
	}
	
	// Atteindre une position depuis la position actuelle en ne passant que par des cases observees
	public static void goTo(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	
	/**=========================================== Construction base de regles ==============================================================================*/
	
	public static ArrayList<Regle> construireBR() {
		
		ArrayList<Regle> regles = null;
		
		
		
		//regles.add(new Regle("V0",ventObserve, ventPresent));
		//regles.add(new Regle("C0",cacaObserve, cacaPresent));
		
		
		return regles;
		
	}
	
	/*public static ArrayList<Fait> construireBF() {
		
		ArrayList<Fait> baseDeFaits = null;
		
		for(int x = 0 ; x<Parametres.getTAILLE_GRILLE() ; x++) {
			for(int y = 0 ; y<Parametres.getTAILLE_GRILLE() ; y++) {
				baseDeFaits.add(new Fait("ventObserve",false,x,y));
				baseDeFaits.add(new Fait("cacaObserve",false,x,y));
				baseDeFaits.add(new Fait("videObserve",false,x,y));
				baseDeFaits.add(new Fait("portailObserve",false,x,y));
				baseDeFaits.add(new Fait("monstreEn",false,x,y));
				baseDeFaits.add(new Fait("crevasseEn",false,x,y));
			}
		}
		
		return baseDeFaits;
	}*/
	


	/**=============================================== Reinitialisation ==============================================================================*/
	public void reinitialiserAgent() {
		this.listElementObs.removeAll(listElementObs);
		this.caseVoisines.removeAll(caseVoisines);
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

	public ArrayList<Voisin> getCaseVoisines() {
		return caseVoisines;
	}
	
	public ArrayList<Fait> getBaseDeFaits() {
		return BF;
	}

	public ArrayList<Regle> getBaseDeRegles() {
		return BR;
	}


}