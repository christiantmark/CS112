package ps6;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class LLBag implements Bag{
	
	private class Node {
        private Object item;
        private Node next;
        
        private Node(Object i, Node n) {
            item = i;
            next = n;
        }
    }
	
    private Node head;
    private int itemCount;
    
    
	public LLBag() {
		head = new Node(null, null);
        itemCount = 0;
	}
	
	public LLBag(int numItems) {
        head = new Node(null, null);
        itemCount = 0;
    }

	public boolean add(Object item) {
		if (item == null) {
            throw new IllegalArgumentException("item must be non-null");
        } else {
        	head.next = new Node(item, head.next);
			itemCount++;
            return true;
        }
	}
    
    /** 
     * removes one occurrence of the specified item (if any) from the
     * Bag.  Returns true on success and false if the specified item
     * (i.e., an object equal to item) is not in the Bag.
     */
    public boolean remove(Object item) {
    	Node trav = head.next;
    	if(itemCount == 1) {
    		head = new Node(null, null);
    		itemCount--;
    	}
    	else {
	    	while(trav.next!=null) {
	    		if(trav.next.item.equals(item)) {
	    			trav.next = trav.next.next;
	    			itemCount--;
	    			return true;
	    		}
	    		trav = trav.next;
	    	}
    	}
    	return false;
    }
    
    /**
     * returns true if the specified item is in the Bag, and false
     * otherwise.
     */
    public boolean contains(Object item) {
    	LLListIterator trav = new LLListIterator(head);
    	while(trav.hasNext()) {
    		if(trav.next().equals(item)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * returns the number of items in the Bag.
     */
    public int numItems() {
    	return itemCount;
    }
    
    /**
     * grab - returns a reference to a randomly chosen in the Bag.
     */
    public Object grab() {
    	if (itemCount == 0) {
            throw new IllegalStateException("the bag is empty");
        }
    	Node trav = head;
    	int whichOne = (int)(Math.random() * itemCount-1)+1;
    	for(int i = 0; i < whichOne; i++) {
    		trav = trav.next;
    	}
    	return trav.item;
    }
    
    /**
     * toArray - return an array containing the current contents of the bag
     */
    public Object[] toArray() {
    	Object[] arr = new Object[itemCount];
    	Node trav = head.next;
    	for(int i = 0; i < itemCount; i++) {
    		
    		arr[i] = trav.item;
    		trav = trav.next;
    	}
    	return arr;
    }
    
    public String toString() {
        String str = "{";
        Node trav = head.next;
        for (int i = 0; i < itemCount; i++) {
            str = str + trav.item;
            if (i != itemCount - 1) {
                str += ", ";
            }
            trav = trav.next;
        }
        
        str = str + "}";
        return str;
    }
    
    private class LLListIterator implements ListIterator {
        private Node nextNode;       // the next node to visit    
        
        public LLListIterator(Node n) {
            nextNode = n;
            nextNode = head.next;    // skip over the dummy head node
        }
        
        /*
         * hasNext - does the iterator have additional items to visit?
         */
        public boolean hasNext() {
            return (nextNode != null);
        }
        
        /*
         * next - returns a reference to the next Object in the iteration
         */
        public Object next() {
            if (nextNode == null) {
                throw new NoSuchElementException();
            }
            
            Object item = nextNode.item;
            nextNode = nextNode.next;
            return item;
        }
    }
    
    /* Test the ArrayBag implementation. */
    public static void main(String[] args) {
        // Create a Scanner object for user input.
        Scanner scan = new Scanner(System.in);
        
        // Create an ArrayBag named bag1.
        System.out.print("number of items in bag 1: ");
        int numItems = scan.nextInt();
        Bag bag1 = new LLBag(numItems);
        scan.nextLine();    // consume the rest of the line
        
        // Read in strings, add them to bag1, and print out bag1.
        String itemStr;
        for (int i = 0; i < numItems; i++) {
            System.out.print("item " + i + ": ");
            itemStr = scan.nextLine();
            bag1.add(itemStr);
        }
        System.out.println("bag 1 = " + bag1);
        System.out.println();
        
        System.out.print("this is the number of items in the bag is ");
        System.out.println(bag1.numItems());
        System.out.println("");
        
        System.out.println("see if this item is contained in the bag: ");
        itemStr = scan.nextLine();
        System.out.println(bag1.contains(itemStr));
        System.out.println();
        
        // Select a random item and print it.
        Object item = bag1.grab();
        System.out.println("grabbed " + item);
        System.out.println();
        Object item2 = bag1.grab();
        System.out.println("grabbed " + item2);
        System.out.println();
        Object item3 = bag1.grab();
        System.out.println("grabbed " + item3);
        System.out.println();
        
        // Iterate over the objects in bag1, printing them one per line.
        Object[] items = bag1.toArray();
        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i]);
        }
        System.out.println();
        
        // Get an item to remove from bag1, remove it, and reprint the bag.
        System.out.print("item to remove: ");
        itemStr = scan.nextLine();
        if (bag1.contains(itemStr)) {
        	System.out.println(bag1.contains(itemStr) + " " + itemStr + " will be removed");
            bag1.remove(itemStr);
        }
        else System.out.println(bag1.contains(itemStr) + " " + itemStr + " will not be removed bc it doesnt exist");
        
        System.out.print("this is the number of items in the bag is ");			//number of items in bag
        System.out.println(bag1.numItems());
        System.out.println("");
        
        System.out.println("bag 1 = " + bag1);
        System.out.println();
        
        System.out.println("type item out to be added");
        itemStr = scan.nextLine();
        System.out.print("the item was added: ");
        System.out.print(bag1.add(itemStr));
        System.out.println("");
        
        System.out.print("\nthis is the number of items in the bag is ");			//number of items in bag
        System.out.println(bag1.numItems());
        System.out.println("");
        System.out.println("bag 1 = " + bag1);
    }
}
