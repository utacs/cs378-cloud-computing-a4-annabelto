// This class was written with the help of GPT
package edu.cs.utexas.HadoopEx;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import task1.GPSErrorJob;
import task2.GPSErrorRateJob;
import task3.EarningsJob;

public class MainDriver {
    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            System.err.println("Usage: MainDriver <input path> <output path task 1> <output path task 2> <output path task 3>");
            System.exit(-1);
        }

        Configuration conf = new Configuration();

        // Run Task 1
        Job job1 = GPSErrorJob.createJob(conf, args[0], args[1]);
        if (!job1.waitForCompletion(true)) {
            System.exit(1);
        }

        // Run Task 2
        Job job2 = GPSErrorRateJob.createJob(conf, args[0], args[2]);
        if (!job2.waitForCompletion(true)) {
            System.exit(1);
        }

        // Run Task 3
        Job job3 = EarningsJob.createJob(conf, args[0], args[3]);
        if (!job3.waitForCompletion(true)) {
            System.exit(1);
        }

        System.exit(0);
    }
}
