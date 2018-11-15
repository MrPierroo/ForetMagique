package model;

import java.util.ArrayList;

public class Regle {

	String nom;
	ArrayList<Fait> premisses;
	Fait conclusion;
	
	public Regle(String nom, ArrayList<Fait> premisses, Fait conclusion) {
		this.nom = nom;
		this.premisses = new ArrayList<>(premisses);
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
	
	public ArrayList<Fait> getPremisses() {
		return this.premisses;
	}
	
	public Fait getConclusion() {
		return this.conclusion;
	}
	
	public String toString(){
		return this.getNom();
	}
	
}
