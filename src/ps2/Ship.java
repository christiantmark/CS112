package ps2;

public class Ship {
	//instance fields
	private String type;
	private int length;
	private int numHits;
	
	//constructors
	public Ship() {
		type = "";
		length = 0;
		numHits = 0;
	}
	
	public Ship(String t, int l) {
		type = t;
		length = l;
		numHits = 0;
	}
	
	//accessor methods
	public String getType() {
		return type;
	}
	
	public int getLength() {
		return length;
	}
	
	public int getNumHits() {
		return numHits;
	}
	
	public char getSymbol() {
		return type.charAt(0);
	}
	
	public boolean isSunk() {
		boolean b = true;
		if (numHits < length)
			b = false;
		return b;
			
	}
	
	public String toString() {
		return type + " of length " + Integer.toString(length);
	}
	
	//mutator methods
	public void applyHit() {
		numHits++;
	}
}
