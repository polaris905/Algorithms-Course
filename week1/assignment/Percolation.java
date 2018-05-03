import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int sizes; // 代表矩阵的边数
    private final WeightedQuickUnionUF uf1; // 第一个UF对象，用于判断percolate
    private final WeightedQuickUnionUF uf2; // 第二个UF对象，用于判断isFull
    private boolean[] isOpen; // 记录isOpen
    private int openSites;
    private int topSite;
    private int bottomSite;
    
    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("Illegal constructor Argument");
        else {
            sizes = n;
            isOpen = new boolean[n * n + 2]; // 初始化数组，数组长度为矩阵全部实体点，加上2个虚拟点，标记是否为open
            openSites = 0;
            topSite = 0; // 顶端虚拟点的值为0
            bottomSite = n * n + 1; // 底端虚拟点的值为矩阵实体点数加1
            for (int i = 1; i <= n * n; i++) {
                isOpen[i] = false; // 遍历所有数组中的所有实体点，把open状态标记为false
            }
            uf1 = new WeightedQuickUnionUF(n * n + 2); // 第一个uf，数量为全部实体点加2个虚拟点
            uf2 = new WeightedQuickUnionUF(n * n + 1); // 第二个uf，数量为全部实体点加1个虚拟点（顶端虚拟点）
        }
    }
    
    private boolean isValid(int row, int col) {
        if ((row > 0 && row <= sizes) && (col > 0 && col <= sizes))
            return true;
        return false;
    }
    
    private int xyTo1D(int row, int col) {
        return (row - 1) * sizes + col; // 矩阵中个点二维坐标转换为从1开始的对应的序号
    }
    
    private void unionNeibourgh(int row, int col) {
        int p = xyTo1D(row, col);
        if (p % sizes == 1) { // 如果当前点位于矩阵的第一列
            if (isOpen(row, col + 1)) { // 如果当前点右边相邻的点的状态是open
                uf1.union(p, xyTo1D(row, col + 1)); // 在uf1中连接当前点和右边相邻点
                uf2.union(p, xyTo1D(row, col + 1)); // 在uf2中连接当前点和右边相邻点
            }
        }
        else if (p % sizes == 0) { // 如果当前点位于矩阵的最后一列
            if (isOpen(row, col - 1)) {
                uf1.union(p, xyTo1D(row, col - 1));
                uf2.union(p, xyTo1D(row, col - 1));
            }
        }
        else { // 如果当前点位于矩阵非两端的列
            if (isOpen(row, col + 1)) {
                uf1.union(p, xyTo1D(row, col + 1));
                uf2.union(p, xyTo1D(row, col + 1));
            }
            if (isOpen(row, col - 1)) {
                uf1.union(p, xyTo1D(row, col - 1));
                uf2.union(p, xyTo1D(row, col - 1));
            }
        }
        if (p <= sizes) { // 当前点位于矩阵的第一行
            uf1.union(p, topSite); // 在uf1中连接当前点与顶端虚拟点
            uf2.union(p, topSite); // 在uf2中连接当前点与顶端虚拟点
            if (isOpen(row + 1, col)) {
                uf1.union(p, xyTo1D(row + 1, col));
                uf2.union(p, xyTo1D(row + 1, col));
            }
        }
        else if (p > sizes * (sizes - 1)) { // 当前点位于矩阵的最后一行
            uf1.union(p, bottomSite); // 在uf1中连接当前点与底端虚拟点，连接的目的在于可以通过判断两个虚拟点确定是否percolate
            if (isOpen(row - 1, col)) {
                uf1.union(p, xyTo1D(row - 1, col));
                uf2.union(p, xyTo1D(row - 1, col));
            }
        }
        else {
            if (isOpen(row + 1, col)) {
                uf1.union(p, xyTo1D(row + 1, col));
                uf2.union(p, xyTo1D(row + 1, col));
            }
            if (isOpen(row - 1, col)) {
                uf1.union(p, xyTo1D(row - 1, col));
                uf2.union(p, xyTo1D(row - 1, col));
            }
        }
    }
    
    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isValid(row, col)) {
            if (!isOpen[xyTo1D(row, col)]) {
                openSites++;
                isOpen[xyTo1D(row, col)] = true;
                if (sizes != 1) unionNeibourgh(row, col);
                else { // 如果矩阵大小为1，打开唯一的实体点时，uf1中两个虚拟点连接，uf2中实体点与顶端虚拟点连接
                    uf1.union(bottomSite, topSite);
                    uf2.union(1, topSite);
                }
            }
        }
        else throw new IllegalArgumentException("Illegal Argument"); 
    }
    
    public boolean isOpen(int row, int col) { // is site (row, col) open?
        if (isValid(row, col)) return isOpen[xyTo1D(row, col)];
        else throw new IllegalArgumentException("Illegal Argument");   
    }
    
    public boolean isFull(int row, int col) { // is site (row, col) full?
        if (isValid(row, col)) {
            if (isOpen(row, col))
                return uf2.connected(xyTo1D(row, col), topSite);
            else return false;
        }
        else throw new IllegalArgumentException("Illegal Argument"); 
    }
                     
    public int numberOfOpenSites() { // number of open sites
        return openSites;
    }
    
    public boolean percolates() { // does the system percolate?
        return uf1.connected(topSite, bottomSite);
    }
  
    public static void main(String[] args)  { // test client (optional)
    }
}
