/* Name: Akshay Kumar
 * netID: aktwo@
 * Precept: P02 (Diego Botero)
 * Assignment: 8 - Burrows Wheeler
 * Description: Given a stream of input, apply the move-to-front
 * compression algorithm.
 * Compilation: javac MoveToFront.java
 * Execution: java MoveToFront ("+" or "-" parameter) < (input_file.txt)
 * Dependencies: BinaryStdOut, BinaryStdIn
 */

public class MoveToFront 
{
    // apply move-to-front encoding, reading from 
    // standard input and writing to standard output
    public static void encode()
    {
        int R = 256;
        char[] c = new char[R];
        
        for (int i = 0; i < R; i++)
        {
            c[i] = (char) i;
        }
        
        // read in standard input and apply move to front algorithm
        while (!BinaryStdIn.isEmpty()) 
        {
            BinaryStdOut.write(moveToFront(BinaryStdIn.readChar(), c), 8);
        }
        
        BinaryStdOut.close();
    }
    
    // apply move-to-front decoding, reading from 
    // standard input and writing to standard output
    public static void decode()
    {
        int R = 256;
        char[] c = new char[R];
        
        for (int i = 0; i < R; i++)
        {
            c[i] = (char) i;
        }
        
        // read in input and apply move to front algorithm in reverse
        while (!BinaryStdIn.isEmpty()) 
        {
            int i = BinaryStdIn.readInt(8);
            BinaryStdOut.write(c[i], 8);
            moveToFront(c[i], c);
        }
        
        BinaryStdOut.close();
    }
    
    // private helper method to move a character in a char array to the front
    private static int moveToFront(char c, char[] array)
    {
        int index = -1;
        
        // find the index of the character in the array
        for (int i = 0; i < array.length; i++)
        {
            if (array[i] == c) 
            {
                index = i;
                break;
            }
        }
        
        // move the char to the front of the array and shift all entries over
        char temp = array[index];
        array[index] = array[0];
        array[0] = temp;
        
        for (int i = 1; i < index; i++)
        {
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
        
        return index;
    }
    
    // if args[0] is '-', apply move-to-front encoding
    // if args[0] is '+', apply move-to-front decoding
    public static void main(String[] args)
    {
        if (args[0].equals("-")) encode();
        else if (args[0].equals("+")) decode();
    }
}