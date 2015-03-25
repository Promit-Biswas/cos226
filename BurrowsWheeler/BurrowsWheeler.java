/* Name: Akshay Kumar
 * netID: aktwo@
 * Precept: P02 (Diego Botero)
 * Assignment: 8 - Burrows Wheeler
 * Description: Given a stream of input, apply the Burrows-Wheeler
 * compression algorithm.
 * Compilation: javac BurrowsWheeler.java
 * Execution: java BurrowsWheeler ("+" or "-" parameter) < (input_file.txt)
 * Dependencies: BinaryStdOut, BinaryStdIn
 */
public class BurrowsWheeler
{
// apply Burrows-Wheeler encoding, reading from 
// standard input and writing to standard output
    
    public static void encode()
    {
// read input
        String input = BinaryStdIn.readString();
        int length = input.length();
        String[] suffixes = new String[length];
        
// create suffix array
        for (int i = 0; i < length; i++)
        {
            suffixes[i] = input.substring(i, length) + input.substring(0, i);
        }
        
// sort suffix array
        java.util.Arrays.sort(suffixes);
        
// find the value of the suffix array that contains the original
        int first = java.util.Arrays.binarySearch(suffixes, input);
        
        BinaryStdOut.write(first);
        
// print out the last column of the suffix array
        for (int i = 0; i < length; i++)
        {
            BinaryStdOut.write(suffixes[i].charAt(length - 1), 8);
        }
        
        BinaryStdOut.close();
        
    }
    
// apply Burrows-Wheeler decoding, reading from 
// standard input and writing to standard output
    
    public static void decode()
    {
        int firstValue = BinaryStdIn.readInt();
        String lastColumn = BinaryStdIn.readString();
        int N = lastColumn.length();
        int[] next = new int[N];
        String[] t = new String[N];
        String[] first = new String[N];
        boolean[] marked = new boolean[N];
        
// initialize last and first columns
        for (int i = 0; i < N; i++)
        {
            t[i] = "" + lastColumn.charAt(i);
            first[i] = t[i];
        }
        
// sort the first columns
        java.util.Arrays.sort(first);
        
// initialize the "next" array
        for (int i = 0; i < N; i++)
        {
            next[i] = find(first[i], t, marked);
        }
        
// follow the next array through the values to recreate the string
        
        
        for (int i = 0; i < N; i++)
        {
            BinaryStdOut.write(first[firstValue], 8);
            firstValue = next[firstValue];
        }
        
        BinaryStdOut.close();
        
    }
    
// helper method to find a string in an unordered array
    private static int find(String string, String[] array, boolean[] marked)
    {
        for (int i = 0; i < array.length; i++)
        {
            if (array[i].equals(string) && !marked[i]) 
            {
                marked[i] = true;
                return i;
            }
        }
        
        return -1;
    }
    
// if args[0] is '-', apply Burrows-Wheeler encoding
// if args[0] is '+', apply Burrows-Wheeler decoding
    public static void main(String[] args)
    {
        if (args[0].equals("-")) encode();
        else if (args[0].equals("+")) decode();
    }
}