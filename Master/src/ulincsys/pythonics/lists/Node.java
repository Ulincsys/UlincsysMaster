package ulincsys.pythonics.lists;

/**
	* Plist Node. Bi-directional data container.
	* @author Ulincsys
	*/
public class Node {
	public Node next;
	public Node prev;
	public Object data;

	/**
	* Initializes empty Node. O(1)
	*/
	public Node() {
		next = null;
		prev = null;
		data = null;
	}
	
	/**
	* Initializes Node with given (Object)data. O(1)
	*/
	public Node(Object dat) {
		next = null;
		prev = null;
		data = dat;
	}

	/**
	* Initializes Node with given (Object)data, previous and next Nodes. O(1)
	*/
	public Node(Node prev, Node next, Object dat) {
		this.next = next;
		this.prev = prev;
		data = dat;
	}
	
	/**
	* Returns string representation of (Object)data. O(1)
	*/
	public String toString() {
		if(data == null) {
			return "null";
		}
		return data.toString();
	}
	
	/**
	* Returns true if Node data is instance of (int). O(1)
	*/
	public boolean isInt() {
		return (data instanceof Integer);
	}

	/**
	* Returns true if Node data is instance of (String). O(1)
	*/
	public boolean isStr() {
		return (data instanceof String);
	}
	
	public boolean isType(Object test) {
		return (test.getClass() == data.getClass());
	}

	/**
	* Returns the internal reflective Class of (Object)data. O(n)
	*/
	public Class<? extends Object> getType() {
		try {
			return data.getClass();
		} catch(Exception e) {
			return null;
		}
	}
}