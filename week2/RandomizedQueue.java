import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a = (Item[]) new Object[1];
    private int N = 0;
    private int M = 0;
    
    public RandomizedQueue() {
    }
    
    private void refresh() {
        if (M < N) {
            resize(N);
        }
        else resize(2 * N);
    }
    
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        int j = 0;
        for (int i = 0; i < M; i++) {
            while (j < a.length) {
                if (a[j] == null) j++;
                else break;
            }
            temp[i] = a[j++];
        }
        a = temp;
        N = M;
    }
  
    public boolean isEmpty() {
        return M == 0;
    }
    public int size() {
        return M;
    }
    public void enqueue(Item item) {
        if (item == null) throw new java.lang.IllegalArgumentException();
        else {
            if (N == a.length) refresh();
            a[N++] = item;
            M++;
        }
    }
    public Item dequeue() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        else {
            int randomIndex = StdRandom.uniform(M);
            Item item = a[randomIndex];
            a[randomIndex] = null;
            M--;
            refresh();
            return item;
        }
    }

    public Item sample() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        else {
            int randomIndex = StdRandom.uniform(M);
            return a[randomIndex];
        }
    }

    public Iterator<Item> iterator() {
        return new RQIterator();
    }
    
    private class RQIterator implements Iterator<Item> {   
        RQIterator() {
            if (M != a.length)
                resize(M);
            StdRandom.shuffle(a);
        }
        
        private int i = M;
        
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
        // Empty
    }
}
