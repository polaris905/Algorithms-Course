import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a = (Item[]) new Object[1];
    private int N = 0;
    
    public RandomizedQueue() {
        
    }
    
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        int j = 0;
        for (int i = 0; i < N; i++) {
            while (j < a.length) {
                if (a[j] == null) j++;
                else break;
            }
            temp[i] = a[j++];
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
            a[randomIndex] = null;
            N--;
            if (N > 0 && N == a.length / 4) 
                resize(a.length / 2);
            else resize(a.length);
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
        RQIterator() {
            if(N < a.length) resize(N);
            StdRandom.shuffle(a);
        }
        
        private int i = N;
        
        public boolean hasNext() {
            return i > 0;
        }
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
        public Item next() {
            if (i == 0) throw new java.util.NoSuchElementException();
            else return a[--i];
        }
    }
    public static void main(String[] args) {
        
    }
}
