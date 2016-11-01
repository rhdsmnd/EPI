
public class Main {
	public static void main(String[] args) {
		boolean verbose = false;
		if (args.length > 1 &&
					(args[1].equals("--verbose") || args[1].equals("-v"))) {
			verbose = true;
		}
		System.out.println("Running tests for Elements of Programming Interviews,"
							+ " problem 10.3: Least Common Ancestor.");

		Tester.runTests("./tests.txt", verbose, (input, verbose) -> {
			TreeUtil.TreeParser<Integer> intTreeParser
						= new TreeUtil.TreeParser<Integer>(Integer.class, BinTree.class);
			String[] inpTrees = input.split("\n");
			if (input.length != 3) {
				System.out.println("Need 3 trees to run leastCommonAncestor function.");
			}
			BinTree<Integer> root
						= (BinTree<Integer>) intTreeParser.treeFromString(inpTrees[0],
															BinTree.class, 2);
			BinTree<Integer> subTree1 
						= (BinTree<Integer>) intTreeParser.treeFromString(inpTrees[1],
															BinTree.class, 2);
			BinTree<Integer> subTree2 
						= (BinTree<Integer>) intTreeParser.treeFromString(inpTrees[2],
															BinTree.class, 2);
			BinTree<Integer> lca = Lca.findLca(root, subTree1, subTree2); 
			return lca.toString();
		};

		}

	}	
}
