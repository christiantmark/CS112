package ps1;
/*
 * Problem Set 1
 * 
 * A simple interactive program that performs operations 
 * on a set of three integers.
 */

import java.util.*;

public class SimpleStats {

    public static void printMenu() {
        System.out.println("(0) Enter new numbers");
        System.out.println("(1) Find the largest");
        System.out.println("(2) Compute the sum");
        System.out.println("(3) Compute the range (largest - smallest)");
        System.out.println("(4) Compute the average");
        System.out.println("(5) Print the numbers in ascending order");
        System.out.println("(6) Quit");
        System.out.println();
    }
    
    /*** PUT YOUR SEPARATE HELPER METHODS FOR OPTIONS 1-5 HERE ***/
    
    public static int getLargest(int n1, int n2, int n3) {
    	int i = 0;
    	if (n1 > n2 && n1 > n3)
    		i = n1;
    	else if (n2 > n1 && n2 > n3)
    		i = n2;
    	else if (n3 > n1 && n3 > n2)
    		i = n3;
    	else i = n1;
    	return i;
    }
        
    public static int getSum(int n1, int n2, int n3) {
    	return n1 + n2 + n3;
    }
    
    public static int getSmallest(int n1, int n2, int n3) {
    	int i = 0;
    	if (n1 < n2 && n1 < n3)
    		i = n1;
    	else if (n2 < n1 && n2 < n3)
    		i = n2;
    	else if (n3 < n1 && n3 < n2)
    		i = n3;
    	else i = n1;
    	return i;
    }
    
    public static double getAvg(int n1, int n2, int n3) {
    	double sum = (double)getSum(n1, n2, n3);
    	return sum/3;
    }
    
    public static void asc(int n1, int n2, int n3) {
    	String s = "";
    	int top = 0;
    	int mid = 0;
    	int bot = 0;
    	if (n1 == n2 && n2 == n3) {
    		for (int i = 0; i < 3; i++)
    			s += Integer.toString(n1) + "\n";
    	}
    	else if (getLargest(n1,n2,n3) == n1) {
    		top = n1;  
    		if (n2 > n3) {
    			mid = n2;
    			bot = n3;
    		} else {
    			mid = n3;
    			bot = n2;
    		}
    	}
    	else if (getLargest(n1,n2,n3) == n2) {
    		top = n2;
    		if (n1 > n3) {
    			mid = n1;
    			bot = n3;    			
    		} else {
    			mid = n3;
    			bot = n1;
    		}
    	}
    	else {
    		top = n3;
    		if (n1 > n2) {
    			mid = n1;
    			bot = n2;
    		} else {
    			mid = n2;
    			bot = n1;
    		}
    	}
    	s += Integer.toString(bot) + " " + Integer.toString(mid) + " " + Integer.toString(top);
    	System.out.println(s);;
    }
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);        

        // the three integers
        int n1 = 2;
        int n2 = 4;
        int n3 = 6;

        boolean more_input = true;
        
        do {
            System.out.print("The current numbers are: ");
            System.out.println(n1 + " " + n2 + " " + n3);
            
            printMenu();
            System.out.print("Enter your choice: ");
            int choice = scan.nextInt();
            
            /*
             * Expand this conditional statement to process choices 1-5.
             * Make sure to follow the guidelines in the assignment for
             * doing so.
             */
            if (choice == 0) {
                System.out.print("Enter three new numbers: ");
                n1 = scan.nextInt();
                n2 = scan.nextInt();
                n3 = scan.nextInt();
            } else if (choice == 1) {
            	System.out.println(getLargest(n1,n2,n3));
            } else if (choice == 2) {
            	System.out.println(getSum(n1, n2, n3));
            } else if (choice == 3) {
            	System.out.println(getLargest(n1,n2,n3)-getSmallest(n1,n2,n3));
            } else if (choice == 4) {
            	System.out.println(getAvg(n1,n2,n3));
            } else if (choice == 5) {
            	asc(n1,n2,n3);
            } else if (choice == 6) {
                more_input = false;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
            
            System.out.println();
        } while (more_input);
        
        System.out.println("Have a nice day!");
    }
}
