package hw2;

import edu.princeton.cs.introcs.StdRandom;

public class PercolationStats {
    private int T;
    private double[] fractions;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        this.T = T;
        int allSites = N * N;
        fractions = new double[T];

        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            while(!p.percolates()) {
                p.open(StdRandom.uniform(N),StdRandom.uniform(N));
            }
            fractions[i] = (double) p.numberOfOpenSites() / allSites;
        }
    }

    // average of the fraction
    public double mean() {
        double sum = 0;
        for (int i = 0; i < T; i++) {
            sum += fractions[i];
        }
        return sum / T;
    }

    // standard deviation
    public double stddev() {
        double mean = mean();
        double all = 0;
        for (int i = 0; i < T; i++) {
            all += Math.pow(fractions[i] - mean, 2);
        }
        return Math.pow(all, 0.5);
    }

    public double confidenceLow() {
        return (mean() - 1.96 * stddev()) / Math.pow(T, 0.5);
    }

    public double confidenceHigh() {
        return (mean() + 1.96 * stddev()) / Math.pow(T, 0.5);
    }

//    public static void main(String[] args) {
//        PercolationStats s = new PercolationStats(20, 100, new PercolationFactory());
//        System.out.println(s.mean());
//    }
}
