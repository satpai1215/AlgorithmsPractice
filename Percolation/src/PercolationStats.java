public class PercolationStats {
	
	private double avg;
	private double stdev;
	private int T;
	
   public PercolationStats(int N, int T) {
	   // perform T independent computational experiments on an N-by-N grid
	   this.T= T;

	   int openSites;
	   double[] p = new double[T];
	   Percolation perc;
	   
	   for (int i = 0; i < T; i++) {
		   perc = new Percolation(N);
		   openSites = 0;
		   
		   while (!perc.percolates()) {
			   int randI = StdRandom.uniform(1, N+1);
			   int randJ = StdRandom.uniform(1, N+1);
			   
			   if(!perc.isOpen(randI, randJ)) {
				   perc.open(randI, randJ);
				   openSites++;   
			   }
		   }
		   
		  p[i] = (double)openSites/(N*N);
		 System.out.print(openSites + "\n");
	   }
	   
	   avg = StdStats.mean(p);
	   stdev = StdStats.stddev(p);	     
	   
   }
   
   public double mean() {
	   // sample mean of percolation threshold
	   return avg;
   }
   
   public double stddev() {
	   // sample standard deviation of percolation threshold
	   if (T == 1) return Double.NaN;
	   else return stdev;
   }
   
   public double confidenceLo() {
	   // returns lower bound of the 95% confidence interval
	   return (avg - (1.96*stdev)/Math.sqrt(T));
   }
   
   public double confidenceHi() {
	   // returns upper bound of the 95% confidence interval
	   return (avg + (1.96*stdev)/Math.sqrt(T));
   }
   
   public static void main(String[] args) {
	   if (args.length != 2) throw new IndexOutOfBoundsException("2 arguments must be added");
	   
	   int N = Integer.parseInt(args[0]);
	   int T = Integer.parseInt(args[1]);
	   
	   if (N <=0 || T<= 0) throw new java.lang.IllegalArgumentException("N and T must be greater than 0");
	   
	   PercolationStats run = new PercolationStats(N, T);
	   System.out.print("mean = " + run.mean() + "\n");
	   System.out.print("stdev = " + run.stddev() + "\n");
	   System.out.print("95% confidence interval = " + run.confidenceLo() + ", " + run.confidenceHi());
   }
}
