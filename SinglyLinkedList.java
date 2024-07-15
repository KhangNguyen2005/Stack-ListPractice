	package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * SinglyLinkedList represents a generic singly linked list implementation that
 * implements the List interface. It allows for insertion, deletion, and
 * retrieval of elements at specified positions, as well as other common
 * operations such as checking for emptiness, obtaining the size, and clearing
 * the list.
 *
 * @author Khang Hoang Nguyen & Phuc Bao Do
 * @version Feb 29, 2024
 */
public class SinglyLinkedList<T> implements List<T> {
	private Node<T> head;
	private int size;

	/**
	 * Constructs an empty SinglyLinkedList with initial values of null and zero for
	 * the head reference and size, respectively.
	 */
	public SinglyLinkedList() {
		head = null;
		size = 0;
	}

	/**
	 * Inserts the specified element at the beginning of this list.
	 *
	 * @param element the element to add
	 */
	@Override
	public void insertFirst(T element) {
		head = new Node<>(element, head);
		size++;
	}

	/**
	 * Inserts the specified element at the specified position in this list. Shifts
	 * the element currently at that position (if any) and any subsequent elements
	 * to the right (adds one to their indices).
	 *
	 * @param index   - the index at which the specified element is to be inserted
	 * @param element - the element to be inserted
	 * @throws IndexOutOfBoundsException - if the index is out of range
	 */
	@Override
	public void insert(int index, T element) throws IndexOutOfBoundsException {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException();
		if (index == 0)
			insertFirst(element);
		else {
			Node<T> prevNode = getNode(index - 1);
			prevNode.next = new Node<>(element, prevNode.next);
			size++;
		}
	}

	/**
	 * Returns the node at the specified position in this list.
	 *
	 * @param pos the position of the node to return
	 * @return the node at the specified position in this list
	 */
	private Node<T> getNode(int pos) {
		Node<T> temp = head;
		for (int i = 0; i < pos; i++) {
			temp = temp.next;
		}
		return temp;
	}

	/**
	 * Returns the first element in this list.
	 *
	 * @return the first element in this list
	 * @throws NoSuchElementException if this list is empty
	 */
	@Override
	public T getFirst() throws NoSuchElementException {
		if (head == null)
			throw new NoSuchElementException();
		return head.data;
	}

	/**
	 * Returns the element at the specified position in this list.
	 *
	 * @param index the index of the element to return
	 * @return the element at the specified position in this list
	 * @throws IndexOutOfBoundsException if the index is out of range
	 */
	@Override
	public T get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException();
		Node<T> currNode = getNode(index);
		return currNode.data;
	}

	/**
	 * Removes and returns the first element from this list.
	 *
	 * @return the first element from this list
	 * @throws NoSuchElementException if this list is empty
	 */
	@Override
	public T deleteFirst() throws NoSuchElementException {
		if (head == null)
			throw new NoSuchElementException();
		T temp = head.data;
		head = head.next;
		size--;
		return temp;
	}

	/**
	 * Removes and returns the element at the specified position in this list.
	 *
	 * @param index the index of the element to be removed
	 * @return the element previously at the specified position
	 * @throws IndexOutOfBoundsException if the index is out of range
	 */
	@Override
	public T delete(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException();

		if (index == 0)
			return deleteFirst();
		else {
			Node<T> prevNode = getNode(index - 1);
			T temp = prevNode.next.data;
			prevNode.next = prevNode.next.next;
			size--;
			return temp;
		}
	}

	/**
	 * Returns the index of the first occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element.
	 *
	 * @param element the element to search for
	 * @return the index of the first occurrence of the specified element in this
	 *         list, or -1 if this list does not contain the element
	 */
	@Override
	public int indexOf(T element) {
		Node<T> currNode = head;
		for (int i = 0; i < size; i++) {
			if (currNode.data.equals(element))
				return i;
			currNode = currNode.next;
		}
		return -1;
	}

	/**
	 * Returns the number of elements in this list.
	 *
	 * @return the number of elements in this list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns true if this list contains no elements.
	 *
	 * @return true if this list contains no elements
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Removes all of the elements from this list.
	 */
	@Override
	public void clear() {
		head = null;
		size = 0;
	}

	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence (from first to last element).
	 *
	 * @return an array containing all of the elements in this list in proper
	 *         sequence
	 */
	@Override
	public T[] toArray() {
		Object[] array = new Object[size];
		Node<T> currNode = head;
		for (int i = 0; i < size; i++) {
			array[i] = currNode.data;
			currNode = currNode.next;
		}
		return (T[]) array;
	}

	/**
	 * Returns an iterator over the elements in this list.
	 *
	 * @return an iterator over the elements in this list
	 */
	@Override
	public Iterator<T> iterator() {
		return new SinglyListIterator();
	}

	/**
	 * Implementation of the iterator for traversing the elements of the list.
	 */
	private class SinglyListIterator implements Iterator<T> {
		private Node<T> curr = head;
		private Node<T> pre = null;

		/**
		 * Returns true if the iteration has more elements.
		 *
		 * @return true if the iteration has more elements
		 */
		@Override
		public boolean hasNext() {
			return curr != null;
		}

		/**
		 * Returns the next element in the iteration.
		 *
		 * @return the next element in the iteration
		 * @throws NoSuchElementException if the iteration has no more elements
		 */
		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T data = curr.data;
			pre = curr;
			curr = curr.next;
			return data;
		}

		/**
		 * Removes from the underlying collection the last element returned by this
		 * iterator (optional operation). This method can be called only once per call
		 * to next().
		 *
		 * @throws IllegalStateException if the next() method has not yet been called,
		 *                               or the remove() method has already been called
		 *                               after the last call to next()
		 */
		@Override
		public void remove() {
			if (pre == null) {
				throw new IllegalStateException();
			}
			if (pre == head)
				head = curr;
			else
				pre.next = curr;

			pre = null;
			size--;
		}
	}

	/**
	 * A node in the singly linked list that holds data and a reference to the next
	 * node.
	 *
	 * @param <T> the type of data stored in the node
	 */
	private static class Node<T> {
		private T data;
		private Node<T> next;

		public Node(T data, Node<T> next) {
			this.data = data;
			this.next = next;
		}
	}

}