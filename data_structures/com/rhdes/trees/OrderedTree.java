package com.rhdes.trees;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class OrderedTree<T> extends Tree<T> {

	public OrderedTree(int numChildren, T value) {
		this(numChildren, value, OrderedTree.class);
	}

	protected OrderedTree(int numChildren, T value, Class<?> subType) {
		super(numChildren, value, subType);

		children = new Object[numChildren];
		for (int i = 0; i < children.length; i += 1) {
			children[i] = null;
		}
		this.numChildren = 0;
	}

	@Override
	public int numChildren() {
		// Keeping a tally is much faster than iterating over array
		return this.numChildren;
	}

	// move code later to after Tree methods
	public OrderedTree<T> getChild(int index) {
		if (index < maxChildren()) {
			return (OrderedTree<T>) children[index];
		} else {
			return null;
		}
	}

	@Override
	public void deleteChild(Tree<T> child) {
		for (int i = 0; i < children.length; i += 1) {
			OrderedTree<T> elem = (OrderedTree<T>) children[i];
			if (child == elem) {
				children[i] = null;
				child.setParent(null);
				this.numChildren -= 1;
			}
		}
	}	

	public void deleteChild(int index) {
		if (index < maxChildren() && children[index] != null) {
			((OrderedTree<T>) children[index]).setParent(null);
			children[index] = null;
			this.numChildren -= 1;
		}
	}

	public void setChild(int index, Tree<T> newChild) {
		if (index >= this.maxChildren()) {
			// raise exception
			return;
		}
		if (children[index] != null) {
			Tree<T> curChild = (Tree<T>) children[index];
			curChild.setParent(null);
			children[index] = null;
		}
		if (newChild != null
					&& this.getClass().equals(newChild.getClass())
					&& this.maxChildren == newChild.maxChildren()) {
			
			children[index] = newChild;
			this.numChildren += 1;
			newChild.setParent(this);
		}
	}

	//@Override
	public boolean isEqual(Tree<T> otherTree) {
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

		OrderedTree<T> thisChild;
		OrderedTree<T> otherChild;
		for (int i = 0; i < this.maxChildren(); i += 1) {
			thisChild = this.getChild(i);
			otherChild = ((OrderedTree<T>) otherTree).getChild(i);

			if (thisChild == null && otherChild == null) {
				continue;
			} else if (thisChild == null || otherChild == null
					|| !(thisChild.isEqual(otherChild))) {
				return false;
			}
		}
		return true;
	}


	@Override
	public Iterator<Tree<T>> getChildren() {
		return new Iterator<Tree<T>>() {
			@Override
			public boolean hasNext() {
				while (i < children.length && children[i] == null) {
					i += 1;
				}
				if (i >= children.length) {
					return false;
				} else {
					return true;
				}
			}

			@Override
			public Tree<T> next() throws NoSuchElementException {
				while (i < children.length && children[i] == null) {
					i += 1;
				}
				if (i >= children.length) {
					throw new NoSuchElementException();
				} else {
					Tree<T> ret = (Tree<T>) children[i];
					i += 1;
					return ret;
				}
			}

			private int i = 0;
		};
	}

	// move later to match class order in Tree.java
	@Override
	public boolean containsChild(Tree<T> desc) {
		for (int i = 0; i < children.length; i += 1) {
			if (desc == children[i]) {
				return true;
			}
		}
		return false;
	}

	// TEST TEST TEST
	@Override
	public String toString() {
		String ret = "";
		ret += "(" + this.getValue();
		for (int i = 0; i < children.length; i += 1) {
			if (children[i] == null) {
				ret += " ()";
			} else { 
				ret += " " + children[i].toString();
			}
		}
		ret += ")";
		return ret;
	}

	protected int numChildren;
	protected final Object[] children;
}

