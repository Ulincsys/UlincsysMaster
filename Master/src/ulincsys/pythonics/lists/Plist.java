package ulincsys.pythonics.lists;

import java.util.*;
import static ulincsys.pythonics.Util.*;

/**
	* A typeless linked list with extended features.
	* Supports random fill, sorting and printing.
	* Returns data as typecast (Any)Object;
	* @author Ulincsys
	*/
public class Plist {
	private Node head;
	private Node tail;
	private Node cursor;
	private Node store;
	private int len;
	
	/**
	* Initializes empty plist. O(1)
	*/
	public Plist() {
		head = new Node();
		tail = new Node(head, null, null);
		head.next = tail;
		store = cursor = head;
		len = 0;
	}
	
	/**
	* Initializes plist with the given number of (k) objects, or with an Object[] array of (k) objects. O(k)
	*/
	public Plist(Object... objects) {
		head = new Node();
		tail = new Node(head, null, null);
		head.next = tail;
		store = cursor = head;
		len = 0;
		
		for(Object item : objects) {
			add(item);
		}
	}
	
	/**
	* Returns the number of items contained within parent list. O(1)
	*/
	public int len() {
		return len;
	}

	/**
	* Returns true if the head of the list points to the tail, else false. O(1)
	*/
	public boolean isEmpty() {
		return (head.next == tail) ? true : false;
	}

	/**
	* Adds all items in given List of (k) length to list.
	* Takes any List<Any>. O(K)
	*/
	public void addList(List items) {
		for(Object item : items) {
			add(item);
		}
	}
	
	/**
	* Adds item to end of list. Takes any (Object)data. O(1)
	*/
	public void add(Object data) {
		Node temp = new Node(tail.prev, tail, data);
		temp.prev.next = tail.prev = temp;
		++len;
	}

	/**
	* Adds all given (k) items to list. Takes any (Object)data. O(k)
	*/
	public void add(Object... items) {
		for(Object item : items) {
			add(item);
		}
	}
	
	/**
	* Returns the item at given index as typecast (Any)Object. O(n)
	*/
	@SuppressWarnings("unchecked")
	public <Any> Any get(int index) {
		if(!checkIndex(index)) {
			return null;
		}
		Node temp = nodeAt(index);
		
		Object ret = temp.data;
		Class<? extends Object> dat = ret.getClass();
		return (Any) dat.cast(ret);
	}
	
	/**
	* Resets list cursor to list head, stores previous location for restore(). O(1)
	*/
	public void rewind() {
		store = cursor;
		if(isEmpty()) {
			cursor = head;
		} else {
			cursor = head.next;
		}
	}
	
	/**
	* Restores list cursor to previous location given by rewind(). O(1)
	*/
	public void restore() {
		cursor = store;
	}
	
	/**
	* Returns false if cursor next Node is tail of list or null, else true. O(1)
	*/
	public boolean hasNext() {
		return (cursor.next != tail && cursor.next != null) ? true : false;
	}
	
	/**
	* Returns false if cursor previous Node is head of list or null, else true. O(1)
	*/
	public boolean hasPrev() {
		return (cursor.prev != head && cursor.prev != null) ? true : false;
	}
	
	/**
	* Returns cursor next Node if hasNext(), else null. O(1)
	*/
	public Node next() {
		if(hasNext()) {
			cursor = cursor.next;
			return cursor;
		}
		return null;
	}
	
	/**
	* Returns cursor previous Node if hasPrev(), else null. O(1)
	*/
	public Node prev() {
		if(hasPrev()) {
			cursor = cursor.prev;
			return cursor;
		}
		return null;
	}
	
	/**
	* Removes Node at index and returns data as (Any)Object, else null. Invokes nodeAt(index). O(n)
	*/
	@SuppressWarnings("unchecked")
	public <Any> Any rem(int index) {
		if(!checkIndex(index)) {
			return null;
		}
		Node temp = nodeAt(index);
		Object ret = temp.data;
		
		stripNode(temp);
		--len;
		
		Class<? extends Object> dat = ret.getClass();
		return (Any) dat.cast(ret);
	}

	/**
	* Removes given node from its list unless it is immovable.
	* Returns true if removal is successful, else false. O(1)
	*/
	public boolean stripNode(Node item) {
		if(immovable(item)) {
			return false;
		}
		
		if(cursor == item) {
			if(store == cursor) {
				store = head;
			}
			if(next() == null) {
				if(prev() == null) {
					cursor = head;
				}
			}
		}

		item.prev.next = item.next;
		item.next.prev = item.prev;
		item.next = item.prev = null;
		return true;
	}
	
	/**
	* Returns Node at index, else null.
	* Can take negative index to reference list in reverse, as long as len() + index is >= 0. O(n)
	*/
	public Node nodeAt(int index) {
		if(!checkIndex(index)) {
			return null;
		} else if(index < 0) {
			index += len;
		}
		rewind();
		int i = 0;
		
		while(++i <= index) {
			next();
		}
		
		Node temp = cursor;
		restore();
		return temp;
	}
	
	/**
	* Returns Object array of data items, else null if list is empty. O(n)
	*/
	public Object[] toArray() {
		if(isEmpty()) {
			return null;
		}
		rewind();
		
		Object[] temp = new Object[len];
		for(int i = 0; i < len; ++i) {
			temp[i] = cursor.data;
			next();
		}
		
		restore();
		return temp;
	}
	
	/**
	* Swaps data items between two Nodes, given that neither of them are immovable() and that they are not the same Node.
	* Returns false if the swap fails, or true if the swap succeeds. O(1)
	*/
	public boolean swap(Node one, Node two) {
		if(immovable(one) || immovable(two) || one == two) {
			return false;
		}
		Object temp = one.data;
		one.data = two.data;
		two.data = temp;
		return true;
	}

	/**
	* Returns true if the given Node is equal to this.head or this.tail.
	* Returns true if the given Node has null next or previous Nodes, else false. O(1)
	*/
	public boolean immovable(Node test) {
		return (test == head || test == tail ||
		 test.next == null || test.prev == null) ? true : false;
	}
	
	/**
	* Swaps data items between two nodes at the given indices, given that neither of them are immovable() and that they are not the same node.
	* Returns false if the swap fails, or true if the swap succeeds.
	* Invokes nodeAt(index). O(n)
	*/
	public boolean swap(int one, int two) {
		if((checkIndex(one) || checkIndex(two)) || one == two) {
			return false;
		}
		
		Node n1 = nodeAt(one);
		Node n2 = nodeAt(two);
		return swap(n1, n2);
	}
	
	/**
	* Converts list to Object array and returns it.
	* Clears list of all items.
	* Invokes toArray() and drop(). O(n)
	*/
	public Object[] reset() {
		Object[] temp = toArray();
		drop();
		return temp;
	}

	/**
	* Removes all references to data items on list, and resets len(). O(1)
	*/
	public void drop() {
		head.next = tail;
		store = cursor = tail.prev = head;
		len = 0;
	}
	
	/**
	* Returns false if the given index is greater than or equal to len(), or if len() + index is less than 0, else true. O(1)
	*/
	public boolean checkIndex(int index) {
		if(index >= len) {
			return false;
		} else if((len + index) < 0) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	* Adds given amount (k) of random numbers between 1 and 1000 to list. O(k)
	*/
	public void fill(int amt) {
		Random rand = new Random();
		while(--amt >= 0) {
			add((rand.nextInt() % 1000) + 1);
		}
	}
	
	/**
	* Prints formatted string representations of each item separated by brackets. O(n)
	*/
	public void read() {
		rewind();
		System.out.println("Plist with " + len + " items:");
		do {
			System.out.print("[ " + cursor.toString() + " ] ");
		} while(next() != null);

		System.out.println();
		restore();
	}
	
	/**
	* Prints formatted string representations of each item separated by brackets. O(n)
	*/
	public void read(int breakLn) {
		int willBreak = 0;
		rewind();
		System.out.println("Plist with " + len + " items:");
		do {
			System.out.print("[ " + cursor.toString() + " ] ");
			if((++willBreak % breakLn) == 0) {
				System.out.println();
			}
		} while(next() != null);

		System.out.println();
		restore();
	}

	public void sortInts() {
		ArrayList<Object> sorted = removeType(Integer.class);
		sorted.sort(new compareInts());
		addList(sorted);
	}

	public void sortAlpha() {
		ArrayList<Object> sorted = removeType(String.class);
		sorted.sort(new compareStrings());
		addList(sorted);
	}

	public void sortType(Class<? extends Object> typ, Comparator<? super Object> c) {
		ArrayList<Object> sorted = removeType(typ);
		sorted.sort(c);
		addList(sorted);
	}
	
	/** Removes all Objects of a given type from the list and returns an 
	 * ArrayList populated with the items.
	 * @param typ The class used to determine what Objects are removable.
	 * @apiNote Does not test superclass, must match exactly.*/
	public ArrayList<Object> removeType(Class<? extends Object> typ) {
		if(len == 0) {
			return null;
		}
		
		ArrayList<Object> removed = new ArrayList<Object>();
		rewind();
		while(cursor != tail) {
			if(cursor.getType() == typ) {
				removed.add(cursor.data);
				stripNode(cursor);
				--len;
			} else {
				cursor = cursor.next;
			}
		}
		
		restore();
		return removed;
	}
}




class compareInts implements Comparator<Object> {
	public int compare(Object a, Object b) {
		if(Int(a) > Int(b)) {
			return 1;
		} else if(Int(a) < Int(b)) {
			return -1;
		}
		return 0;
	}
}

class compareStrings implements Comparator<Object> {
	public int compare(Object a, Object b) {
		return Str(a).compareToIgnoreCase(Str(b));
	}
}









