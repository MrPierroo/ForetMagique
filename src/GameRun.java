
import controler.ButtonHandler;
import elements.Caca;
import elements.Crevasse;
import elements.Monstre;
import elements.Portail;
import elements.Vent;
import model.*;
import view.*;

public class GameRun implements Runnable{

	public void run() {

		/**Initialisation de l environement*/
		initialisationEnvironnement(Parametres.getNiveau());
		ButtonHandler bh = new ButtonHandler();
		System.out.println("Environnement : Hero en "+model.Environnement.agent.getX()+" , "+model.Environnement.agent.getY());

		Draw drawing = new Draw(Parametres.NOM_ENVIRONNEMENT,Environnement.ListEnvironement);
		drawing.render();
		
		while(true) {
			if(Environnement.newCycle) {
				drawing.render();
				Environnement.newCycle = false;
			}
			try {Thread.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
		}

	}

	// -------------------------------------------------Methode pour initialiser l'environnement-------------------------------------------------------------------------------

	public void initialisationEnvironnement(int niveau) {

		int compteurMonstres = 0;
		int compteurCrevasses = 0;
		int compteurPortails = 0;
		
		Parametres.setTAILLE_GRILLE(2+niveau);
		
		Parametres.setNOMBRE_MONSTRES(niveau);
		Parametres.setNOMBRE_CREVASSES(niveau);

		while(thereIsMonstre() == false || compteurMonstres<Parametres.getNOMBRE_MONSTRES()) {
			genererMonstreCaca();
			if(thereIsMonstre()) {
				compteurMonstres++;
			}
		}

		while(thereIsCrevasse() == false || compteurCrevasses<Parametres.getNOMBRE_CREVASSES()) {
			genererCrevasseVent();
			if(thereIsCrevasse()) {
				compteurCrevasses++;
			}
		}

		while(thereIsPortail() == false || compteurPortails<Parametres.getNOMBRE_PORTAILS()) {
			genererPortail();
			if(thereIsPortail()) {
				compteurPortails++;
			}
		}

		nettoyer();

		placerHero();

	}

	/** ======================================= Methode utile a la gestion de l environnement ===========================================================*/

	// ------------------------------------------------Generer les elements-------------------------------------------------------------------------------

	public void genererMonstreCaca() {
		int x = (int) (Math.random()*Parametres.getTAILLE_GRILLE());
		int y = (int) (Math.random()*Parametres.getTAILLE_GRILLE());
		if(Environnement.caseDisponible(x, y)) {
			Environnement.ListEnvironement.add(new Monstre(x, y));
			System.out.println("Environnement : Monstre apparu en "+x+","+y);
			if(x-1 >= 0) {
				if (Environnement.caseDisponible(x-1, y)) {
					Environnement.ListEnvironement.add(new Caca(x-1, y));
					System.out.println("Environnement : Caca apparu en "+(x-1)+","+y);
				}
			}
			if(x+1 < Parametres.getTAILLE_GRILLE()) {
				if (Environnement.caseDisponible(x+1, y)) {
					Environnement.ListEnvironement.add(new Caca(x+1, y));
					System.out.println("Environnement : Caca apparu en "+(x+1)+","+y);
				}
			}
			if(y-1 >= 0) {
				if (Environnement.caseDisponible(x, y-1)) {
					Environnement.ListEnvironement.add(new Caca(x, y-1));
					System.out.println("Environnement : Caca apparu en "+(x)+","+(y-1));
				}
			}
			if(y+1 < Parametres.getTAILLE_GRILLE()) {
				if (Environnement.caseDisponible(x, y+1)) {
					Environnement.ListEnvironement.add(new Caca(x, y+1));
					System.out.println("Environnement : Caca apparu en "+(x)+","+(y+1));
				}
			}
		}
	}

	public void genererCrevasseVent() {
		int x = (int) (Math.random()*Parametres.getTAILLE_GRILLE());
		int y = (int) (Math.random()*Parametres.getTAILLE_GRILLE());
		if(Environnement.caseDisponible(x, y)) {
			Environnement.ListEnvironement.add(new Crevasse(x, y));
			System.out.println("Environnement : Crevasse apparue en "+x+","+y);
			if(x-1 >= 0) {
				if (Environnement.caseDisponible(x-1, y)) {
					Environnement.ListEnvironement.add(new Vent(x-1, y));
					System.out.println("Environnement : Vent apparu en "+(x-1)+","+y);
				}
			}
			if(x+1 < Parametres.getTAILLE_GRILLE()) {
				if (Environnement.caseDisponible(x+1, y)) {
					Environnement.ListEnvironement.add(new Vent(x+1, y));
					System.out.println("Environnement : Vent apparu en "+(x+1)+","+y);
				}
			}
			if(y-1 >= 0) {
				if (Environnement.caseDisponible(x, y-1)) {
					Environnement.ListEnvironement.add(new Vent(x, y-1));
					System.out.println("Environnement : Vent apparu en "+(x)+","+(y-1));
				}
			}
			if(y+1 < Parametres.getTAILLE_GRILLE()) {
				if (Environnement.caseDisponible(x, y+1)) {
					Environnement.ListEnvironement.add(new Vent(x, y+1));
					System.out.println("Environnement : Vent apparu en "+(x)+","+(y+1));
				}
			}
		}
	}

	public void genererPortail() {
		int x = (int) (Math.random()*Parametres.getTAILLE_GRILLE());
		int y = (int) (Math.random()*Parametres.getTAILLE_GRILLE());
		if(Environnement.caseDisponible(x, y)) {
			Environnement.ListEnvironement.add(new Portail(x, y));
			System.out.println("Environnement : Portail cree en "+x+","+y);
		}
	}

	public void placerHero() {
		boolean boucle = true;
		int a = 0;
		int b = 0;
		while(boucle == true) {
			a = (int) (Math.random()*Parametres.getTAILLE_GRILLE());
			b = (int) (Math.random()*Parametres.getTAILLE_GRILLE());
			if(!isThereMonstre(a,b) && !isThereCrevasse(a,b) && !isTherePortail(a,b)) {
				Environnement.agent.setX(a);
				Environnement.agent.setY(b);
				System.out.println("Environnement : Hero place en "+a+" , "+b);
				boucle = false;
			}
			else {
				System.out.println("Environnement : Hero refuse en "+a+" , "+b);
			}
		}
	}


	// ----------------------------------------------Nettoyer les cacas et vents mal agences-------------------------------------------------------------------------------

	public void nettoyer() {
		for (int i = 0; i < Environnement.ListEnvironement.size(); i++) {
			if(Environnement.ListEnvironement.get(i).getNom() == Parametres.NOM_CACA || Environnement.ListEnvironement.get(i).getNom() == Parametres.NOM_VENT) {
				int x = Environnement.ListEnvironement.get(i).getX();
				int y = Environnement.ListEnvironement.get(i).getY();
				System.out.println("Caca ou vent detecte en "+x+" "+y);
				if(isThereMonstre(x,y) || isThereCrevasse(x,y) || isTherePortail(x,y)) {
					System.out.println("Environnement : Nettoyage de "+Environnement.ListEnvironement.get(i).getNom()+" en "+x+" "+y);
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



	// -------------------------------------------------Voir la presence d element-------------------------------------------------------------------------------

	public boolean thereIsMonstre() {
		for (int i = 0; i < Environnement.ListEnvironement.size(); i++) {
			if(Environnement.ListEnvironement.get(i).getNom() == Parametres.NOM_MONSTRE) {
				return true;
			}
		}
		return false;
	}

	public boolean thereIsCrevasse() {
		for (int i = 0; i < Environnement.ListEnvironement.size(); i++) {
			if(Environnement.ListEnvironement.get(i).getNom() == Parametres.NOM_CREVASSE) {
				return true;
			}
		}
		return false;
	}

	public boolean thereIsPortail() {
		for (int i = 0; i < Environnement.ListEnvironement.size(); i++) {
			if(Environnement.ListEnvironement.get(i).getNom() == Parametres.NOM_PORTAIL) {
				return true;
			}
		}
		return false;
	}

	public boolean isThereMonstre(int x, int y) {
		for (int i = 0; i < Environnement.ListEnvironement.size(); i++) {
			int a = Environnement.ListEnvironement.get(i).getX();
			int b = Environnement.ListEnvironement.get(i).getY();
			if(x==a && y==b && Environnement.ListEnvironement.get(i).getNom() == Parametres.NOM_MONSTRE) {
				return true;
			}
		}
		return false;
	}

	public boolean isThereCrevasse(int x, int y) {
		for (int i = 0; i < Environnement.ListEnvironement.size(); i++) {
			int a = Environnement.ListEnvironement.get(i).getX();
			int b = Environnement.ListEnvironement.get(i).getY();
			if(x==a && y==b && Environnement.ListEnvironement.get(i).getNom() == Parametres.NOM_CREVASSE) {
				return true;
			}
		}
		return false;
	}

	public boolean isTherePortail(int x, int y) {
		for (int i = 0; i < Environnement.ListEnvironement.size(); i++) {
			int a = Environnement.ListEnvironement.get(i).getX();
			int b = Environnement.ListEnvironement.get(i).getY();
			if(x==a && y==b && Environnement.ListEnvironement.get(i).getNom() == Parametres.NOM_PORTAIL) {
				return true;
			}
		}
		return false;
	}

	public boolean isThereCaca(int x, int y) {
		for (int i = 0; i < Environnement.ListEnvironement.size(); i++) {
			int a = Environnement.ListEnvironement.get(i).getX();
			int b = Environnement.ListEnvironement.get(i).getY();
			if(x==a && y==b && Environnement.ListEnvironement.get(i).getNom() == Parametres.NOM_CACA) {
				return true;
			}
		}
		return false;
	}

	public boolean isThereVent(int x, int y) {
		for (int i = 0; i < Environnement.ListEnvironement.size(); i++) {
			int a = Environnement.ListEnvironement.get(i).getX();
			int b = Environnement.ListEnvironement.get(i).getY();
			if(x==a && y==b && Environnement.ListEnvironement.get(i).getNom() == Parametres.NOM_VENT) {
				return true;
			}
		}
		return false;
	}

	// -------------------------------------------------Mettre a jour l'environement-------------------------------------------------------------------------------

	public void majEnvironement() {

		int PositionX = getXPositionAgent();
		int PositionY = getYPositionAgent();
		int lastAction = getLastActionAgent();


		if(lastAction == Agent.LANCER_CAILLOU) {
			//TODO
		}

	}

}
