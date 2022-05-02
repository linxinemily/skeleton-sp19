package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] sites;
    private boolean[][] sites_open;
    private WeightedQuickUnionUF set;
    private int number_of_open_sites;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("integer might be greater than 0.");
        }
        int[][] arr = new int[N][N];
        boolean[][] arr_open = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = i * N + (j+1);
                arr_open[i][j] = false;
            }
        }
        sites = arr;
        sites_open = arr_open;
        number_of_open_sites = 0;

        set = new WeightedQuickUnionUF(N*N+2); // add top, bottom virtual sites
        for (int k = 0; k < N; k++) {
            // link first row to virtual site N+1
            set.union(xyTo1D(0, k), N + 1);
            // link last row to virtual site N+2
            set.union(xyTo1D(N - 1, k), N + 2);
        }
    }

    private int xyTo1D(int row, int col) {
        return sites[row][col];
    }

    public void open(int row, int col) {
        if (row < 0 || col < 0 || row >= sites.length || col >= sites.length) {
            throw new IndexOutOfBoundsException();
        }

        if (isOpen(row, col)) return;

        sites_open[row][col] = true;
        number_of_open_sites += 1;
        // check if there are open site besides top, right, bottom, left, if true, connect them
        // top
        if (isOpen(row-1, col)) {
            set.union(xyTo1D(row, col), xyTo1D(row-1, col));
        }
        // bottom
        if (isOpen(row+1, col)) {
            set.union(xyTo1D(row, col), xyTo1D(row+1, col));
        }
        // right
        if (isOpen(row, col+1)) {
            set.union(xyTo1D(row, col), xyTo1D(row, col+1));
        }
        // left
        if (isOpen(row, col-1)) {
            set.union(xyTo1D(row, col), xyTo1D(row, col-1));
        }
    }

    public boolean isFull(int row, int col) {
        return set.find(xyTo1D(row, col)) == set.find(xyTo1D(0,0));
    }

    public boolean isOpen(int row, int col) {
        if (row < 0 || col < 0 || row >= sites.length || col >= sites.length) {
//            throw new IndexOutOfBoundsException();
            return false;
        }
        return sites_open[row][col];
    }

    public int numberOfOpenSites() {
        return number_of_open_sites;
    }

    public boolean percolates() {
        return set.find(xyTo1D(0,0)) == set.find(xyTo1D(sites.length - 1, 0));
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(5);
        p.open(3,4);
        p.open(2,4);
        p.open(2,2);
        p.open(2,3);
        p.open(0,2);
        p.open(1,2);
        boolean isFull = p.isFull(2,2);
        System.out.println(isFull);
//        p.open(4,4);
        System.out.println(p.percolates());
    }
}
