
import com.rhdes.trees.BinTree;
import com.rhdes.trees.TreeUtil;

import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		TestRunner.runMain(args, 10, 3, "Least Common Ancestor");
/**
		Tester.runMain(args, 10, 3, "Least Common Ancestor", (input, verbose) -> {
			TreeUtil.TreeParser<Integer> intTreeParser
						= new TreeUtil.TreeParser<Integer>(Integer.class, BinTree.class);
			String[] inpTrees = input.split("\n");
			if (inpTrees.length != 3) {
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
			Lca<Integer> memoLca = new Lca<Integer>();

			BinTree<Integer> lca = memoLca.findLca(root, subTree1, subTree2);

			if (lca != null) {
				return lca.toString();
			} else {
				System.out.println(subTree1);
				System.out.println(subTree2);
				return "";
			}
		});*/
	}
}
