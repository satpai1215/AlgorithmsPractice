
public class Percolation {
	private int gridLength;
	private WeightedQuickUnionUF UFgrid;
	private boolean[][] grid;
	
	private int xyto1D(int i, int j) {
		//subtract 1 from i to count all rows before current
		//add j to get to current site in next row
		return gridLength*(i - 1) + j;
	}
	
	private void validateIndices(int i, int j) {
		
		if (i <= 0 || i > gridLength) throw new IndexOutOfBoundsException("row index i out of bounds");
		if (j <= 0 || j > gridLength) throw new IndexOutOfBoundsException("column index j out of bounds");
		//System.out.print(i + " " + j + "\n");
	}
	
	public Percolation(int N) {
		// create N-by-N grid, with all sites blocked (set to false)
		
		//initialize class variables
		UFgrid = new WeightedQuickUnionUF(N*N + 2); // add two additional nodes for virtual top (0) and bottom (N^2) sites
		gridLength = N;
		grid = new boolean[N][N];
	}
	
	public void open(int i, int j) {
		// open site (row i, column j) if it is not already
		
		//step 1: check that indices are valid
		validateIndices(i,j);
		
		int current = xyto1D(i, j);
		int up = xyto1D(i-1, j);
		int down = xyto1D(i+1, j);
		int left = xyto1D(i, j-1);
		int right = xyto1D(i, j+1);
		
		//step 2: mark site as open
		grid[i-1][j-1] = true;
		
		//step 3: link current site to open neighbors, or virtual top/bottom sites if at top/bottom row
		//neighbors = (i+/-1, j) and (i, j +/- 1), unless at edge
		
		if(i == 1) UFgrid.union(current, 0); //connect to virtual top
		
		if ((i+1) <= gridLength && isOpen(i+1, j)) UFgrid.union(down, current);
		if ((i-1) > 0 && isOpen(i-1, j)) UFgrid.union(up, current);
		if ((j+1) <= gridLength && isOpen(i, j+1)) UFgrid.union(right, current);
		if ((j-1) > 0 && isOpen(i, j-1)) UFgrid.union(left, current);
		
		//checks if on bottom row and is connected to virtual top, if so connect to virtual bottom  => solves backwash
		if(i == gridLength && isFull(i, j)) UFgrid.union(current, (gridLength*gridLength));
		
	}
	public boolean isOpen(int i, int j) {
		// is site (row i, column j) open?
		validateIndices(i,j);
		return grid[i-1][j-1];
	}
	public boolean isFull(int i, int j) {
		// is site (row i, column j) full?
		validateIndices(i,j);
		
		//check if site is connected to virtual top site
		return UFgrid.connected(xyto1D(i,j), 0);
	}
	public boolean percolates() {
		// does the system percolate?
		//check if virtual bottom site is connected to virtual top site.
		return UFgrid.connected(0,(gridLength*gridLength));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N = 4;
		Percolation P = new Percolation(N);
		for(int i = 1; i <= N; i++) {
			P.open(i, 1);
		}
		//System.out.println(P.UFgrid.connected(P.xyto1D(1,1), P.xyto1D(2,1)));
		System.out.print(P.percolates());
	}

}
