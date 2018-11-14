package model;

public class Regle {

	String Nom;
	Fait Premisse;
	Fait Predicat;
	
	public Regle() {
		
	}
	
	public Regle(String nom, Fait premisse, Fait predicat) {
		Nom = nom;
		Premisse = premisse;
		Predicat = predicat;
	}

	public String getNom() {
		return Nom;
	}
	
	public Fait getPremisse() {
		return Premisse;
	}
	
	public Fait getPredicat() {
		return Predicat;
	}
	
}
