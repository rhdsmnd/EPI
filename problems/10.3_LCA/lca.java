
/** Finds the "least common ancestor" (lca) of FIRSTBINTREE and SECONDBINTREE,
 *  both of which are in ROOT, where the lca is the node furthest from the root
 *  that contains both FIRSTBINTREE and SECONDBINTREE. */
public Lca {
    public static BinTree<T> findLca(BinTree<T> root, BinTree<T> firstBinTree, BinTree<T> secondBinTree) {
        if (root == null || firstBinTree == null || secondBinTree == null) {
            return null;
        }
        if (firstBinTree == secondBinTree) {
            // alternatively, note the error
            return firstBinTree;
        }

        if (root == firstBinTree) {
            return firstBinTree;
        } else if (root == secondBinTree) {
            return secondBinTree;
        }

        leftAnc = findLca(root.right, firstBinTree, secondBinTree);
        rightAnc = findLca(root.left, firstBinTree, secondBinTree);

        if (leftAnc == null) {
            return rightAnc;
        } else if (rightAnc == null) {
            return leftAnc;
        } else {
            return root;
        }

    }
}
