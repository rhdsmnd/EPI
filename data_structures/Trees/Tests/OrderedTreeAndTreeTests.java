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
}
