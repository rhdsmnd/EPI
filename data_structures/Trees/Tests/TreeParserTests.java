import static org.junit.Assert.*;
import org.junit.Test;

public class TreeParserTests {

	@Test
	public void intTreeTest() {
		TreeUtil.TreeParser<Integer> intTreeParser
				= new TreeUtil.TreeParser<Integer>(Integer.class,
										 OrderedTree.class);
		String ordIntTree = "(1 () ())";
		
		Tree<Integer> parsedIntTree = intTreeParser.treeFromString(ordIntTree,
															OrderedTree.class, 2);
		Tree<Integer> eqIntTree = new OrderedTree<Integer>(2, 1, OrderedTree.class);
		assertTrue(intTreeParser.treeFromString(ordIntTree,
											OrderedTree.class, 2).equals(parsedIntTree));

		String simpBinTreeInt = "(1)";
		Tree<Integer> eqBinTree = new BinTree<Integer>(1);
		Tree<Integer> parsedBinTree = intTreeParser.treeFromString(simpBinTreeInt,
															BinTree.class, 2);
		assertTrue(parsedBinTree.equals(eqBinTree));

		String handleWhitespace = "(   1 ( \n) (\t	)		)";
		parsedIntTree = intTreeParser.treeFromString(handleWhitespace,
															OrderedTree.class, 2);
		eqIntTree = new OrderedTree<Integer>(2, 1, OrderedTree.class);
		assertTrue(eqIntTree.equals(parsedIntTree));

		String multilevelTree = "(1 (2) (3))";
		eqBinTree = new BinTree<Integer>(1);
		Tree<Integer> leftChild = new BinTree<Integer>(2);
		Tree<Integer> rightChild = new BinTree<Integer>(3);
		eqBinTree.setChild(BinTree.LEFT, leftChild);
		eqBinTree.setChild(BinTree.RIGHT, rightChild);

		parsedBinTree = intTreeParser.treeFromString(multilevelTree,
															BinTree.class, 2);

		String unbalancedTree = "(1 () (2 () (3)))";
		parsedBinTree = intTreeParser.treeFromString(unbalancedTree,
													BinTree.class, 2);
		eqBinTree = new BinTree<Integer>(1);
		Tree<Integer> child1 = new BinTree<Integer>(2);
		Tree<Integer> child2 = new BinTree<Integer>(3);

		eqBinTree.setChild(BinTree.RIGHT, child1);
		child1.setChild(BinTree.RIGHT, child2);


		String largerMultilevelTree = "(1 (2 (4) (5)) (3 (6) (7))) ";
		parsedBinTree = intTreeParser.treeFromString(largerMultilevelTree,
															BinTree.class, 2);
		// Changing variable name semantics: child2 now refers to node with data = 2
		eqBinTree = new BinTree<Integer>(1);
		child2 = new BinTree<Integer>(2);
		Tree<Integer> child3=  new BinTree<Integer>(3);
		Tree<Integer> child4=  new BinTree<Integer>(4);
		Tree<Integer> child5=  new BinTree<Integer>(5);
		Tree<Integer> child6=  new BinTree<Integer>(6);
		Tree<Integer> child7=  new BinTree<Integer>(7);

		// wire the trees together
		eqBinTree.setChild(BinTree.LEFT, child2);
		eqBinTree.setChild(BinTree.RIGHT, child3);
		child2.setChild(BinTree.LEFT, child4);
		child2.setChild(BinTree.RIGHT, child5);
		child3.setChild(BinTree.LEFT, child6);
		child3.setChild(BinTree.RIGHT, child7);

		assertTrue(eqBinTree.equals(parsedBinTree));

		child3.deleteChild(BinTree.LEFT);
		parsedBinTree.getChild(BinTree.RIGHT).deleteChild(BinTree.LEFT);

		assertTrue(eqBinTree.equals(parsedBinTree));

		String threeChildren = "(1 (2 (5) (6) (7)) (3 (8) (9) (10)) (4 (11) (12) (13)))";
		parsedIntTree = intTreeParser.treeFromString(threeChildren, OrderedTree.class,
														3);
		eqIntTree = new OrderedTree<Integer>(3, 1);

		// THERE HAS GOT TO BE A BETTER WAY AKA MAKE ONE
		child2 = new OrderedTree<Integer>(3, 2);
		child3 = new OrderedTree<Integer>(3, 3);
		child4 = new OrderedTree<Integer>(3, 4);
		child5 = new OrderedTree<Integer>(3, 5);
		child6 = new OrderedTree<Integer>(3, 6);
		child7 = new OrderedTree<Integer>(3, 7);
		Tree<Integer> child8 = new OrderedTree<Integer>(3, 8);
		Tree<Integer> child9 = new OrderedTree<Integer>(3, 9);
		Tree<Integer> child10 = new OrderedTree<Integer>(3, 10);
		Tree<Integer> child11 = new OrderedTree<Integer>(3, 11);
		Tree<Integer> child12 = new OrderedTree<Integer>(3, 12);
		Tree<Integer> child13 = new OrderedTree<Integer>(3, 13);

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

		assertTrue(eqIntTree.equals(parsedIntTree));

	}
}
