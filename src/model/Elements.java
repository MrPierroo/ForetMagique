package model;

public abstract class Elements {
	
	protected int X;	//coordonne en abscisse 
	protected int Y;	//coordonne en ordonne
	
	protected String Nom;

	public Elements() {
	}
	
	public Elements(int x, int y, int p, String nom) {
		X=x;
		Y=y;
		Nom=nom;
	}


 	public int getX() {
		return X;
	}
	
	public int getY() {
		return Y;
	}

	
	public String getNom() {
		return Nom;
	}
	
	//debuggage 
	public String toString(){
		return "("+this.X+","+this.Y+","+this.Nom+")";
	}
}
