public class LinkedList
{
	// Attributes

	private Node head, tail;
	private int length;

	// Constructor

	public LinkedList() {
		head = tail = null;
		length = 0;
	}

	// getHead

	public Node getHead(){	
		return head;	
	}

	// getTail

	public Node getTail(){
		return tail;
	}

	// Length

	public int length(){
		return length;
	}

	// isEmpty

	public boolean isEmpty() {
		return head == null;

	}

	// addFirst (Value Version)

	public void addFirst(int value){
		addFirst(new Node(value));
	}

	// addFirst (Node Version)

	public void addFirst(Node newNode){
		Node formerHead = head;
		head = newNode;
		head.setNext(formerHead);

		if (++ length == 1)
			tail = head;
	}

	// removeFirst

	public void removeFirst(){
		if (!(isEmpty()))
		{
			head = head.getNext();
		}

		if (-- length == 0)
		{
			tail = head;
		}
	}

	// addLast (Value Version)

	public void addLast(int value){
		addLast(new Node(value));
	}

	// addLast (Node Version)

	public void addLast(Node newTail){
		if (isEmpty())
		{
			head = tail = newTail;
		}
		else {
			tail.setNext(newTail);
			tail = newTail;
		}
		length ++;

	}

	// removeLast

	public void removeLast() {
		if (!(isEmpty())){
			Node newTail = head;

			while (newTail.getNext() != tail){
				newTail = newTail.getNext();

				tail = newTail;
				tail.setNext(null);

				length --;
			}
		}
	}

	// Equals

	public int countDuplicate(LinkedList l, int value) {
		int count = 0;
		
		for (Node i = l.getHead(); i != null; i = i.getNext()) {
			if(i.getValue() == value) {
				count++;
			}
		}
		return count;
	}

	public boolean isMatch(LinkedList l1, LinkedList l2){

		int len = l1.length;

		Node current_l1 = l1.getHead();

		while(len != 0) {

			int count_l1 = countDuplicate(l1, current_l1.getValue());
			int count_l2 = countDuplicate(l2, current_l1.getValue());

			if(count_l1 != count_l2) {
				return false;
			}
			current_l1 = current_l1.getNext();

			len -- ;

		}
		return true;
	}

	public boolean Equals(Object o){
		return o instanceof LinkedList && 
				(((LinkedList) o).length() == this.length) &&
									isMatch(this, (LinkedList) o);
	}

	// Reverse
	
	public void reverseLinkedList() {
		Node previous = null;
		Node current = head;
		Node next = null;

		while(current != null) {
			next = current.getNext();
			current.setNext(previous);
			previous = current;
			current = next;
		}
		this.head = previous;
	}

	// Is Reversed

	public boolean IsReversed(LinkedList l){
		Node current = head;
		int len = l.length; 

		Node l_head = l.getHead();
		Node tmp = l_head; 

		for (Node i = current; i != null; i = i.getNext()){
			for (int j = 0; j < len - 1; j++){ 
				l_head = l_head.getNext();
			}
			if(i.getValue() != l_head.getValue()) {
				return false;
			}
			l_head = tmp;
			len --;
		}
		return true;
	}

	// To String

	public String toString() {
		String strToReturn = "";
		Node current = head;

		if (head == null){
			return "The List Is Empty!";
		}
		while (current != null) {
			strToReturn += "[" + current.getValue() + "]->" + " ";
			current = current.getNext();
		}
		return strToReturn + "Null";
	}

	// Sorting LinkList
}
