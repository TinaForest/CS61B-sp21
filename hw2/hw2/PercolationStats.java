package hw2;

import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.introcs.StdRandom;

import java.util.ArrayList;

import static edu.princeton.cs.algs4.StdRandom.uniform;

public class PercolationStats {
    private double[] a;
    private double mean;
    private double stddev;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0)
            throw new IllegalArgumentException("N<=0||T<=0");
        double count;
        a = new double[T];
        for (int i = 0; i < T; i++) {
            count = 0;
            Percolation perc = pf.make(N);
            int row;
            int col;
            while (!perc.percolates()) {
                count++;
                // pick sites, open them,
                do {
                    row = uniform(N);
                    col = uniform(N);
                } while (perc.isOpen(row, col));
                perc.open(row, col);
            }
            a[i] = count / (N * N);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        mean = StdStats.mean(a);
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        stddev = StdStats.stddev(a);
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean - 1.96 * stddev / Math.sqrt(a.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean + 1.96 * stddev / Math.sqrt(a.length);
    }
    /*
    public static void main(String[] args){
        PercolationStats ps=new PercolationStats(20,20,new PercolationFactory());
        System.out.println(ps.mean());
        System.out.println(ps.stddev());
        System.out.println("["+ps.confidenceLow()+", "+ps.confidenceHigh()+"]");
    }
     */
}
