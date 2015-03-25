/**********************************************************************
 *  readme.txt template                                                   
 *  Baseball Elimination
 **********************************************************************/

Name:             Akshay Kumar
Login:            aktwo
Precept:          P02


Partner name:     N/A
Partner login:    N/A
Partner precept:  N/A

If you have a partner state how many times you partnered with each 
other before? (Only 3 or less is a valid answer.) N/A

Hours to complete assignment (optional): 4



/**********************************************************************
 *  Explain concisely how you built the FlowNetwork from the input file.
 **********************************************************************/

First, I populated the w[], r[], l[], and g[][] arrays from the input 
data. I also created two symbol tables to convert between a team name
and its numerical ID.

Then, I created a symbol table with each of the node names (Key) and 
the corresponding vertex in the graph (Value). Node "s" was the source, 
node "t" was the sink, node "i-j" is the game vertex between team i 
and team j, and node "i" is the team vertex of team i.

After setting up this naming convention, I iterated all pairs of team IDs
and initialized the flow between Node "s" and Node "i-j" to the value 
of g[i][j]. Then I initialized the flow between Node "i-j" to nodes 
"i" and "j" as being positive infinity. In the final loop, I initialized the flow between node "i" and node "s" to be w[x] + r[x] - w[i]. 

/**********************************************************************
 *  Consider the sports league defined in teams12.txt. Explain in
 *  nontechnical terms (using the results of certificate of elimination
 *  and grade-school arithmetic) why Japan is mathematically
 *  eliminated.
 **********************************************************************/

Japan is eliminated by the subset of teams { Iran Brazil Russia Poland }.
The total number of wins between them is 5+5+5+6 = 21. The total number
of games remaining between them is 8. This means each team will win an average of 29/4 = 7.25 games. This means that regardless of the outcome,
one team in this set must win at least 8 games. Japan, on the other hand, has only 2 wins and only 4 games remaining, so it cannot achieve more 
than 6 wins, so it's mathematically eliminated!

/**********************************************************************
 *  What is the order of growth of the amount of memory (in the worst
 *  case) of your program to determine whether *one* team is eliminated
 *  as a function of the number of teams N?
 *
 *  Briefly justify your answer.
 **********************************************************************/

Number of vertices =  (N)(N-1)/2 + (N) + 2 (~0.5N^2)
                      (number of pairs) + (number of teams) +
                                          (source/sink)

Number of edges    =  (N)(N-1)/2 + (2)*((N)(N-1)/2) + N (~1.5N^2)
                      (number of edges from source to game vertices) +
                      (number of edges from game to team vertices) +
                      (number of edges from team vertices to sink)

Amount of memory   =  ~2N^2


/**********************************************************************
 *  What is the order of growth of the running time (in the worst case)
 *  of your program to determine whether *one* team is eliminated
 *  as a function of of the number of teams N?
 *
 *  Assume that the order of growth of the running time (in the worst
 *  case) to compute a maxflow in a network with V vertices and E edges
 *  is V E^2.
 *
 *  Briefly justify your answer.
 **********************************************************************/

From above, V = ~(0.5N^2) and E = ~(1.5N^2). In order to solve the 
elimination problem for one team, we have to compute *one* maxflow, 
which has an order of growth of V*E*E = ~(1.125N^6).

/**********************************************************************
 *  If you did the extra credit, describe the input file and
 *  what specific cases it tests. Ideally, your input file should
 *  be based on real data and/or contain a team whose elimination
 *  would not be obvious to a sports writer.
 **********************************************************************/

N/A.

/**********************************************************************
 *  Known bugs / limitations.
 **********************************************************************/

N/A.

/**********************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **********************************************************************/

N/A.

/**********************************************************************
 *  Describe any serious problems you encountered.                    
 **********************************************************************/

N/A.

/**********************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **********************************************************************/
This was a pretty straightforward assignment.
