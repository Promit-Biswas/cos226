/****************************************************************************
  *  PercolationStatsSlow.java
  *  PURPOSE
  *  HOW TO EXECUTE
  *  ADD COMMENTS
  * 
  *  NAME: Akshay Kumar
  *  NETID: aktwo
  *  PRECEPT: P02
  *  DATE: 9/18/12
  *
  ****************************************************************************/

import java.util.Random;

public class PercolationStatsSlow
{
    private double[] samples;
    private double T;
    
    public PercolationStatsSlow(int N, int T)
    {
        if (N <= 0) throw new java.lang.IllegalArgumentException("N must be greater than 0");
        if (T <= 0) throw new java.lang.IllegalArgumentException("T must be greater than 0");
        this.T = T;
        samples = new double[T];
        
        Stopwatch stopwatch = new Stopwatch();
        
        for (int i = 0; i < T; i++)
        {
            PercolationSlow p = new PercolationSlow(N);
            while(!p.percolates())
            {
                int a = StdRandom.uniform(N);
                int b = StdRandom.uniform(N);
                if(!p.isOpen(a,b))
                {
                    p.open(a,b);
                    samples[i]++;
                }
            }
            
            samples[i] = samples[i]/(N*N);
            
        }
        
        System.out.println("Running Time: " + stopwatch.elapsedTime());
        
    }
    
    public double mean()
    {
        return StdStats.mean(samples);
    }
    
    public double stddev()
    {
        if (T == 1) return Double.NaN;
        else return StdStats.stddev(samples);
    }
    
    public static void main(String[] args)
    {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStatsSlow p = new PercolationStatsSlow(N,T);
        System.out.println("Mean = " + p.mean());
        System.out.println("Standard Deviation = " + p.stddev());
        System.out.println("Confidence Interval = " + (p.mean()-((1.96*p.stddev())/Math.sqrt(T))) + ", " + (p.mean()+((1.96*p.stddev())/Math.sqrt(T))));
        
    }
    
}