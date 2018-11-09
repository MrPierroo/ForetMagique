package model;

public abstract class Elements {
	
	protected int X;	//coordonne en abscisse 
	protected int Y;	//coordonne en ordonne
	
	protected int Pts;	// pts recompense ou a enlever
	protected String Nom;

	public Elements() {
	}
	
	public Elements(int x, int y, int p, String nom) {
		X=x;
		Y=y;
		Pts=p;
		Nom=nom;
	}


 	public int getX() {
		return X;
	}
	
	public int getY() {
		return Y;
	}

	public int getPts() {
		return Pts;
	}
	
	public String getNom() {
		return Nom;
	}
	
	//debuggage 
	public String toString(){
		return "("+this.X+","+this.Y+")";
	}
}
