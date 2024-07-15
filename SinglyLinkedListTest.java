package assign06;

import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class contains JUnit tests for the SinglyLinkedList class. It tests
 * various methods of the SinglyLinkedList class to ensure they work as
 * expected. The tests cover normal cases, edge cases, and corner cases.
 *
 * @author Khang Hoang Nguyen & Phuc Bao Do
 * @version Feb 29, 2024
 */
class SinglyLinkedListTest {
	private SinglyLinkedList<URL> list;

	@BeforeEach
	void setUp() throws Exception {
		SinglyLinkedList<URL> list = new SinglyLinkedList<URL>();
		this.list = list;
	}

	@Test
	void testSinglyLinkedListInsertFirst() throws MalformedURLException {
		URL url1 = new URL("https://example1.com");
		URL url2 = new URL("https://example2.com");
		list.insertFirst(url1);
		list.insertFirst(url2);
		assertEquals(url2, list.getFirst());
		assertEquals(url1, list.get(1));
		assertEquals(2, list.size());
	}

	@Test
	void testSinglyLinkedListInsert() throws MalformedURLException {
		list.clear();
		URL url1 = new URL("https://example1.com");
		URL url2 = new URL("https://example2.com");

		list.insertFirst(url1);
		list.insert(list.size(), url2);
		list.insert(1, new URL("https://example3.com"));
		assertEquals(new URL("https://example3.com"), list.get(1));
		assertEquals(3, list.size());

	}

	@Test
	void testSinglyLinkedListInsertThrow() throws MalformedURLException {
		SinglyLinkedList<URL> list = new SinglyLinkedList<>();

		assertThrows(IndexOutOfBoundsException.class, () -> list.insert(-1, new URL("https://example.com")));
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.insert(list.size() + 1, new URL("https://example.com")));

	}

	@Test
	void testSinglyLinkedListInsertIndexZero() throws MalformedURLException {
		SinglyLinkedList<URL> list = new SinglyLinkedList<>();
		URL url = new URL("https://example.com");

		list.insert(0, url);

		assertEquals(url, list.getFirst());
	}

	@Test
	void testSinglyListStackGetFirstThrow() throws MalformedURLException {
		SinglyLinkedList<URL> list = new SinglyLinkedList<>();
		assertThrows(NoSuchElementException.class, () -> list.getFirst());

	}

	@Test
	void testSinglyListStackGetThrow() throws MalformedURLException {
		SinglyLinkedList<URL> list = new SinglyLinkedList<>();
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(list.size() + 1));

	}

	@Test
	void testSinglyLinkedListDeleteFirst() throws MalformedURLException {
		list.clear();
		URL url1 = new URL("https://example1.com");
		list.insertFirst(url1);
		URL url2 = new URL("https://example2.com");
		list.insertFirst(url2);

		URL url3 = new URL("https://example3.com");
		list.insertFirst(url3);

		URL url4 = new URL("https://example4.com");
		list.insertFirst(url4);

		assertEquals(url4, list.deleteFirst());
		assertEquals(3, list.size());
		assertEquals(url3, list.deleteFirst());
		assertEquals(2, list.size());

	}

	@Test
	void testSinglyLinkedListDeleteFirstThrow() throws MalformedURLException {
		assertThrows(NoSuchElementException.class, () -> list.deleteFirst());

	}

	@Test
	void testSinglyLinkedListDelete() throws MalformedURLException {
		list.clear();
		URL url1 = new URL("https://example1.com");
		list.insertFirst(url1);
		URL url2 = new URL("https://example2.com");
		list.insertFirst(url2);

		URL url3 = new URL("https://example3.com");
		list.insertFirst(url3);

		URL url4 = new URL("https://example4.com");
		list.insertFirst(url4);

		assertEquals(url1, list.delete(list.size() - 1));
		assertEquals(3, list.size());
		assertEquals(url2, list.delete(list.size() - 1));

	}

	@Test
	void testSinglyLinkedListDeleteThrow() throws MalformedURLException {
		assertThrows(IndexOutOfBoundsException.class, () -> list.delete(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.delete(list.size() + 1));

	}

	@Test
	void testSinglyLinkedListDeleteIndexZero() throws MalformedURLException {
		list.clear();
		URL url1 = new URL("https://example1.com");
		list.insertFirst(url1);
		assertEquals(url1, list.delete(0));

	}

	@Test
	void testSinglyLinkedListIndexOf() throws MalformedURLException {
		URL url1 = new URL("https://example1.com");
		list.insert(0, url1);
		list.insertFirst(url1);
		URL url2 = new URL("https://example2.com");
		list.insertFirst(url2);

		URL url3 = new URL("https://example3.com");
		list.insertFirst(url3);

		URL url4 = new URL("https://example4.com");
		list.insert(1, url4);

		assertEquals(0, list.indexOf(url3));
		assertEquals(1, list.indexOf(url4));
		assertEquals(2, list.indexOf(url2));

	}

	@Test
	void testSinglyLinkedListIndexOfReturnNegativeOne() throws MalformedURLException {
		URL nonExistingURL = new URL("https://example3.com");
		int index = list.indexOf(nonExistingURL);

		assertEquals(-1, index);
	}

	@Test
	void testSinglyLinkedListToArray() throws MalformedURLException {
		list.clear();
		URL url1 = new URL("https://example1.com");
		URL url2 = new URL("https://example2.com");
		URL url3 = new URL("https://example3.com");
		list.insertFirst(url1);
		list.insertFirst(url2);
		list.insertFirst(url3);

		Object[] array = list.toArray();

		assertArrayEquals(new URL[] { url3, url2, url1 }, array);
	}

	@Test
	void testSinglyLinkedListIterator() throws MalformedURLException {
		list.clear();
		URL url1 = new URL("https://example1.com");
		URL url2 = new URL("https://example2.com");
		URL url3 = new URL("https://example3.com");
		list.insertFirst(url1);
		list.insertFirst(url2);
		list.insertFirst(url3);

		Iterator<URL> iterator = list.iterator();

		assertTrue(iterator.hasNext());
		assertEquals(url3, iterator.next());
		assertEquals(url2, iterator.next());
		assertEquals(url1, iterator.next());
		assertFalse(iterator.hasNext());

	}

	@Test
	void testSinglyLinkedListRemove() throws MalformedURLException {
		SinglyLinkedList<URL> list = new SinglyLinkedList<>();
		URL url1 = new URL("https://example1.com");
		URL url2 = new URL("https://example2.com");
		URL url3 = new URL("https://example3.com");
		list.insertFirst(url1);
		list.insertFirst(url2);
		list.insertFirst(url3);

		Iterator<URL> iterator = list.iterator();

		iterator.next();
		iterator.remove();

		assertEquals(url2, list.getFirst());
		assertEquals(2, list.size());
	}

	@Test
	void testSinglyLinkedListRemoveThrow() throws MalformedURLException {
		SinglyLinkedList<URL> list = new SinglyLinkedList<>();
		Iterator<URL> iterator = list.iterator();
		assertThrows(IllegalStateException.class, () -> iterator.remove());
	}

	@Test
	void testSinglyLinkedListHasNext() throws MalformedURLException {
		list.clear();
		URL url1 = new URL("https://example1.com");
		URL url2 = new URL("https://example2.com");
		URL url3 = new URL("https://example3.com");
		list.insertFirst(url1);
		list.insertFirst(url2);
		list.insertFirst(url3);

		Iterator<URL> iterator = list.iterator();

		SinglyLinkedList<URL> emptyList = new SinglyLinkedList<>();
		Iterator<URL> iterator2 = emptyList.iterator();

		assertTrue(iterator.hasNext());
		assertFalse(iterator2.hasNext());

	}

	@Test
	void testSinglyLinkedListNext() throws MalformedURLException {
		list.clear();
		URL url1 = new URL("https://example1.com");
		URL url2 = new URL("https://example2.com");
		URL url3 = new URL("https://example3.com");
		list.insertFirst(url1);
		list.insertFirst(url2);
		list.insertFirst(url3);

		Iterator<URL> iterator = list.iterator();
		assertTrue(iterator.hasNext());
		assertEquals(iterator.next(), url3);
	}

	@Test
	void testSinglyLinkedListIsEmpty() throws MalformedURLException {
		list.clear();
		assertTrue(list.isEmpty());
	}

	@Test
	void testSinglyLinkedListHasNextThrow() throws MalformedURLException {
		list.clear();
		URL url1 = new URL("https://example1.com");
		list.insertFirst(url1);

		list.clear();
		Iterator<URL> iterator = list.iterator();

		assertThrows(NoSuchElementException.class, () -> iterator.next());
	}

	@Test
	void testSinglyLinkedListLargeProblemSize() throws MalformedURLException {
		int numElements = 10000;
		for (int i = 0; i < numElements; i++) {
			list.insertFirst(new URL("https://example" + i + ".com"));
		}
		assertEquals(numElements, list.size());
	}

	@Test
	void testSinglyLinkedListLargeDuplication() throws MalformedURLException {
		URL url = new URL("https://example.com");
		list.insertFirst(url);
		list.insertFirst(url);
		assertEquals(2, list.size());
	}

	@Test
	void testSinglyLinkedListMixedDataTypes() throws MalformedURLException {
		String str = "String";
		Integer integer = 123;
		URL url = new URL("https://example.com");
		SinglyLinkedList<Object> list = new SinglyLinkedList<>();

		list.insertFirst(str);
		list.insertFirst(integer);
		list.insertFirst(url);

		assertEquals(url, list.getFirst());
		assertEquals(integer, list.get(1));
		assertEquals(str, list.get(2));

	}

	@Test
	void testSinglyLinkedListModificationDuringIteration() throws MalformedURLException {
		list.clear();
		URL url1 = new URL("https://example1.com");
		URL url2 = new URL("https://example2.com");
		URL url3 = new URL("https://example3.com");
		list.insertFirst(url1);
		list.insertFirst(url2);
		list.insertFirst(url3);

		Iterator<URL> iterator = list.iterator();

		list.insertFirst(new URL("https://example5.com"));
		list.insert(2, new URL("https://example6.com"));

		iterator = list.iterator();
		assertEquals(new URL("https://example5.com"), iterator.next());
		iterator.next();
		assertEquals(new URL("https://example6.com"), iterator.next());

	}

	@Test
	void testSinglyLinkedListInsertAndDeleteCornerCase() throws MalformedURLException {
		URL url1 = new URL("https://example1.com");
		URL url2 = new URL("https://example2.com");
		URL url3 = new URL("https://example3.com");
		list.insertFirst(url1);
		list.insert(list.size(), url2);
		list.insert(list.size(), url3);
		URL url4 = new URL("https://example4.com");
		list.insertFirst(url4);
		assertEquals(url4, list.getFirst());

		URL url5 = new URL("https://example5.com");
		list.insert(2, url5);
		assertEquals(url5, list.get(2));

		assertEquals(url4, list.deleteFirst());
		assertEquals(4, list.size());
		assertEquals(url1, list.getFirst());

		assertEquals(url3, list.delete(list.size() - 1));
		assertEquals(3, list.size());

		assertEquals(url5, list.delete(1));
		assertEquals(2, list.size());
	}

}