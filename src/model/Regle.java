package model;

import java.util.ArrayList;

public class Regle {

	String nom;
	ArrayList<Fait> premisses;
	Fait conclusion;
	
	public Regle(String nom, ArrayList<Fait> premisses, Fait conclusion) {
		this.nom = nom;
		this.premisses = new ArrayList<>();
		this.conclusion = conclusion;
	}
	
	public boolean isApplicable(ArrayList<Fait> BF) {
		for (Fait f : this.premisses) {
			if(!BF.contains(f)) return false;
		}
		return true;
	}

	public String getNom() {
		return this.nom;
	}
	
	public ArrayList<Fait> getPremisse() {
		return this.premisses;
	}
	
	public Fait getPredicat() {
		return this.conclusion;
	}
	
}
