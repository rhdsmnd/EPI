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
		if (index < this.maxChildren() && newChild == null) {
			if (this.children[index] != null) {
				Tree<T> curChild = (Tree<T>) this.children[index];
				curChild.setParent(null);
				this.children[index] = null;
			}	
		} else if (index < this.maxChildren() && this.getClass().equals(newChild.getClass())
			&& this.maxChildren == newChild.maxChildren()) {
			
			this.children[index] = newChild;
			this.numChildren += 1;
			newChild.setParent(this);
		}
	}

	public boolean equals(Tree<T> otherTree) {
		if (otherTree == null || this.treeType != otherTree.getTreeType()
				|| this.maxChildren() != otherTree.maxChildren()) {
			return false;
		}

		T thisData = this.getValue();
		T otherData = otherTree.getValue();

		boolean equalValues;
		if (thisData == null && otherData == null) {
			equalValues = true;
		} else if (thisData == null || otherData == null) {
			equalValues = false;
		} else {
			equalValues = thisData.equals(otherData);
		}

		if (!equalValues) {
			return false;
		}

		boolean equalChildren;
		Tree<T> thisChild;
		Tree<T> otherChild;
		for (int i = 0; i < this.maxChildren(); i += 1) {
			thisChild = this.getChild(i);
			otherChild = otherTree.getChild(i);

			if (thisChild == null && otherChild == null) {
				continue;
			} else if (thisChild == null || otherChild == null
					|| !(thisChild.equals(otherChild))) {
				return false;
			}
		}
		return true;
	}

 

	protected int numChildren;
	protected final Object[] children;
}

