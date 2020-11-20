package ps8;

/*
 * ChainedHashTable.java
 *
 * Computer Science 112, Boston University
 * 
 * Modifications and additions by:
 *     name:
 *     email:
 */

import java.util.*;     // to allow for the use of Arrays.toString() in testing

/*
 * A class that implements a hash table using separate chaining.
 */
public class ChainedHashTable implements HashTable {
    /* 
     * Private inner class for a node in a linked list
     * for a given position of the hash table
     */
    private class Node {
        private Object key;
        private LLQueue<Object> values;
        private Node next;
        
        private Node(Object key, Object value) {
            this.key = key;
            values = new LLQueue<Object>();
            values.insert(value);
            next = null;
        }
    }
    
    private Node[] table;      // the hash table itself
    private int numKeys;       // the total number of keys in the table
        
    /* hash function */
    public int h1(Object key) {
        int h1 = key.hashCode() % table.length;
        if (h1 < 0) {
            h1 += table.length;
        }
        return h1;
    }
    
    /*** Add your constructor here ***/
    public ChainedHashTable(int size) {
    	if(size < 0)
    		throw new IllegalArgumentException("invalid parameter");
    	table = new Node[size];
    }
    
    /*
     * insert - insert the specified (key, value) pair in the hash table.
     * Returns true if the pair can be added and false if there is overflow.
     */
    public boolean insert(Object key, Object value) {
        /** Replace the following line with your implementation. **/
    	int index = h1(key);
    	if(table[index] == null) {
    		table[index] = new Node(key, value);
    		numKeys++;
    		return true;
    	}
    	else {
    		Node trav = table[index];
    		while(trav != null) {
    			if(trav.key.equals(key)) {
    				trav.values.insert(value);
    				return true;
    			}
    			trav = trav.next;
    		}
    		trav = new Node(key, value);
    		trav.next = table[index];
    		table[index] = trav;
    		numKeys++;
    		return true;
    	}
    }
    
    /*
     * search - search for the specified key and return the
     * associated collection of values, or null if the key 
     * is not in the table
     */
    public Queue<Object> search(Object key) {
        /** Replace the following line with your implementation. **/
    	for(int i = 0; i < table.length; i++) {
    		Node trav = table[i];
    		while(trav != null && trav.key != null) {
    			if(trav.key.equals(key)) {
    				return trav.values;
    			}
    			trav = trav.next;
    		}
    	}
    	return null;
    }
    
    /* 
     * remove - remove from the table the entry for the specified key
     * and return the associated collection of values, or null if the key 
     * is not in the table
     */
    public Queue<Object> remove(Object key) {
        /** Replace the following line with your implementation. **/
    	if(key==null) {
    		throw new IllegalArgumentException("invalid");
    	}
    	int index = h1(key);
    	Node trav = table[index];
    	while(trav != null) {
    		if(trav.key.equals(key)) {				
    			Node hold = new Node(null, null);
    			if(trav.next==null)	{					//if the node in the table is by itself then we can replace the reference to it
    				table[index] = null;
					numKeys--;
    				return trav.values;
    			}
    			else if(table[index].key==key)	{		//if the node has another position, then replace the reference node in the array to the node after the one we are replacing
    				hold = trav;
    				table[index] = trav.next;
					numKeys--;
    				return hold.values;
    			}
    			else 									//if it is not the first node in the space in the array that we have to remove then we look at the linked entries
    				while(trav!=null) {
    					hold = trav;					//the node "hold" from earlier is used as a reference to the previous entry
    					trav = trav.next;
    					if(trav.key.equals(key)) 
    						hold.next = trav.next;
    					numKeys--;
    					return trav.values;
    				}
    		}
    		trav = trav.next;
    	}
        return null;
    }
    
    
    /*** Add the other required methods here ***/
    public int getNumKeys() {
    	return numKeys;
    }
    
    /**
     * takes no parameters and that returns a value of type double that represents the load factor of the table: 
     * the number of keys in the table divided by the size of the table
     */
    public double load() {
    	return (double)getNumKeys()/table.length;
    }
    
    /**
     * takes no parameters and that returns an array of type Object containing all of the keys in the hash table
     */
    public Object[] getAllKeys() {
    	Object[] arr = new Object[getNumKeys()];
    	int count = 0;
    	for(int i = 0; i < table.length; i++) {
    		if(table[i] == null ) {
    			
    		}
    		else {
	    		Node trav = table[i];
	    		while(trav != null && trav.key != null) {
	    			arr[count] = trav.key;
	    			count++;
	    			trav = trav.next;
	    		}
    		}
    	}
    	return arr;
    }
    
    /**
     * resize() takes an integer representing the new size, 
     * and that grows the table to have that new size. It should not return a value
     */
    public void resize(int size) {
    	if(size==table.length) {
    	}
    	else if(size<table.length) {
    		throw new IllegalArgumentException("invalid size");
    	}
    	else {
    		Node[] tempTable = table;
	    	table = new Node[size];
	    	for(Node n : tempTable) {
	    		while(n!=null) {
	    			int index = h1(n.key);
	    	    	if(table[index] == null) {
	    	    		table[index] = new Node(n.key, n.values);
	    	    	}
	    	    	else {
	    	    		Node trav = table[index];
	    	    		while(trav != null) {
	    	    			if(trav.key.equals(n.key)) {
	    	    				trav.values.insert(n.values);
	    	    			}
	    	    			trav = trav.next;
	    	    		}
	    	    		trav = new Node(n.key, n.values);
	    	    		trav.next = table[index];
	    	    		table[index] = trav;
	    	    	}
	    	    	n=n.next;
	    		}
	    	}
    	}
    }
    
    /*
     * toString - returns a string representation of this ChainedHashTable
     * object. *** You should NOT change this method. ***
     */
    public String toString() {
        String s = "[";
        
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                s += "null";
            } else {
                String keys = "{";
                Node trav = table[i];
                while (trav != null) {
                    keys += trav.key;
                    if (trav.next != null) {
                        keys += "; ";
                    }
                    trav = trav.next;
                }
                keys += "}";
                s += keys;
            }
        
            if (i < table.length - 1) {
                s += ", ";
            }
        }       
        
        s += "]";
        return s;
    }

    public static void main(String[] args) {
        /** Add your unit tests here **/
    	System.out.println("--------My Unit Tests--------");
    	ChainedHashTable table = new ChainedHashTable(6);
    	table.insert("cowboy", 20);
    	table.insert("fishing rod", 1);
    	table.insert("helloworld", 8);
    	table.insert("test", 15);
    	System.out.println("table before resize:");
    	System.out.println(table);
    	table.resize(8);
    	System.out.println("table after resize:");
    	System.out.println(table);
    	System.out.println("testing resizing table to smaller size:");
    	try {
    		table.resize(3);
    	}
    	catch(Exception e) {
    		System.out.println(e);
    	}
    	
    	table = new ChainedHashTable(5);
    	System.out.println("");
    	System.out.println("--------More Tests--------");
    	table.insert("computer", 2);
    	table.insert("screen", 9);
    	table.insert("headset", 9);
    	table.insert("bug", 7);
    	System.out.println("original table:");
    	System.out.println(table);
    	System.out.println("will insert(\"screen\", 15) work?");
    	System.out.println(table.insert("screen", 15));
    	System.out.println("table after:");
    	System.out.println(table);
    	System.out.println("test to remove computer and headset works");
    	table.remove("computer");
    	table.remove("headset");
    	System.out.println(table);
    	System.out.println("test get all keys");
    	System.out.println(Arrays.toString(table.getAllKeys()));
    	System.out.println("");
    	
    }
}
