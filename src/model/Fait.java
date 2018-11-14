package model;

public class Fait {
	
	boolean Predicat;
	int X;
	int Y;
	
	public Fait(boolean predicat, int x, int y) {
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
	

}
