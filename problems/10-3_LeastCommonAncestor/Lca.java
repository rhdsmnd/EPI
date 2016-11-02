import java.util.HashMap;

/** Finds the "least common ancestor" (lca) of FIRSTBINTREE and SECONDBINTREE,
 *  both of which are in ROOT, where the lca is the node furthest from the root
 *  that contains both FIRSTBINTREE and SECONDBINTREE. */
public class Lca<T> {

	public Lca() {
		hasDescMap = new HashMap<BinTree<T>, HashMap<BinTree<T>, Boolean>>();
	}

    public BinTree<T> findLca(BinTree<T> root, final BinTree<T> firstBinTree, final BinTree<T> secondBinTree) {
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

		if (hasDescendant(root, firstBinTree) && hasDescendant(root, secondBinTree)) {
			return root;
		} else {
			return null;
		}
    }

	public boolean hasDescendant(BinTree<T> root, final BinTree<T> descendant) {
		if (root == null) {
			return false;
		} else if (root.isEqual(descendant)) {
			return true;
		}

		/** memoize: trade space for time */
		if (!hasDescMap.containsKey(descendant)) {
			hasDescMap.put(descendant, new HashMap<BinTree<T>, Boolean>());
		}

		HashMap<BinTree<T>, Boolean> containsDesc = hasDescMap.get(descendant);
		if (containsDesc.containsKey(root)) {
			return containsDesc.get(root);
		}

		containsDesc.put(root,
				hasDescendant((BinTree<T>) root.getChild(BinTree.LEFT), descendant) ||
				hasDescendant((BinTree<T>) root.getChild(BinTree.RIGHT), descendant));
		return containsDesc.get(root);
	}

	private HashMap<BinTree<T>, HashMap<BinTree<T>, Boolean>> hasDescMap;
}
