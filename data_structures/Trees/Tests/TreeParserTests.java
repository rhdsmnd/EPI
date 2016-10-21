import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Iterator;
import java.util.ArrayList;

public class TreeParserTests {

	@Test
	public void OrdIntTreeTest() {
		TreeUtil.TreeParser<Integer> intTreeParser
				= new TreeUtil.TreeParser<Integer>(Integer.class,
										 OrderedTree.class);
		String ordIntTree = "(1 () ())";
		
		OrderedTree<Integer> parsedIntTree = (OrderedTree<Integer>) intTreeParser.treeFromString(ordIntTree,
															OrderedTree.class, 2);
		OrderedTree<Integer> eqIntTree = new OrderedTree<Integer>(2, 1, OrderedTree.class);
		assertTrue(((OrderedTree<Integer>) intTreeParser.treeFromString(ordIntTree,
											OrderedTree.class, 2)).isEqual(parsedIntTree));

		String simpBinTreeInt = "(1)";
		OrderedTree<Integer> eqBinTree = new BinTree<Integer>(1);
		OrderedTree<Integer> parsedBinTree = (OrderedTree<Integer>) intTreeParser.treeFromString(simpBinTreeInt,
															BinTree.class, 2);
		assertTrue(parsedBinTree.isEqual(eqBinTree));

		String handleWhitespace = "(   1 ( \n) (\t	)		)";
		parsedIntTree = (OrderedTree<Integer>) intTreeParser.treeFromString(handleWhitespace,
															OrderedTree.class, 2);
		eqIntTree = new OrderedTree<Integer>(2, 1, OrderedTree.class);
		assertTrue(eqIntTree.isEqual(parsedIntTree));

		String multilevelTree = "(1 (2) (3))";
		eqBinTree = new BinTree<Integer>(1);
		OrderedTree<Integer> leftChild = new BinTree<Integer>(2);
		OrderedTree<Integer> rightChild = new BinTree<Integer>(3);
		eqBinTree.setChild(BinTree.LEFT, leftChild);
		eqBinTree.setChild(BinTree.RIGHT, rightChild);

		parsedBinTree = (OrderedTree<Integer>) intTreeParser.treeFromString(multilevelTree,
															BinTree.class, 2);

		String unbalancedTree = "(1 () (2 () (3)))";
		parsedBinTree = (OrderedTree<Integer>) intTreeParser.treeFromString(unbalancedTree,
													BinTree.class, 2);
		eqBinTree = new BinTree<Integer>(1);
		OrderedTree<Integer> child1 = new BinTree<Integer>(2);
		OrderedTree<Integer> child2 = new BinTree<Integer>(3);

		eqBinTree.setChild(BinTree.RIGHT, child1);
		child1.setChild(BinTree.RIGHT, child2);


		String largerMultilevelTree = "(1 (2 (4) (5)) (3 (6) (7))) ";
		parsedBinTree = (OrderedTree<Integer>) intTreeParser.treeFromString(largerMultilevelTree,
															BinTree.class, 2);
		// Changing variable name semantics: child2 now refers to node with data = 2


		eqBinTree = new BinTree<Integer>(1);
		child2 = new BinTree<Integer>(2);
		OrderedTree<Integer> child3=  new BinTree<Integer>(3);
		OrderedTree<Integer> child4=  new BinTree<Integer>(4);
		OrderedTree<Integer> child5=  new BinTree<Integer>(5);
		OrderedTree<Integer> child6=  new BinTree<Integer>(6);
		OrderedTree<Integer> child7=  new BinTree<Integer>(7);

		// wire the trees together
		eqBinTree.setChild(BinTree.LEFT, child2);
		eqBinTree.setChild(BinTree.RIGHT, child3);
		child2.setChild(BinTree.LEFT, child4);
		child2.setChild(BinTree.RIGHT, child5);
		child3.setChild(BinTree.LEFT, child6);
		child3.setChild(BinTree.RIGHT, child7);


		assertTrue(eqBinTree.isEqual(parsedBinTree));

		child3.deleteChild(BinTree.LEFT);
		parsedBinTree.getChild(BinTree.RIGHT).deleteChild(BinTree.LEFT);

		assertTrue(eqBinTree.isEqual(parsedBinTree));

		String threeChildren = "(1 (2 (5) (6) (7)) (3 (8) (9) (10)) (4 (11) (12) (13)))";
		parsedIntTree = (OrderedTree<Integer>) intTreeParser.treeFromString(threeChildren, OrderedTree.class,
														3);
		eqIntTree = new OrderedTree<Integer>(3, 1);

		// THERE HAS GOT TO BE A BETTER WAY AKA MAKE ONE
		child2 = new OrderedTree<Integer>(3, 2);
		child3 = new OrderedTree<Integer>(3, 3);
		child4 = new OrderedTree<Integer>(3, 4);
		child5 = new OrderedTree<Integer>(3, 5);
		child6 = new OrderedTree<Integer>(3, 6);
		child7 = new OrderedTree<Integer>(3, 7);
		OrderedTree<Integer> child8 = new OrderedTree<Integer>(3, 8);
		OrderedTree<Integer> child9 = new OrderedTree<Integer>(3, 9);
		OrderedTree<Integer> child10 = new OrderedTree<Integer>(3, 10);
		OrderedTree<Integer> child11 = new OrderedTree<Integer>(3, 11);
		OrderedTree<Integer> child12 = new OrderedTree<Integer>(3, 12);
		OrderedTree<Integer> child13 = new OrderedTree<Integer>(3, 13);

		eqIntTree.setChild(0, child2);
		eqIntTree.setChild(1, child3);
		eqIntTree.setChild(2, child4);

		child2.setChild(0, child5);
		child2.setChild(1, child6);
		child2.setChild(2, child7);

		child3.setChild(0, child8);
		child3.setChild(1, child9);
		child3.setChild(2, child10);

		child4.setChild(0, child11);
		child4.setChild(1, child12);
		child4.setChild(2, child13);

		assertTrue(eqIntTree.isEqual(parsedIntTree));

	}

	
	@Test
	public void UnordIntTreeTest() {
		TreeUtil.TreeParser<Integer> unordIntTreeParser = new TreeUtil.TreeParser(Integer.class,
																		UnorderedTree.class);
		String simpleTree = "(1)"; 

		Tree<Integer> parsedTree = unordIntTreeParser.treeFromString(simpleTree,
																	UnorderedTree.class);
		assertEquals(parsedTree.getValue(), new Integer(1));
		assertEquals(parsedTree.numChildren(), 0);

		String simpleWithChildren = "(1 (2) (3))";
		parsedTree = unordIntTreeParser.treeFromString(simpleWithChildren,
														UnorderedTree.class);

		ArrayList<Integer> childVals = new ArrayList<Integer>();
		Iterator<Tree<Integer>> children = parsedTree.getChildren();
		Tree<Integer> childIter;
		while(children.hasNext()) {
			childIter = children.next();
			childVals.add(childIter.getValue());
			assertEquals(UnorderedTree.class, childIter.getTreeType());
		}
		assertTrue(childVals.contains(2));
		assertTrue(childVals.contains(3));
		assertEquals(childVals.size(), 2);
	}

}
