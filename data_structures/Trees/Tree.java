public abstract class Tree<T, C extends Tree<T, C>> {

//	public abstract int numChildren();
	public abstract int maxChildren();

//	public abstract boolean containsTree(Tree<T> inpTree);


	public T getValue() {
		return this.value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	protected T value;
	protected final int maxChildren;
}
