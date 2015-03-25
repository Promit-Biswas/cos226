/****************************************************************************
  *  Percolation.java
  * 
  *  Creates a Percolation object that takes in one integer parameter N, 
  *  which refers to the size of the square grid that will be used. 
  *  Allows sites in the grid to be opened and determines whether or not
  *  percolation occurs.
  *  
  *  Execution: java Percolation
  *  Compilation: javac Percolation.java
  * 
  *  NAME: Akshay Kumar
  *  NETID: aktwo
  *  PRECEPT: P02
  *  DATE: 9/18/12
  *
  ****************************************************************************/

public class Percolation 
{
    private int N; // Percolation object size
    private WeightedQuickUnionUF sites; // 1D representation of the percolation grid
    private int topLocation; // array position of top "virtual" node
    private int bottomLocation; // array position of bottom "virtual" node
    
    //boolean array with false if the square is blocked and true if empty
    private boolean[][] openSites;
    
    // create N-by-N grid, with all sites blocked
    public Percolation(int N)
    {
        this.N = N;
        sites = new WeightedQuickUnionUF((N*N)+2);
        topLocation = N*N;
        bottomLocation = (N*N)+1;
        openSites = new boolean[N][N];
    }
    
    // open site (row i, column j) if it is not already open
    public void open(int i, int j)
    {
        if (!inGrid(i, j)) 
            throw new IndexOutOfBoundsException("indices " + i + "and" + j 
                                                + " must be between 0 and " 
                                                    + (N-1));
        
        openSites[i][j] = true;
        
        //if applicable, connect the up/down/left/right sites to the current site
        if (inUnit(i, j+1) && checkIfOpen(i, j+1)) 
            sites.union(convert(i, j), convert(i, j+1));
        if (inUnit(i, j-1) && checkIfOpen(i, j-1)) 
            sites.union(convert(i, j), convert(i, j-1));
        if (inUnit(i-1, j) && checkIfOpen(i-1, j)) 
            sites.union(convert(i, j), convert(i-1, j));
        if (inUnit(i+1, j) && checkIfOpen(i+1, j)) 
            sites.union(convert(i, j), convert(i+1, j));
    }
    
    //does the system percolate?
    public boolean percolates()
    {
        return sites.connected(topLocation, bottomLocation);
    }
    
    // is site (row i, column j) open?
    public boolean isOpen(int i, int j)
    {
        if (!inGrid(i, j)) 
            throw new IndexOutOfBoundsException("indices " + i + " and " + j 
                                                + " must be between 0 and " 
                                                    + (N-1));
        else return openSites[i][j];
    }
    
    // is site (row i, column j) full?
    public boolean isFull(int i, int j)
    {
        if (!inGrid(i, j)) 
            throw new IndexOutOfBoundsException("indices " + i + "and" + j
                                                + " must be between 0 and " 
                                                    + (N-1));
        
        return sites.connected(convert(i, j), topLocation);
    }
    
    //testing method
    public static void main(String[] args)
    {
        Percolation p = new Percolation(3);
        p.open(0, 0);
        System.out.println(p.isOpen(0, 0));
        System.out.println(p.isFull(0, 0));
        System.out.println(p.isOpen(1, 0));
        p.open(1, 0);
        System.out.println(p.isOpen(1, 0));
        System.out.println(p.isFull(1, 0));
        p.open(1, 1);
        p.open(2, 1);
        System.out.println(p.percolates());
        
        System.out.println("---");
        
        Percolation q = new Percolation(1);
        System.out.println(q.percolates());
        q.open(0, 0);
        System.out.println(q.isOpen(0, 0));
        System.out.println(q.percolates());
    }
    
    //helper method to check if a space is open (including redirection for
    //virtual nodes at the top and bottom). Note that this method assumes
    //that the virtual top and bottom node are "open".
    private boolean checkIfOpen(int i, int j)
    {
        if (!inUnit(i, j)) return false;
        else if ((inUnit(i, j)) && (!inGrid(i, j))) return true;
        else return openSites[i][j];
    }
    
    //helper method to determine whether a coordinate pair is in the grid
    private boolean inGrid(int i, int j)
    {
        if ((i < 0) || (i > N-1)) return false;
        else if ((j < 0) || (j > N-1)) return false;
        else return true;
    }
    
    //helper method to determine whether a coordinate pair is in the grid
    //or a reference to the virtual top or bottom node
    private boolean inUnit(int i, int j)
    {
        if ((i < -1) || (i > N)) return false;
        else if ((j < 0) || (j > N-1)) return false;
        else return true;
    }
    
    //helper method to convert a 2D coordinate into a 1D coordinate, 
    //with special handling of coordinates that are supposed to point to 
    //the virtual top or bottom node
    private int convert(int i, int j)
    {
        if (i == -1) return topLocation;
        else if (i == N) return bottomLocation;
        else return (i*(N))+j;
    }
}