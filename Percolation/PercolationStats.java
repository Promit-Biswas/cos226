/****************************************************************************
  *  PercolationStats.java
  *  
  *  This program uses the Percolation data type to run a Monte Carlo 
  *  simulation and experimentally determine the percolation threshold for
  *  a square grid of size N. The number of trials is the second parameter
  *  of this class.
  *  
  *  Execution: java PercolationStats 100 100
  *  Compilation: javac PercolationStats.java
  * 
  *  NAME: Akshay Kumar
  *  NETID: aktwo
  *  PRECEPT: P02
  *  DATE: 9/18/12
  *
  ****************************************************************************/

public class PercolationStats
{
    private double[] samples; //data value to store the result of each trial
    private double T; //number of trials
    
    //runs T Monte Carlo simulations on a Percolation object of size N
    //and stores the results in the samples[] array
    public PercolationStats(int N, int T)
    {
        if (N <= 0) 
            throw new java.lang.IllegalArgumentException("N must be greater than 0");
        if (T <= 0) 
            throw new java.lang.IllegalArgumentException("T must be greater than 0");
        
        samples = new double[T];
        this.T = T;
        
        for (int i = 0; i < T; i++)
        {
            Percolation p = new Percolation(N);
            while (!p.percolates())
            {
                int a = StdRandom.uniform(N);
                int b = StdRandom.uniform(N);
                if (!p.isOpen(a, b))
                {
                    p.open(a, b);
                    samples[i]++;
                }
            }
            samples[i] = samples[i]/(N*N);
        }
    }
    
    //returns the mean of the sample values
    public double mean()
    {
        return StdStats.mean(samples);
    }
    
    //returns the standard deviation of the sample values
    public double stddev()
    {
        if (T == 1) return Double.NaN;
        else return StdStats.stddev(samples);
    }
    
    //takes in command line arguments and prints out the mean and
    //standard deviation of the simulation
    public static void main(String[] args)
    {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats p = new PercolationStats(N, T);
        System.out.println("Mean = " + p.mean());
        System.out.println("Standard Deviation = " + p.stddev());
        System.out.println("95% Confidence Interval = "
                           + (p.mean()-((1.96*p.stddev())/Math.sqrt(T))) + ", "
                           + (p.mean()+((1.96*p.stddev())/Math.sqrt(T))));
        
    }
    
}