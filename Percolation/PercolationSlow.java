/****************************************************************************
  *  Percolation.java
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

public class PercolationSlow 
{
    private int N; // Percolation object size
    private QuickUnionUF sites; // 1D representation of the percolation grid
    private int topLocation; // array position of top "virtual" node
    private int bottomLocation; // array position of bottom "virtual" node
    private boolean[][] openSites; // boolean array with false if the square is blocked and true if empty
    
    public static void main(String[] args)
    {
        Percolation p = new Percolation(3);
        p.open(0,0);
        System.out.println(p.isOpen(0,0));
        System.out.println(p.isFull(0,0));
        System.out.println(p.isOpen(1,0));
        p.open(1,0);
        System.out.println(p.isOpen(1,0));
        System.out.println(p.isFull(1,0));
        p.open(1,1);
        p.open(2,1);
        System.out.println(p.percolates());
    }
    
    // create N-by-N grid, with all sites blocked
    public PercolationSlow(int N)
    {
        this.N = N;
        sites = new QuickUnionUF((N*N)+2);
        topLocation = N*N;
        bottomLocation = (N*N)+1;
        openSites = new boolean[N][N];
    }
    
    public void open(int i, int j)
    {
        if (!inGrid(i,j)) throw new IndexOutOfBoundsException("indices " + i + "and" + j + " must be between 0 and " + (N-1));
        
        openSites[i][j] = true;
        
        if (inUnit(i,j+1) && checkIfOpen(i,j+1)) sites.union(convert(i,j),convert(i,j+1));
        if (inUnit(i,j-1) && checkIfOpen(i,j-1)) sites.union(convert(i,j),convert(i,j-1));
        if (inUnit(i-1,j) && checkIfOpen(i-1,j)) sites.union(convert(i,j),convert(i-1,j));
        if (inUnit(i+1,j) && checkIfOpen(i+1,j)) sites.union(convert(i,j),convert(i+1,j));
    }
    
    public boolean percolates()
    {
        return sites.connected(topLocation, bottomLocation);
    }
    
    public boolean isOpen(int i, int j)
    {
        if (!inGrid(i,j)) throw new IndexOutOfBoundsException("indices " + i + " and " + j + " must be between 0 and " + (N-1));
        else return openSites[i][j];
    }
    
    public boolean isFull(int i, int j)
    {
        if (!inGrid(i,j)) throw new IndexOutOfBoundsException("indices " + i + "and" + j + " must be between 0 and " + (N-1));
        
        return sites.connected(convert(i,j),topLocation);
    }
    
    private boolean checkIfOpen(int i, int j)
    {
        if (!inUnit(i,j)) return false;
        else if ((inUnit(i,j)) && (!inGrid(i,j))) return true;
        else return openSites[i][j];
    }
    
    private boolean inGrid(int i, int j)
    {
        if ((i < 0) || (i > N-1)) return false;
        if ((j < 0) || (j > N-1)) return false;
        else return true;
    }
    
    private boolean inUnit(int i, int j)
    {
        if ((i < -1) || (i > N)) return false;
        if ((j < 0) || (j > N-1)) return false;
        else return true;
    }
    
    private int convert(int i, int j)
    {
        if(i == -1) return topLocation;
        else if(i == N) return bottomLocation;
        else return (i*(N))+j;
    }
}
