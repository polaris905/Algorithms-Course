import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int sizes;
    private final WeightedQuickUnionUF uf1;
    private final WeightedQuickUnionUF uf2;
    private boolean[] isOpen;
    private int openSites;
    private int topSite;
    private int bottomSite;
    
    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("Illegal constructor Argument");
        else {
            sizes = n;
            isOpen = new boolean[n * n + 2];
            openSites = 0;
            topSite = 0;
            bottomSite = n * n + 1;
            for (int i = 1; i <= n * n; i++) {
                isOpen[i] = false;
            }
            uf1 = new WeightedQuickUnionUF(n * n + 2);
            uf2 = new WeightedQuickUnionUF(n * n + 1);
        }
    }
    
    private boolean isValid(int row, int col) {
        if ((row > 0 && row <= sizes) && (col > 0 && col <= sizes))
            return true;
        return false;
    }
    
    private int xyTo1D(int row, int col) {
        return (row - 1) * sizes + col;
    }
    
    private void unionNeibourgh(int row, int col) {
        int p = xyTo1D(row, col);
        if (p % sizes == 1) {
            if (isOpen(row, col + 1)) {
                uf1.union(p, xyTo1D(row, col + 1));
                uf2.union(p, xyTo1D(row, col + 1));
            }
        }
        else if (p % sizes == 0) {
            if (isOpen(row, col - 1)) {
                uf1.union(p, xyTo1D(row, col - 1));
                uf2.union(p, xyTo1D(row, col - 1));
            }
        }
        else {
            if (isOpen(row, col + 1)) {
                uf1.union(p, xyTo1D(row, col + 1));
                uf2.union(p, xyTo1D(row, col + 1));
            }
            if (isOpen(row, col - 1)) {
                uf1.union(p, xyTo1D(row, col - 1));
                uf2.union(p, xyTo1D(row, col - 1));
            }
        }
        if (p <= sizes) {
            uf1.union(p, topSite);
            uf2.union(p, topSite);
            if (isOpen(row + 1, col)) {
                uf1.union(p, xyTo1D(row + 1, col));
                uf2.union(p, xyTo1D(row + 1, col));
            }
        }
        else if (p > sizes * (sizes - 1)) {
            uf1.union(p, bottomSite);
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
                else {
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
