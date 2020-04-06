
# GA implementation # 
# Name: Yunxiao Xiang # 
# NID: N12351630 #

##########################################################################################
Class files are inside the src folder, junit files are in the junit folder
Each folder contains a package called ga where class files and corresponding
test files live in.

To run: (please following those instructions)

Create the following packages in the src directory: 
ga

Copy the classes in the corresponding packages to each of them in the src: 

 Circle.java
 Cr.java
 GA.java
 I_EvalObject.java
 MyThread.java
 RandomGenerator.java
 Shuffler.java
 Task.java
 ThreadPool.java


Create the following packages in the junit directory: 
ga


Copy the following classes to corresponding package:
Test_Circle.java
Test_Cr.java
Test_GA.java
Test_GA2.java
Test_Shuffler.java
Test_ThreadPool.java


There are no additional classes, input files, or output files.

##########################################################################################

Besides the GA, I create MyThread, Task, ThreadPool classes for multithreading purpose.

The algorithm is as follows:
In stead of using single thread to compute the fitness for each Cr, we use multithread
method. Each computeFitness process is created as a new task and then put in the task 
list in the ThreadPool. Then ThreadPool will notify next avaliable thread to run the 
task. Since we used thread safe data structure and synchronized method modifier, there 
would not be concurrent issue. For each round for computing (ie. computing fitness value 
for 1000 Cr), we wait until all thread to finish.
