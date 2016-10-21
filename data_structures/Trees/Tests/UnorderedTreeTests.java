import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ArrayList;

public class UnorderedTreeTests {

	@Test
	public void addDelContainsTest() {
		UnorderedTree<Integer> root = new UnorderedTree<Integer>(1, UnorderedTree.class);
		UnorderedTree<Integer> child2 = new UnorderedTree<Integer>(2, UnorderedTree.class);
		UnorderedTree<Integer> child3 = new UnorderedTree<Integer>(3, UnorderedTree.class);

		Iterator<Tree<Integer>> empty = root.getChildren();

		assertTrue(!empty.hasNext());

		try {
			empty.next();
			assertTrue(false);
		} catch (NoSuchElementException e) {
			assertTrue(true);
		}

		assertEquals(root.maxChildren(), Integer.MAX_VALUE);
		assertEquals(root.numChildren(), 0);

		root.addChild(child2);
		root.addChild(child3);	
		
		assertEquals(root.numChildren(), 2);
		assertEquals(child2.getParent(), root);
		assertTrue(root.containsChild(child3));

		ArrayList<UnorderedTree<Integer>> childList = new ArrayList<UnorderedTree<Integer>>();
		childList.add(child2);
		childList.add(child3);

		Iterator<Tree<Integer>> twoChildren = root.getChildren();

		try {
			UnorderedTree<Integer> iter = (UnorderedTree<Integer>) twoChildren.next();	
			assertTrue(childList.contains(iter));
			childList.remove(iter);
			iter = (UnorderedTree<Integer>) twoChildren.next();
			assertTrue(childList.contains(iter));
			childList.remove(iter);
			assertTrue(!twoChildren.hasNext());
		} catch (NoSuchElementException e) {
			assertTrue(false);
		}


		root.deleteChild(child2);

		assertNull(child2.getParent());
		assertEquals(root.numChildren(), 1);
		assertTrue(!root.containsChild(child2));

		
	}
}
