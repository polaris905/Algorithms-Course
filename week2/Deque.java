import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int N;
    
    private class Node {
        Item item;
        Node previous;
        Node next;
    }
    public Deque() {
    }
    public boolean isEmpty() {
        return N == 0;
    }
    public int size() {
        return N;
    }
    public void addFirst(Item item) {
        if (item == null) throw new java.lang.IllegalArgumentException();
        else {
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            if (isEmpty()) last = first;
            else 
            {
                oldfirst.previous = first;
                first.next = oldfirst;
            }
            oldfirst = null;
            N++;
        }
    }
    
    public void addLast(Item item) {
        if (item == null) throw new java.lang.IllegalArgumentException();
        else {
            Node oldlast = last;
            last = new Node();
            last.item = item;
            if (isEmpty()) first = last;
            else {
                oldlast.next = last;
                last.previous = oldlast;
            }
            oldlast = null;
            N++;
        }
    }
    
    public Item removeFirst() {
        if (N == 0)
            throw new java.util.NoSuchElementException();
        else {
            Item item = first.item;
            first = first.next;
            N--;
            if (isEmpty()) {
                last = null;
            }
            else first.previous = null;
            return item;
        }
    }
    public Item removeLast() {
        if (N == 0)
            throw new java.util.NoSuchElementException();
        else {      
            Item item = last.item;
            last = last.previous;
            N--;
            if (isEmpty()) {
                first = null;
            }
            else last.next = null;
            return item;
        }
    }
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }
    
    private class DequeIterator implements Iterator<Item> {
        private Node current = first;
        
        public boolean hasNext() {
            return current != null;
        }
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    
    public static void main(String[] args) {
        Deque<String> dq = new Deque<String>();
        dq.addLast("bbb");
        for (String s : dq)
            System.out.println(s + " is left");
        dq.addFirst("aaa");
        dq.addLast("ccc");
        dq.addLast("ddd");
        for (String s : dq)
            System.out.println(s + " is left");
    }
}
