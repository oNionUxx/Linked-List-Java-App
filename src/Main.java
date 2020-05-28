import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	// Instance Variables 

	public static boolean exit, result;
	public static int lenght, choise;
	public static Scanner input = new Scanner(System.in);

	// Linked Lists

	static LinkedList l1 = new LinkedList();
	static LinkedList l2 = new LinkedList();
	static LinkedList l3 = new LinkedList();

	// Stack

	static LinkedStack s = new LinkedStack();

	// fromTheEnd

	public static int fromTheEndIterative(LinkedList l, int N) { 
		l.reverseLinkedList();

		Node current = l.getHead();
		int count = 0;

		while(N != count) {
			count++;
			current = current.getNext();
		}
		return current.getValue();
	}

	public static int reverse(Node node, int N) { 
		if(N == 0) {
			return node.getValue();
		}
		return reverse(node.getNext(), N - 1);

	}

	public static int fromTheEndRecursive(LinkedList l, int N) {
		l.reverseLinkedList();
		return reverse(l.getHead(), N);	
	}

	public static int fromTheEnd(LinkedList l, int N) {
		Node current = l.getHead(); 

		for (int i = 0; i < (l.length() - 1) - N; i++) {
			current = current.getNext();
		}
		return current.getValue();
	}

	// selectionSort

	public static void swap(LinkedList l, int replace, int maxOrMin) { // 

		Node prevReplace = null, currReplace = l.getHead(); 

		// Search for replace (keep track of prevReplace and currReplace)

		while (currReplace != null && currReplace.getValue() != replace) { 
			prevReplace = currReplace; 
			currReplace = currReplace.getNext();
		} 

		// Save the pointer of currMax

		Node temp = currReplace.getNext();

		Node prevMaxOrMin = null, currMaxOrMin = l1.getHead();

		// Search for max (keep track of prevMaxOrMin and currMaxOrMin ) 

		while (currMaxOrMin != null && currMaxOrMin.getValue() != maxOrMin) { 
			prevMaxOrMin = currMaxOrMin; 
			currMaxOrMin = currMaxOrMin.getNext();
		} 

		// If either replace or MaxOrMin is not present, nothing to do 

		if (currReplace == null || currMaxOrMin == null) {
			return; 
		}

		// If replace is not head of linked list 

		if (prevReplace != null) {
			prevReplace.setNext(currMaxOrMin);
		}

		// make maxOrmin the new head 

		else {		
			l1.removeFirst();
			l1.addFirst(currMaxOrMin);
			currReplace.setNext(currMaxOrMin.getNext());
			currMaxOrMin.setNext(prevMaxOrMin);
		}

		// If MaxOrMin  is not head of linked list 

		if (prevMaxOrMin != null) 
			prevMaxOrMin.setNext(currReplace);

		// make replace the new head 

		else {
			l1.removeFirst();
			l1.addFirst(currReplace);
			currReplace.setNext(currMaxOrMin);
		}

		// Swap next pointers

		currReplace.setNext(currMaxOrMin.getNext());
		currMaxOrMin.setNext(temp);
	}

	public static void selectionSort(LinkedList l) { 

		int len = l.length();

		for (int i = 1; i < l.length(); i++) {
			Node current = l.getHead();
			Node max = current;
			for (int j = 0; j < len - 1; j ++) { 

				if(current.getValue() > max.getValue()){
					max = current;
				}
				current = current.getNext();
			}
			if(max.getValue() > current.getValue()){
				swap(l, current.getValue(), max.getValue());
			}
			len --;
		}
	}

	// funnySelectionSort

	public static void funnySelectionSort(LinkedList l) {
		int len = l.length();

		for (int i = 1; i < l.length(); i++) {
			Node current = l.getHead();
			Node min = current;
			for (int j = 0; j < len - 1; j ++) { 

				if(current.getValue() < min.getValue()){
					min = current;
				}
				current = current.getNext();
			}
			if(min.getValue() < current.getValue()){
				swap(l, current.getValue(), min.getValue());
			}
			len --;
		}
	}

	// isPalindrome

	public static boolean isPalindrome(LinkedList l) {

		Node current = l.getHead();

		// Go over the list
		// push all the linkedList 
		// values into the stack

		while(current != null) {
			s.push(current.getValue());
			current = current.getNext();
		}

		// Initialize current back 
		// to the list's head value

		current = l.getHead();

		// Iterate until stack is empty
		// Compare each time values 
		// from both list and stack

		while(!s.isEmpty()){
			if(current.getValue() != s.peek()) {
				return false;
			}
			current = current.getNext();
			s.pop();		
		}

		// If all values are match 

		return true;
	}

	// This method checks if a list's node
	// using another method is in another list
	// If so, add the node to a new list

	public static LinkedList addNode(LinkedList newL, LinkedList l1, LinkedList l2) {
		for (Node i = l1.getHead(); i != null; i = i.getNext()) {
			if(!isExists(l2, i)) {
				newL.addFirst(i.getValue());
			}
		}
		return newL;	
	}

	// Given a certain list and a node
	// This method checks if the 
	// node's value is in the list

	public static boolean isExists(LinkedList l, Node h) {
		Node current = l.getHead();

		while(current != null) {
			if(current.getValue() == h.getValue()) {
				return true;
			}
			current = current.getNext();
		}
		return false;
	}

	// uniqueElements

	public static LinkedList uniqueElements(LinkedList l1, LinkedList l2){

		LinkedList newList = new LinkedList();

		// Check which values 
		// in l1 exists in l2
		// Add non present values
		// to the newList

		addNode(newList, l1, l2);

		// Check which values 
		// in l2 exists in l1
		// Add non present values
		// to the newList

		addNode(newList, l2, l1);

		return newList;
	}

	public static LinkedList biggerSize(LinkedList big_L, LinkedList smal_L) {

		// Difference between lists size 

		int len = big_L.length() - smal_L.length();

		int count = 0;

		// For each iteration the 
		// bigger list remove its head
		// until both lists are equal in size

		while(len != count) {
			big_L.removeFirst();
			count++;
		}

		// Return the new size of the bigger 
		// list equal to the smaller now

		return big_L;
	}

	// commonStoresFrom

	public static Node commonStoresFrom(LinkedList l1, LinkedList l2) throws NullPointerException {

		// Check for the bigger list

		if(l1.length() > l2.length()) {
			l1 = biggerSize(l1, l2);
		}
		else {
			l2 = biggerSize(l2, l1);
		}

		// Pointers for both lists

		Node currentL1 = l1.getHead();
		Node currentL2 = l2.getHead();

		// Go over both lists until
		// current nodes's values are equal

		while(currentL1 != null) {

			if(currentL1.getValue() == currentL2.getValue()) {
				return currentL1;
			}

			currentL1 = currentL1.getNext();
			currentL2 = currentL2.getNext();
		}

		// Return last common Store value

		return null;
	}

	// commonRoutesFrom

	public static int commonRoutesFrom(LinkedList l1, LinkedList l2) {

		// Check for the bigger list

		if(l1.length() > l2.length()) {
			l1 = biggerSize(l1, l2);
		}
		else {
			l2 = biggerSize(l2, l1);
		}

		// Pointers for both lists

		Node currL1 = l1.getHead();
		Node currL2 = l2.getHead();

		// Initialize first 
		// value visited by both couples

		int firstCommonStore = -1;

		// Initialize match value to false
		// No match has yet to be found

		boolean match = false;

		// Go over both lists

		while(currL1 != null || currL2 != null) {

			// If match was found

			if(currL1.getValue() == currL2.getValue()) {

				// If no match was found early on
				// firstCommonStore gets the last current value
				// sets the match value to true

				if(match == false) {
					firstCommonStore = currL1.getValue();
					match = true;
				}
			}

			// If no match was found from current running

			else {
				firstCommonStore = -1;
				match = false;
			}

			currL1 = currL1.getNext();
			currL2 = currL2.getNext();
		}

		// If no match was found at all

		return firstCommonStore;
	}

	// Header

	private static void printHeadr() {
		System.out.println("                  	       "         +         "+-----------------------------------------------+");
		System.out.println("                  	       "	     +         "|    	        Welcome To Our	               |");
		System.out.println("                  	       "	     +         "|	       Menu Applications!\t       |"); 
		System.out.println("                  	       "	     +         "+-----------------------------------------------+");
	}

	// Menu

	private static void printMenu() { 
		System.out.println("\n Plese make a selection:        ");
		System.out.println(" -----------------------          ");
		System.out.println("  1. Activate 'Equlas'    	      ");
		System.out.println("  2. Revers List          	      ");
		System.out.println("  3. Activate 'isReversed'  	  ");
		System.out.println("  4. Activate 'From The End'      ");
		System.out.println("  5. Sort List   				  ");
		System.out.println("  6. Activate Funny Selection Sort");
		System.out.println("  7. Activate isPalindrome        ");
		System.out.println("  8. Activate Unique Elements     ");
		System.out.println("  9. Activate Common Stores From  ");
		System.out.println(" 10. Activate Common Routes From  ");
		System.out.println("  0. Quit						  ");
	}

	// Run Menu

	public static void runMenu() {
		while(!exit) {
			printMenu();
			choise = getInput();
			switchCase(choise);
			if(choise != 0)
				backToMenuOption();
		}
	}

	// Get Back To Menu

	public static void backToMenuOption() {
		boolean isAnswerOK = false;
		System.out.print("\n Get back to mainMenu? (Y/N) ");

		while(!isAnswerOK) {
			String ans = input.next();

			if(ans.equalsIgnoreCase("N")) {
				exit = true;
				isAnswerOK = true;
				System.out.println("\n Thank you for using our application." + "\n");
			}
			else if(ans.equalsIgnoreCase("Y")) {
				isAnswerOK = true;
			}
			else {
				System.out.println("\n Invalid answer!, please type only (Y/N)");
			}	
		}
	}

	// Get Input

	public static int getInput() throws NumberFormatException {
		choise = -1;
		while(choise < 0 ||choise > 10) {
			try {
				System.out.print("\n Enter your choise: ");

				choise = Integer.parseInt(input.next());

				if(choise < 0 || choise > 10) {
					System.out.println("\n Invalid selection. please type a number between 0 and 10 only");
				}
			}
			catch(NumberFormatException e) {
				System.out.println("\n Your choise should be only numbers");
			}
		}
		return choise;
	}

	// Perform Action

	public static void switchCase(int choise) {
		int len_1, len_2;

		switch(choise) {
		case 0:
			quit();
			break;

		case 1:
			userInput(l1);
			userInput(l2);
			result = l1.Equals(l2);
			if(result == true){
				System.out.println("\n Lists are identical!");
			}
			else {
				System.out.println("\n Seems Like the lists are not similar");
			}
			break;

		case 2:
			userInput(l1);
			printReverse(l1);
			break;

		case 3:
			userInput(l1);
			userInput(l2);
			result = l1.IsReversed(l2);
			if(result == true){
				System.out.println("\n Lists are in a reverse order to one another!");
			}
			else {
				System.out.println("\n Lists are not in a reverse order to one another");
			}
			break;

		case 4:
			userInput(l1);
			printValue(l1);
			break;

		case 5:
			userInput(l1);
			printSelectionSort(l1);
			break;

		case 6:
			userInput(l1);
			System.out.println("\n Before sorting : " + l1 + "\n");
			funnySelectionSort(l1);
			System.out.println("\n Afrer  sorting : " + l1);
			break;

		case 7:
			userInput(l1);
			result = isPalindrome(l1);
			if(result == true) {
				System.out.println("\n List is a plaindrome!");
			}
			else {
				System.out.println("\n List is not a plaindrome!");
			}
			break;

		case 8:
			userInput(l1);
			userInput(l2);
			l3 = uniqueElements(l1, l2);
			System.out.println("\n New list is: " + l3);
			break;

		case 9:
			userInput(l1);
			userInput(l2);			
			Node ans = commonStoresFrom(l1, l2);
			if(ans == null) {
				System.out.println("\n No common store to begin with was found");
			}
			else {
				System.out.println("\n The couple met at store " + ans + " and since then did not separated");
			}
			break;

		case 10:
			System.out.print("\n --Enter the length one list at a time-- " + "\n");
			System.out.print("\n First  list: ");
			len_1 = input.nextInt();
			System.out.print("\n Second list: ");
			len_2 = input.nextInt();
			System.out.print("\n --Enter values-- " + "\n");
			System.out.println("\n First  list: ");
			addNonNegative(l1, len_1);
			System.out.println("\n Second list: ");
			addNonNegative(l2, len_2);
			int answer = commonRoutesFrom(l1, l2);
			if(answer == - 1) {
				System.out.print("\n Seems like the couple did not had the chance to meet at the mall");
			}
			else {
				System.out.print("\n The couple met at store " + answer + " and since then did not separated" );
			}
			System.out.println();
			break;

		default:
			System.out.println("An unknown error has occured.");
		}
	}

	// Quit Method Options

	public static void quit() {
		System.out.print("\n Are you sure you want to quit? (Y/N) ");
		String ans = "";
		while(!ans.equalsIgnoreCase("N")) {
			ans = input.next();

			if(ans.equalsIgnoreCase("Y")) {
				exit = true;
				System.out.println("\n Thank you for using our application." + "\n");
				break;
			}
			else if(ans.equalsIgnoreCase("N")) {
				System.out.println("\n Thank you for choosing to stay!" + "\n");
			}
			else {
				System.out.println("\n Invalid answer!, please type only (Y/N)" + "\n");
			}
		}
	}

	// Add Values

	public static void addValues(LinkedList l, int len) throws InputMismatchException {
		while(!l.isEmpty()) {
			l.removeFirst();
		}

		int i = 0;

		while(i != len) {
			try {		
				int in = input.nextInt();
				l.addFirst(in);
				i++;
			} 
			catch(InputMismatchException e) {
				System.out.println("\n Your choise should be only numbers, please try again");
				input.next();
			}
		}
	}

	// add Non Negative Values Method

	public static void addNonNegative(LinkedList l, int len) throws InputMismatchException {
		while(!l.isEmpty()) {
			l.removeFirst();
		}

		int i = 0;

		while(i != len) {
			try {
				int in = input.nextInt();
				if (in >= 0) {
					l.addFirst(in);
					i++;
				}
				else {
					System.out.println("\n Enter only a positive value!");
				}
			}
			catch(InputMismatchException e) {
				System.out.println("\n Your choise should be only numbers, please try again");
				input.next();
			}
		}
	}

	// Print Reverse Method

	public static void printReverse(LinkedList l) {
		System.out.println("\n Before reversing : " + l + "\n");
		l.reverseLinkedList();
		System.out.println("\n Afrer  reversing : " + l);
	}

	// Print Value From The End 

	public static void printValue(LinkedList l) {
		System.out.print("\n Enter Index from the end: ");
		int N = input.nextInt();

		N = fromTheEnd(l, N);

		System.out.println("\n The value of the index you entered is: " + N);
	}

	// Print selection sort

	public static void printSelectionSort(LinkedList l) {
		System.out.println("\n Before sorting : " + l + "\n");
		selectionSort(l);
		System.out.println("\n Afrer  sorting : " + l);
	}

	// User Input

	public static void userInput(LinkedList l) throws InputMismatchException {
		int count = 0;
		while(count != 1)
			try {
				System.out.print("\n Enter lenght: ");
				lenght = input.nextInt();
				if(lenght > 0) {
					count ++;
					System.out.print("\n Enter values: " + "\n");
					addValues(l, lenght);
					System.out.println();
				}
				else {
					System.out.println("\n List size must be greater than 0!");
				}
			}
		catch(InputMismatchException e){
			System.out.println("Your choise should be only numbers, please try again");
			input.next();
		}
	}

	// Main

	public static void main(String[] args){

		printHeadr();
		runMenu();	
	}
}