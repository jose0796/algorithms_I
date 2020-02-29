import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {


    private final WeightedQuickUnionUF grid;
    private boolean[][] sites;
    private int openSites;
    private final int size;

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        size = n;
        sites = new boolean[n + 1][n + 1];
        grid = new WeightedQuickUnionUF(n * n + 2);
        openSites = 0;

        initGrid();
    }

    private void initGrid() {
        for (int i = 1; i <= size; ++i) {
            grid.union(0, i);
        }
        for (int i = 1; i <= size; ++i) {
            grid.union(from2Dto1D(size + 1, 1), from2Dto1D(size, i));
        }
    }


    private int from2Dto1D(int row, int col) {
        return (row - 1) * size + col;
    }

    public void open(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || col > size)
            throw new IllegalArgumentException();
        if (!sites[row][col]) {

            openSites++;
            sites[row][col] = true;
            if (row > 1) {
                if (isOpen(row - 1, col))
                    grid.union(from2Dto1D(row - 1, col), from2Dto1D(row, col));
            }
            if (row < size) {
                if (isOpen(row + 1, col)) grid.union(from2Dto1D(row + 1, col), from2Dto1D(row, col));
            }
            if (col > 1) {
                if (isOpen(row, col - 1)) grid.union(from2Dto1D(row, col - 1), from2Dto1D(row, col));
            }
            if (col < size) {
                if (isOpen(row, col + 1)) grid.union(from2Dto1D(row, col + 1), from2Dto1D(row, col));
            }

        }


    }

    public boolean isOpen(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || col > size)
            throw new IllegalArgumentException();
        return sites[row][col];
    }

    public boolean isFull(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || col > size)
            throw new IllegalArgumentException();
        return isOpen(row, col) && grid.connected(0, from2Dto1D(row, col));
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return grid.connected(0, from2Dto1D(size + 1, 1));
    }

    public static void main(String[] args) {
//        Percolation percolation = new Percolation(4);
//
//        percolation.open(1, 1);
//        percolation.open(1, 2);
//        percolation.open(2, 2);
//        percolation.open(3, 2);
//        percolation.open(4, 2);
//        StdOut.println(percolation.percolates());
    }
}
