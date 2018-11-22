package bst;

public class BinarySearchTree<E extends Comparable<? super E>> {
	BinaryNode<E> root;
    private int size;
    
    public static void main (String[] args) {
    	BSTVisualizer v = new BSTVisualizer("Visualizer", 700, 500);
    	BinarySearchTree<Integer> bst = new BinarySearchTree<>();
    	
//    	bst.add(1);
//    	bst.add(2);
//    	bst.add(3);
//    	bst.add(4);
//    	bst.add(5);
    	
    	bst.add(5);
    	bst.add(3);
    	bst.add(7);
    	bst.add(2);
    	bst.add(4);
    	bst.add(6);
    	bst.add(8);
    	
    	v.drawTree(bst);
    }
    
	/**
	 * Constructs an empty binary searchtree.
	 */
	public BinarySearchTree() {
		root = null;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		return add(x, root);
	}
	
	private boolean add(E x, BinaryNode<E> node) {
		if (root == null) {
			root = new BinaryNode<>(x);
			size++;
			return true;
		} else {
			if (x.compareTo(node.element) == 0) {
				return false;
			} else if (x.compareTo(node.element) < 0) {
				if (node.left == null) {
					node.left = new BinaryNode<>(x);
					size++;
					return true;
				} else {
					return add(x, node.left);
				}
			} else {
				if (node.right == null) {
					node.right = new BinaryNode<>(x);
					size++;
					return true;
				} else {
					return add(x, node.right);
				}
			}
		}
	}
	
	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() {
		return height(root);
	}
	
	private int height (BinaryNode<E> node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + Math.max(height(node.left), height(node.right));
		}
	}
	
	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		printTree(root);
	}
	
	private void printTree (BinaryNode<E> n) {
		if (n == null) {
			return;
		} else {
			printTree(n.left);
			System.out.println(n.element);
			printTree(n.right);
		}
	}

	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		
	}
	
	/*
	 * Adds all elements from the tree rooted at n in inorder to the array a
	 * starting at a[index].
	 * Returns the index of the last inserted element + 1 (the first empty
	 * position in a).
	 */
	private int toArray(BinaryNode<E> n, E[] a, int index) {
		return 0;
	}
	
	/*
	 * Builds a complete tree from the elements a[first]..a[last].
	 * Elements in the array a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(E[] a, int first, int last) {
		return null;
	}
	


	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}	
	}
	
}
