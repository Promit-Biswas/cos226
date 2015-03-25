/**********************************************************************
 *  readme.txt template                                                   
 *  WordNet
**********************************************************************/

Name:    Akshay Kumar
Login:   aktwo@
Precept: P02A

Partner name:     N/A
Partner login:    N/A
Partner precept:  N/A

If you have a partner state how many times you partnered with each 
other before? (Only 3 or less is a valid answer.)


/**********************************************************************
 *  Describe concisely the data structure(s) you used to store the 
 *  information in synsets.txt. Why did you make this choice?
 **********************************************************************/

I used two SeparateChainingHashST objects; one with the Key as the id 
and the Value as the synset, and the other with the Key as a word and 
the Value as a Bag of ID integer values (since one word can have more 
than one ID). The former allowed me to convert from a numeric ID to a 
synset and the latter allowed me to convert from a word to all the 
numeric IDs that referenced that word. 

I used a hash table because it runs in constant time for typical inputs 
(given that the hash function satisfies the uniform hashing assumption).


/**********************************************************************
 *  Describe concisely the data structure(s) you used to store the 
 *  information in hypernyms.txt. Why did you make this choice?
 **********************************************************************/

I used a Digraph to store the relationships between IDs given in 
the hypernyms.txt input files. This representation made the most sense
because a directed graph captures all the relevant information (the 
first ID points to all the other IDs on the line) and also allows for 
breadth-first search (which becomes crucial for finding the SAP and 
distances between nouns).

/**********************************************************************
 *  Describe concisely your algorithm to compute the shortest ancestral
 *  path in SAP.java? What is the order of growth of the worst-case
 *  running time of your methods as a function of the number of
 *  vertices V and the number of edges E in the digraph? What is the
 *  order of growth of the best-case running time?
 **********************************************************************/

Description: Perform breadth-first search on both inputs (v and w) by
creating two BreadthFirstDirectedPaths objects. Then, iterate over all 
the vertices and calculate the sum of the distance between (v and i) and
(w and i) for each vertex (i). The vertex for which this sum is smallest 
is the closest common ancestor. The resulting minimal sum is the length
between these two WordNet nouns (or collection of nouns).

method                               best case            worst case
------------------------------------------------------------------------
length(int v, int w)                     V                   E + V

ancestor(int v, int w)                   V                   E + V         

length(Iterable<Integer> v,              V                   E + V       
Iterable<Integer> w)

ancestor(Iterable<Integer> v,            V                   E + V
         Iterable<Integer> w)




/**********************************************************************
 *  If you implemented an optimization describe it here.
 **********************************************************************/

N/A

/**********************************************************************
 *  Known bugs / limitations.
 **********************************************************************/

N/A

/**********************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **********************************************************************/

Brainstormed with Sam Lite.

/**********************************************************************
 *  Describe any serious problems you encountered.                    
 **********************************************************************/

N/A.

/**********************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **********************************************************************/

This assignment was pretty fun! Definitely less debugging than previous
programs :)
