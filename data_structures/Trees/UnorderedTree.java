public class UnorderedTree<T> extends Tree<T> {

	public UnorderedTree(T value, UnorderedTree<T> treeType) {
		this.children = new ArrayList<UnorderedTree<T>>();
		this.treeType = treeType;
	}

	public int numChildren() {
		return children.size();
	}

	/** Method less relevant than OrderedChildren. May add method
	 *  to configure max children based on percentage of heap. Though
	 *  this would require another field for knowing the size of the
	 *  tree, and would be expensive to manage when updating an arbitrary
	 *  subtree. */
	public int maxChildren() {
		return Integer.MAX_INT;
	}

	public Iterator<UnorderedTree<T>> getChildren() {
		return this.children.iterator();
	}

	public void deleteChild(Tree<T> child) {
		if (this.children.contains(child) != null) {
			this.children.remove(child);
			child.setParent(null);
		}
	}


	public boolean contains(Tree<T> child) {
		return this.children.contains(child);
	}

	private HashSet<UnorderedTree<T>> children;	
}
