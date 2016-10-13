import static org.junit.Assert.*;
import org.junit.Test;

public class OrderedTreeAndTreeTests {

	@Test
	public void initAndChildAssign() {
		Tree<String> root = new OrderedTree<String>(2, "root");
		assertEquals("root", root.getValue());
		Tree<String> child1 = new OrderedTree<String>(2, "child1");
		Tree<String> child2 = new OrderedTree<String>(2, "child2");
		assertEquals(2, root.maxChildren());

		root.setChild(0, child1);
		root.setChild(1, child2); 

		assertEquals(child1, root.getChild(0));
		assertEquals(child2, root.getChild(1));
		assertEquals(root, child1.getParent());

		assertEquals(2, root.numChildren());
		assertEquals(2, root.maxChildren());
		assertEquals(OrderedTree.class, root.getTreeType());

		root.deleteChild(0);
		assertEquals(1, root.numChildren());

		assertNull(child1.getParent());
		assertNull(root.getChild(0));

		root.deleteChild(child2);
		assertNull(child2.getParent());
		assertNull(root.getChild(1));
		assertEquals(0, root.numChildren());
		
	}

	@Test
	public void matchingTreesOnly() {
		Tree<String> root = new OrderedTree<String>(2, "root");
		Tree<String> child1 = new OrderedTree<String>(3, "child1");

		root.setChild(0, child1);
		assertEquals(0, root.numChildren());
	}

	@Test
	public void testChildrenCounter() {
		Tree<String> root = new OrderedTree<String>(2, "root");
		Tree<String> child1 = new OrderedTree<String>(2, "child1");

		root.setChild(0, child1);
		assertEquals(1, root.numChildren());

		Tree<String> child2 = new OrderedTree<String>(2, "child2");
		root.setChild(1, child2);
		assertEquals(2, root.numChildren());

		root.deleteChild(child2);
		assertEquals(1, root.numChildren());

		root.deleteChild(child1);
		assertEquals(0, root.numChildren());
	}

	@Test
	public void orderedEqualsTest() {
		Tree<String> root = new OrderedTree<String>(2, "root");
		Tree<String> eqRoot = new OrderedTree<String>(2, "root");

		assertTrue(root.equals(eqRoot));
		assertTrue(eqRoot.equals(root));

		Tree<String difChildNum = new OrderedTree<String>(3, "root");
		assertTrue(!root.equals(difChildNum)); 

		Tree<String> difTreeType = new BinTree<String>("root");
		assertTrue(!root.equals(difTreeType));

		Tree<String> child1 = new OrderedTree<String>(2, "child1");
		root.setChild(0, child1);
		Tree<String> eqChild1 = new OrderedTree<String>(2, "child1");
		eqRoot.setChild(0, eqChild1);
		assertTrue(root.equals(eqRoot));

		Tree<String> child2 = new OrderedTree<String>(2, "child2");
		Tree<String> child3 = new OrderedTree<String>(2, "child3");
		Tree<String> child4 = new OrderedTree<String>(2, "child4");
		Tree<String> child5 = new OrderedTree<String>(2, "child5");
		Tree<String> child6 = new OrderedTree<String>(2, "child6");
		root.setChild(1, child2);
		child1.setChild(0, child3);
		child1.setChild(1, child4);
		child2.setChild(0, child5);
		child2.setChild(1, child6);
		
		Tree<String> eqChild2 = new OrderedTree<String>(2, "child2");
		Tree<String> eqChild3 = new OrderedTree<String>(2, "child3");
		Tree<String> eqChild4 = new OrderedTree<String>(2, "child4");
		Tree<String> eqChild5 = new OrderedTree<String>(2, "child5");
		Tree<String> eqChild6 = new OrderedTree<String>(2, "child6");
		eqRoot.setChild(1, eqChild2);
		eqChild1.setChild(0, eqChild3);
		eqChild1.setChild(1, eqChild4);
		eqChild2.setChild(0, eqChild5);
		eqChild2.setChild(1, eqChild6);

		assertTrue(root.equals(eqRoot));

		root.deleteChild(0);
		eqRoot.deleteChild(0);

		swapChild = new OrderedTree<String>(2, "root");
		swapChild.setChild(0, child2);
		swapChild.setChild(1, child1);

		assertTrue(!root.equals(swapChild));

		swapChild.deleteChild(1);
		swapChild.setChild(0, child1);

		assertTrue(!root.equals(swapChild));

		eqChild2.deleteChild(0);
		child2.deleteChild(0);

		assertTrue(root.equals(eqRoot));
	}
}
