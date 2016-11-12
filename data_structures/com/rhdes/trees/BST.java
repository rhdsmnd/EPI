package com.rhdes.trees;

public class BST<T> extends BinTree<T> {
	public BST(T value) {
		super(value);
/**

		boolean isComparable = false;
		Type[] genInterfaces = value.getClass().getGenericInterfaces();
		for (int i = 0; i < genInterfaces.length; i += 1) {
            ParameterizedType parType = (ParameterizedType) genInterfaces[i];	
			if (parType.getRawType() == java.lang.Comparable) {
				if (value.getClass().equals(parType.getActualTypeArguments()[0])) {
					isComparable = true;
				}
				break;
			}
		}

		if (!isComparable) {
			System.out.println("Does not implement Comparable<T> interface.");
		}
*/
	}

	public void insertValue(T value) {

	}
}
