/**********************************************************************
 *  readme.txt template                                                   
 *  Burrows-Wheeler data compression.
**********************************************************************/

Name:    Akshay Kumar
Login:   aktwo
Precept: P02

Partner name:     N/A
Partner login:    N/A
Partner precept:  N/A



/**********************************************************************
 *  List in table format which input files you used to test your program.
 *  Fill in columns for how long your program takes to compress and
 *  decompress these instances (by applying BurrowsWheeler, MoveToFront,
 *  and Huffman in succession). Also, fill in the third column for
 *   the compression ratio.
 **********************************************************************/

File               Encoding Time    Decoding time      Compression ratio
------------------------------------------------------------------------
amendments.txt     2.028 (s)        1.824 (s)          0.4
rand10K.bin        0.945 (s)        0.810 (s)          1
us.gif             1.244 (s)        1.008 (s)          1

/**********************************************************************
 *  Compare the results of your program (compression ratio and running
 *  time) on mobydick.txt to that of the most popular Windows
 *  compression program (pkzip) or the most popular Unix/Mac one (gzip).
 *  If you don't have pkzip, use 7zip and compress using zip format.
 **********************************************************************/

My program kept running out of memory with mobydick.txt
and I couldn't get it to work. On one iteration it took over
10 minutes and crashed. Gzip, on the other hand, took about 
1.5 seconds and had a compression ratio of approximately 0.43. 

/**********************************************************************
 *  Give the order of growth of the running time of each of the 6
 *  methods as a function of the input size N and the alphabet size R
 *  both in practice (on typical English text inputs) and in theory
 *  (in the worst case), e.g., N, N log N, N^2, R N.
 **********************************************************************/

                             typical            worst
-----------------------------------------------------------
BurrowsWheeler encode()     N + R              N + R
BurrowsWheeler decode()     N + R              N + R
MoveToFront encode()        N + R              N + R
MoveToFront decode()        N + R              N + R
Huffman compress()          N + R log R        N + R log R
Huffman expand()            N                  N


/**********************************************************************
 *  Known bugs / limitations.
 **********************************************************************/

Runs out of memory for large input files. 

/**********************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **********************************************************************/


/**********************************************************************
 *  Describe any serious problems you encountered.                    
 **********************************************************************/


/**********************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **********************************************************************/
