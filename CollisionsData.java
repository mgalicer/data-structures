package project5;

import java.util.HashMap;
import java.util.Map;

//import project5.BST_Recursive.Node;
/**
 * 
 * @author Mari Galicer, some methods written by Joanna Klukowska
 *
 * @param Collision, the data that is stored in nodes of the tree
 */
public class CollisionsData {
	
	// root of the tree
	protected Node root;
	// current number of nodes in the tree
	protected int numOfElements;
	//helper variable used by the remove methods 
	private boolean found;

	/**
	 * Default constructor that creates an empty tree.
	 */
	public CollisionsData() {
		this.root = null;
		numOfElements = 0;
	}
	
	/**
	 * Returns a report that specifies the important collision info for the specified zip code.
	 * @param zip
	 * @param dateBegin
	 * @param dateEnd
	 * @return String
	 */
	public String getReport(String zip, Date dateBegin, Date dateEnd) {
		int[] injuries = new int[10];
		find(this.root, zip, dateBegin, dateEnd, injuries);
		String header = "Motor Vehicle Collisions for zipcode " + zip + " (" + dateBegin.toString() + " - " + dateEnd.toString() + ")\n";
		String divider = "====================================================================\n";
		int numFatalities = injuries[0] + injuries[1] + injuries[2];
		int numInjuries = injuries[3] + injuries[4] + injuries[5];
		int total = injuries[6];
				
		return header + divider + "Total number of collisions: " + total + "\n"
				+ String.format("%28s","Number of fatalities: ") + numFatalities +"\n"
				+ String.format("%28s","pedestrians: ") + injuries[0] + "\n"
				+ String.format("%28s","cyclists: ") + injuries[1] + "\n"
				+ String.format("%28s","motorists: ") + injuries[2] + "\n"
				+ String.format("%28s","Number of injuries: ") + numInjuries + "\n" 
				+ String.format("%28s","pedestrians: ") + injuries[3] + "\n"
				+ String.format("%28s","cyclists: ") + injuries[4] + "\n"
				+ String.format("%28s","motorists: ") + injuries[5] + "\n\n";
	}
	
	private void find(Node node, String zip, Date dateBegin, Date dateEnd, int[] injuries) {
		if(node == null) {
			return; 
		} else if(node.data.getZip().compareTo(zip) < 0) {
			// go right			
			find(node.right, zip, dateBegin, dateEnd, injuries);
		} else if(node.data.getZip().compareTo(zip) > 0){
			// go left
			find(node.left, zip, dateBegin, dateEnd, injuries);
		} else {
			//	zip equals zip at node, check if in date range
			if(node.data.date.compareTo(dateBegin) >= 0 && node.data.date.compareTo(dateEnd) <= 0) {
				injuries[0] += node.data.getPedestriansKilled();
				injuries[1] += node.data.getCyclistKilled();
				injuries[2] += node.data.getMotoristsKilled();
				injuries[3] += node.data.getPedestriansInjured();
				injuries[4] += node.data.getCyclistsInjured();
				injuries[5] += node.data.getMotoristsInjured();
				injuries[6] += 1;
			}
			find(node.left, zip, dateBegin, dateEnd, injuries);
			find(node.right, zip, dateBegin, dateEnd, injuries);
		}
	}
	
	
	/**
	 * 
	 * @return
	 */
	public int balanceFactor(Node node) {
		if(node.right == null) return node.height;
		if(node.left == null) return node.height;
		return Math.abs(node.right.height - node.left.height);
	}
	
	/**
	 * Updates the height of the node passed in.
	 * @param node whose height you want to update
	 */
	private void updateHeight(Node node) {
		if(node.left == null && node.right == null) {
			node.height = 0;
		} else if(node.left == null) {
			node.height = node.right.height + 1;
		} else if(node.right == null) {
			node.height = node.left.height + 1;
		} else {
			// if node has both children, choose the greater of the two
			node.height = Math.max(node.right.height, node.left.height) + 1;
		}
	}
	
	/**
	 * Add the given data item to the tree. If item is null, the tree does not
	 * change. If item already exists, the tree does not change. 
	 * 
	 * @param item the new element to be added to the tree
	 */
	public void add(Collision item) {
		if (item == null)
			return;
		root = add (root, item);
	}

	/*
	 * Actual recursive implementation of add.  
	 * 
	 * @param item the new element to be added to the tree
	 */
	private Node add(Node node, Collision item) {
		if (node == null) { 
			numOfElements++;
			return new Node(item);
		}
		if (node.data.compareTo(item) > 0) {
			node.left = add(node.left, item);
		} else if (node.data.compareTo(item) < 0) {
			node.right = add(node.right, item);
		} else {};
		
		updateHeight(node);
		if(balanceFactor(node) > 1) {
			return rebalance(node);
		}
		
		return node;
		
	}
	
	private int height(Node node) {
		if(node == null) return -1;
		return node.height;
	}
	
	private Node rebalance(Node node) {
				
		if(height(node.right) > height(node.left)) {
			// perform RR rotation
			if(height(node.right.right) > height(node.right.left)) {
				return balanceRR(node);
			// perform RL rotation	
			} else { 
				return balanceRL(node); 
			}
			
	
		} else {
			if(height(node.left.left) > height(node.left.right)) {
				return balanceLL(node);
			} else {
				return balanceLR(node);
			}
		}
	}
	
	private Node balanceLL(Node A) {
		Node B = A.left;
		A.left = B.right;
		B.right = A;
		
		updateHeight(A);
		updateHeight(B);
		
		return B;
	}
	
	private Node balanceRR(Node node) {
		Node B = node.right;
		
		node.right = B.left; 
		B.left = node;
		
		updateHeight(node);
		updateHeight(B);
		return B;
	}
	
	private Node balanceLR(Node A) {
		Node B = A.left;
		Node C = B.right;
		A.left = C.right;
		B.right = C.left;
		C.left = B;
		C.right = A;
		
		updateHeight(A);
		updateHeight(B);
		updateHeight(C);
		
		return C;
	}
	
	private Node balanceRL(Node A) {
		Node B = A.right;
		Node C = B.left;
		A.right = C.left;
		B.left = C.right;
		C.right = B;
		C.left = A;
		
		updateHeight(A);
		updateHeight(B);
		updateHeight(C);
		
		return C;
	}

	/**
	 * Remove the item from the tree. If item is null the tree remains unchanged. If
	 * item is not found in the tree, the tree remains unchanged.
	 * 
	 * @param target the item to be removed from this tree 
	 */
	public boolean remove(Collision target)
	{
		root = recRemove(target, root);
		return found;
	}

	/*
	 * Actual recursive implementation of remove method: find the node to remove.  
	 * 
	 * @param target the item to be removed from this tree 
	 */
	private Node recRemove(Collision target, Node node) {
		if (node == null)
			found = false;
		else if (target.compareTo(node.data) < 0)
			node.left = recRemove(target, node.left);
		else if (target.compareTo(node.data) > 0)
			node.right = recRemove(target, node.right );
		else {
			node = removeNode(node);
			found = true;
		}
		
		return node;
	}
	
	/*
	 * Actual recursive implementation of remove method: perform the removal.  
	 * 
	 * @param target the item to be removed from this tree 
	 * @return a reference to the node itself, or to the modified subtree 
	 */
	private Node removeNode(Node node)
	{
		Collision data;
		if (node.left == null)
			return node.right ;
		else if (node.right  == null)
			return node.left;
		else {
			data = getPredecessor(node.left);
			node.data = data;
			node.left = recRemove(data, node.left);
			
			updateHeight(node);
			if(balanceFactor(node) > 1) {
				return rebalance(node);
			}
			
			return node;
		}
	}
	
	/*
	 * Returns the information held in the rightmost node of subtree  
	 * 
	 * @param subtree root of the subtree within which to search for the rightmost node 
	 * @return returns data stored in the rightmost node of subtree  
	 */
	private Collision getPredecessor(Node subtree)
	{
		if (subtree==null) throw new NullPointerException("getPredecessor called with an empty subtree");
		Node temp = subtree;
		while (temp.right  != null)
			temp = temp.right ;
		return temp.data;
	}

	/**
	 * Determines the number of elements stored in this BST.
	 * 
	 * @return number of elements in this BST
	 */
	public int size() {
		return numOfElements;
	}

	/**
	 * Returns a string representation of this tree using an inorder traversal . 
	 * @see java.lang.Object#toString()
	 * @return string representation of this tree 
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		inOrderPrint(root, s);
		return s.toString();
	}

	/*
	 * Actual recursive implementation of inorder traversal to produce string
	 * representation of this tree.
	 * 
	 * @param tree the root of the current subtree
	 * @param s the string that accumulated the string representation of this BST
	 */
	private void inOrderPrint(Node tree, StringBuilder s) {
		if (tree != null) {
			inOrderPrint(tree.left, s);
			s.append(tree.data.toString() + "  ");
			inOrderPrint(tree.right , s);
		}
	}

	/**
	 * Produces tree like string representation of this BST.
	 * @return string containing tree-like representation of this BST.
	 */
	public String toStringTreeFormat() {

		StringBuilder s = new StringBuilder();

		preOrderPrint(root, 0, s);
		return s.toString();
	}

	/*
	 * Actual recursive implementation of preorder traversal to produce tree-like string
	 * representation of this tree.
	 * 
	 * @param tree the root of the current subtree
	 * @param level level (depth) of the current recursive call in the tree to
	 *   determine the indentation of each item
	 * @param output the string that accumulated the string representation of this
	 *   BST
	 */
	private void preOrderPrint(Node tree, int level, StringBuilder output) {
		if (tree != null) {
			String spaces = "\n";
			if (level > 0) {
				for (int i = 0; i < level - 1; i++)
					spaces += "   ";
				spaces += "|--";
			}
			output.append(spaces);
			output.append(tree.data.getZip());
			preOrderPrint(tree.left, level + 1, output);
			preOrderPrint(tree.right , level + 1, output);
		}
		// uncomment the part below to show "null children" in the output
//		else {
//			String spaces = "\n";
//			if (level > 0) {
//				for (int i = 0; i < level - 1; i++)
//					spaces += "   ";
//				spaces += "|--";
//			}
//			output.append(spaces);
//			output.append("null");
//		}
	}
	
	
	/**
	 * Node class is used to represent nodes in a binary search tree.
	 * It contains a data item that has to implement Comparable interface
	 * and references to left and right subtrees. 
	 * 
	 * @author Joanna Klukowska
	 */
	protected static class Node {
	
		
		protected Node left;  //reference to the left subtree 
		protected Node right; //reference to the right subtree
		protected Collision data;            //data item stored in the node

		protected int height; 
		
		
		/**
		 * Constructs a BSTNode initializing the data part 
		 * according to the parameter and setting both 
		 * references to subtrees to null.
		 * @param data
		 *    data to be stored in the node
		 */
		protected Node(Collision data) {
			this.data = data;
			left = null;
			right = null;
			height = 0; 
		}
		
		/**
		 * 
		 * @return height of the node it is called on
		 */
		public int getHeight() {
			return this.height;
		}
		
			
		/* (non-Javadoc)
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		public int compareTo(Node other) {
			return this.data.compareTo(other.data);
		} 
	
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return data.toString();
		}
		
	
		
	}

}
