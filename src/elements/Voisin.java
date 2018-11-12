package elements;

import model.Parametres;

public class Voisin extends model.Elements {

	private int scoreDanger;	// echelle de 0 a 10, 0:sûre et 10:danger certain, -1:danger non evalue

	public Voisin(int x, int y) {
		X = x;
		Y = y;
		Nom = "";
		scoreDanger = -1;
	}

	public int getScoreDanger() {
		return scoreDanger;
	}

	public void setScoreDanger(int scoreDanger) {
		this.scoreDanger = scoreDanger;
	}

}