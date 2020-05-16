import java.util.EmptyStackException;

public class LinkedStack implements Stack
{	
	// Attributes

	private LinkedList stack;

	// Constructor

	public LinkedStack()
	{	
		stack = new LinkedList();
	}

	// Private Methods

	private void throwExceptionIfStackIsEmpty()
	{
		if (isEmpty())
			throw new EmptyStackException();
	}

	// Interface Methods

	@Override
	public void push(int n)
	{

		stack.addFirst(n);
	}

	@Override
	public int pop()
	{
		throwExceptionIfStackIsEmpty();

		int removedElement = stack.getHead().getValue();
		stack.removeFirst();
		return removedElement;
	}

	@Override
	public int peek()
	{
		throwExceptionIfStackIsEmpty();

		return stack.getHead().getValue();
	}

	@Override
	public boolean isEmpty() {

		return stack.isEmpty();
	}

	// toString

	public String toString()
	{
		String strToReturn = "";
		Node current = stack.getHead();

		while (current != null)
		{
			strToReturn += current.getValue();
			current = current.getNext();

			if (current != null)
			{
				strToReturn += "\n";
			}
		}

		return strToReturn;
	}
}