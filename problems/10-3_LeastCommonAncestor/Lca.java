
/** Finds the "least common ancestor" (lca) of FIRSTBINTREE and SECONDBINTREE,
 *  both of which are in ROOT, where the lca is the node furthest from the root
 *  that contains both FIRSTBINTREE and SECONDBINTREE. */
public class Lca {
    public static<T> BinTree<T> findLca(BinTree<T> root, final BinTree<T> firstBinTree, final BinTree<T> secondBinTree) {
        if (root == null || firstBinTree == null || secondBinTree == null) {
            return null;
        }

		BinTree<T> leftChildRes = findLca((BinTree<T>) root.getChild(BinTree.LEFT), firstBinTree, secondBinTree);
		if (leftChildRes != null) {
			return leftChildRes;
		}

		BinTree<T> rightChildRes = findLca((BinTree<T>) root.getChild(BinTree.RIGHT), firstBinTree, secondBinTree);
		if (rightChildRes != null) {
			return rightChildRes;
		}

		if (root.hasDescendant(firstBinTree) && root.hasDescendant(secondBinTree)) {	
			return root;
		} else {
			return null;
		}
    }

	public static<T> boolean hasDescendant(BinTree<T> root, final BinTree<T> descendant) {
		// memoize
		if (root == null) {
			return false;
		} else if (root.isEqual(descendant)) {
			return true;
		}
		return hasDescendant(root.getChild(BinTree.LEFT), descendant) ||
				hasDescendant(root.getChild(BinTree.RIGHT), descendant);
	}
}
