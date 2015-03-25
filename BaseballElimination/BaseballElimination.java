/* Name: Akshay Kumar
 * netID: aktwo@
 * Precept: P02 (Diego Botero)
 * Assignment: 6 - Baseball Elimination
 * Description: Given a game schedule, this program 
 * determines which teams can be mathematically eliminated
 * from winning first place.
 * Compilation: javac BaseballElimination.java
 * Execution: java BaseballElimination <input-file>.txt
 * Dependencies: In, SeparateChainingHashST
 */

public class BaseballElimination
{
    private int n; // number of teams
    private int requiredMaxFlow; // total number of games that can be played
    
    // symbol table for converting between teams and ids
    private SeparateChainingHashST<String, Integer> teams2ids; 
    private SeparateChainingHashST<Integer, String> ids2teams;
    
    // symbol table for converting node names into vertices for the directed graph
    private SeparateChainingHashST<String, Integer> vertices;
    private FordFulkerson ff; // Ford-Fulkerson algorithm data-type
    private int[] w; // number of wins
    private int[] l; // number of losses
    private int[] r; // number of remaining games
    private int[][] g; // game schedule
    
    // create a baseball division from given filename in format specified below
    public BaseballElimination(String filename)
    {
        // create file input
        In in = new In(filename);
        n = in.readInt();
        teams2ids = new SeparateChainingHashST<String, Integer>();
        ids2teams = new SeparateChainingHashST<Integer, String>();
        
        // initialize instance variables
        w = new int[n];
        l = new int[n];
        r = new int[n];
        g = new int[n][n];
        
        int counter = 0;
        
        // populate instance variables
        while (!in.isEmpty())
        {
            String teamName = in.readString();
            teams2ids.put(teamName, counter);
            ids2teams.put(counter, teamName);
            w[counter] = in.readInt();
            l[counter] = in.readInt();
            r[counter] = in.readInt();
            
            for (int i = 0; i < n; i++)
            {
                g[counter][i] = in.readInt();
            }
            
            counter++;
        }
        
        // find the total number of games left 
        // for the maxflow problem
        requiredMaxFlow = 0;
        
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
            requiredMaxFlow += g[i][j];
        
    }
    
    // number of teams
    public int numberOfTeams()  
    {
        return n;
    }
    
    // all teams
    public Iterable<String> teams() 
    {
        return teams2ids.keys();
    }
    
    // number of wins for given team    
    public int wins(String team)   
    {
        int i = checkTeam(team);
        return w[i];
    }
    
    // number of losses for given team
    public int losses(String team)      
    {
        int i = checkTeam(team);
        return l[i];
    }
    
    // number of remaining games for given team
    public int remaining(String team)
    {
        int i = checkTeam(team);
        return r[i];
    }
    
    // number of remaining games between team1 and team2
    public int against(String team1, String team2) 
    {
        int t1 = checkTeam(team1);
        int t2 = checkTeam(team2);
        return g[t1][t2];
    }
    
    // is given team eliminated?
    public boolean isEliminated(String team)
    {
        // get teamID of the team
        int x = checkTeam(team);
        
        // trivial elimination
        for (int i = 0; i < n; i++)
        {
            if (w[x] + r[x] < w[i]) 
            {
                return true;
            }
        }
        
        // non-trivial elimination
        initializeFordFulkerson(team);
        
        // if max-flow is equal to number of games left, this
        // team can still come out on top, otherwise we return 
        // true (that it has been eliminated)
        if (ff.value() == requiredMaxFlow) return false;
        return true;
    }
    
    // subset R of teams that eliminates given team; null if not eliminated    
    public Iterable<String> certificateOfElimination(String team)  
    {
        int x = checkTeam(team);
        
        Stack<String> stack = new Stack<String>();
        
        // trivial elimination
        for (int i = 0; i < n; i++)
        {
            if (w[x] + r[x] < w[i])
            {
                stack.push(ids2teams.get(i));
                return stack;
            }
        }
        
        // nontrivial elimination
        initializeFordFulkerson(team);
        
        for (int i = 0; i < n; i++)
        {
            int vertex = vertices.get(i+"");
            if (ff.inCut(vertex)) stack.push(ids2teams.get(i));
        }
        
        if (stack.size() == 0) return null;
        return stack;
    }
    
    // helper method to return the int-value of the team and throw exceptions
    private int checkTeam(String team)
    {
        if (!teams2ids.contains(team)) 
            throw new java.lang.IllegalArgumentException();
        return teams2ids.get(team);
    }
    
    // helper method to compute the maxflow problem for a given team input
    private void initializeFordFulkerson(String team)
    {
        
        // get value of x
        int x = checkTeam(team);
        
        // populate the vertices symbol table if it hasn't already been populated
        if (vertices == null) initializeVertices();
        
        // initialize the FlowNetwork object
        FlowNetwork network = new FlowNetwork(vertices.size());
        
        // initialize source to gameVertex edges and 
        // gameVertex to teamVertex edges
        for (int i = 0; i < n; i++)
        {
            for (int j = i+1; j < n; j++)
            {
                int source = vertices.get("s");
                int gameVertex = vertices.get(i + "-" + j);
                int team1Vertex = vertices.get(i+"");
                int team2Vertex = vertices.get(j+"");
                
                network.addEdge(
                    new FlowEdge(source, gameVertex, g[i][j]));
                network.addEdge(
                    new FlowEdge(gameVertex, team1Vertex, Double.POSITIVE_INFINITY));
                network.addEdge(
                    new FlowEdge(gameVertex, team2Vertex, Double.POSITIVE_INFINITY));
            }
        }
        
        // initialize teamVertex to sink edges
        for (int i = 0; i < n; i++)
        {
            int teamVertex = vertices.get(i+"");
            int sink = vertices.get("t");
            network.addEdge(
                   new FlowEdge(teamVertex, sink, Math.max(0, w[x] + r[x] - w[i])));
        }
        
        // initialize the Ford-Fulkerson object
        ff = new FordFulkerson(network, vertices.get("s"), vertices.get("t"));
    }
    
    // helper method to convert from a string into an input
    private void initializeVertices()
    {
        vertices = new SeparateChainingHashST<String, Integer>();
        int counter = 0;
        
        // source vertex
        vertices.put("s", 0);
        counter++;
        
        // game vertices
        for (int i = 0; i < n; i++)
        {
            for (int j = i+1; j < n; j++)
            {
                vertices.put(i + "-" + j, counter);
                counter++;
            }
        }
        
        // team vertices
        for (int i = 0; i < n; i++)
        {
            vertices.put(i+"", counter);
            counter++;
        }
        
        // sink vertex
        vertices.put("t", counter);
    }
    
    // for testing
    public static void main(String[] args) {
        BaseballElimination division = new BaseballElimination(args[0]);
        for (String team : division.teams()) {
            if (division.isEliminated(team)) {
                StdOut.print(team + " is eliminated by the subset R = { ");
                for (String t : division.certificateOfElimination(team))
                    StdOut.print(t + " ");
                StdOut.println("}");
            }
            else {
                StdOut.println(team + " is not eliminated");
            }
        }
    }
}