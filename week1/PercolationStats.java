import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE = 1.96;
    private double[] threshold;
    private int t;
    private double mean;
    private double stddev;
    
    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("Illegal constructor Argument");
        else if (n == 1) {
            threshold = new double[1];
            threshold[0] = 1.0;
        }
        else {
            t = trials;
            threshold = new double[t];
            Percolation perc;
            for (int i = 0; i < t; i++) {
                perc = new Percolation(n);
                while (!perc.percolates()) {
                    perc.open(StdRandom.uniform(1, n + 1), StdRandom.uniform(1, n + 1));
                }
                threshold[i] = perc.numberOfOpenSites() * 1.0 / (n * n);
            }
            mean = StdStats.mean(threshold);
            stddev = StdStats.stddev(threshold);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }
    
    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }
    
    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return (mean() - CONFIDENCE * stddev() / Math.sqrt(t));
    }
    
    // high endpoint of 95% confidence interval
    public double confidenceHi() { 
        return (mean() + CONFIDENCE * stddev() / Math.sqrt(t));
    }

    // test client (described below)
    public static void main(String[] args) { //test client
    }
}
