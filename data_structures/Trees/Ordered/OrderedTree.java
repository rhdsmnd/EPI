public class OrderedTree<T> extends Tree<T> {
	public OrderedTree(int numChildren, ) {
		super(numChildren, value);

		this.children = new C[numChildren];
		for (int i = 0; i < this.children.length; i += 1) {
			this.children[i] = null;
		}

	}

	@Override
	public int numChildren() {
		return this.children.length;
	}

	@Override
	public int maxChildren() {
		return this.maxChildren;
	}

	@Override
	public Tree<T, C> getChild(int index) {
		if (index < maxChildren()) {
			return children[index];
		} else {
			return null;
		}
	}

	@Override
	public void deleteChild(C child) {
		for (int i = 0; i < this.children.length; i += 1) {
			C elem = this.children[i];
			if (child == elem) {
				this.children[i] = null;
			}
		}
	}		

	@Override
	public void deleteChild(int index) {
		if (index < maxChildren()) {
			children[index] = null;
		}
	}

	@Override
	public void setChild(int index, Tree<T, C> newChild) {
		if (index < this.maxChildren()) {
			this.children[index] = newChild;
		}
	}

	protected final C[] children;
}

