package ps7;

/*
 * LinkedTree.java
 *
 * Computer Science 112
 *
 * Modifications and additions by:
 *     name:
 *     username:
 */

import java.util.*;

/*
 * LinkedTree - a class that represents a binary tree containing data
 * items with integer keys.  If the nodes are inserted using the
 * insert method, the result will be a binary search tree.
 */
public class LinkedTree {
    // An inner class for the nodes in the tree
    private class Node {
        private int key;         // the key field
        private LLList data;     // list of data values for this key
        private Node left;       // reference to the left child/subtree
        private Node right;      // reference to the right child/subtree
        
        private Node(int key, Object data){
            this.key = key;
            this.data = new LLList();
            this.data.addItem(data, 0);
            this.left = null;
            this.right = null;
        }
    }
    
    // the root of the tree as a whole
    private Node root;
    
    public LinkedTree() {
        root = null;
    }
    
    /*
     * Prints the keys of the tree in the order given by a preorder traversal.
     * Invokes the recursive preorderPrintTree method to do the work.
     */
    public void preorderPrint() {
        if (root != null) {
            preorderPrintTree(root);      
        }
        System.out.println();
    }
    
    /*
     * Recursively performs a preorder traversal of the tree/subtree
     * whose root is specified, printing the keys of the visited nodes.
     * Note that the parameter is *not* necessarily the root of the 
     * entire tree. 
     */
    private static void preorderPrintTree(Node root) {
        System.out.print(root.key + " ");
        if (root.left != null) {
            preorderPrintTree(root.left);
        }
        if (root.right != null) {
            preorderPrintTree(root.right);
        }
    }
    
    /*
     * Prints the keys of the tree in the order given by a postorder traversal.
     * Invokes the recursive postorderPrintTree method to do the work.
     */
    public void postorderPrint() {
        if (root != null) {
            postorderPrintTree(root);      
        }
        System.out.println();
    }
    
    /*
     * Recursively performs a postorder traversal of the tree/subtree
     * whose root is specified, printing the keys of the visited nodes.
     * Note that the parameter is *not* necessarily the root of the 
     * entire tree. 
     */
    private static void postorderPrintTree(Node root) {
        if (root.left != null) {
            postorderPrintTree(root.left);
        }
        if (root.right != null) {
            postorderPrintTree(root.right);
        }
        System.out.print(root.key + " ");
    }
    
    /*
     * Prints the keys of the tree in the order given by an inorder traversal.
     * Invokes the recursive inorderPrintTree method to do the work.
     */
    public void inorderPrint() {
        if (root != null) {
            inorderPrintTree(root);      
        }
        System.out.println();
    }
    
    /*
     * Recursively performs an inorder traversal of the tree/subtree
     * whose root is specified, printing the keys of the visited nodes.
     * Note that the parameter is *not* necessarily the root of the 
     * entire tree. 
     */
    private static void inorderPrintTree(Node root) {
        if (root.left != null) {
            inorderPrintTree(root.left);
        }
        System.out.print(root.key + " ");
        if (root.right != null) {
            inorderPrintTree(root.right);
        }
    }
    
    /* 
     * Inner class for temporarily associating a node's depth
     * with the node, so that levelOrderPrint can print the levels
     * of the tree on separate lines.
     */
    private class NodePlusDepth {
        private Node node;
        private int depth;
        
        private NodePlusDepth(Node node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
    
    /*
     * Prints the keys of the tree in the order given by a
     * level-order traversal.
     */
    public void levelOrderPrint() {
        LLQueue<NodePlusDepth> q = new LLQueue<NodePlusDepth>();
        
        // Insert the root into the queue if the root is not null.
        if (root != null) {
            q.insert(new NodePlusDepth(root, 0));
        }
        
        // We continue until the queue is empty.  At each step,
        // we remove an element from the queue, print its value,
        // and insert its children (if any) into the queue.
        // We also keep track of the current level, and add a newline
        // whenever we advance to a new level.
        int level = 0;
        while (!q.isEmpty()) {
            NodePlusDepth item = q.remove();
            
            if (item.depth > level) {
                System.out.println();
                level++;
            }
            System.out.print(item.node.key + " ");
            
            if (item.node.left != null) {
                q.insert(new NodePlusDepth(item.node.left, item.depth + 1));
            }
            if (item.node.right != null) {
                q.insert(new NodePlusDepth(item.node.right, item.depth + 1));
            }
        }
        System.out.println();
    }
    
    /*
     * Searches for the specified key in the tree.
     * If it finds it, it returns the list of data items associated with the key.
     * Invokes the recursive searchTree method to perform the actual search.
     */
    public LLList search(int key) {
        Node n = searchTree(root, key);
        if (n == null) {
            return null;
        } else {
            return n.data;
        }
    }
    
    /*
     * Recursively searches for the specified key in the tree/subtree
     * whose root is specified. Note that the parameter is *not*
     * necessarily the root of the entire tree.
     */
    private static Node searchTree(Node root, int key) {
        if (root == null) {
            return null;
        } else if (key == root.key) {
            return root;
        } else if (key < root.key) {
            return searchTree(root.left, key);
        } else {
            return searchTree(root.right, key);
        }
    }
    
    /*
     * Inserts the specified (key, data) pair in the tree so that the
     * tree remains a binary search tree.
     */
    public void insert(int key, Object data) {
        // Find the parent of the new node.
        Node parent = null;
        Node trav = root;
        while (trav != null) {
            if (trav.key == key) {
                trav.data.addItem(data, 0);
                return;
            }
            parent = trav;
            if (key < trav.key) {
                trav = trav.left;
            } else {
                trav = trav.right;
            }
        }
        
        // Insert the new node.
        Node newNode = new Node(key, data);
        if (parent == null) {    // the tree was empty
            root = newNode;
        } else if (key < parent.key) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }
    
    /*
     * FOR TESTING: Processes the integer keys in the specified array from 
     * left to right, adding a node for each of them to the tree. 
     * The data associated with each key is a string based on the key.
     */
    public void insertKeys(int[] keys) {
        for (int i = 0; i < keys.length; i++) {
            insert(keys[i], "data for key " + keys[i]);
        }
    }
    
    /*
     * Deletes the node containing the (key, data) pair with the
     * specified key from the tree and return the associated data item.
     */
    public LLList delete(int key) {
        // Find the node to be deleted and its parent.
        Node parent = null;
        Node trav = root;
        while (trav != null && trav.key != key) {
            parent = trav;
            if (key < trav.key) {
                trav = trav.left;
            } else {
                trav = trav.right;
            }
        }
        
        // Delete the node (if any) and return the removed data item.
        if (trav == null) {   // no such key    
            return null;
        } else {
            LLList removedData = trav.data;
            deleteNode(trav, parent);
            return removedData;
        }
    }
    
    /*
     * Deletes the node specified by the parameter toDelete.  parent
     * specifies the parent of the node to be deleted. 
     */
    private void deleteNode(Node toDelete, Node parent) {
        if (toDelete.left != null && toDelete.right != null) {
            // Case 3: toDelete has two children.
            // Find a replacement for the item we're deleting -- as well as 
            // the replacement's parent.
            // We use the smallest item in toDelete's right subtree as
            // the replacement.
            Node replaceParent = toDelete;
            Node replace = toDelete.right;
            while (replace.left != null) {
                replaceParent = replace;
                replace = replace.left;
            }
            
            // Replace toDelete's key and data with those of the 
            // replacement item.
            toDelete.key = replace.key;
            toDelete.data = replace.data;
            
            // Recursively delete the replacement item's old node.
            // It has at most one child, so we don't have to
            // worry about infinite recursion.
            deleteNode(replace, replaceParent);
        } else {
            // Cases 1 and 2: toDelete has 0 or 1 child
            Node toDeleteChild;
            if (toDelete.left != null) {
                toDeleteChild = toDelete.left;
            } else {
                toDeleteChild = toDelete.right;  // null if it has no children
            }
            
            if (toDelete == root) {
                root = toDeleteChild;
            } else if (toDelete.key < parent.key) {
                parent.left = toDeleteChild;
            } else {
                parent.right = toDeleteChild;
            }
        }
    }
    
    /*
     * "wrapper method" for the recursive depthInTree() method
     * from PS 7, Problem 6
     */
    public int depth(int key) {
        if (root == null) {    // root is the root of the entire tree
            return -1;
        }
        
        return depthInTree(key, root);
    }
    
    /*
     * original version of the recursive depthInTree() method  
     * from PS 7, Problem 6. You will write a more efficient version
     * of this method.
     */
    private static int depthInTree(int key, Node root) {
        if (key == root.key) {
            return 0;
        }
        
        if (root.left != null) {
            int depthInLeft = depthInTree(key, root.left);
            if (depthInLeft != -1) {
                return depthInLeft + 1;
            }
        }
        
        if (root.right != null) {
            int depthInRight = depthInTree(key, root.right);
            if (depthInRight != -1) {
                return depthInRight + 1;
            }
        }
        
        return -1;
    }
    
    public int depthIter(int key) {
    	int count = 0;
    	Node trav = root;
    	while(trav != null) {
    		if(key == trav.key)
    			return count;
    		else if(key < trav.key) {
    			trav = trav.left;
    			count++;
    		}
    		else if(key > trav.key) {
    			trav = trav.right;
    			count++;
    		}
    	}
    	return -1;
    }
    
    public int numLeafNodes() {
        return numLeafNodesInTree(root);
    }
    
    private int numLeafNodesInTree(Node n) {
    	if(n == null)
    		return 0;
    	if(n.left == null && n.right == null)
    		return 1;
    	else
    		return numLeafNodesInTree(n.left) +  numLeafNodesInTree(n.right);
    }
    
    public int deleteSmallest() {
    	Node trav = root;
    	if(trav == null)
    		return -1;
    	if(trav.left == null) {
    		int hold = trav.key;
    		root = null;
    		return hold;
    	}
    	while(trav.left.left != null) {
    		trav = trav.left;
    	}
    	int k = trav.left.key;
    	if(trav.left.right == null)
    		trav.left = null;
    	else
    		trav.left = trav.left.right;
    	return k;
    }
    
    public static void main(String[] args) {
        System.out.println("--- Testing depth()/depthInTree() from Problem 4 ---");
        System.out.println();
        System.out.println("(0) Testing on tree from Problem 3, depth of 28 node");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {44, 35, 53, 23, 48, 62, 28, 57, 80};
            tree.insertKeys(keys);
            
            int results = tree.depth(28);
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(3);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 3);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests
        
        /*
         * Add at least two unit tests for each method from Problem 6.
         * Test a variety of different cases. 
         * Follow the same format that we have used above.
         */
        
        System.out.println("--- Testing depthIter() ---");
        System.out.println();
        System.out.println("(1) Testing on tree from Problem 5, depth of 48 node");
        try {
            LinkedTree tree1 = new LinkedTree();
            int[] keys1 = {44, 35, 53, 23, 48, 62, 28, 57, 80};
            tree1.insertKeys(keys1);
            
            int results = tree1.depthIter(48);
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(2);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 2);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();
        System.out.println("(2) Testing on custom tree, depth of 80 node");
        try {
            LinkedTree tree2 = new LinkedTree();
            int[] keys2 = {34, 23, 49, 12, 22, 9, 0, 23, 80, 53, 38};
            tree2.insertKeys(keys2);
            tree2.levelOrderPrint();
            
            int results = tree2.depthIter(80);
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(2);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 2);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests
        
        System.out.println("--- Testing numLeafNodesInTree()/numLeafNodes() ---");
        System.out.println();
        System.out.println("(3) Testing on tree from Problem 5, number of leaf nodes");
        try {
            LinkedTree tree3 = new LinkedTree();
            int[] keys3 = {44, 35, 53, 23, 48, 62, 28, 57, 80};
            tree3.insertKeys(keys3);
            
            int results = tree3.numLeafNodes();
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(4);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 4);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();
        System.out.println("(4) Testing on custom tree, number of leaf nodes");
        try {
            LinkedTree tree4 = new LinkedTree();
            int[] keys4 = {34, 23, 49, 12, 22, 9, 0, 23, 80, 53, 38};
            tree4.insertKeys(keys4);
            tree4.levelOrderPrint();
            
            int results = tree4.numLeafNodes();
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(4);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 4);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests
        
        System.out.println("--- Testing deleteSmallest() ---");
        System.out.println();
        System.out.println("(5) Testing on tree from Problem 5");
        try {
        	LinkedTree tree5 = new LinkedTree();
            System.out.println("empty tree: " + tree5.deleteSmallest());
            System.out.println();

            int[] keys5 = {44, 35, 53, 23, 48, 62, 28, 57, 80};
            tree5.insertKeys(keys5);
            tree5.levelOrderPrint();
            System.out.println();

            System.out.println("first deletion: " + tree5.deleteSmallest());
            tree5.levelOrderPrint();
            System.out.println();

            System.out.println("second deletion: " + tree5.deleteSmallest());
            tree5.levelOrderPrint();
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();
        System.out.println("(6) Testing on custom tree");
        try {
        	LinkedTree tree6 = new LinkedTree();
            System.out.println("empty tree: " + tree6.deleteSmallest());
            System.out.println();

            int[] keys5 = {34, 23, 49, 12, 22, 9, 0, 23, 80, 53, 38};
            tree6.insertKeys(keys5);
            tree6.levelOrderPrint();
            System.out.println();

            System.out.println("first deletion: " + tree6.deleteSmallest());
            tree6.levelOrderPrint();
            System.out.println();

            System.out.println("second deletion: " + tree6.deleteSmallest());
            tree6.levelOrderPrint();
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
    	LLStack<Integer> s = new LLStack<Integer>();
    	s.push(5);
    	s.push(4);
    	System.out.println(s);
    	
        
        
        /*
        System.out.println();    // include a blank line between tests
        
        //test depthIter
        LinkedTree tree = new LinkedTree();
        System.out.println("empty tree: " + tree.depthIter(13));
        int[] keys = {37, 26, 42, 13, 35, 56, 30, 47, 70};
        tree.insertKeys(keys);
        System.out.println("depth of 13: " + tree.depthIter(13));
        System.out.println("depth of 37: " + tree.depthIter(37));
        System.out.println("depth of 47: " + tree.depthIter(47));
        System.out.println("depth of 50: " + tree.depthIter(50));

        System.out.println();    // include a blank line between tests
        
        //test numLeafNodes
        LinkedTree tree1 = new LinkedTree();
        System.out.println("before: " + tree1.numLeafNodes());
        int[] keys1 = {37, 26, 42, 13, 35, 56, 30, 47, 70};
        tree1.insertKeys(keys1);
        System.out.println("after: " + tree1.numLeafNodes());

        System.out.println();    // include a blank line between tests
        
        //test deleteSmallest
        LinkedTree tree2 = new LinkedTree();
        System.out.println("empty tree: " + tree2.deleteSmallest());
        System.out.println();

        int[] keys2 = {37, 26, 42, 13, 35, 56, 30, 47, 70};
        tree2.insertKeys(keys2);
        tree2.levelOrderPrint();
        System.out.println();

        System.out.println("first deletion: " + tree2.deleteSmallest());
        tree2.levelOrderPrint();
        System.out.println();

        System.out.println("second deletion: " + tree2.deleteSmallest());
        tree2.levelOrderPrint();

        System.out.println();    // include a blank line between tests
        */
        
    }
}
