import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {

    private static final double CONFIDENCE = 1.96;
    private final int trials;
    private final double meanValue;
    private final double stddevValue;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        Percolation percolation;
        this.trials = trials;
        double[] m = new double[trials];

        for (int t = 0; t < trials; ++t) {
            percolation = new Percolation(n);
            while (true) {
                percolation.open(StdRandom.uniform(1, n + 1), StdRandom.uniform(1, n + 1));
                if (percolation.percolates()) {
                    break;
                }
            }

            m[t] = (double) percolation.numberOfOpenSites() / (double) (n * n);
        }
        meanValue = StdStats.mean(m);
        stddevValue = StdStats.stddev(m);
    }

    public double mean() {
        return meanValue;
    }

    public double stddev() {
        return stddevValue;
    }

    public double confidenceLo() {
        return mean() - ((CONFIDENCE * stddev()) / (Math.sqrt(trials)));
    }

    public double confidenceHi() {
        return mean() + ((CONFIDENCE * stddev()) / (Math.sqrt(trials)));
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(n, t);
        StdOut.println("mean    =       " + percolationStats.mean());
        StdOut.println("stddev  =       " + percolationStats.stddev());
        StdOut.println("95% confidence interval = [" + percolationStats.confidenceLo() + ",  " + percolationStats.confidenceHi() + "]");
    }

}
