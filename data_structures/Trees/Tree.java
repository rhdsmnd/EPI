public abstract class Tree<T> {

	public abstract int numChildren();
	public abstract int maxChildren();

	protected Tree(int numChildren, T value, Class<?> treeType) {
		if (this.getClass().isAssignableFrom(treeType)) {
			this.treeType = treeType;
		} else {
			// raise exception
			System.out.println("Treetype not a subtype of Tree class.");
		}

		if (numChildren < 1) {
			// raise exception
			System.out.println("Number children not positive in Tree.java");
			this.maxChildren = 0;
		} else {
			this.maxChildren = numChildren;
		}
		this.value = value;
	}

	public T getValue() {
		return this.value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	protected void setParent(Tree<T> parent) {
		//null checks
		if (parent == null || (this.matchesTreeType(parent)
				&& this.maxChildren() == parent.maxChildren())) {
			this.parent = parent;
		}
	}

	public Tree<T> getParent() {
		return this.parent;
	}

	public Class<?> getTreeType() {
		return this.treeType;
	}

	protected boolean matchesTreeType(Tree<T> otherTree) {
		return this.treeType == otherTree.treeType;
	}

	public abstract boolean isEqual(Tree<T> otherTree);

	public abstract Iterator<? extends Tree<T>> getChildren();

	public abstract void deleteChild(Tree<T> child);

	public abstract boolean containsChild(Tree<T> child);

	//public abstract boolean contains(Tree<T> desc);

	public abstract Iterator<? extends Tree<T>> getChildren();

	
	protected Tree<T> parent;
	protected T value;
	protected final int maxChildren;

	protected final Class<?> treeType;

}
