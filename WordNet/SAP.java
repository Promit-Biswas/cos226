/* Name: Akshay Kumar
 * netID: aktwo@
 * Precept: P02 (Diego Botero)
 * Assignment: 6 - WordNet
 * Description: Given a Digraph, this object computes the closest common 
 * ancestor of two vertices and the length between two vertices.
 * Compilation: javac SAP.java
 * Execution/Testing: java SAP <filename of graph>.txt
 * Dependencies: Digraph, BreadthFirstDirectedPaths
 */

public class SAP
{
    private static final int INFINITY = Integer.MAX_VALUE;
    private final Digraph G;
    
    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G)
    {
        this.G = new Digraph(G);
    }
    
    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w)
    {
        
        // check to see if v or w are out-of-bounds
        if ((v < 0 || v >= G.V()) && (w < 0 || w >= G.V()))
        {
            throw new IndexOutOfBoundsException();
        }
        
        // initialize BFS from v and w
        BreadthFirstDirectedPaths vbfs = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths wbfs = new BreadthFirstDirectedPaths(G, w);
        
        int bestDistance = INFINITY;
        int currentDistance = INFINITY;
        
        // calculate the distance to each vertex from v and w
        // and find the best distance
        for (int i = 0; i < G.V(); i++)
        {
            if ((vbfs.distTo(i) != INFINITY) && (wbfs.distTo(i) != INFINITY))
            {
                currentDistance = vbfs.distTo(i) + wbfs.distTo(i);
            }
            else { currentDistance = INFINITY; }
            
            if (currentDistance < bestDistance)
            {
                bestDistance = currentDistance;
            }
        }
        
        if (bestDistance == INFINITY) return -1;
        return bestDistance;
        
    }
    
    // a common ancestor of v and w that participates in a shortest 
    // ancestral path; -1 if no such path
    public int ancestor(int v, int w)
    {
        
        // check to see if v or w are out-of-bounds
        if ((v < 0 || v >= G.V()) && (w < 0 || w >= G.V()))
        {
            throw new IndexOutOfBoundsException();
        }
        
        // initialize BFS from v and w
        BreadthFirstDirectedPaths vbfs = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths wbfs = new BreadthFirstDirectedPaths(G, w);
        
        int bestDistance = INFINITY;
        int bestAncestor = INFINITY;
        int currentDistance = INFINITY;
        
        // calculate the distance to each vertex from v and w
        // and find the best distance
        for (int i = 0; i < G.V(); i++)
        {
            if ((vbfs.distTo(i) != INFINITY) && (wbfs.distTo(i) != INFINITY))
            {
                currentDistance = vbfs.distTo(i) + wbfs.distTo(i);
            }
            else { currentDistance = INFINITY; }
            
            if (currentDistance < bestDistance)
            {
                bestDistance = currentDistance;
                bestAncestor = i;
            }
        }
        
        if (bestAncestor == INFINITY) return -1;
        return bestAncestor;
        
    }
    
    // length of shortest ancestral path between any vertex in v and any 
    // vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w)
    {
        
        // check to see if v or w are out-of-bounds
        for (int a : v)
        {
            if (a < 0 || a >= G.V())
            {
                throw new IndexOutOfBoundsException();
            }
        }
        
        for (int b : w)
        {
            if (b < 0 || b >= G.V())
            {
                throw new IndexOutOfBoundsException();
            }
        }
        
        // initialize BFS from v and w
        BreadthFirstDirectedPaths vbfs = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths wbfs = new BreadthFirstDirectedPaths(G, w);
        
        int bestDistance = INFINITY;
        int currentDistance = INFINITY;
        
        // calculate the distance to each vertex from v and w
        // and find the best distance
        for (int i = 0; i < G.V(); i++)
        {
            if ((vbfs.distTo(i) != INFINITY) && (wbfs.distTo(i) != INFINITY))
            {
                currentDistance = vbfs.distTo(i) + wbfs.distTo(i);
            }
            else { currentDistance = INFINITY; }
            
            if (currentDistance < bestDistance)
            {
                bestDistance = currentDistance;
            }
        }
        
        if (bestDistance == INFINITY) return -1;
        return bestDistance;
        
    }
    
    // a common ancestor that participates in shortest 
    // ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
    {
        for (int a : v)
        {
            if (a < 0 || a >= G.V())
            {
                throw new IndexOutOfBoundsException();
            }
        }
        
        for (int b : w)
        {
            if (b < 0 || b >= G.V())
            {
                throw new IndexOutOfBoundsException();
            }
        }
        
        // initialize BFS from v and w
        BreadthFirstDirectedPaths vbfs = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths wbfs = new BreadthFirstDirectedPaths(G, w);
        
        int bestDistance = INFINITY;
        int bestAncestor = INFINITY;
        int currentDistance = INFINITY;
        
        // calculate the distance to each vertex from v and w
        // and find the best distance
        for (int i = 0; i < G.V(); i++)
        {
            if ((vbfs.distTo(i) != INFINITY) && (wbfs.distTo(i) != INFINITY))
            {
                currentDistance = vbfs.distTo(i) + wbfs.distTo(i);
            }
            else { currentDistance = INFINITY; }
            
            if (currentDistance < bestDistance)
            {
                bestDistance = currentDistance;
                bestAncestor = i;
            }
        }
        
        if (bestAncestor == INFINITY) return -1;
        return bestAncestor;
    }
    
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length   = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
    
}