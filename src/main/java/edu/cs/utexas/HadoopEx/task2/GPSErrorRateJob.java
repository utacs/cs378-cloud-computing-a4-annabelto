// This class was written with the help of GPT
package task2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class GPSErrorRateJob {
    public static Job createJob(Configuration conf, String inputPath, String outputPath) throws Exception {
        Job job = Job.getInstance(conf, "GPS Error Rate");
        job.setJarByClass(GPSErrorRateJob.class);
        job.setMapperClass(GPSErrorRateMapper.class);
        job.setReducerClass(GPSErrorRateReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path(inputPath));
        FileOutputFormat.setOutputPath(job, new Path(outputPath));
        return job;
    }
}
