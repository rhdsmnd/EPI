public class OrderedTree<T> extends Tree<T> {

	protected OrderedTree(int numChildren, T value) {
		super(numChildren, value, OrderedTree.class);

		this.children = new Object[numChildren];
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
	public Tree<T> getChild(int index) {
		if (index < maxChildren()) {
			return (Tree<T>) children[index];
		} else {
			return null;
		}
	}

	@Override
	public void deleteChild(Tree<T> child) {
		for (int i = 0; i < this.children.length; i += 1) {
			OrderedTree<T> elem = (OrderedTree<T>) this.children[i];
			if (child == elem) {
				this.children[i] = null;
			}
		}
	}	

	@Override
	public void deleteChild(int index) {
		if (index < maxChildren()) {
			((OrderedTree<T>) children[index]).setParent(null);
			children[index] = null;
		}
	}

	@Override
	public void setChild(int index, Tree<T> newChild) {
		if (index < this.maxChildren() && this.treeType.isAssignableFrom(newChild.getClass())) {
			this.children[index] = newChild;
			newChild.setParent(this);
		}
	}

	protected final Object[] children;
}

