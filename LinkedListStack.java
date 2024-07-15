package assign06;

import java.util.NoSuchElementException;

/**
 * This class implements the Stack interface using a singly linked list as the
 * underlying data structure. It provides methods to perform stack operations
 * such as push, pop, peek, clear, isEmpty, and size.
 *
 * @author Khang Hoang Nguyen & Phuc Bao Do
 * @version Feb 29, 2024
 */
public class LinkedListStack<T> implements Stack<T> {
	private SinglyLinkedList<T> singlyLinkedList;

	public LinkedListStack() {
		singlyLinkedList = new SinglyLinkedList<>();
	}

	/**
	 * Removes all elements from the stack.
	 */
	@Override
	public void clear() {
		singlyLinkedList.clear();
	}

	/**
	 * Checks whether the stack is empty.
	 *
	 * @return true if the stack is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return singlyLinkedList.isEmpty();
	}

	/**
	 * Retrieves, but does not remove, the element at the top of the stack.
	 *
	 * @return the element at the top of the stack
	 * @throws NoSuchElementException if the stack is empty
	 */
	@Override
	public T peek() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("Stack is empty");
		}
		return singlyLinkedList.getFirst();
	}

	/**
	 * Removes and returns the element at the top of the stack.
	 *
	 * @return the element at the top of the stack
	 * @throws NoSuchElementException if the stack is empty
	 */

	@Override
	public T pop() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        T temp = singlyLinkedList.getFirst();
        singlyLinkedList.deleteFirst();
        return temp;
    }

	/**
	 * Pushes an element onto the top of the stack.
	 *
	 * @param element the element to be pushed onto the stack
	 */

	@Override
	public void push(T element) {
		singlyLinkedList.insertFirst(element);
	}

	/**
	 * Returns the number of elements in the stack.
	 *
	 * @return the number of elements in the stack
	 */

	@Override
	public int size() {
		return singlyLinkedList.size();
	}
}
