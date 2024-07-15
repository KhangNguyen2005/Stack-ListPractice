package assign06;

import java.net.URL;
import java.util.NoSuchElementException;

/**
 * A simple web browser simulation class that maintains browsing history.
 * 
 * @author Khang Hoang Nguyen & Phuc Bao Do
 * @version Feb 29, 2024
 */
public class WebBrowser {
	private LinkedListStack<URL> backward;
	private LinkedListStack<URL> forward;
	private URL currentSite;

	/**
	 * Constructs a new WebBrowser object with empty browsing history.
	 */
	public WebBrowser() {
		backward = new LinkedListStack<>();
		forward = new LinkedListStack<>();
		currentSite = null;
	}

	/**
	 * Constructs a new WebBrowser object with provided browsing history.
	 *
	 * @param history The browsing history to initialize the WebBrowser with.
	 */
	public WebBrowser(SinglyLinkedList<URL> history) {
		backward = new LinkedListStack<>();
		forward = new LinkedListStack<>();
		currentSite = null;

		LinkedListStack<URL> tempStack = new LinkedListStack<>();

		for (URL site : history) {
			tempStack.push(site);
		}

		while (!tempStack.isEmpty())
			backward.push(tempStack.pop());

		currentSite = backward.pop();
	}

	/**
	 * Visits a webpage, updating the browsing history.
	 *
	 * @param webpage The URL of the webpage to visit.
	 */
	public void visit(URL webpage) {
		if (!(currentSite == null))
			backward.push(currentSite);
		currentSite = webpage;
		forward.clear();
	}

	/**
	 * Moves back to the previous webpage in the browsing history.
	 *
	 * @return The URL of the previous webpage.
	 * @throws NoSuchElementException If there are no previous webpages in the
	 *                                history.
	 */
	public URL back() throws NoSuchElementException {
		if (backward.isEmpty())
			throw new NoSuchElementException();
		forward.push(currentSite);
		currentSite = backward.pop();
		return currentSite;
	}

	/**
	 * Moves forward to the next webpage in the browsing history.
	 *
	 * @return The URL of the next webpage.
	 * @throws NoSuchElementException If there are no next webpages in the history.
	 */
	public URL forward() throws NoSuchElementException {
		if (forward.isEmpty())
			throw new NoSuchElementException();
		backward.push(currentSite);
		currentSite = forward.pop();
		return currentSite;
	}

	/**
	 * Retrieves the browsing history.
	 *
	 * @return A SinglyLinkedList containing the browsing history.
	 */
	public SinglyLinkedList<URL> history() {
		SinglyLinkedList<URL> historyList = new SinglyLinkedList<>();
		LinkedListStack<URL> tempStack = new LinkedListStack<>();

		while (!backward.isEmpty()) {
			URL url = backward.pop();
			tempStack.push(url);
		}

		while (!tempStack.isEmpty()) {
			URL url = tempStack.pop();
			historyList.insertFirst(url);
			backward.push(url);
		}
		historyList.insertFirst(currentSite);
		return historyList;
	}

}