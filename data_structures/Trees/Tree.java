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

	public void setParent(Tree<T> parent) {
		if (this.treeType.isAssignableFrom(parent.getClass())) {
			this.parent = parent;
		}
	}

	public Tree<T> getParent() {
		return this.parent;
	}

	public abstract Tree<T> getChild(int index);

	public abstract void deleteChild(Tree<T> child);
	public abstract void deleteChild(int index);

	public abstract void setChild(int index, Tree<T> child);
	
	protected Tree<T> parent;
	protected T value;
	protected final int maxChildren;

	protected Class<?> treeType;

}
