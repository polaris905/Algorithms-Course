/*
这个算法解决的问题是，假设有N个site，检验N中给定2个点是否连接，如果不连接，就连接并列出这两个点
其中N个site用数组id来定义，在初始化时，每个site设定一个对应的数字作为它现在的component，也就是序号为0-9的点，对应的component也是0-9
在component的意思是，如果site具有相同的component，那么他们就是彼此连接的
现在代码中find和union两个方法没有实现，如何实现，有不同种算法分别讨论
*/

public class UF {
  private int[] id; // access to component id (site indexed)
  private int count; // number of components
  
  public UF(int N) { // Initialize component id array.
    count = N;
    id = new int[N];
    
    for (int i = 0; i < N; i++)
      id[i] = i;
  }
  
  public int count() { 
    return count;
  }
  
  public boolean connected(int p, int q) {
    return find(p) == find(q);
  }
  
  /*
  以下是find和union方法的第一种算法，即Quick-Find，也就是给定site"p"，find立即就返回它所在的component，即int[p]的值
  union方法是通过比较p和q两个site的值，如果不相等，则把所有site中每个值等于p的值的site，赋值为q的值，实质上就是把与p同一类的site都变为与q同一类
  */
  public int find(int p) {
    return id[p];
  }
  
  public void union(int p, int q) { // Put p and q into the same component.
    int pID = find(p);
    int qID = find(q);
    // Nothing to do if p and q are already in the same component.
    if (pID == qID)
      return; 
    // Rename p’s component to q’s name.
    for (int i = 0; i < id.length; i++) 
      if (id[i] == pID)
        id[i] = qID;
    count--;
  }
    
  public static void main(String[] args) { // Solve dynamic connectivity problem on StdIn.
    int N = StdIn.readInt(); // Read number of sites.
    UF uf = new UF(N); // Initialize N components.
    
    while (!StdIn.isEmpty()) {
      int p = StdIn.readInt();
      int q = StdIn.readInt(); // Read pair to connect.
      
      if (uf.connected(p, q))
        continue; // Ignore if connected.
      
      uf.union(p, q); // Combine components
      StdOut.println(p + " " + q); // and print connection.
    }
    
    StdOut.println(uf.count() + " components");
  }
}
