package com.rhdes.queues;

import java.util.Arrays;

public class Queue<T> {

	private static final int DEFAULT_SIZE = 8;
	private static final int DEALLOC_THRESHOLD = 128;

	private static final int RESIZE_RATIO = 2;

	private enum Resize {
		LARGER, SMALLER
	}

	public Queue() {
		this(Integer.MAX_VALUE);
	}

	public Queue(int maxSize) {
		if (maxSize < 0) {
			// exception
			this.maxSize = 0;
		} else {
			this.maxSize = maxSize;
		}
		values = new Object[DEFAULT_SIZE];
		head = 0;
		tail = 0;
	}

	public T dequeue() {
		if (head != tail) {
			T ret = (T) values[tail];
			tail = advance(tail);
			if (values.length >= DEALLOC_THRESHOLD
							&& numValues() < values.length / (2 * RESIZE_RATIO)) {
				// resize, take in both cases, overflow or not
				//values = Arrays.copyOf(values, values.length / RESIZE_RATIO);
			}
			return ret;
		} else {
			// queue empty
			return null;
		}
	}

	public boolean enqueue(T value) {
		if (numValues() == maxSize) {
			// throw exception (stack is full)
			return false;
		} else if (numValues() == values.length) {
			// resize
			//values = Arrays.copyOf(values, values.length * RESIZE_RATIO);
		}
		values[head] = value;
		head = advance(head);
		return true;
	}

	private boolean shouldResize(Resize r) {
		if (r == Resize.SMALLER) {
			return numValues() < 2 * RESIZE_RATIO;
		} else {
			return numValues() == values.length;
		}
	}

	private void resize(Resize r) {
		onBoundary = head < tail || values.length == numValues();
		if (r == Resize.SMALLER) {

		} else {

		}

	}

	pubic int numValues() {
		if (head > tail) {
			return head - tail;	
		} else if (head < tail) {
			// separate arithmetic operations to prevent possible overflow?
			int tailToEnd = values.length - tail;
			return head + tailToEnd;
		} else if (prev(head) == null) {
			return 0;
		} else {
			return values.length;
		}
	}

	private int advance(int index) {
		if (index == values.length - 1) {
			return 0;
		} else {
			return index + 1;
		}
	}

	private T prev(int index) {
		if (index < 0) {
			// throw exception
			return null;
		} else if (index == 0)  {
			return values[values.length - 1];
		} else {
			return values[index - 1];
		}
	}

	// different from 'head' in Stack; corresponds to next empty slot
	// not index of data in queue
	private int head;
	private int tail;
	private int maxSize;
	private Object[] values;
}
