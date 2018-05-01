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
        if (N == a.length) refresh();
        a[N++] = item;
        M++;
    }
    public Item dequeue() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        else {
            int randomIndex = StdRandom.uniform(N);
            while (a[randomIndex] == null)
                randomIndex = StdRandom.uniform(N);
            Item item = a[randomIndex];
            a[randomIndex] = null;
            M--;
            if (M > 0 && M == a.length / 4) refresh();
            return item;
        }
    }

    public Item sample() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        else {
            int randomIndex = StdRandom.uniform(a.length);
            if (a[randomIndex] != null)
                return a[randomIndex];
            else return sample();
        }
    }

    public Iterator<Item> iterator() {
        if (M != a.length)
            resize(M);
        StdRandom.shuffle(a);
        return new RQIterator();
    }
    
    private class RQIterator implements Iterator<Item> {
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
