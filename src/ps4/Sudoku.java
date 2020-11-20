package ps4;

/**
 * Sudoku.java
 * 
 * Implementation of a class that represents a Sudoku puzzle and solves
 * it using recursive backtracking.
 *
 * Computer Science 112, Boston University
 *
 * your name: 
 * partner's name and email (if any):
 */

import java.io.*;   // allows us to read from a file
import java.util.*;

public class Sudoku {    
    // The current contents of the cells of the puzzle. 
    private int[][] grid;
    
    /*
     * Indicates whether the value in a given cell is fixed 
     * (i.e., part of the initial configuration).
     * valIsFixed[r][c] is true if the value in the cell 
     * at row r, column c is fixed, and false otherwise.
     */
    private boolean[][] valIsFixed;
    
    /*
     * This 3-D array allows us to determine if a given subgrid (i.e.,
     * a given 3x3 region of the puzzle) already contains a given
     * value.  We use 2 indices to identify a given subgrid:
     *
     *    (0,0)   (0,1)   (0,2)
     *
     *    (1,0)   (1,1)   (1,2)
     * 
     *    (2,0)   (2,1)   (2,2)
     * 
     * For example, subgridHasVal[0][2][5] will be true if the subgrid
     * in the upper right-hand corner already has a 5 in it, and false
     * otherwise.
     */
    private boolean[][][] subgridHasVal;
    
    /*** ADD YOUR ADDITIONAL FIELDS HERE. ***/
    private final int[] section00 = {0,1,2,9,10,11,18,19,20};
    private final int[] section01 = {3,4,5,12,13,14,21,22,23};
    private final int[] section02 = {6,7,8,15,16,17,24,25,26};
    private final int[] section10 = {27,28,29,36,37,38,45,46,47};
    private final int[] section11 = {30,31,32,39,40,41,48,49,50};
    private final int[] section12 = {33,34,35,42,43,44,51,52,53};
    private final int[] section20 = {54,55,56,63,64,65,72,73,74};
    private final int[] section21 = {57,58,59,66,67,68,75,76,77};
    private final int[] section22 = {60,61,62,69,70,71,78,79,80};
    
    private final int[][] colArr = {{0,9,18,27,36,45,54,63,72},
    								{1,10,19,28,37,46,55,64,73},
    								{2,11,20,29,38,47,56,65,74},
    								{3,12,21,30,39,48,57,66,75},
    								{4,13,22,31,40,49,58,67,76},
    								{5,14,23,32,41,50,59,68,77},
    								{6,15,24,33,42,51,60,69,78},
    								{7,16,25,34,43,52,61,70,79},
    								{8,17,26,35,44,53,62,71,80}};

    /* 
     * Constructs a new Puzzle object, which initially
     * has all empty cells.
     */
    public Sudoku() {
        this.grid = new int[9][9];
        this.valIsFixed = new boolean[9][9];     
        
        /* 
         * Note that the third dimension of the following array is 10,
         * because we need to be able to use the possible values 
         * (1 through 9) as indices.
         */
        this.subgridHasVal = new boolean[3][3][10];        

        /*** INITIALIZE YOUR ADDITIONAL FIELDS HERE. ***/
    }
    
    /*
     * Place the specified value in the cell with the specified
     * coordinates, and update the state of the puzzle accordingly.
     */
    public void placeVal(int val, int row, int col) {
        this.grid[row][col] = val;
        this.subgridHasVal[row/3][col/3][val] = true;
        
        /*** UPDATE YOUR ADDITIONAL FIELDS HERE. ***/
    }
        
    /*
     * remove the specified value from the cell with the specified
     * coordinates, and update the state of the puzzle accordingly.
     */
    public void removeVal(int val, int row, int col) {
        this.grid[row][col] = 0;
        this.subgridHasVal[row/3][col/3][val] = false;
        
        /*** UPDATE YOUR ADDITIONAL FIELDS HERE. ***/
    }  
    
    /*
     * read in the initial configuration of the puzzle from the specified 
     * Scanner, and use that config to initialize the state of the puzzle.  
     * The configuration should consist of one line for each row, with the
     * values in the row specified as integers separated by spaces.
     * A value of 0 should be used to indicate an empty cell.
     * 
     * You should not change this method.
     */
    public void readConfig(Scanner input) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                int val = input.nextInt();
                this.placeVal(val, r, c);
                if (val != 0) {
                    this.valIsFixed[r][c] = true;
                }
            }
            input.nextLine();
        }
    }
    
    /*
     * Displays the current state of the puzzle.
     * You should not change this method.
     */        
    public void printGrid() {
        for (int r = 0; r < 9; r++) {
            this.printRowSeparator();
            for (int c = 0; c < 9; c++) {
                System.out.print("|");
                if (this.grid[r][c] == 0) {
                    System.out.print("   ");
                } else {
                    System.out.print(" " + this.grid[r][c] + " ");
                }
            }
            System.out.println("|");
        }
        this.printRowSeparator();
    }
        
    // A private helper method used by display() 
    // to print a line separating two rows of the puzzle.
    private static void printRowSeparator() {
        for (int i = 0; i < 9; i++) {
            System.out.print("----");
        }
        System.out.println("-");
    }
    
    /*** ADD ANY ADDITIONAL METHODS HERE. ***/
    private int getVal(int n) {
    	return grid[getCellRow(n)][getCellCol(n)];
    }
    
    //if an given array contains a specific integer				operational
    private static boolean contains(int[] arr, int n) {
    	for(int i = 0; i < arr.length; i++) {
    		if(arr[i]==n) {
    			return true;
    		}
    	}
    	return false;
    }
    
    //returns row position of cell number						operational
    public int getCellRow(int n) {
    	if(n >= 0 && n < 9)
    		return 0;
    	else if(n >= 9 && n < 18)
    		return 1;
    	else if(n >= 18 && n < 27)
    		return 2;
    	else if(n >= 27 && n < 36)
    		return 3;
    	else if(n >= 36 && n < 45)
    		return 4;
    	else if(n >= 45 && n < 54)
    		return 5;
    	else if(n >= 54 && n < 63)
    		return 6;
    	else if(n >= 63 && n < 72)
    		return 7;
    	else if(n >= 72 && n < 81);
    		return 8;
    }
    
    //returns the column position of cell number				operational
    public int getCellCol(int n) {
    	return n%9;
    }
    
    //if number position on board is correct
    private boolean sectionIsValid(int position, int value) { //operational
    	boolean section = false;
    	
    	if(contains(section00,position))
    		section = subgridHasVal[0][0][value];
    	else if(contains(section01,position))
    		section = subgridHasVal[0][1][value];
    	else if(contains(section02,position))
    		section = subgridHasVal[0][2][value];
    	else if(contains(section10,position))
    		section = subgridHasVal[1][0][value];
    	else if(contains(section11,position))
    		section = subgridHasVal[1][1][value];
    	else if(contains(section12,position))
    		section = subgridHasVal[1][2][value];
    	else if(contains(section20,position))
    		section = subgridHasVal[2][0][value];
    	else if(contains(section21,position))
    		section = subgridHasVal[2][1][value];
    	else if(contains(section22,position))
    		section = subgridHasVal[2][2][value];
    	
    	return !section;
    }
    
    private boolean rowIsValid(int position, int value) {
    	boolean row = false;
    	
    	if(position >= 0 && position < 9) {
    		//System.out.println(Arrays.toString(grid[0]));
			row = contains(grid[0],value);
    		
    	}
    	else if(position >= 9 && position < 18)
    		row = contains(grid[1],value);
    	else if(position >= 18 && position < 27)
    		row = contains(grid[2],value);
    	else if(position >= 27 && position < 36)
    		row = contains(grid[3],value);
    	else if(position >= 36 && position < 45)
    		row = contains(grid[4],value);
    	else if(position >= 45 && position < 54)
    		row = contains(grid[5],value);
    	else if(position >= 54 && position < 63)
    		row = contains(grid[6],value);
    	else if(position >= 63 && position < 72)
    		row = contains(grid[7],value);
    	else if(position >= 72 && position < 81)
    		row = contains(grid[8],value);
    	
    	return !row;
    }
    
    private boolean colIsValid(int position, int value) {
		if(contains(colArr[getCellCol(position)],value)) {
			
		}
		for(int i = 0; i < 9; i++) {
			if(getVal(colArr[getCellCol(position)][i])==value) {
				return false;
			}
		}
		return true;
    }
         
    /*
     * This is the key recursive-backtracking method.  Returns true if
     * a solution has already been found, and false otherwise.
     * 
     * Each invocation of the method is responsible for finding the
     * value of a single cell of the puzzle. The parameter n
     * is the number of the cell that a given invocation of the method
     * is responsible for. We recommend that you consider the cells
     * one row at a time, from top to bottom and left to right,
     * which means that they would be numbered as follows:
     *
     *     0  1  2  3  4  5  6  7  8
     *     9 10 11 12 13 14 15 16 17
     *    18 ...
     */
    private boolean solveRB(int n) {
    	
    	if(n > 80)
        	return true;
        else {
        	
        	int row = getCellRow(n);
        	int col = getCellCol(n);
        	if(valIsFixed[row][col]) {
        		return solveRB(n+1);
        	}
        	else {
        		
	        	for(int i = 1; i < 10; i++) {
	        		if(sectionIsValid(n,i) && rowIsValid(n,i) && colIsValid(n,i)) {
	        			placeVal(i,row,col);
	        			if(solveRB(n+1)) {
	        				return true;
	        			}
	        			removeVal(i,row,col);
	        		}
	        	}
        	}
        }
        return false;
    } 
    
    /*
     * public "wrapper" method for solveRB().
     * Makes the initial call to solveRB, and returns whatever it returns.
     */
    public boolean solve() { 
        boolean foundSol = this.solveRB(0);
        return foundSol;
    }
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Sudoku puzzle = new Sudoku();
        
        System.out.print("Enter the name of the puzzle file: ");
        String filename = scan.nextLine();
        
        try {
            Scanner input = new Scanner(new File(filename));
            puzzle.readConfig(input);
        } catch (IOException e) {
            System.out.println("error accessing file " + filename);
            System.out.println(e);
            System.exit(1);
        }
        
        System.out.println();
        System.out.println("Here is the initial puzzle: ");
        puzzle.printGrid();
        System.out.println();
        
        if (puzzle.solve()) {
            System.out.println("Here is the solution: ");
        } else {
            System.out.println("No solution could be found.");
            System.out.println("Here is the current state of the puzzle:");
        }
        puzzle.printGrid();
    }    
}
