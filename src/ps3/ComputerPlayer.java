package ps3;
import java.util.*;

public class ComputerPlayer extends Player {
	
	public ComputerPlayer(String name) {
		super(name);
	}
	
	public Guess nextGuess(Scanner s, Board b) {
		String tried = new String("");
		//if nothing has been tried before
		Guess g = super.nextGuess(s, b);
		for(int i = 0; i < b.getDimension(); i ++) {
			for(int j = 0; j < b.getDimension(); j ++) {
				if(b.hasBeenTried(i, j))
					tried += "t";
				else
					tried += "x";
			}
		}
		String compare = new String("");
		for (int i = 0; i < b.getDimension()*b.getDimension(); i++)
			compare += "x";
		if(tried.equals(compare)) {
			System.out.println("nothing has been tried");
			return g;
		}
		//if previous guess was sunk
		else if (b.sunkShipAt(g.getRow(), g.getColumn())) {
			System.out.println("previous guess was sunk");
			return super.nextGuess(s, b);
		}
		//if all positions adjacent have been hit
		else if(b.previousHit(g.getRow(), g.getColumn())) {
			if((g.getColumn()>0 && b.hasBeenTried(g.getRow(), g.getColumn()-1)) && (g.getRow()<b.getDimension()-1 && b.hasBeenTried(g.getRow()+1, g.getColumn())) && (g.getColumn()<b.getDimension()-1 && b.hasBeenTried(g.getRow(), g.getColumn()+1)) && (g.getRow()>0 && b.hasBeenTried(g.getRow()-1, g.getColumn()))) {
				System.out.println("all positions adjacent have been hit");
				return g;
			}
		}
		/*
		 * smart part
		 * looks at the board to see if there are any previous hits and hits around that area.
		 */
		for(int i = 0; i < b.getDimension(); i ++) {
			for(int j = 0; j < b.getDimension(); j++) {
				if(b.previousHit(i, j)) {
					if(i>0 && !b.hasBeenTried(i-1, j)) {
						Guess t = new Guess(i-1,j);
						return t;
					}
					else if(i<b.getDimension()-1 && !b.hasBeenTried(i+1, j)) {
						Guess t = new Guess(i+1,j);
						return t;
					}
					else if(j>0 && !b.hasBeenTried(i,j-1)) {
						Guess t = new Guess(i,j-1);
						return t;
					}
					else if(j<b.getDimension()-1 && !b.hasBeenTried(i, j+1)) {
						Guess t = new Guess(i,j+1);
						return t;
					}
				}
			}
		}
		return g;
	}
	
}
