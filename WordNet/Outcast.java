/* Name: Akshay Kumar
 * netID: aktwo@
 * Precept: P02 (Diego Botero)
 * Assignment: 6 - WordNet
 * Description: Given a WordNet and a set of WordNet nouns, 
 * find the noun that is least related to all the others.
 * Compilation: javac Outcast.java
 * Execution/Testing: java Outcast <synsets>.txt <hypernyms>.txt <input-files>.txt
 * Dependencies: WordNet
 */

public class Outcast
{
    private final WordNet wordnet;
    
    // constructor takes a WordNet object
    public Outcast(WordNet wordnet)
    {
        this.wordnet = wordnet;
    }
    
    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns)
    {
        
        int maxDistance = -1;
        int pointer = -1;
        
        for (int i = 0; i < nouns.length; i++)
        {
            int currentDistance = -1;
            
            for (int j = 0; j < nouns.length; j++)
            {
                currentDistance += wordnet.distance(nouns[i], nouns[j]);
            }
            
            if (maxDistance < currentDistance)
            {
                maxDistance = currentDistance;
                pointer = i;
            }
        }
        
        return nouns[pointer];
    }
    
    // method for testing
    public static void main(String[] args) 
    {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) 
        {
            String[] nouns = In.readStrings(args[t]);
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}