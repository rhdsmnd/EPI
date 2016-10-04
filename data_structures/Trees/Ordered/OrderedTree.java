public class OrderedTree<T> extends Tree<T> {

	public OrderedTree(int numChildren, T value) {
		this(numChildren, value, OrderedTree.class);
	}

	protected OrderedTree(int numChildren, T value, Class<?> subType) {
		super(numChildren, value, subType);

		this.children = new Object[numChildren];
		for (int i = 0; i < this.children.length; i += 1) {
			this.children[i] = null;
		}
		this.numChildren = 0;
	}

	@Override
	public int numChildren() {
		// Keeping a tally is much faster than iterating over array
		return this.numChildren;
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
				child.setParent(null);
				this.numChildren -= 1;
			}
		}
	}	

	@Override
	public void deleteChild(int index) {
		if (index < maxChildren() && children[index] != null) {
			((OrderedTree<T>) children[index]).setParent(null);
			children[index] = null;
			this.numChildren -= 1;
		}
	}

	@Override
	public void setChild(int index, Tree<T> newChild) {
		if (index < this.maxChildren() && this.treeType.isAssignableFrom(newChild.getClass())
			&& this.maxChildren == newChild.maxChildren()) {
			
			this.children[index] = newChild;
			this.numChildren += 1;
			newChild.setParent(this);
		}
	}

	protected int numChildren;
	protected final Object[] children;
}

