import java.util.Iterator;
import java.util.HashSet;

public class UnorderedTree<T> extends Tree<T> {

	public UnorderedTree(T value, Class<?> treeType) {
		super(Integer.MAX_INT, value, treeType);
		children = new HashSet<UnorderedTree<T>>();
	}

	public int numChildren() {
		return children.size();
	}

	public boolean addChild(Tree<T> child) {
		if (this.matchesTreeType(child)) {
			children.add(child);
			child.setParent(this);
		}
	}

	@Override
	public Iterator<? extends Tree<T>> getChildren() {
		return children.iterator();
	}

	public void deleteChild(Tree<T> child) {
		if (children.contains(child) != null) {
			children.remove(child);
			child.setParent(null);
		}
	}

	@Override
	public boolean containsChild(Tree<T> child) {
		return children.contains(child);
	}

	private HashSet<UnorderedTree<T>> children;	
}
