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

	public String getNom() {
		return this.nom;
	}
	
	public ArrayList<Fait> getPremisses() {
		return this.premisses;
	}
	
	public Fait getConclusion() {
		return this.conclusion;
	}
	
}
