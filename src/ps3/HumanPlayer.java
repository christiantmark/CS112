package ps3;
import java.util.Scanner;

public class HumanPlayer extends Player {
	
	public HumanPlayer(String name) {
		super(name);
	}

	public Guess nextGuess(Scanner s, Board b) {
		int row = s.nextInt();
        int col = s.nextInt();
        
        Guess guess = new Guess(row, col);
        return guess;
    }
}
