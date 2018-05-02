/*
此算法选择可变数组作为数据结构，dequeue方法通过随机生成一个小于数组当前大小的数字为序号的数组成员，将其设定为null，然后调用resize方法，
返回一个大小不变或者缩小的新数组，将原数组除了null以外的成员复制到新数组。此算法的最大缺陷在于每次调用dequeue方法时都要对数组进行复制，
执行成本比较大。目前正在考虑的是，可否运用数组和链表相结合的数据结构来实现这一算法。
*/
    
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
            // 通过遍历原数组，把值为null的过滤掉
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
            // 根据当前数组大小和非null元素数量的关系，来判定在过滤数组null元素的同时是缩小数组大小还是不变
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
        // 次数运用构造函数是为了在执行iterator之前把数组中的null元素过滤掉，并将数组调整为最合适的大小，然后将数组排序随机打乱
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
