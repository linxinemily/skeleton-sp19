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
                arr[i][j] = i * N + j;
                arr_open[i][j] = false;
            }
        }
        sites = arr;
        sites_open = arr_open;
        number_of_open_sites = 0;

        set = new WeightedQuickUnionUF(N*N+2); // add top, bottom virtual sites
        int virtual_top = N*N;
        int virtual_bottom = N*N +1;

        for (int k = 0; k < N; k++) {
            set.union(virtual_top, xyTo1D(0, k));
            set.union(virtual_bottom, xyTo1D(N - 1, k));
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
        return isOpen(row, col) && set.find(xyTo1D(row, col)) == set.find(xyTo1D(0,0));
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
        // when N is 1
        if (xyTo1D(0,0) == xyTo1D(sites.length - 1, 0)) {
            return isOpen(0, 0);
        }

        return set.find(xyTo1D(0,0)) == set.find(xyTo1D(sites.length - 1, 0));
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(1);

//        p.open(0, 2);
//        p.open(1, 2);
//        p.open(2, 2);
//        p.open(2, 0);
//
//        System.out.println(p.isFull(2, 0) );
//        p.open(1, 0);
//        p.open(0, 0);
//        p.open(0, 5);
//        p.open(1, 5);
//        p.open(2, 5);
//        p.open(3, 5);
//        p.open(4, 5);
//        p.open(4, 4);
//        p.open(3, 3);
//        p.open(2, 3);
//        p.open(1, 3);
//        p.open(1, 2);
//        p.open(1, 1);
//        p.open(1, 0);
//        p.open(2,4);
//        p.open(2,2);
//        p.open(2,3);
//        p.open(0,2);
//        p.open(1,2);
//        boolean isFull = p.isFull(0,0);
//        System.out.println(isFull);
//        p.open(0,0);
        System.out.println(p.percolates());
    }
}
