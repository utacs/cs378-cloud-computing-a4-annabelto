# Please add your team members' names here. 

## Team members' names 

1. Student Name: Annabel To

   Student UT EID: att959

##  Course Name: CS378 - Cloud Computing 

##  Unique Number: 51515
    


# Add your Project REPORT HERE 

### Implementation Notes
For this project, I relied heavily on the use of Hadoop and its internal Writeable serialization. Because I could not use n4 CPUs due to quota issues, parallelization was a challenge which led me to separate each task into different jobs, rather than consolidating tasks 1 and 2 together--from my experience in the in-class assignment, this could lead to bottlenecks during execution. I considered the use of a separate class to allow for the writing of the objects for each task, but ultimately decided against it to keep code simple.

### Task 1
For the small dataset, output from all three reducers is given in /output/task1. Unfortunately, I did not have a chance to combine the output into a single file, but we can see that the earlier hours had a smaller number of GPS errors, though this is likely is due to the lack of taxi traveling done during those hours, rather than a correlation between time of day and GPS errors.

### Task 2
For the small dataset, output from all three reducers is given in /output/task2. Once more, I did not have time to consolidate the findings into a single sorted file, but I believe the results may be erroneous due to the fact that all selected taxis are reporting an error rate of 100%. 

### Task 3
For the small dataset, the top 10 drivers with highest earnings per minute (with their earnings) are given below:
```
(69B6FBD28F84175AB1504F6BFF001A49, 2399.9998) 
(30B2ACBAF002305533FF0D31D34A7C06, 702.0)
(4C3B2A31227663A59E1AA7B45157160D, 625.0)
(A0AC85170C37E1D076ADE05B0238C58E, 540.0)
(08026D69508127F4DE855678ABCE7E0A, 375.0)
(6E1D7195E38AA7A36B375C1C60AD8632, 317.30768)
(7826BDE4CE3E44EE1BB7B926A38A4B2A, 279.85715) 
(975D5E840C0F5D9611A71E9A811D11F7, 190.90909)
(619BF4020E6542AA0E28FBC7081271F1, 105.882355)
(35AA6D19080F673C75048A2D99742602, 103.33333)
```

### Screenshots
In the `screenshots/` folder is a screenshot of the machine cluster (note that n2-standards were used as the quota for n2 CPUs could not be increased in time) and a screenshot of the hadoop job records for execution with the small dataset. Unfortunately GCP's URL for Yarn job tracking did not work, so I downloaded the dashboard tracking metrics as well as the log outputs which show the progression of job execution. For the large dataset, the task is currently executing on the GCP machines for the large dataset--logs and Yarn dashbaord screenshots are included for this job as well.


# Project Template

# Running on Laptop     ####

Prerequisite:

- Maven 3

- JDK 1.6 or higher

- (If working with eclipse) Eclipse with m2eclipse plugin installed


The java main class is:

edu.cs.utexas.HadoopEx.WordCount 

Input file:  Book-Tiny.txt  

Specify your own Output directory like 

# Running:




## Create a JAR Using Maven 

To compile the project and create a single jar file with all dependencies: 
	
```	mvn clean package ```



## Run your application
Inside your shell with Hadoop

Running as Java Application:

```java -jar target/MapReduce-WordCount-example-0.1-SNAPSHOT-jar-with-dependencies.jar SOME-Text-Fiel.txt  output``` 

Or has hadoop application

```hadoop jar your-hadoop-application.jar edu.cs.utexas.HadoopEx.WordCount arg0 arg1 ... ```



## Create a single JAR File from eclipse



Create a single gar file with eclipse 

*  File export -> export  -> export as binary ->  "Extract generated libraries into generated JAR"
