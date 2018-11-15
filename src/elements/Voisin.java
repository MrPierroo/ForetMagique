package elements;

import java.util.ArrayList;

import model.Elements;
import model.Environnement;
import model.Parametres;

public class Voisin extends model.Elements {

	private int scoreDanger;	// echelle de 0 a 10, 0:sûre et 10:danger certain, -1:danger non evalue
	private ArrayList<Elements> elementsObsVoisin; // liste des elements voisin de la case connu par l agent

	public Voisin(int x, int y) {
		X = x;
		Y = y;
		Nom = "";
		scoreDanger = -1;
		elementsObsVoisin = new ArrayList<>();
	}

	public int getScoreDanger() {
		return scoreDanger;
	}

	public void setScoreDanger(int scoreDanger) {
		this.scoreDanger = scoreDanger;
	}
	
	public void analyseElementsVoisinObs() {
		elementsObsVoisin.clear();
		for (Elements e : Environnement.agent.getListElementObs()) {
			int x = e.getX();
			int y = e.getY();
			if( (x==X && Math.abs(Y-y)==1) || (y==Y && Math.abs(X-x)==1)) elementsObsVoisin.add(e);
		}
	}

	public ArrayList<Elements> getElementsObsVoisin() {
		return elementsObsVoisin;
	}
	
	public int getNbDanger() {
		int[] nbdanger = new int[4];
		for (Elements elements : elementsObsVoisin) {
			if(elements.getNom().equals(Parametres.NOM_CACA) || elements.getNom().equals(Parametres.NOM_VENT) ) {
				if(elements.getX() == X && elements.getY()== Y-1) nbdanger[0] = 1;
				if(elements.getX() == X && elements.getY()== Y+1) nbdanger[1] = 1;
				if(elements.getX() == X+1 && elements.getY()== Y) nbdanger[2] = 1;
				if(elements.getX() == X-1 && elements.getY()== Y-1) nbdanger[3] = 1;
			}
		}
		return nbdanger[0] + nbdanger[1]+nbdanger[2]+nbdanger[3];
	}

}