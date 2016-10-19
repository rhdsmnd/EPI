public class UnorderedTree<T> extends Tree<T> {

	public UnorderedTree(T value, UnorderedTree<T> treeType) {
		this.children = new ArrayList<UnorderedTree<T>>();
		this.treeType = treeType;
	}

	public int numChildren() {
		return children.size();
	}

	public int maxChildren() {
		return Integer.MAX_INT;
	}

	public boolean addChild(Tree<T> child) {
		if (this.matchesTreeType(child)) {
			this.children.add(child);
			child.setParent(this);
		}
	}

	@Override
	public Iterator<? extends Tree<T>> getChildren() {
		return this.children.iterator();
	}

	public void deleteChild(Tree<T> child) {
		if (this.children.contains(child) != null) {
			this.children.remove(child);
			child.setParent(null);
		}
	}

	@Override
	public boolean containsChild(Tree<T> child) {
		return this.children.contains(child);
	}

	private HashSet<UnorderedTree<T>> children;	
}
