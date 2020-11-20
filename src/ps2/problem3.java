package ps2;

import java.util.Arrays;

public class problem3 {
	public static void main(String[] args) {
		int[][] twoD = { {1, 2, 3},
                		 {4, 5, 6},
                		 {7, 8, 9} };
		for(int i = 0; i < twoD.length; i++) {	
			System.out.println(twoD[i][twoD[i].length-1]);
		}
		
		for (int i = 0; i < twoD.length; i++) {
			System.out.println(twoD[i][i]);
		}

	}
}
