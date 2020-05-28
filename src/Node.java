public class Node {
	
	// Attributes

	private Node next;
	private int value;

	// Constructor

	public Node(int value)
	{
		this.value = value;
	}

	// Getters

	public int getValue(){
		return value;
	}

	public Node getNext(){
		return next;
	}

	// Setters

	public void setNext(Node next){
		this.next = next;
	}

	// To String

	public String toString() {
		return Integer.toString(value);
	}
}