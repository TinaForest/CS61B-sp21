package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufBackwash;
    private boolean[][] grid;
    private int count;
    private int virtualTop;
    private int virtualBot;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N<=0");
        }
        this.N = N;
        uf = new WeightedQuickUnionUF(N * N + 2);
        ufBackwash = new WeightedQuickUnionUF(N * N + 1);
        virtualTop = N * N;
        for (int i = 0; i < N; i++) {
            uf.union(virtualTop, i);
            ufBackwash.union(virtualTop, i);
        }
        virtualBot = N * N + 1;
        for (int i = 0; i < N; i++) {
            uf.union(virtualBot, N * (N - 1) + i);
        }
        grid = new boolean[N][N];
        count = 0;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isOpen(row, col)) {
            return;
        }
        int index = xyTo1D(row, col);
        grid[row][col] = true;
        count++;
        // if there is open sites around, then union this site with that.
        if (row - 1 >= 0 && isOpen(row - 1, col)) {
            uf.union(index, xyTo1D(row - 1, col));
            ufBackwash.union(index, xyTo1D(row - 1, col));
        }
        if (row + 1 < N && isOpen(row + 1, col)) {
            uf.union(index, xyTo1D(row + 1, col));
            ufBackwash.union(index, xyTo1D(row + 1, col));
        }
        if (col - 1 >= 0 && isOpen(row, col - 1)) {
            uf.union(index, xyTo1D(row, col - 1));
            ufBackwash.union(index, xyTo1D(row, col - 1));
        }
        if (col + 1 < N && isOpen(row, col + 1)) {
            uf.union(index, xyTo1D(row, col + 1));
            ufBackwash.union(index, xyTo1D(row, col + 1));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!isOpen(row, col)) {
            return false;
        }
        int index = xyTo1D(row, col);
        if (row != 0) {
            return ufBackwash.connected(virtualTop, index);
        }
        return true;
    }

    // does the system percolate?
    public boolean percolates() {
        if (N == 1) {
            return isOpen(0, 0);
        } else if (N == 2) {
            if (uf.connected(0, 2) || uf.connected(1, 3)) {
                return true;
            }
            return false;
        } else {
            return uf.connected(virtualTop, virtualBot);
        }
    }

    // number of open sites
    public int numberOfOpenSites() {
        return count;
    }

    private int xyTo1D(int r, int c) {
        return r * N + c;
    }

    private void validate(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }
    }

    // use for unit testing (not required)}
    public static void main(String[] args) {
        return;
    }
}
