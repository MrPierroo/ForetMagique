package model;

import java.util.ArrayList;
import java.util.Vector;

public class Environnement {
	
	public static ArrayList<Elements> ListEnvironement = new ArrayList<Elements>();
	public static Agent agent = new Agent();
	private static int scoreEnvironnement;
	public static Vector<Integer> scoresObtenus = new Vector<>();
	private static double moyenneScore;
	public static boolean newCycle = false;

	
	// verifie que la case x,y ne contient pas deja un element similaire ou un monstre et une crevasse mal agences
	public static boolean caseDisponible(int x, int y) {
		for (int i = 0; i < ListEnvironement.size(); i++) {
			int a = ListEnvironement.get(i).getX();
			int b = ListEnvironement.get(i).getY();
			if(x==a && y==b && (ListEnvironement.get(i).getNom() == Parametres.NOM_MONSTRE || ListEnvironement.get(i).getNom() == Parametres.NOM_CREVASSE)) {
				return false;
			}
		}
		return true;
	}

	
	//retourne la position dans la liste d'un element souhaite en fonction de son type et de ses coordonnees
	public static int indiceElementMonstre(int x, int y) {
		int id = -1;
		for (int i = 0; i < ListEnvironement.size(); i++) {
			int a = ListEnvironement.get(i).getX();
			int b = ListEnvironement.get(i).getY();
			if(x==a && y==b && ListEnvironement.get(i).getNom() == Parametres.NOM_MONSTRE) {
				id = i;
			}
		}
		return id;
	}
	
	public static int indiceElementCrevasse(int x, int y) {
		int id = -1;
		for (int i = 0; i < ListEnvironement.size(); i++) {
			int a = ListEnvironement.get(i).getX();
			int b = ListEnvironement.get(i).getY();
			if(x==a && y==b && ListEnvironement.get(i).getNom() == Parametres.NOM_CREVASSE) {
				id = i;
			}
		}
		return id;
	}
	
	public static int indiceElementCaca(int x, int y) {
		int id = -1;
		for (int i = 0; i < ListEnvironement.size(); i++) {
			int a = ListEnvironement.get(i).getX();
			int b = ListEnvironement.get(i).getY();
			if(x==a && y==b && ListEnvironement.get(i).getNom() == Parametres.NOM_CACA) {
				id = i;
			}
		}
		return id;
	}
	
	public static int indiceElementVent(int x, int y) {
		int id = -1;
		for (int i = 0; i < ListEnvironement.size(); i++) {
			int a = ListEnvironement.get(i).getX();
			int b = ListEnvironement.get(i).getY();
			if(x==a && y==b && ListEnvironement.get(i).getNom() == Parametres.NOM_VENT) {
				id = i;
			}
		}
		return id;
	}
	
	public static int indiceElementPortail(int x, int y) {
		int id = -1;
		for (int i = 0; i < ListEnvironement.size(); i++) {
			int a = ListEnvironement.get(i).getX();
			int b = ListEnvironement.get(i).getY();
			if(x==a && y==b && ListEnvironement.get(i).getNom() == Parametres.NOM_PORTAIL) {
				id = i;
			}
		}
		return id;
	}
	
	public static int getScoreEnvironnement() {
		return scoreEnvironnement;
	}

	public static void setScoreEnvironnement(int scoreEnvironnement) {
		Environnement.scoreEnvironnement = scoreEnvironnement;
	}
	
	public static double getMoyenneScore() {
		return moyenneScore;
	}

	public static void setMoyenneScore(double moyenneScore) {
		Environnement.moyenneScore = moyenneScore;
	}

}
