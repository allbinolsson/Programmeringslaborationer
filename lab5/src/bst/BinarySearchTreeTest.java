package bst;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

public class BinarySearchTreeTest {
	private BinarySearchTree<Integer> tree;
	
	@Before
	public void start () {
		tree = new BinarySearchTree<>();
	}
	
	@After
	public void tearDown () {
		tree = null;
	}
	
	@Test
	public void testAdd () {
		assertTrue("Can't insert number properly", tree.add(1));
		assertFalse("Can insert duplicate", tree.add(1));
	}
	
	@Test
	public void testHeight () {
		assertTrue("Wrong height in empty tree", tree.height() == 0);	// Height of empty tree
		tree.add(5);	// Root node
		tree.add(3);
		tree.add(4);
		tree.add(2);
		assertTrue("Wrong height in not empty tree", tree.height() == 3);	// Height if not empty tree
	}
	
	@Test 
	public void testSize () {
		assertTrue("Wrong size in empty tree", tree.size() == 0);		// Size of empty tree
		tree.add(5);	// Root node
		tree.add(3);
		tree.add(4);
		tree.add(2);
		assertTrue("Wrong size of not empty tree", tree.size() == 4);	// Size of not empty tree
	}
}
