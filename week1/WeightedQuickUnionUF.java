/*
这是Quick-Union的改进算法，在Quick-Union算法中，最糟糕的情况是N个site纵向排列成为一颗N个结点的树，这是因为在执行union时是嫁接顺序任意的
本算法声明了一个数组sz，用来记录每个component的大小，在连接时总是选取sz值小的连接到sz值大的，如此，树的深度为lgN
*/
// 这个还不是完整版

public class WeightedQuickUnionUF {
  private int[] id; // parent link (site indexed)
  private int[] sz; // size of component for roots (site indexed)
  private int count; // number of components
  
  public WeightedQuickUnionUF(int N) {
    count = N;
    id = new int[N];
    for (int i = 0; i < N; i++)
      id[i] = i;
    
    sz = new int[N];
    for (int i = 0; i < N; i++)
      sz[i] = 1;
  }
  
  public int count() {
    return count;
  }
  
  public boolean connected(int p, int q) {
    return find(p) == find(q);
  }
  
  private int find(int p) { // Follow links to find a root.
    while (p != id[p])
      p = id[p];
    return p;
  }
  
  public void union(int p, int q) {
    int i = find(p);
    int j = find(q);
    if (i == j) return;
    // Make smaller root point to larger one.
    if (sz[i] < sz[j]) {
      id[i] = j; sz[j] += sz[i];
    }
    else {
      id[j] = i; sz[i] += sz[j];
    }
    count--;
  }
}
