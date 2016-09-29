public class OrderedTree<T, C extends Tree<T, C>>
											extends Tree<T> {
	public OrderedTree<T, C> (int numChildren, T value) {
		if (numChildren < 1) {
			//raise exception
			return;
		}

		this.children = new C[numChildren];
		for (int i = 0; i < this.children.length; i += 1) {
			this.children[i] = null;
		}

		this.maxChildren = numChildren;

		this.value = value;
	}

	@Override
	public int maxChildren() {
		return this.maxChildren;
	}

	public void setChild(int index, Tree<T, C> newChild) {
		if (index < this.maxChildren()) {
			this.children[index] = newChild;
		}
	}

	public Tree<T, C> getChild(int index) {
		if (index < maxChildren()) {
			return children[index];
		} else {
			return null;
		}
	}

	public void deleteChild(int index) {
		if (index < maxChildren()) {
			children[index];
		}
	}

	protected final C[] children;
}

