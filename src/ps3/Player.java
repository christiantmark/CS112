package ps3;
/*
 * Player.java - blueprint class for objects that represent a single
 * "random" player in the game of Battleship.
 * 
 * Computer Science 112, Boston University
 */

import java.util.*;

public class Player {
    // a constant for the maximum number of ships per player
    public static final int SHIPS_PER_PLAYER = 5; 
    
    // fields
    private String name;
    
    // PS 2: add the fields for the player's collection of ships
    private Ship[] ships;
    private int numShips;
    
    
    /*
     * constructor for a Player with the specified name
     */
    public Player(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("name must have at least one character");
        }
        
        this.name = name;
    
        // PS 2: initialize the fields that you added above
        ships = new Ship[SHIPS_PER_PLAYER];
        numShips = 0;
    }
    
    /*
     * getName - returns the name of the player
     */
    public String getName() {
        return this.name;
    }
    
    /*
     * addShip - add the specified ship to the player's collection of ships
     */
    public void addShip(Ship s) {
        if (s == null) {
            throw new IllegalArgumentException("parameter must be non-null");
        }
        
        // PS 2: add code to this method
        if(numShips < 5)
        	ships[numShips] = s;
        numShips++;
        
    }
    
    /*
     * removeShip - removes the specified ship from the player's collection of ships
     */
    public void removeShip(Ship s) {
        if (s == null) {
            throw new IllegalArgumentException("parameter must be non-null");
        }
        
        // PS 2: add code to this method
        for (int i = 0; i < ships.length; i++)
        	if(ships[i] == s) {
        		numShips--;
        		ships[i] = ships[numShips];
        		ships[numShips] = null;
        	}
        
    }
    
    /*
     * printShips - prints whatever ships remain in the player's collection
     */
    public void printShips() {
        // PS 2: implement this method
    	for(int i = 0; i < numShips; i ++)
    		System.out.println(ships[i].toString());
    }
    
    /*
     * hasLost - has this player lost the game?
     * Returns true if this is the case, and false otherwise.
     */
    public boolean hasLost() {
        // PS 2: implement this method
        if(numShips == 0)
        	return true;
        return false;
    }
    
    /*
     * nextGuess - returns a Guess object representing the player's
     * next guess for the location of a ship on the board specified
     * by the parameter otherBoard.
     */
    public Guess nextGuess(Scanner console, Board otherBoard) {
        int row;
        int col;
        
        // Keep randomly selecting coordinates until we get 
        // a position that has not already been tried.
        do {
            row = Board.RAND.nextInt(otherBoard.getDimension());
            col = Board.RAND.nextInt(otherBoard.getDimension());
        } while (otherBoard.hasBeenTried(row, col));
        
        Guess guess = new Guess(row, col);
        return guess;
    }
    
    /*
     * toString - returns a string representation of the player
     */
    public String toString() {
        return this.name;
    }
}