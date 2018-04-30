import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        }
        int i = 0;
        for (String s : rq) {
            if (i < Integer.parseInt(args[0])) {
                i++;
                System.out.println(s);
            }
            else break;
        }
    }
}
