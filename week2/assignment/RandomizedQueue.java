import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a = (Item[]) new Object[1];
    private int N = 0;
    
    public RandomizedQueue() {
        
    }
    
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }
  
    public boolean isEmpty() {
        return N == 0;
    }
    
    public int size() {
        return N;
    }
    
    public void enqueue(Item item) {
        if (item == null) throw new java.lang.IllegalArgumentException();
        else {
            if (N == a.length) resize(a.length * 2);
            a[N++] = item;
        }
    }
    
    public Item dequeue() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        else {
            int randomIndex = StdRandom.uniform(N);
            Item item = a[randomIndex];
            a[randomIndex] = a[N-1];
            a[N-1] = null;
            N--;
            if (N > 0 && N == a.length / 4) 
                resize(a.length / 2);
            return item;
        }
    }

    public Item sample() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        else {
            int randomIndex = StdRandom.uniform(N);
            return a[randomIndex];
        }
    }

    public Iterator<Item> iterator() {
        return new RQIterator();
    }
    
    private class RQIterator implements Iterator<Item> {   
        private Item temp;
        private int size = N;
        private Item[] result;
        
        RQIterator() {
            result = (Item[]) new Object[N];
            for (int i = 0; i < size; i++)
                result[i] = a[i];
        }
        
        public boolean hasNext() {
            return size > 0;
        }
        
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
        
        public Item next() {
            if (size == 0) throw new java.util.NoSuchElementException();
            else {
                int randomIndex = StdRandom.uniform(size);
                temp = result[randomIndex];
                result[randomIndex] = result[--size];
                result[size] = temp;
                return result[size];
            }
        }
    }
    
    public static void main(String[] args) {
        
    }
}
