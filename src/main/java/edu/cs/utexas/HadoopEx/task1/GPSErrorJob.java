// This class was written with the help of GPT
package task1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class GPSErrorJob {
    public static Job createJob(Configuration conf, String inputPath, String outputPath) throws Exception {
        Job job = Job.getInstance(conf, "GPS Error Count");
        job.setJarByClass(GPSErrorJob.class);
        job.setMapperClass(GPSErrorMapper.class);
        job.setReducerClass(GPSErrorReducer.class);
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path(inputPath));
        FileOutputFormat.setOutputPath(job, new Path(outputPath));
        return job;
    }
}
