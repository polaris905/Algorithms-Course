import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int sizes;
    private boolean[] opened; 
    private int openSites;
    WeightedQuickUnionUF uf;
    
    public Percolation(int n) {  // create n-by-n grid, with all sites blocked             
       sizes = n;
       opened = new boolean[n * n];
       openSites = 0;
       uf = new WeightedQuickUnionUF(n * n);     
       for(int i = 1; i < n; i++) {
           uf.union(0, i);
       }      
       for(int i = 0; i < n * n; i++) {
           opened[i] = false;
       }
    }

    private int xyTo1D(int row, int col) {
        return (row - 1) * sizes + col - 1;
    }
    
    private void unionNeibourgh(int row, int col) {
        int p = xyTo1D(row,col);
        if(p % sizes == 0) {
            if(isOpen(row, col + 1)) uf.union(p, xyTo1D(row, col + 1));
        }
        else if(p % sizes == (sizes - 1)) {
            if(isOpen(row, col - 1)) uf.union(p, xyTo1D(row, col - 1));
        }
        else {
            if(isOpen(row, col + 1)) uf.union(p, xyTo1D(row, col + 1));
            if(isOpen(row, col - 1)) uf.union(p, xyTo1D(row, col - 1));
        }
        if(p < sizes) {
            if(isOpen(row + 1, col)) uf.union(p, xyTo1D(row + 1, col));
        }
        else if(p >= sizes * (sizes - 1)) {
            if(isOpen(row - 1, col)) uf.union(p, xyTo1D(row - 1, col));
        }
        else {
            if(isOpen(row + 1, col)) uf.union(p, xyTo1D(row + 1,col));
            if(isOpen(row - 1, col)) uf.union(p, xyTo1D(row - 1,col));
        }                                              
    }
    
    public void open(int row, int col) {   // open site (row, col) if it is not open already
        if (!opened[xyTo1D(row, col)]) {
            opened[xyTo1D(row, col)] = true; 
            openSites++;
            unionNeibourgh(row, col);
        }
    }
    
    public boolean isOpen(int row, int col) { // is site (row, col) open?
        return opened[xyTo1D(row, col)];
    }
    
    public boolean isFull(int row, int col) { // is site (row, col) full?
        return uf.connected(xyTo1D(row, col), 0);
    }
                     
    public int numberOfOpenSites() {      // number of open sites
        return openSites;
    }
    
    public boolean percolates() {             // does the system percolate?
        for(int i = 1; i <= sizes; i++)
        {
            if(isFull(sizes, i)){
                return true;
            }
        }
        return false;
    }
  
    public static void main(String[] args)  { // test client (optional)
        Percolation per = new Percolation(5);
        System.out.println(per.percolates());
    }
}
