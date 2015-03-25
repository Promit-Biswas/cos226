/* Name: Akshay Kumar
 * netID: aktwo@
 * Precept: P02 (Diego Botero)
 * Assignment: 6 - WordNet
 * Description: Given a list of synsets and hypernym relationships,
 * construct and test a WordNet object.
 * Compilation: javac WordNet.java
 * Execution: java WordNet <synset-file>.txt <hypernym-file>.txt
 * Dependencies: In, SeparateChainingHashST, Digraph, 
 *               SAP, Bag, DirectedCycle, StdRandom
 */

public class WordNet
{
    private final SeparateChainingHashST<Integer, String> id2text;
    private final SeparateChainingHashST<String, Bag<Integer>> text2id;
    private final SAP sap;
    
    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms)
    {
        // initialize hash tables
        id2text = new SeparateChainingHashST<Integer, String>();
        text2id = new SeparateChainingHashST<String, Bag<Integer>>();
        
        // read synset input file
        In synsetInput = new In(synsets);
        int vertices = 0;
        while (synsetInput.hasNextLine())
        {
            
            // read in a line and split it
            String[] synsetData = synsetInput.readLine().split(",");
            
            // define data from the line
            int id = Integer.parseInt(synsetData[0]);
            String[] synsetText = synsetData[1].split(" ");
            
            // populate id2text hash table from the line
            
            id2text.put(id, synsetData[1]);
            
            // populate text2id hash table from the line
            
            for (int i = 0; i < synsetText.length; i++)
            {
                if (text2id.contains(synsetText[i]))
                {
                    text2id.get(synsetText[i]).add(id);
                }
                
                else
                {
                    Bag<Integer> idBag = new Bag<Integer>();
                    idBag.add(id);
                    text2id.put(synsetText[i], idBag);
                }
                
            }
            
            // update the counter of the number of IDs
            vertices++;
        }
        
        // read hypernym input file
        In hypernymInput = new In(hypernyms);
        Digraph graph = new Digraph(vertices);
        
        // process each line of the hypernym data file
        while (hypernymInput.hasNextLine())
        {
            // split the data
            String[] hypernymData = hypernymInput.readLine().split(",");
            
            // find the parent ID
            int parentID = Integer.parseInt(hypernymData[0]);
            
            // populate digraph with edges
            for (int i = 1; i < hypernymData.length; i++)
            {
                graph.addEdge(parentID, Integer.parseInt(hypernymData[i]));
            }
        }
        
        // make sure that graph is acyclic
        DirectedCycle graphTester = new DirectedCycle(graph);
        if (graphTester.hasCycle())
        {
            throw new java.lang.IllegalArgumentException("WordNet contains a cycle");
        }
        
        // check to see if graph is rooted
        if (!isRooted(graph))
        {
            throw new java.lang.IllegalArgumentException("Wordnet is not rooted");
        }
        
        sap = new SAP(graph);
        
    }
    
    
    // helper method to check if a digraph is rooted
    private boolean isRooted(Digraph graph)
    {
        int counter = 0;
        
        // iterate over all vertices and return false 
        // if more than one vertex has an outdegree of 0
        
        for (int i = 0; i < graph.V(); i++)
        {
            if (((Bag<Integer>) graph.adj(i)).size() == 0)
            {
                counter++;
            }
            
            if (counter > 1) return false;
        }
        
        if (counter == 1) return true;
        return false;
    }
    
    // returns all WordNet nouns
    public Iterable<String> nouns()
    {
        return text2id.keys();
    }
    
    // is the word a WordNet noun?
    public boolean isNoun(String word)
    {
        return text2id.contains(word);
    }
    
    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB)
    {
        if (!isNoun(nounA) || !isNoun(nounB))
        {
            throw new java.lang.IllegalArgumentException();
        }
        
        Bag<Integer> a = text2id.get(nounA);
        Bag<Integer> b = text2id.get(nounB);
        return sap.length(a, b);
    }
    
    // a synset (second field of synsets.txt) that is the common ancestor
    // of nounA and nounB in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB)
    {
        if (!isNoun(nounA) || !isNoun(nounB))
        {
            throw new java.lang.IllegalArgumentException();
        }
        
        Bag<Integer> a = text2id.get(nounA);
        Bag<Integer> b = text2id.get(nounB);
        String synset = id2text.get(sap.ancestor(a, b));
        return synset;
    }
    
    // do unit testing of this class
    public static void main(String[] args)
    {
        WordNet test = new WordNet(args[0], args[1]);
        
        // print out the contents of both hash tables
        System.out.println("ID to Synset Hash Table:");
        for (int s : test.id2text.keys())
        {
            System.out.println(s + ": " + test.id2text.get(s));
        }
        System.out.println("--------------------------");
        System.out.println("Word to IDs Hash Table: ");
        for (String s : test.text2id.keys())
        {
            System.out.print(s + ": ");
            for (int a : test.text2id.get(s))
            {
                System.out.println(a + " ");
            }
        }
        System.out.println("--------------------------");
        // pick two random IDs and find the distance and SAP between them
        
        int idOne = StdRandom.uniform(test.id2text.size());
        int idTwo = StdRandom.uniform(test.id2text.size());
        String wordOne = test.id2text.get(idOne);
        String wordTwo = test.id2text.get(idTwo);
        
        System.out.println("First word  =             " + wordOne);
        System.out.println("Second word =             " + wordTwo);
        System.out.println("Distance between words  = " 
                               + test.distance(wordOne, wordTwo));
        System.out.println("Closest common ancestor = "
                               + test.sap(wordOne, wordTwo));
        
        
    }
}