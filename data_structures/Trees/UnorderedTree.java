public class UnorderedTree<T> extends Tree<T> {

	public UnorderedTree(T value, UnorderedTree<T> treeType) {
		this.children = new ArrayList<UnorderedTree<T>>();
		this.treeType = treeType;
	}

	public int numChildren() {
		return children.size();
	}

	/** Method less relevant than OrderedChildren. May add method
	 *  to configure max children based on percentage of heap. Though
	 *  this would require another field for knowing the size of the
	 *  tree, and would be expensive to manage when updating an arbitrary
	 *  subtree.
	public int maxChildren() {
		return Integer.MAX_INT;
	}

	public Iterator<UnorderedTree<T>> getChildren() {
		return this.children.iterator();
	}

	public void deleteChildVal(Tree<T> child) {
		this.children.removeAll(child);
	}

	public void deleteChildRef(Tree<T> child) {
		for (int i = 0; i < this.children.size(); i += 1) {
			if (child == this.children.get(i)) {
				this.children.remove(i);
			}
		}	
	}

	// repeated code for findChildVal and findChildrenVal??
	// refactor for DRY?
	public UnorderedTree<T> findChildRef(Tree<T> child) {
		for (int i = 0; i < this.children.size(); i += 1) {
			UnorderedTree<T> iter = this.children.get(i);
			if (iter == child) {
				return iter;
			}
		}
	}

	public UnorderedTree<T> findChildVal(Tree<T> child) {
		for (int i = 0; i < this.children.size(); i += 1) {
			UnorderedTree<T> iter = this.children.get(i);
			if (iter.equals(child)) {
				return iter;
			}
		}
	}

	public Array[UnorderedTree<T>] findChildrenRef(Tree<T> child) {
		//possibly raise exception if not typesafe

		ArrayList<UnorderedTree<T>> retList = new ArrayList<UnorderedTree<T>>();
		for (int i = 0; i < this.children.size(); i += 1) {
			UnorderedTree<T> iter = this.children.get(i);
			if (iter == child) {
				retList.add(iter);
			}
		}
		UnorderedTree<T>[] ret = new UnorderedTree<T>[retList.size()];
		return retList.toArray(ret);
	}

	public Array[UnorderedTree<T>] findChildrenVal(Tree<T> child) {
		//possibly raise exception if not typesafe

		ArrayList<UnorderedTree<T>> retList = new ArrayList<UnorderedTree<T>>();
		for (int i = 0; i < this.children.size(); i += 1) {
			UnorderedTree<T> iter = this.children.get(i);
			if (iter.equals(child)) {
				retList.add(iter);
			}
		}
		UnorderedTree<T>[] ret = new UnorderedTree<T>[retList.size()];
		return retList.toArray(ret);
	}

	private ArrayList<UnorderedTree<T>> children;	
}
