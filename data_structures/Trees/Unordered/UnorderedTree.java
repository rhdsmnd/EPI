import java.util.Iterator;
import java.util.HashSet;

public class UnorderedTree<T> extends Tree<T> {

	public UnorderedTree(T value) {
		this(value, UnorderedTree.class);
	}

	public UnorderedTree(T value, Class<?> treeType) {
		super(Integer.MAX_VALUE, value, treeType);
		children = new HashSet<Tree<T>>();
	}


	public int numChildren() {
		return children.size();
	}

	public void addChild(Tree<T> child) {
		if (this.matchesTreeType(child)) {
			children.add(child);
			child.setParent(this);
		} else {
			// raise exception
			System.out.println("Tree types do not match, cannot add child");
		}
	}

	@Override
	public Iterator<Tree<T>> getChildren() {
		return children.iterator();
	}

	public void deleteChild(Tree<T> child) {
		if (children.contains(child)) {
			children.remove(child);
			child.setParent(null);
		}
	}

	@Override
	public boolean containsChild(Tree<T> child) {
		return children.contains(child);
	}

	private HashSet<Tree<T>> children;	
}
