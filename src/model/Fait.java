package model;

public class Fait {
	
	String Nom;
	boolean Predicat;
	int X;
	int Y;
	
	public Fait() {
		
	}
	
	public Fait(String nom, boolean predicat, int x, int y) {
		Nom = nom;
		Predicat = predicat;
		X = x;
		Y = y;
	}

	public int getX() {
		return X;
	}
	
	public int getY() {
		return Y;
	}
	
	public boolean getPredicat() {
		return Predicat;
	}
	
	public void setX(int x) {
		X = x;
	}
	
	public void setY(int y) {
		Y = y;
	}
	
	public void setPredicat(boolean predicat) {
		Predicat = predicat;
	}
	
	public String getNom() {
		return Nom;
	}
	
	public void setNom(String nom) {
		Nom = nom;
	}
	

}
