package Game;

import controler.ButtonHandler;
import elements.Caca;
import elements.Crevasse;
import elements.Monstre;
import elements.Portail;
import elements.Vent;
import model.*;
import view.*;

public class GameRun implements Runnable{

	public static boolean demandeNouveauNiveau = false;

	public void run() {

		/**Initialisation de l environement*/
		System.out.println("Initialisation du niveau "+Parametres.getNiveau()+" ... ");
		initialisationEnvironnement(Parametres.getNiveau());
		ButtonHandler bh = new ButtonHandler();
		System.out.println("Environnement : Hero en "+model.Environnement.agent.getX()+" , "+model.Environnement.agent.getY());

		Draw drawingEnvironnement = new Draw(Parametres.NOM_ENVIRONNEMENT,Environnement.ListEnvironement);
		ViewAgent drawingAgent = new ViewAgent(Parametres.NOM_AGENT, Environnement.ListEnvironement);
		drawingEnvironnement.render();
		drawingAgent.render();
		System.out.println("Initialisation effectuee ! ");

		while(true) {
			if(Environnement.newCycle) {
				observationAgent();
				
				Environnement.agent.observer();
				drawingEnvironnement.render();
				drawingAgent.render();
				Environnement.newCycle = false;
			}
			if(GameRun.demandeNouveauNiveau) {
				System.out.println("Initialisation du niveau "+Parametres.getNiveau()+" ... ");
				for(int i = 0 ; i<Environnement.agent.getListElementObs().size() ; i++) {
					System.out.println(Environnement.agent.getListElementObs().get(i).getNom());
				}
				initialisationEnvironnement(Parametres.getNiveau());
				drawingEnvironnement.render();
				drawingAgent.render();
				System.out.println("Initialisation effectuee ! ");
				GameRun.demandeNouveauNiveau = false;
			}
			try {Thread.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
		}

	}

	// -------------------------------------------------Methode pour initialiser l'environnement-------------------------------------------------------------------------------

	public static void initialisationEnvironnement(int niveau) {

		int compteurMonstres = 0;
		int compteurCrevasses = 0;
		int compteurPortails = 0;

		Environnement.agent.reinitialiserAgent();
		Environnement.reinitialiserEnvironnement();

		Parametres.setTAILLE_GRILLE(2+niveau);

		Parametres.setNOMBRE_MONSTRES((int)Parametres.getTAILLE_GRILLE()/2);
		Parametres.setNOMBRE_CREVASSES((int)Parametres.getTAILLE_GRILLE()/2);

		while(Environnement.thereIsMonstre() == false || compteurMonstres<Parametres.getNOMBRE_MONSTRES()) {
			genererMonstreCaca();
			if(Environnement.thereIsMonstre()) {
				compteurMonstres++;
			}
		}
		
		nettoyer();

		while(Environnement.thereIsCrevasse() == false || compteurCrevasses<Parametres.getNOMBRE_CREVASSES()) {
			genererCrevasseVent();
			if(Environnement.thereIsCrevasse()) {
				compteurCrevasses++;
			}
		}
		
		nettoyer();

		while(Environnement.thereIsPortail() == false || compteurPortails<Parametres.getNOMBRE_PORTAILS()) {
			genererPortail();
			if(Environnement.thereIsPortail()) {
				compteurPortails++;
			}
		}

		nettoyer();

		placerHero();

		Environnement.agent.ajouterVisionAgent();
		Environnement.agent.observerVoisin();

	}

	/** ======================================= Methode utile a la gestion de l environnement ===========================================================*/

	// ------------------------------------------------Generer les elements-------------------------------------------------------------------------------

	public static void genererMonstreCaca() {
		int x = (int) (Math.random()*Parametres.getTAILLE_GRILLE());
		int y = (int) (Math.random()*Parametres.getTAILLE_GRILLE());
		if(Environnement.caseDisponible(x, y)) {
			Environnement.ListEnvironement.add(new Monstre(x, y));
			System.out.println("Environnement : creation de monstre en "+x+" , "+y);
			if(x-1 >= 0) {
				if (Environnement.caseDisponible(x-1, y)) {
					Environnement.ListEnvironement.add(new Caca(x-1, y));
				}
			}
			if(x+1 < Parametres.getTAILLE_GRILLE()) {
				if (Environnement.caseDisponible(x+1, y)) {
					Environnement.ListEnvironement.add(new Caca(x+1, y));
				}
			}
			if(y-1 >= 0) {
				if (Environnement.caseDisponible(x, y-1)) {
					Environnement.ListEnvironement.add(new Caca(x, y-1));
				}
			}
			if(y+1 < Parametres.getTAILLE_GRILLE()) {
				if (Environnement.caseDisponible(x, y+1)) {
					Environnement.ListEnvironement.add(new Caca(x, y+1));
				}
			}
		}
	}

	public static void genererCrevasseVent() {
		int x = (int) (Math.random()*Parametres.getTAILLE_GRILLE());
		int y = (int) (Math.random()*Parametres.getTAILLE_GRILLE());
		if(Environnement.caseDisponible(x, y)) {
			Environnement.ListEnvironement.add(new Crevasse(x, y));
			System.out.println("Environnement : creation de crevasse en "+x+" , "+y);
			if(x-1 >= 0) {
				if (Environnement.caseDisponible(x-1, y)) {
					Environnement.ListEnvironement.add(new Vent(x-1, y));
				}
			}
			if(x+1 < Parametres.getTAILLE_GRILLE()) {
				if (Environnement.caseDisponible(x+1, y)) {
					Environnement.ListEnvironement.add(new Vent(x+1, y));
				}
			}
			if(y-1 >= 0) {
				if (Environnement.caseDisponible(x, y-1)) {
					Environnement.ListEnvironement.add(new Vent(x, y-1));
				}
			}
			if(y+1 < Parametres.getTAILLE_GRILLE()) {
				if (Environnement.caseDisponible(x, y+1)) {
					Environnement.ListEnvironement.add(new Vent(x, y+1));
				}
			}
		}
	}

	public static void genererPortail() {
		int x = (int) (Math.random()*Parametres.getTAILLE_GRILLE());
		int y = (int) (Math.random()*Parametres.getTAILLE_GRILLE());
		if(Environnement.caseDisponible(x, y)) {
			Environnement.ListEnvironement.add(new Portail(x, y));
		}
	}

	public static void placerHero() {
		boolean boucle = true;
		int a = 0;
		int b = 0;
		while(boucle == true) {
			a = (int) (Math.random()*Parametres.getTAILLE_GRILLE());
			b = (int) (Math.random()*Parametres.getTAILLE_GRILLE());
			if(!Environnement.monstreEn(a,b) && !Environnement.crevasseEn(a,b) && !Environnement.portailEn(a,b)) {
				Environnement.agent.setX(a);
				Environnement.agent.setY(b);
				boucle = false;
			}
			else {
			}
		}
	}


	// ----------------------------------------------Nettoyer les cacas et vents mal agences-------------------------------------------------------------------------------

	public static void nettoyer() {
		for (int i = 0; i < Environnement.ListEnvironement.size(); i++) {
			if(Environnement.ListEnvironement.get(i).getNom() == Parametres.NOM_CACA || Environnement.ListEnvironement.get(i).getNom() == Parametres.NOM_VENT) {
				int x = Environnement.ListEnvironement.get(i).getX();
				int y = Environnement.ListEnvironement.get(i).getY();
				if(Environnement.monstreEn(x,y) || Environnement.crevasseEn(x,y) || Environnement.portailEn(x,y)) {
					Environnement.ListEnvironement.remove(i);
				}
			}

		}
	}

	// -------------------------------------------------Obtenir les positions de l agent-------------------------------------------------------------------------------

	public int getXPositionAgent() {
		return Environnement.agent.getX();
	}

	public int getYPositionAgent() {
		return Environnement.agent.getY();
	}

	// -------------------------------------------------Obtenir la derniere action de l agent-------------------------------------------------------------------------------

	public int getLastActionAgent() {
		return Environnement.agent.getLastAction();
	}


	// -------------------------------------------------Mettre a jour l'environement-------------------------------------------------------------------------------

	public void observationAgent() {

		int x = getXPositionAgent();
		int y = getYPositionAgent();
		int lastAction = getLastActionAgent();

		if(Environnement.monstreEn(x,y) || Environnement.crevasseEn(x, y)) {
			if(Parametres.getNiveau()>1) {
				Parametres.setNIVEAU(Parametres.getNiveau()-1);
			}
			GameRun.demandeNouveauNiveau = true;
		}

		if(Environnement.portailEn(x,y) && lastAction == Environnement.agent.SORTIR) {
			Parametres.setNIVEAU(Parametres.getNiveau()+1);
			GameRun.demandeNouveauNiveau = true;
		}

		if(lastAction == Environnement.agent.LANCER_CAILLOU) {
			int a = Environnement.agent.getCaseViseeAvecCaillou()[0];
			int b = Environnement.agent.getCaseViseeAvecCaillou()[1];
			if(Environnement.monstreEn(a,b)) {
				Environnement.removeMonstre(a,b);
				System.out.println("Environnement : monstre tue en "+a+" , "+b);
				
			}	
		}
		

	}

}
