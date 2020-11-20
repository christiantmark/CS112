package ps1;
/*
 * Problem Set 1
 *
 * Practice with static methods, part I
 */

public class Methods {
    /*
     * printVertical - takes a string s and prints the characters of 
     * the string vertically -- with one character per line.
     */
    public static void printVertical(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            System.out.println(c);
        }
    }

    public static void printWithSpaces(String s) {
    	for (int i = 0; i < s.length(); i++) {
    		char c = s.charAt(i);
    		System.out.print(c + " ");
    	}
    	System.out.println("");
    }
    
    public static char middleChar(String s) {
    	int length = s.length();
    	if(length%2 == 1) {
    		return s.charAt(length/2);
    	}
    	return s.charAt((length/2)-1);
    }
    
    public static String moveToEnd(String s, int num) {
    	if(num < s.length()) {
    		String end = s.substring(num);
    		String beginning = s.substring(0,num);
    		return end + beginning;
    	}
    	return s;
    }
    
    public static void main(String[] args) {
        /* Sample test call */
        printVertical("method");      
        printWithSpaces("method");
        char middle = middleChar("clock");
        System.out.println("the middle of clock is: " + middle);
        moveToEnd("Boston", 4);
        moveToEnd("Terriers", 2);
        moveToEnd("Boston", 8);
    }
}
