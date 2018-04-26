public class LinkedStackOfStrings
{
	private Node first = null;

	public class Node
	{
		String item;
		Node next;
	}

	public boolean isEmpty()
	{
		return first == null;
	}

	public void push(String item)
	{
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
	}

	public String pop()
	{
		String item = first.item;
		first = first.next;
		return item;
	}

	public static void main(String[] args)
	{
		LinkedStackOfStrings LS = new LinkedStackOfStrings();
		System.out.println("Is it empty? " + LS.isEmpty());
		LS.push("ABC");
		LS.push("BCD");
		LS.push("EFG");
		System.out.println("Is it empty? " + LS.isEmpty());
		System.out.println("The first item is " + LS.first.item);
		System.out.println("THe second item is " + LS.first.next.item);
		LS.pop();
		System.out.println("Is it empty? " + LS.isEmpty());
	}
}
