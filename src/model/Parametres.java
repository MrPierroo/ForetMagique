package model;

/*Ici sont stocke tout les differents parametre
 * -> gestion au meme endroit, plus facilement gerable*/
public class Parametres {
	
	//Gestion des points (modifiable)
	public static final int COUT_ENERGIE = -1;
	public static final int COUT_CAILLOU = -10;
	
	// pas de temps en ms de la simulaion de l agent (modifiable)
	private static int DELAI_AGENT = 200; 	
	public static final String NOM_CACA = "Caca";
	public static final String NOM_CREVASSE = "Crevasse";
	public static final String NOM_MONSTRE = "Monstre";
	public static final String NOM_PORTAIL = "Portail";
	public static final String NOM_VENT = "Vent";
	public static final String NOM_VIDE = "Vide";
	public static final String NOM_ENVIRONNEMENT = "Environnement";
	public static final String NOM_AGENT = "Agent";
	
	// Gestion des niveaux, taille de grille, nombre d'elements... ne pas modifier
	private static int TAILLE_GRILLE;
	private static int NOMBRE_MONSTRES = 1;
	private static int NOMBRE_CREVASSES = 1;
	private static int NOMBRE_PORTAILS = 1;
	private static int NIVEAU = 1;
	
	
	public static int getTAILLE_GRILLE() {
		return TAILLE_GRILLE;
	}
	
	public static void setTAILLE_GRILLE(int tAILLE_GRILLE) {
		TAILLE_GRILLE = tAILLE_GRILLE;
	}
	
	public static int getDELAI_AGENT() {
		return DELAI_AGENT;
	}

	public static void setDELAI_AGENT(int dELAI_AGENT) {
		DELAI_AGENT = dELAI_AGENT;
	}

	public static int getNOMBRE_MONSTRES() {
		return NOMBRE_MONSTRES;
	}

	public static void setNOMBRE_MONSTRES(int nOMBRE_MONSTRES) {
		NOMBRE_MONSTRES = nOMBRE_MONSTRES;
	}

	public static int getNOMBRE_CREVASSES() {
		return NOMBRE_CREVASSES;
	}

	public static void setNOMBRE_CREVASSES(int nOMBRE_CREVASSES) {
		NOMBRE_CREVASSES = nOMBRE_CREVASSES;
	}

	public static int getNOMBRE_PORTAILS() {
		return NOMBRE_PORTAILS;
	}

	public static void setNOMBRE_PORTAILS(int nOMBRE_PORTAILS) {
		NOMBRE_PORTAILS = nOMBRE_PORTAILS;
	}

	public static int getNiveau() {
		return NIVEAU;
	}
	
	public static void setNIVEAU(int nIVEAU) {
		NIVEAU = nIVEAU;
	}

	
	
}
