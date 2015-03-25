/**********************************************************************
 *  Percolation
 **********************************************************************/

Name: Akshay Kumar
Login: aktwo
Precept: P02
Operating system: OSX
Compiler: JDK 6.0_24
Text editor / IDE: DrJava
Hours to complete assignment (optional): 4
Have you taken (part of) this course before: No
Have you taken (part of) the Coursera course Algorithm, Part I: No

/**********************************************************************
 *  Describe how you implemented Percolation.java. How did you check
 *  whether the system percolates?
 **********************************************************************/

I created a WeightedQuickFindUF data type with the number of spaces in 
the grid plus two. The last two spaces were mapped to the virtual 
top node and the virtual bottom node. Each time a new space was
opened on the top or bottom row, I connected it to the top or bottom 
virtual node, respectively. This had the nice consequence of removing
the 2 for-loops in the constructor. Then, to check whether or not the 
system percolated, I just checked whether or not the top and bottom 
virtual nodes were connected.

/**********************************************************************
 *  Using Percolation with QuickFindUF.java, give a formula (using tilde
 *  notation) for the running time (in seconds) of PercolationStats.java
 *  as a function of N and T.
 *
 *  Be sure to give the leading coefficient.
 **********************************************************************/

(keep T constant) (T = 100)

 N          time (seconds)
------------------------------
100         0.106
200         0.643
400         4.244
800         40.364     

(keep N constant) (N = 100)

 T          time (seconds)
------------------------------
100         0.106
200         0.227
400         0.425
800         0.845 

running time as a function of N and T:  ~(7E-8)*N^3.2 + (1E-3)*T

/**********************************************************************
 *  Repeat the previous question, but use WeightedQuickUnionUF.java.
 **********************************************************************/

(keep T constant) (T = 100)

 N          time (seconds)
------------------------------
100         0.248
200         0.389
400         1.487
800        12.218

(keep N constant) (N = 100)

 T          time (seconds)
------------------------------
100         0.074
200         0.148
400         0.293
800         0.597

running time as a function of N and T:  ~(2.3E-8)*N^3 + (7.5E-4)*T

/**********************************************************************
 *  After reading the course collaboration policy, answer the
 *  following short quiz. This counts for a portion of your grade.
 *  Write down the answers in the space below.
 **********************************************************************/
1. (b)
2. (c)

1. How much help can you give a fellow student taking COS226?
(a) None. Only the preceptors and lab TAs can help.
(b) You can discuss ideas and concepts but once coding begins each
    student can only get help debugging their code from a preceptor
    or a lab TA or a student who has already passed COS226.
(c) You can help a student by discussing ideas, selecting data
    structures, and debugging their code.
(d) You can help a student by emailing him/her your code.

2. What are the rules when partnering?
 (a) You and your partner must both be present while writing code.
     But after that only one person needs to do the analysis.
 (b) You and your partner must both be present while writing code
     and during the analysis but after that only one person
     needs to be present while submitting the code and the
     readme.
 (c) You and your partner must both be present while writing code,
     during the analysis and while submitting the code and the
     readme. Failure to do so is a violation of the course
     collaboration policy and can result in a hearing before the
     Committee on Discipline.
 
/**********************************************************************
 *  Known bugs / limitations.
 **********************************************************************/

I couldn't figure out how to fix the backwash bug.

/**********************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **********************************************************************/

N/A

/**********************************************************************
 *  Describe any serious problems you encountered.                    
 **********************************************************************/

N/A


/**********************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **********************************************************************/

