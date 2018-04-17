//第一种算法
public class QuickFindUF {
  private int[] id;
  
  //初始化数组
  public QuickFindUF(int N) {
    id = new int[N];
    for(int i = 0; i < N; i++) {
      id[i] = i;
    }
  }
  
  //检验p和q是否连接
  public boolean connected(int p, int q) {
    return id[p] == id[q];
  }
  
  //连接p和q，实际上就是把每个等于p的值得元素重新赋值为q的值
  public void union(int p, int q) {
    int pid = id[p];
    int qid = id[q];
    for(int i = 0; i < id.length; i++) {
      if(id[i] == pid) {
        id[i] = qid;
      }
    }
  }
}

//第二种算法
public class QuickUnionUF {
	private int[] id;

	public QuickUnionUF(int N) {
		id = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
	}

	private int root(int i) {
		while(i != id[i]) {
			i = id[i];
		}
		return i;
	}

	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}

	public void union(int p, int q) {
		int i = root(p);
		int j = root(q);
		id[i] = j;
	}
}
