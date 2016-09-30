public abstract class Tree<T> {

	public abstract int numChildren();
	public abstract int maxChildren();

	protected Tree(int numChildren, T value, Class<?> treeType) {
		if (this.getClass().isAssignableFrom(treeType) {
			this.treeType = treeType;
		} else {
			// raise exception
			System.out.println("Couldn't assign Tree type in Tree.java.");
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

	public void setParent(C parent) {
		this.parent = parent;
	}

	public C getParent() {
		return this.parent;
	}

	public abstract C getChild(int index);

	public abstract void deleteChild(C child);
	public abstract void deleteChild(int index);

	public abstract void setChild(int index, C child);
	
	protected C parent;
	protected T value;
	protected final int maxChildren;

	protected final Class<?> treeType;
}
