package ps6;

/*
 * Problem7.java
 * 
 * Computer Science 112, Boston University
 */

public class Problem7 {
    /*
     * getAllOdds (ArrayList version) - takes the ArrayList vals (which is 
     * assumed to contain only integers) and creates and returns a new 
     * ArrayList containing all of the odd integers in vals.
     */
    public static ArrayList getAllOdds(ArrayList vals) {
        /* Replace the line below with your implementation of this method. */
    	ArrayList temp = new ArrayList(vals.length());
    	for(int i = 0; i < vals.length(); i++ ) {
    		temp.addItem(vals.getItem(i), temp.length());
    	}
    	int count = 0;
    	if(temp != null) {
	    	while(count < temp.length()) {
	    		if((Integer)(temp.getItem(count))%2 == 0) {
	    			temp.removeItem(count);
	    			count--;
	    		}
	    		count++;
	    	}
    	}
        return temp;
    }
    
    /*
     * getAllOdds (LLList version) - takes the LLList vals (which is 
     * assumed to contain only integers) and creates and returns a new 
     * LLList containing all of the odd integers in vals.
     */
    public static LLList getAllOdds(LLList vals) {
        /* Replace the line below with your implementation of this method. */
    	LLList temp = new LLList();
    	for(int i = vals.length()-1; i>0; i--) {
    		temp.addItem(vals.getItem(i), 0);
    	}
    	for(int i = 0; i < temp.length(); i ++ ) {
    		if((Integer)(temp.getItem(i))%2 == 0) {
    			temp.removeItem(i);
    			i--;
    		}
    	}
        return temp;
    }
    
    public static void main(String[] args) {
        Integer[] vals = {2, 5, 14, 6, 5, 8, 3};  
        ArrayList list1 = new ArrayList(vals);
        ArrayList odds1 = Problem7.getAllOdds(list1);
        System.out.println(odds1);
        
        LLList list2 = new LLList(vals);
        LLList odds2 = Problem7.getAllOdds(list2);
        System.out.println(odds2);
        /* We encourage you to add additional test code below. */
    }
}