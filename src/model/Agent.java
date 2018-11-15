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
	
	// liste de case incertaines
	private ArrayList<Voisin> frontiere = new ArrayList<>();// case frontiere incertaine
	private ArrayList<Voisin> caseMin3 = new ArrayList<>();	// case comportant au moin 1 danger a proximiter
	private ArrayList<Voisin> caseMin2 = new ArrayList<>();	// case comportant au moin 2 danger a proximiter
	
	// liste de case Certaines
	private ArrayList<Voisin> case0 = new ArrayList<>();	// case comportant 0 danger a proximiter donc sure
	private ArrayList<Voisin> case1 = new ArrayList<>();	// case comportant 1 danger a proximiter
	private ArrayList<Voisin> case2 = new ArrayList<>();	// case comportant 2 danger a proximiter
	private ArrayList<Voisin> case3 = new ArrayList<>();	// case comportant 3 danger a proximiter
	private ArrayList<Voisin> caseMonstre = new ArrayList<>();	// case monstre certain
	private ArrayList<Voisin> caseGouffre = new ArrayList<>();	// case gouffre certain
	
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
	/** ============================================== Inference =============================================================================*/
	public void chercherCible() {
		BR = MoteurInference.initBaseRegle();
		BF = MoteurInference.initBaseDeFait();
		
	}
	
	public void test0() {
		Voisin v = frontiere.get(0);
		frontiere.remove(0);
		
		if(v.getNbDanger() == 0) case0.add(v);
		else if(v.getNbDanger() == 1) case1.add(v);
		else caseMin2.add(v);
	}
	
	public void test2() {
		Voisin v = caseMin2.get(0);
		frontiere.remove(0);
		
		if(v.getNbDanger() == 1){
			//if()
		}
	}
	
	/** ============================================== Observation =============================================================================*/
	
	public void observer() {
		ajouterVisionAgent();
		observerVoisin();
	}
	
	public void observerVoisin() {
		int X = this.X; int Y = this.Y;
		if(X-1>=0) {
			if(!isVoisinObs(X-1, Y) && !isElementObs(X-1, Y)) frontiere.add(new Voisin(X-1, Y));
		}
		if(Y-1>=0) {
			if(!isVoisinObs(X, Y-1) && !isElementObs(X, Y-1)) frontiere.add(new Voisin(X, Y-1));
		}
		if(X<Parametres.getTAILLE_GRILLE()) {
			if(!isVoisinObs(X+1, Y) && !isElementObs(X+1, Y)) frontiere.add(new Voisin(X+1, Y));
		}
		if(Y<Parametres.getTAILLE_GRILLE()) {
			if(!isVoisinObs(X, Y+1) && !isElementObs(X, Y+1)) frontiere.add(new Voisin(X, Y+1));
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
			if(x == a && y == b && elementNonObserve(Environnement.ListEnvironement.get(i))) {
				listElementObs.add(Environnement.ListEnvironement.get(i));
				elementAjoute = true;
			}
		}

		if(elementAjoute == false) {
			if(elementNonObserve(new Vide(x,y))) listElementObs.add(new Vide(x,y));
		}

	}
	
	public boolean elementNonObserve(Elements e) {
		int x = e.getX();
		int y = e.getY();
		for(int i = 0 ; i<listElementObs.size() ; i++) {
			int a = listElementObs.get(i).getX();
			int b = listElementObs.get(i).getY();
			if(x == a && y == b && e.getNom() == listElementObs.get(i).getNom()) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isVoisinObs(int x, int y) {
		for (Voisin v : frontiere) {
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
		for (int i = 0; i < frontiere.size(); i++) {
			Voisin v = frontiere.get(i);
			if(v.getX() == x && v.getY() == y) position = i;
		}
		if(position>-1) frontiere.remove(position);	
	}
	
	/** utilise dans nouvelle version*/
	public ArrayList<Elements> getElementObsAt(int x, int y){
		ArrayList<Elements> listElement = new ArrayList<>();
		
		for (Elements elements : listElementObs) {
			if(elements.getX() == x && elements.getY() == y) listElement.add(elements);
		}
		return listElement;
	}

	/** ============================================ Mise ajour Etat ===========================================================================*/
	
	public void calculScore() {
		
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
		this.frontiere.clear();
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

	public ArrayList<Voisin> getCaseVoisinesFrontiere() {
		return frontiere;
	}
	
	public ArrayList<Fait> getBaseDeFaits() {
		return BF;
	}

	public ArrayList<Regle> getBaseDeRegles() {
		return BR;
	}

	public ArrayList<Voisin> getCaseMin3() {
		return caseMin3;
	}

	public ArrayList<Voisin> getCaseMin2() {
		return caseMin2;
	}

	public ArrayList<Voisin> getCase0() {
		return case0;
	}

	public ArrayList<Voisin> getCase1() {
		return case1;
	}

	public ArrayList<Voisin> getCase2() {
		return case2;
	}

	public ArrayList<Voisin> getCase3() {
		return case3;
	}

	public ArrayList<Voisin> getCaseMonstre() {
		return caseMonstre;
	}

	public ArrayList<Voisin> getCaseGouffre() {
		return caseGouffre;
	}


}