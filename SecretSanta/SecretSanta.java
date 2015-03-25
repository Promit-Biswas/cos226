/* Author: Akshay Kumar
 * Description: Given an input file with an known number of people and 
 * row format "name - preferences", randomly assign secret-santas to 
 * each person, not allowing someone to be their own secret santa.
 * Prints resulting pairings to standard output.
 * Compilation: javac SecretSanta.java
 * Execution: java SecretSanta input_file.txt > secretSantaOutput.txt
 * Dependencies: In, StdRandom
 */

public class SecretSanta
{
    private static class Person
    {
        String name; // name of the person
        String pref; // what they want
        
        // constructor
        public Person(String name, String pref)
        {
            this.name = name;
            this.pref = pref;
        }
        
        // helper methods
        private String getName()
        {
            return name;
        }
        
        private String getPreferences()
        {
            return pref;
        }
        
    }
    
    // main program execution
    public static void main(String[] args)
    {
        // set the arrays to the correct length
        In in = new In(args[0]);
        int length = Integer.parseInt(in.readLine());
        Person[] secretSantas = new Person[length];
        Person[] recipients = new Person[length];
        
        // initialize the arrays
        for (int i = 0; i < length; i++)
        {
            String[] input = in.readLine().split("-");
            secretSantas[i] = new Person(input[0], input[1]);
            recipients[i] = new Person(input[0], input[1]);
        }
        
        // shuffle the recipient array until no one is their own secret santa
        while(!secretSantaPairing(secretSantas, recipients))
        {
            StdRandom.shuffle(recipients);
        }
        
        // print out the resulting pairings
        for (int i = 0; i < length; i++)
        {
            System.out.println(secretSantas[i].getName() + "getting a gift for " 
                                   + recipients[i].getName() + " who asked for " 
                                   + recipients[i].getPreferences());
        }
        
    }
    
    // returns true if no one is their own secret santa, false otherwise
    private static boolean secretSantaPairing(Person[] first, Person[] second)
    {
        int length = first.length;
        for (int i = 0; i < length; i++)
        {
            if (first[i].getName().equals(second[i].getName())) return false;
        }
        
        return true;
    }
}