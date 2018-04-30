package week2;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a = (Item[]) new Object[1];
    private int N = 0;
    private Item[] result;
    private int M = 0;
    
    public RandomizedQueue() {}
    
    private void enlarge(int max) {
        Item[] temp = (Item[])new Object[max];
        for (int i = 0; i < N - M; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }
    
    private void decrease(int max) {
        Item[] temp = (Item[])new Object[max];
        int j = 0;
        for (int i = 0; i < N - M; i++) {
            while ((j < a.length) && (a[j] == null)) {
                j++;
            }
            temp[i] = a[j];
        }
        a = temp;
    }
    public boolean isEmpty() {
        return N - M == 0;
    }
    public int size() {
        return N - M;
    }
    public void enqueue(Item item) {
        if (N == a.length) enlarge(2 * a.length);
        N++;
        for (int i = 0; i < N; i++) {
            if (a[i] == null) {
                a[i] = item;
                break;
            }
        }
    }
    public Item dequeue() {
        if ((N - M) == 0) throw new java.util.NoSuchElementException();
        else {
            int randomIndex = StdRandom.uniform(N);
            if (a[randomIndex] != null) {
                Item item = a[randomIndex];
                a[randomIndex] = null;
                M++;
                if ((N - M) > 0 && (N - M) == a.length / 4) decrease(a.length / 2);
                return item;
            }
            else return dequeue();
        }
    }

    public Item sample() {
        if ((N - M) == 0) throw new java.util.NoSuchElementException();
        else {
            int randomIndex = StdRandom.uniform(N);
            if (a[randomIndex] != null)
                return a[randomIndex];
            else return sample();
        }
    }

    public Iterator<Item> iterator() {
        int i = N - M;
        result = (Item[]) new Object[i];
        for (Item e : a) {
            if (e != null) {
                result[--i] = e;
            }
        }
        StdRandom.shuffle(result);
        return new RQIterator();
    }
    
    private class RQIterator implements Iterator<Item> {
        private int i = N - M;
        
        public boolean hasNext() {
            return i > 0;
        }
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
        public Item next() {
            return result[--i];
        }
    }
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        rq.enqueue("aaa");
        rq.enqueue("bbb");
        System.out.println("delete " + rq.dequeue());
        System.out.println("delete " + rq.sample());
        for (String s : rq)
           System.out.println("left " +s);
    }
}
