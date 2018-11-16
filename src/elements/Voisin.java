package elements;

import java.util.ArrayList;

import model.Elements;
import model.Environnement;
import model.Parametres;

public class Voisin extends model.Elements {

	private ArrayList<Elements> elementsObsVoisin; // liste des elements voisin de la case connu par l agent

	public Voisin(int x, int y) {
		X = x;
		Y = y;
		Nom = "";
		elementsObsVoisin = new ArrayList<>();
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

}