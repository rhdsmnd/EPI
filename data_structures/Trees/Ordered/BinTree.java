

public class BinTree<T, C extends Tree<T, C>> extends
						OrderedTree<T, C> {
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
    
	public BinTree(T value) {
		super(2, value);
	}
 
}
