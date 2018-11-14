package model;

public class Regle {

	Fait Premisse;
	Fait Predicat;
	
	public Regle() {
		
	}
	
	public Regle(Fait premisse, Fait predicat) {
		Premisse = premisse;
		Predicat = predicat;
	}
	
	public Fait getPredicat() {
		return Predicat;
	}
	
}
