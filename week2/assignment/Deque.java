import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int N;
    
    private class Node {
        Item item;
        // 本题要求双向连接，所以要声明一个Node变量指向前一个Node
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
            // 帮助垃圾回收站回收没用的对象
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
            // 帮助垃圾回收站回收没用的对象
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
            // 此代码的作用在于断掉first.previous指向对象的所有引用
            // 倘若去掉此代码，假如后续执行remove系列方法特别是removeLast时，会出现删掉的对象实际上还存在于链表中的问题
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
            // 此代码的作用在于断掉first.previous指向对象的所有引用
            // 倘若去掉此代码，假如后续执行remove系列方法特别是removeLast时，会出现删掉的对象实际上还存在于链表中的问题
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
            if (current == null)
                 throw new java.util.NoSuchElementException();
            else {
                Item item = current.item;
                current = current.next;
                return item;
            }
        }
    }
    
    public static void main(String[] args) {
        Deque<String> dq = new Deque<String>();
        dq.addLast("bbb");
        for (String s : dq)
            System.out.println("The first output: " + s + " is left");
        dq.addFirst("aaa");
        dq.addLast("ccc");
        dq.addLast("ddd");
        dq.removeLast();
        for (String s : dq)
            System.out.println("The second output: " + s + " is left");
    }
}
