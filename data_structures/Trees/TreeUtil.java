
public class TreeUtil {

	public static class TreeParser<T> {
		interface ParseDataObj<T> {
			T parseAndDiscard(StringBuffer strBuf);
		}

		public TreeParser(Class<T> dataType,
							Class<? extends Tree> treeType) {
			this.treeType = treeType;
			System.out.println("class type is: " + dataType);
			if (dataType == String.class) {
				this.dataParser = ((StringBuffer strBuf) -> {
					// search for ", continue if instance = \" // 
					int ind = 0;
					// null check for strBuf? Error case for empty stringbuffer?
					if (strBuf.length() == 0 || strBuf.charAt(ind) != '"') {
						System.out.println("Not valid string");
						return null;
					} else {
						ind += 1;
					}
					
					while ((ind < strBuf.length()) && (strBuf.charAt(ind) != '\"' ||
							strBuf.charAt(ind - 1) == '\\')) {
						ind += 1;
					}
					String ret = strBuf.substring(1, ind);

					// get rid of quote character
					if (ind != strBuf.length()) {
						ind += 1;
					}

					strBuf.delete(0, ind);
					return ret;
				});
			} else if (dataType == Integer.class) {
				this.dataParser = ((StringBuffer strBuf) -> {
					int ind = 0;
			
					while (ind < strBuf.length()
							&& Character.isDigit(strBuf.charAt(ind))) {
						ind += 1;
					}
					String charsRead = strBuf.substring(0, ind);
					strBuf.delete(0, ind);

					try {
						return Integer.parseInt(charsRead);
					} catch (NumberFormatException e) {
						// raise exception
						System.out.println("Couldn't parse string \"" + charsRead
							+ "\" to integer.");
						return null;
					}
				});
			} else {
				System.out.println("Null data parser");
				this.dataParser = null;
			}
		}

		public TreeParser(Class<T> dataType, Class<? extends Tree> treeType,
							ParseDataObj func) {
			this.treeType = treeType;
			this.dataParser = func;
		}

		Class<? extends Tree> treeType;
		ParseDataObj dataParser;

		public Tree<T> treeFromString(String str, Class<? extends Tree> treeType,
										int numChildren) {
			return treeFromString(new StringBuffer(str), treeType, numChildren);
		}

		// syntax (1 () ()))
		public Tree<T> treeFromString(StringBuffer strBuf,
					Class<? extends Tree> treeType, int numChildren) {
			// consider making helper function
			// null checks for strBuf
			nextToken(strBuf);

			int ind = 0;
			if (strBuf.charAt(ind) != '(') {
				// raise exception
				System.out.println("Error with tree format");
				return null;
			}

			// outline

			strBuf.deleteCharAt(ind);
			if (nextToken(strBuf) == ')') {
				return null;
			}

			Object dataObj = this.dataParser.parseAndDiscard(strBuf);
			Tree<T> root = this.createTree(treeType, numChildren, dataObj);

			int childInd = 0;
			Tree<T> child;

			while (nextToken(strBuf) != null && nextToken(strBuf) == '(') {
				child =	this.treeFromString(strBuf, treeType, numChildren);
				setChild(root, child, root.numChildren());
			}

			if (nextToken(strBuf) != null && nextToken(strBuf) == ')') {
				strBuf.deleteCharAt(0);
				return root;
			} else {
				System.out.println("Error parsing tree");
				return null;
			}

		}

		protected void setChild(Tree<T> root, Tree<T> child, int index) {
			if (root.getTreeType().isAssignableFrom(OrderedTree.class)) {
				((OrderedTree<T>) root).setChild(index, child);
			} else if (root.getTreeType().isAssignableFrom(UnorderedTree.class)) {
				((UnorderedTree<T>) root).addChild(child);
			}
		}

		private Character nextToken(StringBuffer strBuf) {
			while (strBuf.length() > 0
					&& Character.isWhitespace(strBuf.charAt(0))) {
				strBuf.deleteCharAt(0);
			}
			if (strBuf.length() > 0) {
				return strBuf.charAt(0);
			} else {
				return null;
			}
		}

		public Tree<T> createTree(Class<?> treeType, int numChildren, Object dataObj) {
			if (treeType == OrderedTree.class) {
				return new OrderedTree<T>(numChildren, (T) dataObj);
			} else if (treeType == BinTree.class) {
				return new BinTree<T>((T) dataObj);
			} else {
				System.out.println("Tree type unrecognized.");
				return null;
			}
		}
	}
}
